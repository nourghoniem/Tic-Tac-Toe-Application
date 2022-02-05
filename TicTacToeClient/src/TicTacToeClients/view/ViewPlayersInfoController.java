/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TicTacToeClients.view;


import java.io.IOException;
import serverside.Players;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import serverside.DatabaseConnection;

/**
 * FXML Controller class
 *
 * @author nghon
 */
public class ViewPlayersInfoController implements Initializable {
     DatabaseConnection data = new DatabaseConnection();
     private Stage stage;
     private Scene scene;
     private Parent root;
     SceneController s = new SceneController();
      String getssss;
     @FXML
     private TableView<Players> table;
     @FXML
     private TableColumn<Players, String> username;
     @FXML
     private TableColumn<Players, String> status;
 
     String m = s.getlogin_user();
     List<Players> players = data.viewPlayers(m);
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       username.setCellValueFactory(new PropertyValueFactory<Players, String>("username"));
       status.setCellValueFactory(new PropertyValueFactory<Players, String>("status"));
       
       table.setItems(getPlayersList());
      
    } 
     ObservableList<Players> getPlayersList(){
     ObservableList<Players> playerm = FXCollections.observableArrayList();
       for(int i = 0; i < players.size(); i++){
           final Players pl = Players.usernameStatus(players.get(i).getUsername(), players.get(i).getStatus());
           playerm.add(pl);
       }
       return playerm;
    }
    public void choosePlayer(ActionEvent event) throws IOException{
       root = FXMLLoader.load(getClass().getResource("chooseMode.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();

    }
 
}
