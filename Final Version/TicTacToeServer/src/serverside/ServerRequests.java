/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static serverside.TicTacToeConstants.PLAYER1;

/**
 *
 * @author nghon
 */
public class ServerRequests extends Thread implements TicTacToeConstants {

    static List<Clients> allClients = new ArrayList<Clients>();
    static List<Clients> twoPlayers = new ArrayList<Clients>();
    DatabaseConnection data = new DatabaseConnection();
    static HashMap<String, Clients> ClientsInfo = new HashMap<String, Clients>();

    Clients client;

    private Socket player2;
    private String invitedUsername;
    private String PlayerUsername;
    private String LoggedInUsername;
    private String recievedMessage;
    private String message;
    private String responseToInvitation;
    FileOutputStream fileOut;
    InetSocketAddress socketAddress;
    String clientIpAddress;
    // DataOutputStream toClient;
    DataOutputStream toPlayer1;
    DataOutputStream toPlayer2;
    boolean accepted = false;
    CountDownLatch countDownLatch = new CountDownLatch(1);
    boolean checkIfPlayerExists = false;
    private volatile boolean checkResponse = false;
    boolean x = true;
    static String invited_User;
    static Socket invited_User_Socket;
    static Socket main_User;
    static String main_User_Uname;
    private boolean checkInvitation = false;
    Socket player2_socket;
    Socket player1_socket;
    boolean acceptedInv = false;
    DataInputStream fromClient;
    DataOutputStream toClient;
    private Socket socket;
    DataInputStream from_p1;
    DataInputStream from_p2;
    DataOutputStream to_p1;
    DataOutputStream to_p2;
    char[][] cells = new char[3][3];
    boolean lucky = true;
    public static int flag = 0;
    int row;
    int col;

