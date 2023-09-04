package com.example.listing_33_4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client2 extends Application {

    //IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override //Override the start method in the Application class
    public void start(Stage primaryStage){
        // Panel p to hold the label and text field
        BorderPane paneForTextfield = new BorderPane();
        paneForTextfield.setPadding(new Insets(5,5,5,5));
        paneForTextfield.setStyle("-fx-border-color: blue");
        paneForTextfield.setLeft(new Label("Enter a radius: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextfield.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        //Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextfield);

        // Create a scene and place it in the stage
        Scene scene = new Scene(mainPane,450,200);
        primaryStage.setTitle("Client"); //Set the stage title
        primaryStage.setScene(scene); //Place the scene in the stage
        primaryStage.show(); // Display the stage

        tf.setOnAction(e -> {

            try {
                //Get the radius from the text field
                double radius = Double.parseDouble(tf.getText().trim());

                //send the radius to the server
                toServer.writeDouble(radius);
                toServer.flush();

                // Get area from the server
                double area = fromServer.readDouble();

                // Display to the text area
                ta.appendText("Radius is " + radius + '\n');
                ta.appendText("Area received from the server is " + area + '\n');

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        try {
            //Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);


            //Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            //Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

