/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeClients.view;

import serverside.Players;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import serverside.DatabaseConnection;

/**
 *
 * @author nghon
 */
public class SceneController {
     private Stage stage;
     private Scene scene;
     private Parent root;
     private static String login_user;
     
     
     DatabaseConnection data = new DatabaseConnection();
     List<Players> players = data.getPlayers();
     List<String> getlogin = new ArrayList<String>();
    
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
         //String usernames = username_in.getText();
         login_user = username_in.getText();
         String passwords = password_in.getText();
          
         if(login_user.isEmpty()){
            System.out.println("Please enter your username"); 
         }
         if(passwords.isEmpty()){
            System.out.println("Please enter your password"); 
         }
         if(data.checkIfPlayerExists(login_user, passwords)){
            getlogin.add(login_user);
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
     }
     public String getlogin_user(){
         return login_user;
     }
    
     
   
 //     String y = this.getSignInInfo();
     }
    
    

