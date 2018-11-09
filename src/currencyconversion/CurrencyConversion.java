package currencyconversion;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CurrencyConversion extends Application {
    
    private Scene scene;
    private Button convert;
    private TextField poundsValue;
    private TextField dollarsValue;
    private RadioButton poundsChoice;
    
    private final int sceneWidth = 300;
    private final int sceneHeight = 200;
    private final String poundsString = "1";
    private final String dollarsString = "1.31";
    
    @Override
    public void start(Stage primaryStage) {
        final double dollarsPerPound = 1.31;
        final double poundsPerDollars = .76;
        
        drawVisualInterface();
        
        convert.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                convertCurrency(dollarsPerPound, poundsPerDollars);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void drawVisualInterface(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(setUpCurrencyChoices());
        borderPane.setCenter(setUpCurrencyInformation());
        convert = new Button("Convert");
        borderPane.setBottom(convert);
        scene = new Scene(borderPane, sceneWidth, sceneHeight);
    }
    
    private HBox setUpCurrencyChoices(){
        poundsChoice = new RadioButton("Pounds");
        RadioButton dollarsChoice = new RadioButton("Dollars");
        poundsChoice.setSelected(true);
        
        ToggleGroup tg = new ToggleGroup();
        poundsChoice.setToggleGroup(tg);
        dollarsChoice.setToggleGroup(tg);
        
        HBox currencyChoices = new HBox();
        currencyChoices.getChildren().add(poundsChoice);
        currencyChoices.getChildren().add(dollarsChoice);
        
        return currencyChoices;
    }
    
    private VBox setUpCurrencyInformation(){
        VBox currencyInformation = new VBox();
        currencyInformation.getChildren().add(setUpPoundsInformation());
        currencyInformation.getChildren().add(setUpDollarsInformation());
        
        return currencyInformation;
    }
    
    private HBox setUpPoundsInformation(){
        Label poundsName = new Label("Pounds");
        poundsValue = new TextField(poundsString);
        //poundsName.setPadding(inset);
        
        HBox poundsInformation = new HBox();
        poundsInformation.getChildren().add(poundsName);
        poundsInformation.getChildren().add(poundsValue);
        
        return poundsInformation;
    }
    
    private HBox setUpDollarsInformation(){
        Label poundsName = new Label("Dollars");
        dollarsValue = new TextField(dollarsString);
        //dollarsName.setPadding(inset);
        
        HBox dollarsInformation = new HBox();
        dollarsInformation.getChildren().add(poundsName);
        dollarsInformation.getChildren().add(poundsValue);
        
        return dollarsInformation;
    }
    
    private void convertCurrency(double dollarsPerPound, double poundsPerDollars){
        if(poundsChoice.isSelected()){
            double dollars = Double.parseDouble(dollarsValue.getText());
            double pounds = poundsPerDollars * dollars;
            poundsValue.setText(String.valueOf(pounds));
        }else{
            double pounds = Double.parseDouble(poundsValue.getText());
            double dollars = dollarsPerPound* pounds;
            dollarsValue.setText(String.valueOf(dollars));
        }
    }
    
}
