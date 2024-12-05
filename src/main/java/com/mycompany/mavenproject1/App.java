package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 500);

        
        AnchorPane headerPane = new AnchorPane();
        headerPane.setPrefSize(600, 100);
        headerPane.setStyle("-fx-background-color: #0000FF;");

        Text welcomeText = new Text("Welcome to Our Banking App!");
        welcomeText.setFont(new Font(30));
        welcomeText.setFill(Color.WHITE);
        welcomeText.setLayoutX(120);
        welcomeText.setLayoutY(60);
        headerPane.getChildren().add(welcomeText);

        
        VBox buttonBox = new VBox(20); 
        buttonBox.setLayoutX(175);
        buttonBox.setLayoutY(250);
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);

        
        Button loginButton = new Button("Log In");
        loginButton.setPrefWidth(250);
        loginButton.setPrefHeight(50);
        loginButton.setStyle("-fx-background-color: #0000FF;");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setFont(new Font(18));
        loginButton.setOnAction(e -> openLoginWindow());

        
        Button signUpButton = new Button("Sign Up");
        signUpButton.setPrefWidth(250);
        signUpButton.setPrefHeight(50);
        signUpButton.setStyle("-fx-background-color: #0000FF;");
        signUpButton.setTextFill(Color.WHITE);
        signUpButton.setFont(new Font(18));
        signUpButton.setOnAction(e -> openSignUpWindow());

        
        buttonBox.getChildren().addAll(loginButton, signUpButton);

        
        Text instructionText = new Text("Please choose an option to continue:");
        instructionText.setFont(new Font(16));
        instructionText.setFill(Color.BLACK);
        instructionText.setLayoutX(170);
        instructionText.setLayoutY(220);

        root.getChildren().addAll(headerPane, buttonBox, instructionText);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Screen");
        primaryStage.show();
    }

   
    private void openLoginWindow() {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.start(new Stage());
    }

    
    private void openSignUpWindow() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
