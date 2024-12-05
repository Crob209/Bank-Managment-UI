package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpPage extends Application {
    private TextField nameTextField;
    private TextField ageTextField;
    private TextField emailTextField;
    private TextField phoneNumberTextField;
    private TextField passwordTextField;
    private Label messageLabel; 

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(520, 400);

        
        AnchorPane headerPane = new AnchorPane();
        headerPane.setPrefSize(520, 82);
        headerPane.setStyle("-fx-background-color: #0000FF;");

        Label registerLabel = new Label("Register");
        registerLabel.setLayoutX(221);
        registerLabel.setLayoutY(32);
        registerLabel.setTextFill(Color.WHITE);
        registerLabel.setFont(new Font(24));
        headerPane.getChildren().add(registerLabel);

       
        nameTextField = new TextField();
        nameTextField.setPromptText("Enter name");
        nameTextField.setLayoutX(39);
        nameTextField.setLayoutY(127);
        nameTextField.setPrefHeight(25);
        nameTextField.setPrefWidth(449);

        ageTextField = new TextField();
        ageTextField.setPromptText("Enter age");
        ageTextField.setLayoutX(39);
        ageTextField.setLayoutY(159);
        ageTextField.setPrefHeight(25);
        ageTextField.setPrefWidth(449);

        emailTextField = new TextField();
        emailTextField.setPromptText("Enter email");
        emailTextField.setLayoutX(39);
        emailTextField.setLayoutY(191);
        emailTextField.setPrefHeight(25);
        emailTextField.setPrefWidth(449);

        phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("Enter phone number");
        phoneNumberTextField.setLayoutX(39);
        phoneNumberTextField.setLayoutY(223);
        phoneNumberTextField.setPrefHeight(25);
        phoneNumberTextField.setPrefWidth(449);

        passwordTextField = new TextField();
        passwordTextField.setPromptText("Create password");
        passwordTextField.setLayoutX(39);
        passwordTextField.setLayoutY(255);
        passwordTextField.setPrefHeight(25);
        passwordTextField.setPrefWidth(449);

        
        Button signUpButton = new Button("Sign up!");
        signUpButton.setLayoutX(39);
        signUpButton.setLayoutY(287);
        signUpButton.setPrefHeight(32);
        signUpButton.setPrefWidth(449);
        signUpButton.setStyle("-fx-background-color: #0000FF;");
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setFont(new Font(14));
        signUpButton.setOnAction(e -> handleSignUpButtonAction());

        
        Button closeButton = new Button("Close");
        closeButton.setLayoutX(39);
        closeButton.setLayoutY(329);
        closeButton.setPrefHeight(32);
        closeButton.setPrefWidth(449);
        closeButton.setStyle("-fx-background-color: #0000FF;");
        closeButton.setTextFill(Color.WHITE);
        closeButton.setFont(new Font(14));
        closeButton.setOnAction(e -> primaryStage.close());

        
        messageLabel = new Label();
        messageLabel.setLayoutX(39);
        messageLabel.setLayoutY(360);
        messageLabel.setTextFill(Color.RED);
        messageLabel.setFont(new Font(14));

        
        root.getChildren().addAll(headerPane, nameTextField, ageTextField, emailTextField, phoneNumberTextField, passwordTextField, signUpButton, closeButton, messageLabel);

        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up Form");
        primaryStage.show();
    }

    public void handleSignUpButtonAction() {
        String name = nameTextField.getText();
        String ageText = ageTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String password = passwordTextField.getText();

       
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            messageLabel.setText("Please enter a valid age.");
            return;
        }

        
        boolean success = BankComm.Signup(name, age, email, phoneNumber, password);
        if (success) {
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Sign up successful");
        } else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Sign up failed. Please try again.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
