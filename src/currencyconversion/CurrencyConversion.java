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
    private Button calculate;
    private RadioButton FutureValueChoice;
    private String principalString;
    private TextField principalValue;
    private String rateString;
    private TextField rateValue;
    private String timeString;
    private TextField timeValue;
    private String numberOfCompoundingsString;
    private TextField numberOfCompoundingsValue;
    private String amountString;
    private TextField amountValue;
    
    private final int sceneWidth = 500;
    private final int sceneHeight = 400;
    
    
    @Override
    public void start(Stage primaryStage) {
        final double dollarsPerPound = 1.31;
        final double poundsPerDollars = .76;
        
        drawVisualInterface();
        
        calculate.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event){
                convertCurrency(dollarsPerPound, poundsPerDollars);
            }
        });
        
        primaryStage.setTitle("Financial App");
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void drawVisualInterface(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(setUpCurrencyChoices());
        borderPane.setCenter(setUpCurrencyInformation());
        calculate = new Button("Calculate");
        borderPane.setBottom(calculate);
        scene = new Scene(borderPane, sceneWidth, sceneHeight);
    }
    
    private HBox setUpCurrencyChoices(){
        FutureValueChoice = new RadioButton("Future Value");
        RadioButton dollarsChoice = new RadioButton("Present Value");
        FutureValueChoice.setSelected(true);
        
        ToggleGroup tg = new ToggleGroup();
        FutureValueChoice.setToggleGroup(tg);
        dollarsChoice.setToggleGroup(tg);
        
        HBox currencyChoices = new HBox();
        currencyChoices.getChildren().add(FutureValueChoice);
        currencyChoices.getChildren().add(dollarsChoice);
        
        return currencyChoices;
    }
    
    private VBox setUpCurrencyInformation(){
        VBox currencyInformation = new VBox();
        currencyInformation.getChildren().add(setUpPoundsInformation());
        currencyInformation.getChildren().add(setUpDollarsInformation());
        
        return currencyInformation;
    }
    
    private HBox setUpPrincipalInformation(){
        Label principalName = new Label("Principal");
        principalValue = new TextField(principalString);
        
        HBox principalInformation = new HBox();
        principalInformation.getChildren().add(principalName);
        principalInformation.getChildren().add(principalValue);
        
        return principalInformation;
    }
    
    private HBox setUpRateInformation(){
        Label rateName = new Label("Rate");
        rateValue = new TextField(rateString);
        
        HBox rateInformation = new HBox();
        rateInformation.getChildren().add(rateName);
        rateInformation.getChildren().add(rateValue);
        
        return rateInformation;
    }
    
    private HBox setUpTimeInformation(){
        Label timeName = new Label("Time");
        timeValue = new TextField(timeString);
        
        HBox timeInformation = new HBox();
        timeInformation.getChildren().add(timeName);
        timeInformation.getChildren().add(timeValue);
        
        return timeInformation;
    }
    
    private HBox setUpNumberOfCompoundingsInformation(){
        Label numberOfCompoundingsName = new Label("Number Of Compoundings");
        numberOfCompoundingsValue = new TextField(numberOfCompoundingsString);
        
        HBox numberOfCompoundingsInformation = new HBox();
        numberOfCompoundingsInformation.getChildren().add(numberOfCompoundingsName);
        numberOfCompoundingsInformation.getChildren().add(numberOfCompoundingsValue);
        
        return numberOfCompoundingsInformation;
    }
    
    private HBox setUpAmountInformation(){
        Label amountName = new Label("Amount");
        amountValue = new TextField(amountString);
        
        HBox amountInformation = new HBox();
        amountInformation.getChildren().add(amountName);
        amountInformation.getChildren().add(amountValue);
        
        return amountInformation;
    }
    
    private void Calculate(double dollarsPerPound, double poundsPerDollars){
        if(FutureValueChoice.isSelected()){
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
