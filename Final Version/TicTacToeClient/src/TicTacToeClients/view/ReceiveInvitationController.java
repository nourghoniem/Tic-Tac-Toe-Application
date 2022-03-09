/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package TicTacToeClients.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nghon
 */
public class ReceiveInvitationController{
    @FXML
    private Label label;
     private Stage stage;
     //private Scene scene;
     private Parent root;
    public void viewMessage(String message) throws IOException{
        label.setText(message);
        System.out.println(message);
//        root = FXMLLoader.load(getClass().getResource("receiveInvitation.fxml"));
//        stage = new Stage();
//        stage.setScene(new Scene(root));
//        stage.show(); 
    }
    
}
