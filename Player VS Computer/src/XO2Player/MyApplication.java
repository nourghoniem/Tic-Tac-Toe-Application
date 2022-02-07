
package XO2Player;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApplication extends Application{

     public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("GUI.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
}
