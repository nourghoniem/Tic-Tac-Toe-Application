/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TicTacToeClients.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nghon
 */
public class ChooseModeController implements Initializable {

     private Stage stage;
     private Scene scene;
     private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void viewInviteList(ActionEvent event) throws IOException{
       root = FXMLLoader.load(getClass().getResource("viewPlayers.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }   
    
}
