/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Players extends Application {
     TextArea area;
     TextField text_field;
     Socket s;
     DataInputStream input;
     PrintStream output;
     Button send_btn;
     boolean listening = true;

     @Override
     public void init(){
       area = new TextArea();
       text_field = new TextField();
       text_field.setPromptText("Enter your message..");
       send_btn = new Button("Send");
       send_btn.setDefaultButton(true);
       try{
          s = new Socket("127.0.0.1", 9090);
          input = new DataInputStream(s.getInputStream());
          output = new PrintStream(s.getOutputStream(), true);
       }catch(IOException e){e.printStackTrace();}     
     
     }
     
     @Override
     public void start(Stage PrimaryStage) {
       
        send_btn.setOnAction(new EventHandler <ActionEvent>(){
           public void handle(ActionEvent e){
            new Thread(new Runnable(){
               public void run(){
                     while(listening){
                             String get_message = text_field.getText();
                           
                             while(get_message != null){
                             output.print(get_message);
                             
                             }
                  }
             }
             }).start();
                try{        
                 input.close();
                 output.close();
                 s.close();
               }catch(IOException m){m.printStackTrace();}
    }   
      });
             
        FlowPane pane = new FlowPane(text_field , send_btn);
        BorderPane border = new BorderPane();
        border.setBottom(pane);
        border.setCenter(area);
        Scene scene = new Scene(border, 500, 400);
        
        PrimaryStage.setScene(scene);
        PrimaryStage.setTitle("Hello JFX");
        PrimaryStage.show();
      }
    
     public static void main(String[] args) {
        // TODO code application logic here
         Application.launch(args);
    }
}
