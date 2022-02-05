/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TicTacToeClients;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nghon
 */
public class TicTacToe extends Application{
   public void start(Stage primaryStage){
      try{
         Parent root = FXMLLoader.load(getClass().getResource("/TicTacToeClients/view/signUP.fxml"));
         Scene scene = new Scene(root);
         primaryStage.setScene(scene);
         primaryStage.show();
       }catch(Exception e){e.printStackTrace();}
    }
   public static void main(String[] args) throws IOException {
         Application.launch(args);
    }
}
