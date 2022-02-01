/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeClients.view;

import TicTacToeClients.Players;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tic.tac.toe.DatabaseConnection;


public class ViewPlayersController implements Initializable {
    DatabaseConnection data = new DatabaseConnection();
    List<Players> players = data.getPlayers();
     @FXML
     private TableView<Players> table;
     @FXML
     private TableColumn<Players, String> usernamecol;
     @FXML
     private TableColumn<Players, String> statuscol;

     @Override
    public void initialize(URL url, ResourceBundle rb) {
 
       usernamecol.setCellValueFactory(new PropertyValueFactory<Players, String>("username"));
       statuscol.setCellValueFactory(new PropertyValueFactory<Players, String>("status"));
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
}
