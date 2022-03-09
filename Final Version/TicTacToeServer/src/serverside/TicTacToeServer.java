package serverside;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import static serverside.ServerRequests.ClientsInfo;
import static serverside.TicTacToeConstants.PLAYER1;
import static serverside.TicTacToeConstants.PLAYER2;
import sun.security.tools.keytool.Main;

public class TicTacToeServer extends Application implements TicTacToeConstants {

    private int sessionNo = 1;
    private int flag = -1;
    private boolean server_status = false;
    private FXMLLoader loader;
    public serverController controller;
    DataOutputStream toClient;
    mainThreadForPlayers mainthread = new mainThreadForPlayers();

    public void start(Stage stage) throws Exception {
        loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/serverside/GUI_server.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        Scene scene = new Scene(root, 600, 550);
        stage.setTitle("TicTacToeServer");
        stage.setScene(scene);
        stage.show();

        controller.start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (flag == -1) {
                    mainthread.start();        
                } else if (flag == 0) {
                    mainthread = new mainThreadForPlayers();
                    mainthread.start();
                }
                flag = 1;
                controller.table.setItems(controller.getPlayersList());
            }
        });

        controller.stop.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (flag == 1) {
                    Platform.runLater(() -> controller.labStatus.setText("server stoped !!!!!!!!!"));
                    try {
                        mainthread.serverSocket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    mainthread.stop();
                    flag = 0;
//                    for (Clients url : ClientsInfo.values()) {
//                     try{
//                       toClient = new DataOutputStream(url.getUser_socket().getOutputStream());
//                       toClient.writeUTF("serverclose");
//                     }catch(Exception m){}
//                     
//                    
//                    }
                }
            }
        });

    }

    public class mainThreadForPlayers extends Thread {

        List<Clients> allClients = new ArrayList<Clients>();
        DatabaseConnection data = new DatabaseConnection();
        HashMap<String, Clients> ClientsInfo = new HashMap<String, Clients>();

        Clients client;
        private Socket player2;
        private DataInputStream fromClient;
        private String invitedUsername;
        private String PlayerUsername;
        private String LoggedInUsername;
        private String recievedMessage;
        private String message;
        private String responseToInvitation;
        FileOutputStream fileOut;
        InetSocketAddress socketAddress;
        String clientIpAddress;
        ObjectOutputStream toClientObj;
        ServerSocket serverSocket;
        DataOutputStream toClient;
        DataOutputStream toPlayer1;
        DataOutputStream toPlayer2;
        boolean accepted = false;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        boolean checkIfPlayerExists = false;
        private volatile boolean checkResponse = false;
        boolean x = true;
        private boolean checkInvitation = false;
        Socket client_socket;
        Socket players_socket;

        public void run() {
            try {

                serverSocket = new ServerSocket(8000);

                Platform.runLater(() -> controller.labStatus.setText(new Date() + ":server Started at socket 8000\n"));
               
                while (x) {
                    Socket players = serverSocket.accept();
                    System.out.println("general socket "+players);
                    new ServerRequests(players).start();

                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public void sendMessageToInvitedPlayer(String username, String invited, HashMap<String, Clients> myMap) throws IOException {
            //HashMap<Socket, String> getResult = new HashMap<Socket, String>();
            Clients c = myMap.get(invited);
            Socket socket = c.getUser_socket();
            toClient = new DataOutputStream(socket.getOutputStream());
            toClient.writeUTF("Hello, " + c.getUsername() + ", " + username + " sent you an invitation");
            toClient.flush();
            toClient.close();
            // responseToInvitation = fromClient.readUTF();
            //getResult.put(socket, responseToInvitation);
        }

    }

}
