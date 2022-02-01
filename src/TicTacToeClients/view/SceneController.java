/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeClients.view;

import TicTacToeClients.Players;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
     private String my_username;
     DatabaseConnection data = new DatabaseConnection();
     List<Players> players = data.getPlayers();
    
     @FXML
     private TextArea textplayer = new TextArea();
     @FXML
     private TextField username;
     @FXML
     private TextField password;
     @FXML
     private TextField username_in;
     @FXML
     private TextField password_in;
     boolean checkPlayerExists = false;
     //int i  = 0;
     @FXML
     ObservableList<Players> players_data = FXCollections.observableArrayList();

    public String getMy_username() {
        return my_username;
    }

    public void setMy_username(String my_username) {
        this.my_username = my_username;
    }
   
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
         if(usernames.isEmpty()){
            System.out.println("Please enter a username"); 
         }
         if(passwords.isEmpty()){
            System.out.println("Please enter a password"); 
         }
         if(data.checkIfExists(usernames)){
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
    public void checkSignIn(ActionEvent event) throws IOException{
         String usernames = username_in.getText();
         String passwords = password_in.getText();
         this.setMy_username(usernames);
         if(usernames.isEmpty()){
            System.out.println("Please enter your username"); 
         }
         if(passwords.isEmpty()){
            System.out.println("Please enter your password"); 
         }
         if(data.checkIfPlayerExists(usernames, passwords)){
            System.out.println("OKAYYY");
     
        
          root = FXMLLoader.load(getClass().getResource("viewPlayers.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();  
         }
         else{
            System.out.println("User does not exist"); 
          }
           //System.out.println("hhbhbgv"+this.getMy_username());   
     }
    public void getSignInInfo(){
         String x = "nourddns";
         System.out.println(x);
        //System.out.println("heyy username "+this.getMy_username());
       // return x;
   }
     
     
   
 //     String y = this.getSignInInfo();
     }
    
    