    public ServerRequests(Socket socket) {
        this.socket = socket;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }
    }

    public void run() {
        try {
            fromClient = new DataInputStream(socket.getInputStream());
            toClient = new DataOutputStream(socket.getOutputStream());
            while (x) {
                recievedMessage = fromClient.readUTF();
                String[] tokens = recievedMessage.split(",");

                switch (tokens[0]) {
                    case "SignUp":
                        PlayerUsername = tokens[1];
                        if (data.checkIfExists(PlayerUsername)) {
                            toClient.writeUTF("exists");

                        } else {
                            Players player = new Players(tokens[1], tokens[2]);
                            data.insertPlayer(player);
                            toClient.writeUTF("success");
                            socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                            clientIpAddress = socketAddress.getAddress().getHostAddress();
                            client = new Clients(PlayerUsername, socket, clientIpAddress);
                            allClients.add(client);
                            ClientsInfo.put(PlayerUsername, client);
//                            for (Clients url : ClientsInfo.values()) {
//                                System.out.println(url.getUsername());
//                                System.out.println(url.getUser_socket());
//                            }
                        }
                        break;
                    case "SignIn":
                        LoggedInUsername = tokens[1];
                        if (data.checkIfPlayerExists(LoggedInUsername, tokens[2])) {
                            toClient.writeUTF("okay");

                            data.updateStatus(LoggedInUsername);
                        } else {

                            toClient.writeUTF("no");
                        }
                        break;
                    case "ViewInfo":
                        if (LoggedInUsername != null) {

                            List<Players> players = data.viewPlayers(LoggedInUsername);
                            toClient.writeInt(players.size());

                            for (Players p : players) {

                                toClient.writeUTF(p.getUsername());
                                toClient.writeUTF(p.getStatus());
                            }

                        } else {
                            System.out.println("jnnjnj");
                        }
                        break;
                    case "ViewPlayer":
                        List<Players> players_inv = data.viewOnlinePlayers(LoggedInUsername);
                        toClient.writeInt(players_inv.size());
                        toClient.writeInt(data.getIdByUsername(LoggedInUsername));
                        for (Players p : players_inv) {

                            toClient.writeUTF(p.getUsername());
                            toClient.writeUTF(p.getStatus());
                        }
                        break;
                    case "OnePlayer":
                        String scores = data.getScore(LoggedInUsername);

                        toClient.writeUTF(scores);
                        data.insertPlayerScore(LoggedInUsername, scores);
                        String newScore = data.getScore(LoggedInUsername);
                        toClient.writeUTF(newScore);

                        break;
                    case "InvitedPlayer":
                        invitedUsername = tokens[1];
                        invited_User = tokens[1];
                        System.out.println(invitedUsername);
                        checkInvitation = true;
                        Clients c = ClientsInfo.get(invitedUsername);
                        Clients c1 = ClientsInfo.get(LoggedInUsername);
                        main_User_Uname = LoggedInUsername;
                        twoPlayers.add(c);
                        twoPlayers.add(c1);
                        player2_socket = c.getUser_socket();
                        invited_User_Socket = player2_socket;
                        player1_socket = c1.getUser_socket();
                        main_User = player1_socket;
                        toClient = new DataOutputStream(player2_socket.getOutputStream());
                        toClient.writeUTF("Hello, " + invitedUsername + " , " + LoggedInUsername + " sent you an invitation");
                        break;
                    case "response":
                        String response = tokens[1];
                        if (response.equals("true")) {
                            acceptedInv = true;
                            for (Clients f : twoPlayers) {
                                toClient = new DataOutputStream(f.getUser_socket().getOutputStream());
                                toClient.writeUTF("accepted,");
                            }
                            new DataOutputStream(invited_User_Socket.getOutputStream()).writeInt(PLAYER2);
                            new DataOutputStream(main_User.getOutputStream()).writeInt(PLAYER1);
                            new DataOutputStream(main_User.getOutputStream()).writeInt(1);

                            // new Thread(new PlayerHandler(main_User, invited_User_Socket)).start();
                            // countDownLatch.countDown();
                        } else {

                        }

                        break;
                    case "Game":
                        int countTurn;

                        System.out.println("Gamessssssssssssss");
                        flag++;
                        System.out.println("flag: " + flag);
                        from_p1 = new DataInputStream(main_User.getInputStream());
                        from_p2 = new DataInputStream(invited_User_Socket.getInputStream());
                        to_p1 = new DataOutputStream(main_User.getOutputStream());
                        to_p2 = new DataOutputStream(invited_User_Socket.getOutputStream());
                        if (flag % 2 != 0 && flag <= 9) {
                            System.out.println("x condition ");
                            countTurn = from_p1.readInt();
                        } else {
                            System.out.println("y condition before");
                            countTurn = from_p2.readInt();
                            System.out.println("y condition after");
                        }
                      
                   
                        System.out.println("server num: " + countTurn);
                        if (countTurn == 1) {
//                                System.out.println("entered even counter: ");
                            row = from_p1.readInt();
                            col = from_p1.readInt();
//                                System.out.println("row: " + row);
//                                System.out.println("col: " + col);
                            cells[row][col] = 'X';
                            if (isWon('X')) {
                                 System.out.println("X WON ");
                               
                                to_p1.writeInt(PLAYER1_WON);
                                to_p2.writeInt(PLAYER1_WON);
                              

                                sendMove(to_p2, row, col);
                                data.insertPlayerScore(main_User_Uname, "10");
                                System.out.println("player1 " +main_User_Uname);
                                System.out.println("player1 score: " + data.getScore(main_User_Uname));
                                break; // Break the loop
                            } else if (Full()) {
                                to_p1.writeInt(DRAW);
                                to_p2.writeInt(DRAW);
                                sendMove(to_p2, row, col);
                                break;
                            } else {
                                to_p2.writeInt(CONTINUE);
                                sendMove(to_p2, row, col);
                            }

                        } else {
                            System.out.println("entered odd counter: ");
                            row = from_p2.readInt();
                            col = from_p2.readInt();
//                                System.out.println("rowww:" + row);
//                                System.out.println("collll:" + col);
                            cells[row][col] = 'O';

                            if (isWon('O')) {
                                 System.out.println("o WON ");
                                to_p1.writeInt(PLAYER2_WON);
                                to_p2.writeInt(PLAYER2_WON);
                                sendMove(to_p1, row, col);
                                data.insertPlayerScore(invited_User, "10");
                                System.out.println("player2 score: " + data.getScore(invited_User));
                                break;
                            } else {
                                to_p1.writeInt(CONTINUE);
                                sendMove(to_p1, row, col);
                            }

                        }
                        break;
                    default:
                        System.out.println("Default");
                }
            }
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (x) {
//                        try {
//                            if (acceptedInv == true) {
//
//                                System.out.println("checkiinggggggg...");
//                            }
//
//                            //countDownLatch.await();
////                        for (Clients f : twoPlayers) {
////                            toClient = new DataOutputStream(f.getUser_socket().getOutputStream());
////                            toClient.writeUTF("accepted,");
////                        }
////                        System.out.println("checkiinggggggg...");
////                            new DataOutputStream(invited_User_Socket.getOutputStream()).writeUTF("accepted,");
////                            new DataOutputStream(main_User.getOutputStream()).writeUTF("accepted,");
////                            String n = fromClient.readUTF();
////                            if (n.contains("getToken")) {
////                                System.out.println("starting...");
////                                new DataOutputStream(invited_User_Socket.getOutputStream()).writeInt(2);
////                                new DataOutputStream(main_User.getOutputStream()).writeInt(1);
////                            }
//                        } catch (Exception e) {
//
//                        }
//
//                    }
//                }
//            }).start();

        } catch (IOException e) {
        }
    }

    public void sendMove(DataOutputStream out, int row, int column) throws IOException {
        out.writeInt(row);
        out.writeInt(column);
    }

    public boolean Full() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWon(char token) {
        for (int i = 0; i < 3; i++) {
            if ((cells[i][0] == token)
                    && (cells[i][1] == token)
                    && (cells[i][2] == token)) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if ((cells[0][j] == token)
                    && (cells[1][j] == token)
                    && (cells[2][j] == token)) {
                return true;
            }
            if ((cells[0][0] == token)
                    && (cells[1][1] == token)
                    && (cells[2][2] == token)) {
                return true;
            }
            if ((cells[0][2] == token)
                    && (cells[1][1] == token)
                    && (cells[2][0] == token)) {
                return true;
            }
        }
        return false;
    }
}
