package dad.calculadora.compleja.javafx;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraComplejaView extends Application {
	
	
	
	private Complejo c1 = new Complejo();
	private Complejo c2 = new Complejo();
	private Complejo c_resultado = new Complejo();		
	private StringProperty op = new SimpleStringProperty(); //Property para el combo
	
	
	
	private TextField operandoA_real_text;
	private TextField operandoB_imag_text;
	private TextField operandoC_real_text;
	private TextField operandoD_imag_text;
	private TextField resultadoRealText;
	private TextField resultadoImaglText;
	private Label signo1_label;
	private Label signo2_label;
	private Label imaginario1_label;
	private Label imaginario2_label;
	private Label imaginario3_label;
	private Label resultado_label;
	private ComboBox operadorCombo;
	//private Button btnResultado;
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		//Inicializacion, configuracion y Layout
		
		operadorCombo = new ComboBox();
		operadorCombo.getItems().addAll("+", "-", "x", "/");
		operadorCombo.setMinWidth(40);
		
		VBox vbox1 = new VBox(operadorCombo);
		vbox1.setAlignment(Pos.CENTER);
			
				
		operandoA_real_text = new TextField();
		operandoA_real_text.setMaxWidth(55);
		
		signo1_label = new Label();		
		signo1_label.setPrefHeight(30);
		
		operandoB_imag_text = new TextField();
		operandoB_imag_text.setMaxWidth(55);
		
		imaginario1_label = new Label();
		imaginario1_label.setText("i");
		imaginario1_label.setPrefHeight(30);
	
		
		
		
		HBox hbox1 = new HBox(operandoA_real_text, signo1_label, operandoB_imag_text, imaginario1_label);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(5);
	
		
		operandoC_real_text = new TextField();
		operandoC_real_text.setMaxWidth(55);

		signo2_label = new Label();	
		signo2_label.setPrefHeight(30);
		
		operandoD_imag_text = new TextField();
		operandoD_imag_text.setMaxWidth(55);
			
				
		imaginario2_label = new Label();
		imaginario2_label.setText("i");
		imaginario2_label.setPrefHeight(30);
		
		
		HBox hbox2 = new HBox(operandoC_real_text, signo2_label,operandoD_imag_text, imaginario2_label);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(5);
		
		
		
		resultadoRealText = new TextField();
		resultadoRealText.setMaxWidth(55);
		resultadoRealText.setDisable(true);
		
		resultado_label = new Label();	
		resultado_label.setPrefHeight(30);
		
		resultadoImaglText = new TextField();
		resultadoImaglText.setMaxWidth(55);	
		resultadoImaglText.setDisable(true);

		
		imaginario3_label = new Label();
		imaginario3_label.setText("i");
		imaginario3_label.setPrefHeight(30);
		
		HBox hbox3 = new HBox(resultadoRealText, resultado_label,  resultadoImaglText, imaginario3_label);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(5);
	
		

		
		
		Separator separator = new Separator();	
		separator.setMaxWidth(140);
		separator.setStyle("-fx-padding: 5px 5px");
		
		VBox vbox2 = new VBox(hbox1, hbox2, separator, hbox3);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setStyle("-fx-padding: 10px 20px");
				
		//btnResultado = new Button();
		//btnResultado.setText("=");
		
		VBox vbox3 = new VBox();
		vbox3.setAlignment(Pos.CENTER);
				
		
		HBox root = new HBox(vbox1, vbox2, vbox3);	
		root.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("CalculadoraCompleja");
		primaryStage.setScene(scene);
		primaryStage.show();
		
				
		
		//Bindeos 
	 

		operandoA_real_text.textProperty().bindBidirectional(c1.realProperty(), new NumberStringConverter()); 
		operandoB_imag_text.textProperty().bindBidirectional(c1.imaginarioProperty(), new NumberStringConverter());
      

		operandoC_real_text.textProperty().bindBidirectional(c2.realProperty(), new NumberStringConverter());
		operandoD_imag_text.textProperty().bindBidirectional(c2.imaginarioProperty(), new NumberStringConverter());
	
	
		resultadoRealText.textProperty().bindBidirectional(c_resultado.realProperty(), new NumberStringConverter());
      	resultadoImaglText.textProperty().bindBidirectional(c_resultado.imaginarioProperty(), new NumberStringConverter());
		
      
	   op.bind(operadorCombo.getSelectionModel().selectedItemProperty());
	  
	
	   resultado_label.textProperty().bind(operadorCombo.getSelectionModel().selectedItemProperty());		
	   signo1_label.textProperty().bind(operadorCombo.getSelectionModel().selectedItemProperty());
	   signo2_label.textProperty().bind(operadorCombo.getSelectionModel().selectedItemProperty());
				
			   
				
      
      
	   	// listeners
		
   		op.addListener((o, ov, nv) -> onOperadorChanged(nv)); //Cuando la operación cambie (observable, oldValue, new Value) Este listener sirve para todas las properties

   		operadorCombo.getSelectionModel().selectFirst(); //Seleccionar por defecto el primer elemento
	 
	
		
	 
	
	}
	
	
	private void onOperadorChanged(String nv) {

		
		
		switch(nv) {
		case "+":
			 c_resultado.realProperty().bind(c1.realProperty().add(c2.realProperty()));
			 c_resultado.imaginarioProperty().bind(c1.imaginarioProperty().add(c2.imaginarioProperty()));
			 
			break;
		case "-":			
			c_resultado.realProperty().bind(c1.realProperty().subtract(c2.realProperty()));
			c_resultado.imaginarioProperty().bind(c1.imaginarioProperty().subtract(c2.imaginarioProperty()));
			break;
			
		case "x": 
			
			c_resultado.realProperty().bind((c1.realProperty().multiply(c2.realProperty())).subtract(c1.imaginarioProperty().multiply(c2.imaginarioProperty())));
			c_resultado.imaginarioProperty().bind((c1.realProperty().multiply(c2.imaginarioProperty())).subtract(c1.imaginarioProperty().multiply(c2.realProperty())));
			break;
			
		case "/":
			
			
			
			
			////////////////////////////////////////////////////////////////////////////////////
			////Dada la complejidad de la operacion pongo un property para cada suboperación///
			
			DoubleProperty ac = new SimpleDoubleProperty();			
			ac.bind(c1.realProperty().multiply(c2.realProperty()));
			
			
			
			DoubleProperty bd = new SimpleDoubleProperty();
			bd.bind(c1.imaginarioProperty().multiply(c2.imaginarioProperty()));
			
			
			
			DoubleProperty bc = new SimpleDoubleProperty();
			bc.bind(c1.imaginarioProperty().multiply(c2.realProperty()));
			
			
			
			DoubleProperty ad = new SimpleDoubleProperty();
			ad.bind(c1.realProperty().multiply(c2.imaginarioProperty()));
			
			
			
			DoubleProperty cuadradoC = new  SimpleDoubleProperty();
			cuadradoC.bind(c2.realProperty().multiply(c2.realProperty()));
			
		
			
			DoubleProperty cuadradoD = new  SimpleDoubleProperty();
			cuadradoD.bind(c2.imaginarioProperty().multiply(c2.imaginarioProperty()));	
			
			
				
			DoubleProperty dividendo_1 = new  SimpleDoubleProperty();
			dividendo_1.bind(ac.add(bd));
			
			DoubleProperty divisor_1 = new  SimpleDoubleProperty();
			divisor_1.bind(cuadradoC.add(cuadradoD));
			
			DoubleProperty dividendo_2 = new  SimpleDoubleProperty();
			dividendo_2.bind(bc.subtract(ad));
			
			DoubleProperty divisor_2 = new  SimpleDoubleProperty();
			divisor_2.bind(cuadradoC.add(cuadradoD));
					
			
			DoubleProperty realDiv = new SimpleDoubleProperty();
			realDiv.bind(dividendo_1.divide(divisor_1));
			
						
			DoubleProperty imagDiv = new SimpleDoubleProperty();
			imagDiv.bind(dividendo_2.divide(divisor_2));
			
			c_resultado.realProperty().bind(realDiv);
			c_resultado.imaginarioProperty().bind(imagDiv);			
			
			
			
			break;
		}
		
	}
	
	


	public static void main(String[] args) {
		launch(args);
	}

}
