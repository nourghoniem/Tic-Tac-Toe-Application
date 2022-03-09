package TicTacToeClients.view;

import Handling2Players.PlayerHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author nghon
 */
public class SceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private static String login_user;
    private static String signUp_user;

    @FXML
    private TextArea textplayer = new TextArea();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username_in;
    @FXML
    private PasswordField password_in;
    @FXML
    private Label failure_message;
    @FXML
    private Label failed_message;
    @FXML
    private Label success_message;
    boolean check = true;
    boolean checkPlayerExists = false;

    //int i  = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        new Thread(() -> {
//            while (check) {
//              Platform.runLater(() -> {
//                try {
//                    String response = PlayerHandler.getFromServer().readUTF();
//                    if (response.equals("serverclose")) {
//                       
//                        stage.close();
//
//                    }
//                } catch (Exception e) {
//                }
//          });
//
//            }
//        }).start();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Signup_1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Signin_1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void checkIfPlayerExists(ActionEvent event) throws IOException {

        signUp_user = username.getText();
        String passwords = password.getText();
        if (signUp_user.isEmpty()) {
            System.out.println("Please enter a username");
            failure_message.setText("Please enter a username");
        }
        if (passwords.isEmpty()) {
            System.out.println("Please enter a password");
            failure_message.setText("Please enter a password");
        }
        PlayerHandler.getToServer().writeUTF("SignUp," + signUp_user + "," + passwords);
        String signUpresponse = PlayerHandler.getFromServer().readUTF();
        if (signUpresponse.equals("exists")) {
            failure_message.setText("Username already exists");
        } else {
            root = FXMLLoader.load(getClass().getResource("Signin_1.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void checkSignIn(ActionEvent event) throws IOException {

        login_user = username_in.getText();
        String passwords = password_in.getText();
        if (login_user.isEmpty() || passwords.isEmpty()) {
            if (login_user.isEmpty()) {
                System.out.println("Please enter your username");
                failed_message.setText("Please enter your username");

            }
            if (passwords.isEmpty()) {
                System.out.println("Please enter your password");
                failed_message.setText("Please enter your password");
            }
        } else {
            PlayerHandler.getToServer().writeUTF("SignIn," + login_user + "," + passwords);
            String signInresponse = PlayerHandler.getFromServer().readUTF();
            if (signInresponse.equals("okay")) {

                root = FXMLLoader.load(getClass().getResource("viewPlayersInfo_1.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                failed_message.setText("User does not exist");
            }
        }
    }

    public String getlogin_user() {
        return login_user;
    }

    public String getSignedUp_user() {
        return signUp_user;
    }

}
