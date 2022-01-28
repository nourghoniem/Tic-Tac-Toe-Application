/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import static tic.tac.toe.TicTacToeConstants.PLAYER1;
import static tic.tac.toe.TicTacToeConstants.PLAYER2;

/**
 *
 * @author nghon
 */
public class ApplicationServer extends Application implements TicTacToeConstants{
      ServerSocket server_socket;
      Socket player1;
      Socket player2;
      DataOutputStream out_p1;
      DataOutputStream out_p2;
      Button start, stop;
      
      public ApplicationServer() throws IOException{
        
       }
      @Override
      public void start(Stage PrimaryStage){
          stop.setOnAction(new EventHandler <ActionEvent>(){
           public void handle(ActionEvent e){
             try{
             server_socket.close();
             }catch(IOException s){s.printStackTrace();}
           }
         });
          
          start.setOnAction(new EventHandler <ActionEvent>(){
           public void handle(ActionEvent e){
              new Thread(()->{
                  try{
                    server_socket = new ServerSocket(9090);
                    while(true){
                        player1 = server_socket.accept();
                        System.out.println("[SERVER] Player 1 joined");
                        out_p1 = new DataOutputStream(player1.getOutputStream());
                        out_p1.writeInt(PLAYER1);
                        player2 = server_socket.accept();
                        System.out.println("[SERVER] Player 2 joined");
                        out_p2 = new DataOutputStream(player2.getOutputStream());
                        out_p2.writeInt(PLAYER2);
                        System.out.println("[SERVER] Player 2 joined");
                        System.out.println("***Starting new Thread for Players [SESSION]***");
                        new Thread(new PlayerHandler(player1, player2)).start();
                      }
                   }catch(IOException s){s.printStackTrace();}
               }).start(); 
               }
          });
         
        
          start = new Button("Start");
          stop = new Button("Stop");
          FlowPane pane = new FlowPane(start, stop);
          Scene scene = new Scene(pane,500,500);
          PrimaryStage.setScene(scene);
          PrimaryStage.setTitle("Tic Tac Toe Server");
          PrimaryStage.show();
      }


      public static void main(String[] args) throws IOException {
        //ApplicationServer server = new ApplicationServer();
         Application.launch(args);
    }
}
