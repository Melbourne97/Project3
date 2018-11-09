package currencyconversion;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CurrencyConversion extends Application {
    
    private Scene scene;
    private Button calculate;
    private RadioButton futureValueChoice;
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
    
    private final int SCENE_WIDTH = 400;
    private final int SCENE_HEIGHT = 250;
    private final Insets INSET = new Insets(5, 5, 5, 5);
    
    
    @Override
    public void start(Stage primaryStage) {        
        drawVisualInterface();
        
        calculate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                Calculate();
            }
        });
        
        primaryStage.setTitle("Financial App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    //sets up the box
    private void drawVisualInterface(){
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(setUpCurrencyChoices());
        borderPane.setCenter(setUpCurrencyInformation());
        calculate = new Button("Calculate");
        borderPane.setBottom(calculate);
        scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
    }
    
    //choices between radio buttons future value and present value
    private HBox setUpCurrencyChoices(){
        futureValueChoice = new RadioButton("Future Value");
        RadioButton presentChoice = new RadioButton("Present Value");
        futureValueChoice.setSelected(true);
        
        ToggleGroup tg = new ToggleGroup();
        futureValueChoice.setToggleGroup(tg);
        presentChoice.setToggleGroup(tg);
        
        HBox currencyChoices = new HBox();
        currencyChoices.getChildren().add(futureValueChoice);
        currencyChoices.getChildren().add(presentChoice);
        
        return currencyChoices;
    }
    
    //dont think its needed but keeping just incase it is
    private VBox setUpCurrencyInformation(){
        VBox currencyInformation = new VBox();
        currencyInformation.getChildren().add(setUpPrincipalInformation());
        currencyInformation.getChildren().add(setUpRateInformation());
        currencyInformation.getChildren().add(setUpTimeInformation());
        currencyInformation.getChildren().add(setUpNumberOfCompoundingsInformation());
        currencyInformation.getChildren().add(setUpAmountInformation());
        currencyInformation.setSpacing(1);
        
        return currencyInformation;
    }
    
    //Principal text area
    private HBox setUpPrincipalInformation(){
        Label principalName = new Label("Principal");
        principalName.setPadding(INSET);
        principalValue = new TextField(principalString);
        principalValue.setText("15000");
        
        HBox principalInformation = new HBox();
        principalInformation.getChildren().add(principalName);
        principalInformation.getChildren().add(principalValue);
        
        return principalInformation;
    }
    
    //Rate text area
    private HBox setUpRateInformation(){
        Label rateName = new Label("Rate");
        rateName.setPadding(INSET);
        rateValue = new TextField(rateString);
        rateValue.setText(".05");
        
        HBox rateInformation = new HBox();
        rateInformation.getChildren().add(rateName);
        rateInformation.getChildren().add(rateValue);
        
        return rateInformation;
    }
    
    //Time text area
    private HBox setUpTimeInformation(){
        Label timeName = new Label("Time");
        timeName.setPadding(INSET);
        timeValue = new TextField(timeString);
        timeValue.setText("30");
        
        HBox timeInformation = new HBox();
        timeInformation.getChildren().add(timeName);
        timeInformation.getChildren().add(timeValue);
        
        return timeInformation;
    }
    
    //Number of Compoundings text area
    private HBox setUpNumberOfCompoundingsInformation(){
        Label numberOfCompoundingsName = new Label("Number Of Compoundings");
        numberOfCompoundingsName.setPadding(INSET);
        numberOfCompoundingsValue = new TextField(numberOfCompoundingsString);
        numberOfCompoundingsValue.setText("12");
        
        HBox numberOfCompoundingsInformation = new HBox();
        numberOfCompoundingsInformation.getChildren().add(numberOfCompoundingsName);
        numberOfCompoundingsInformation.getChildren().add(numberOfCompoundingsValue);
        
        return numberOfCompoundingsInformation;
    }
    
    //Amount text area
    private HBox setUpAmountInformation(){
        Label amountName = new Label("Amount");
        amountName.setPadding(INSET);
        amountValue = new TextField(amountString);
        
        HBox amountInformation = new HBox();
        amountInformation.getChildren().add(amountName);
        amountInformation.getChildren().add(amountValue);
        
        return amountInformation;
    }
    
    //needs calculations for future value and present value
    private void Calculate(){
        if(futureValueChoice.isSelected()){
            double principal = Double.parseDouble(principalValue.getText());
            double rate = Double.parseDouble(rateValue.getText());
            double time = Double.parseDouble(timeValue.getText());
            double compoundings = Double.parseDouble(numberOfCompoundingsValue.getText());
            double amount = principal * Math.pow(1 + rate / compoundings, compoundings * time);
            amountValue.setText(String.valueOf(amount));
        }else{
            double rate = Double.parseDouble(rateValue.getText());
            double time = Double.parseDouble(timeValue.getText());
            double compoundings = Double.parseDouble(numberOfCompoundingsValue.getText());
            double amount = Double.parseDouble(amountValue.getText());
            double present = amount * Math.pow(1 + rate / compoundings,-compoundings * time);
            principalValue.setText(String.valueOf(present));
        }
    }
    
}
