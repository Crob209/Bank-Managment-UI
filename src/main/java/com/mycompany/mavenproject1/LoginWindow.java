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

public class LoginWindow extends Application {
    private TextField emailField;
    private TextField passwordField;
    private Label messageLabel; 

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(540, 500);

        AnchorPane topPane = new AnchorPane();
        topPane.setPrefSize(540, 94);
        topPane.setStyle("-fx-background-color: #0000FF;");

        Label loginLabel = new Label("Login Here");
        loginLabel.setTextFill(Color.WHITE);
        loginLabel.setFont(new Font(30));
        loginLabel.setLayoutX(197);
        loginLabel.setLayoutY(24);
        topPane.getChildren().add(loginLabel);

        emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setPrefSize(441, 27);
        emailField.setLayoutX(49);
        emailField.setLayoutY(163);

        passwordField = new TextField();
        passwordField.setPromptText("Password");
        passwordField.setPrefSize(441, 25);
        passwordField.setLayoutX(49);
        passwordField.setLayoutY(224);

        Button loginButton = new Button("Login");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #0000FF;");
        loginButton.setFont(new Font(20));
        loginButton.setPrefSize(441, 45);
        loginButton.setLayoutX(49);
        loginButton.setLayoutY(367);
        loginButton.setOnAction(e -> handleLoginButtonAction());

        Button closeButton = new Button("Close");
        closeButton.setTextFill(Color.WHITE);
        closeButton.setStyle("-fx-background-color: #0000FF;");
        closeButton.setFont(new Font(20));
        closeButton.setPrefSize(441, 45);
        closeButton.setLayoutX(49);
        closeButton.setLayoutY(440);
        closeButton.setOnAction(e -> primaryStage.close());

        
        messageLabel = new Label();
        messageLabel.setTextFill(Color.RED);
        messageLabel.setFont(new Font(14));
        messageLabel.setLayoutX(49);
        messageLabel.setLayoutY(300);

        root.getChildren().addAll(topPane, emailField, passwordField, loginButton, closeButton, messageLabel);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
        primaryStage.show();
    }

    private void handleLoginButtonAction() {
        String email = emailField.getText();
        String password = passwordField.getText();

       
        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both email and password.");
            return;
        }

       
        boolean success = BankComm.Signin(email, password);
        if (success) {
            messageLabel.setTextFill(Color.GREEN);
            messageLabel.setText("Login successful!");
            
        } else {
            messageLabel.setTextFill(Color.RED);
            messageLabel.setText("Login failed. Please check your credentials.");
        }
    }
}
