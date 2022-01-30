/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeClients.view;

import TicTacToeClients.Players;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tic.tac.toe.DatabaseConnection;

/**
 *
 * @author nghon
 */
public class SceneController {
     private Stage stage;
     private Scene scene;
     private Parent root;
     DatabaseConnection data = new DatabaseConnection();
     @FXML
     private TextField username;
     @FXML
     private TextField password;
     boolean checkPlayerExists = false;
     
     public void switchToSignUp(ActionEvent event) throws IOException{
       root = FXMLLoader.load(getClass().getResource("signUP.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    
     }
     public void switchToSignIn(ActionEvent event) throws IOException{
       root = FXMLLoader.load(getClass().getResource("signIN.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    
     }
     
    public void checkIfPlayerExists(ActionEvent event) throws IOException{
      
         String usernames = username.getText();
         String passwords = password.getText();
         if(data.checkIfExists(usernames, passwords)){
           checkPlayerExists = true;
           System.out.println("Username already exists"); 
         }
         else{ 
           checkPlayerExists = false;
           Players player = new Players(usernames, passwords);
           data.insertPlayer(player);
           root = FXMLLoader.load(getClass().getResource("signIN.fxml"));
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           System.out.println("No user, you can sign up");
         }
    }
     }
    
    

