
package serverside;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;


public class serverController implements Initializable {
     DatabaseConnection database = new DatabaseConnection();
     public Button start;
     public Button stop;
     public Label labStatus;
     @FXML
      TableView<Players> table;
     @FXML
     private TableColumn<Players, String> username;
     @FXML
     private TableColumn<Players, String> status;
     @FXML
     private TableColumn<Players, String> score;
     List<Players> players = database.viewAllPlayers();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       username.setCellValueFactory(new PropertyValueFactory<Players, String>("username"));
       status.setCellValueFactory(new PropertyValueFactory<Players, String>("status"));
       score.setCellValueFactory(new PropertyValueFactory<Players, String>("score"));
       //table.setItems(getPlayersList());
      
    }
     ObservableList<Players> getPlayersList(){
     ObservableList<Players> playerm = FXCollections.observableArrayList();
       for(int i = 0; i < players.size(); i++){
            Players pl = new Players(players.get(i).getUsername(), players.get(i).getStatus(), players.get(i).getScore());
           playerm.add(pl);
       }
       return playerm;
    } 
}
