/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Handling2Players;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author nghon
 */
public class PlayerHandler {

    private static Socket socket;
    private static DataInputStream fromServer;
    private static ObjectInputStream fromServerObj;
    private static ObjectOutputStream toServerObj;
    private static DataOutputStream toServer;
    private static String host = "localhost";
    private static Integer port = 8000;
    
    public PlayerHandler(){
          try{
          socket = new Socket(host , 8000);
          fromServer = new DataInputStream(socket.getInputStream());
          toServer = new DataOutputStream(socket.getOutputStream());
        }catch(Exception e){}
    }
     public static ObjectInputStream getFromServerObj() {
        return fromServerObj;
    }

    public static ObjectOutputStream getToServerObj() {
        return toServerObj;
    }
    public static String getHost() {
        return host;
    }


    public static Socket getSocket() {
        return socket;
    }

    public static DataInputStream getFromServer() {
        return fromServer;
    }

    public static DataOutputStream getToServer() {
        return toServer;
    }

}
