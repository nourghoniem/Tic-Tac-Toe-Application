
package Handling2Players;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClientMain extends Application{
    PlayerHandler player;
    @Override
    public void start(Stage stage) throws IOException {
        player= new PlayerHandler();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/TicTacToeClients/view/Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
   }

    
    public static void main(String[] args) throws IOException{
       Application.launch();
    }
    
}
