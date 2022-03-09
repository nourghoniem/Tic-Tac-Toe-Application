package Handling2Players;

import TicTacToeClients.view.GameController;
import TicTacToeClients.view.ReceiveInvitationController;
import TicTacToeClients.view.SceneController;
import TicTacToeClients.view.ViewPlayersController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import TicTacToeClients.view.XO2PlayerController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
//import sun.security.tools.keytool.Main;

public class TicTacToeClient implements TicTacToeConstants {

    //private boolean accepted = false;
    private String myToken = " ";
    private String otherToken = " ";
//    private int rowSelected;
//    private int columnSelected;
//    private DataInputStream fromServer;
//    private DataOutputStream toServer;
    private boolean continueToPlay = true;
//    private static String getInvited;
//    private static String getPlayer;
//    private String host = "localhost";
    private FXMLLoader loader;
//    private String CheckIfAccepted;
    private XO2PlayerController controller;
//    private GameController gamecontroller;
    PlayerHandler playerHandler;
//    private ReceiveInvitationController receiveinvotationcontroller;
    ViewPlayersController viewPlayersController = new ViewPlayersController();
    ReceiveInvitationController invitationController;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    CountDownLatch latch = new CountDownLatch(1);
    private String l;
    boolean started = false;
    SceneController sceneController = new SceneController();
    boolean check = true;

    private void connectToServer() {
        try {
            playerHandler = new PlayerHandler();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new Thread(() -> {

            try {
               
                    int player =  PlayerHandler.getFromServer().readInt();
                    if (player == PLAYER1) {

                        myToken = "X";
                        otherToken = "O";

                        Platform.runLater(() -> {

                            controller.printToken(myToken);
                            controller.printStatus("Waiting for player 2 to join");
                        });

                       PlayerHandler.getFromServer().readInt();

                        Platform.runLater(()
                                -> controller.printStatus("player 2 has joined, i start"));

                        controller.turn = true;

                    } else if (player == PLAYER2) {
                        myToken = "O";
                        otherToken = "X";
                        Platform.runLater(() -> {
                            controller.printToken(myToken);
                            controller.printStatus("Waiting for player 1 to move");

                        });
                    }
                    while (continueToPlay) {
                        if (player == PLAYER1) {
                            waitForPlayerAction();
                            sendMove();
                            checkInfo();

                        } else if (player == PLAYER2) {
                            checkInfo();
                            waitForPlayerAction();
                            sendMove();
                        }
                    }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    private void waitForPlayerAction() throws InterruptedException {
        while (controller.waitingForoccurAction) {

            Thread.sleep(100);
        }
        controller.waitingForoccurAction = true;
    }

    private void sendMove() {
        try {
            PlayerHandler.getToServer().writeInt(controller.selectedRow);
            PlayerHandler.getToServer().writeInt(controller.selectedCol);
        } catch (IOException ex) {
            Logger.getLogger(TicTacToeClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void checkInfo() throws IOException {
        int status = PlayerHandler.getFromServer().readInt();

        if (status == PLAYER1_WON) {
            continueToPlay = false;
            if (myToken == "X") {
                Platform.runLater(() -> controller.printStatus("i won! (X)"));
            } else if (myToken == "O") {
                Platform.runLater(() -> controller.printStatus("player 1 (x) has won!"));
                receiveMove();
            }
        } else if (status == PLAYER2_WON) {
            continueToPlay = false;
            if (myToken == "O") {
                Platform.runLater(() -> controller.printStatus("i won! (O)"));
            } else if (myToken == "X") {
                Platform.runLater(() -> controller.printStatus("player 2 (O) has won!"));
                receiveMove();
            }
        } else if (status == DRAW) {
            continueToPlay = false;
            Platform.runLater(() -> controller.printStatus("Game is Over , no winner"));
            if (myToken == "O") {
                receiveMove();
            }
        } else {
            receiveMove();
            Platform.runLater(() -> controller.printStatus("My turn"));
            controller.turn = true;

        }
    }

    private void receiveMove() throws IOException {

        int row = PlayerHandler.getFromServer().readInt();
        int column = PlayerHandler.getFromServer().readInt();
        Platform.runLater(() -> controller.arr.get(row).get(column).setText(otherToken));
    }
}
