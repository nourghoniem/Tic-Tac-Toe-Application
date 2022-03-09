package TicTacToeClients.view;

import Handling2Players.PlayerHandler;
import Handling2Players.Players;
import Handling2Players.TicTacToeClient;
import static Handling2Players.TicTacToeConstants.DRAW;
import static Handling2Players.TicTacToeConstants.PLAYER1;
import static Handling2Players.TicTacToeConstants.PLAYER1_WON;
import static Handling2Players.TicTacToeConstants.PLAYER2;
import static Handling2Players.TicTacToeConstants.PLAYER2_WON;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewPlayersController implements Initializable {

    //TicTacToeServer.mainThreadForPlayers in = new TicTacToeServer.mainThreadForPlayers();
    SceneController s = new SceneController();
    String getssss;
    String user;
    private String myToken = " ";
    private String otherToken = " ";
    private static String test;
    private static XO2PlayerController controller;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    private static boolean send_invitation = false;
    @FXML
    private TableView<Players> table;
    @FXML
    private TableColumn<Players, String> usernamecol;
    @FXML
    private TableColumn<Players, String> statuscol;
    @FXML
    private TableColumn<Players, Void> invitecol;
    Button invite = new Button("Invite");
    String m = s.getlogin_user();
    List<Players> players;
    int player1_id;
    int player2_id;
    // PlayerHandler playerh;
    boolean check = true;
    String CheckIfAccepted;
    private boolean continueToPlay = true;
    boolean ifTrue = false;
    static boolean startGame = false;
    Parent root;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TicTacToeClients/view/GUI.fxml"));

            root = loader.load();
            controller = (XO2PlayerController) loader.getController();

            String statusResponse;
            String usernameResponse;
            PlayerHandler.getToServer().writeUTF("ViewPlayer,");
            int listSize = PlayerHandler.getFromServer().readInt();
            player1_id = PlayerHandler.getFromServer().readInt();

            players = new ArrayList<Players>(listSize);
            for (int i = 0; i < listSize; i++) {
                usernameResponse = PlayerHandler.getFromServer().readUTF();
                statusResponse = PlayerHandler.getFromServer().readUTF();
                Players pom = new Players(usernameResponse, statusResponse);
                players.add(pom);
            }

        } catch (Exception e) {
        }
        usernamecol.setCellValueFactory(new PropertyValueFactory<Players, String>("username"));
        statuscol.setCellValueFactory(new PropertyValueFactory<Players, String>("status"));
        invitecol.setCellValueFactory(new PropertyValueFactory<Players, Void>("invite"));
        table.setItems(getPlayersList());
        addButtonToTable();

        new Thread(() -> {
            while (check) {
                try {

                    String response = PlayerHandler.getFromServer().readUTF();
                    String[] tokens = response.split(",");
                    switch (tokens[0]) {
                        case "Hello":
                            System.out.println(response);

                            Platform.runLater(() -> {
                                try {
                                    String checkingReply = popUpMessage(response);
                                    PlayerHandler.getToServer().writeUTF("response," + checkingReply);

                                } catch (Exception e) {
                                }
                            });
                            break;

                        case "accepted":
                            System.out.println("confirmation");
                            Platform.runLater(() -> {
                                try {

                                    Scene SceneMenu = new Scene(root);
                                    Stage stage = (Stage) table.getParent().getScene().getWindow();
                                    stage.setScene(SceneMenu);
                                    stage.show();

                                } catch (Exception e) {
                                }
                            });
                            int player = PlayerHandler.getFromServer().readInt();
                            System.out.println(player);
                            if (player == PLAYER1) {
                                // System.out.println(PLAYER1);
                                myToken = "X";
                                otherToken = "O";

                                Platform.runLater(() -> {
                                    controller.printToken(myToken);
                                    controller.printStatus("Waiting for player 2 to join");
                                });

                                PlayerHandler.getFromServer().readInt();

                                Platform.runLater(() -> {
                                    controller.printStatus("player 2 has joined, i start");

                                }
                                );
                                controller.turn = true;
                            } else if (player == PLAYER2) {
                                //  System.out.println(PLAYER2);
                                myToken = "O";
                                otherToken = "X";
                                Platform.runLater(() -> {
                                    controller.printToken(myToken);
                                    controller.printStatus("Waiting for player 1 to move");

                                });
                            }
                       
                            while (continueToPlay) {
                                System.out.println("continue to play..");
                                if (player == PLAYER1) {
                                   // counter++;
                                    System.out.println("test test player 1");
                                    waitForPlayerAction();
                                   
                                    sendMove(1);
                                    checkInfo();
                            

                                } else if (player == PLAYER2) {
                                  //  counter++;
                                    System.out.println("test test player 2");
                                    checkInfo();
                                    waitForPlayerAction();
                                   
                                    sendMove(2);
                                }
                            }

                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // countDownLatch.countDown();
        }).start();


    }

    ObservableList<Players> getPlayersList() {
        ObservableList<Players> playerm = FXCollections.observableArrayList();
        for (int i = 0; i < players.size(); i++) {
            final Players pl = Players.usernameStatus(players.get(i).getUsername(), players.get(i).getStatus());
            playerm.add(pl);
        }
        return playerm;
    }

    public String addButtonToTable() {

        Callback<TableColumn<Players, Void>, TableCell<Players, Void>> cellFactory = new Callback<TableColumn<Players, Void>, TableCell<Players, Void>>() {
            @Override
            public TableCell<Players, Void> call(final TableColumn<Players, Void> param) {

                final TableCell<Players, Void> cell = new TableCell<Players, Void>() {

                    private final Button btn = new Button("Invite");

                    {

                        btn.setOnAction((ActionEvent event) -> {

                            Players data = getTableView().getItems().get(getIndex());
                            test = data.getUsername();
                            try {

                                PlayerHandler.getToServer().writeUTF("InvitedPlayer," + test);

                            } catch (IOException e) {
                            }
                            send_invitation = true;

                        });

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        invitecol.setCellFactory(cellFactory);

        return test;

    }

    public String getValue() {
        String r = addButtonToTable();
        return r;
    }

    public String getUsername() {
        return test;

    }

    public boolean getIfInvited() {
        return send_invitation;
    }

    public String popUpMessage(String l) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.setTitle("Invitation");
        ButtonType accept = new ButtonType("Accept", ButtonBar.ButtonData.YES);
        ButtonType decline = new ButtonType("Reject", ButtonBar.ButtonData.NO);
        dialog.setContentText(l);
        dialog.getDialogPane().getButtonTypes().addAll(accept, decline);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == accept) {
            CheckIfAccepted = "true";

        } else {
            CheckIfAccepted = "false";

        }
        return CheckIfAccepted;
    }

    private void waitForPlayerAction() throws InterruptedException {
        //controller.waitingForoccurAction

        System.out.println("Is it null pointer?????   " + controller.waitingForoccurAction);
        while (controller.waitingForoccurAction) {

            Thread.sleep(100);

        }
        System.out.println("while exit   " + controller.waitingForoccurAction);
        controller.waitingForoccurAction = true;
        System.out.println("after change controller   " + controller.waitingForoccurAction);
    }

    private void sendMove(int num) {
        try {
            
           
            PlayerHandler.getToServer().writeUTF("Game,");
            PlayerHandler.getToServer().writeInt(num);
             System.out.println("num: "+ num);

//            System.out.println("try_row: "+controller.selectedRow);
//            System.out.println("try_col: "+controller.selectedCol);
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
        System.out.println("received row:   " +row);
        System.out.println("received column:   " +column);
        Platform.runLater(() -> controller.arr.get(row).get(column).setText(otherToken));
    }

}
