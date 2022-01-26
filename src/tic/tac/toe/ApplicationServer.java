/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author nghon
 */
public class ApplicationServer  {
      ServerSocket server_socket;
      Socket inner_socket;
      DataInputStream in;
      PrintStream p;
      ArrayList<PlayerHandler> players = new ArrayList<>();
      ExecutorService pool = Executors.newFixedThreadPool(4);
      boolean listening = true;
      public ApplicationServer() throws IOException{
            
            try{
               server_socket = new ServerSocket(9090);
            }catch(IOException e){e.printStackTrace();}
               while(listening){
                        // System.out.println("[Waiting for a Client...]");
                         inner_socket = server_socket.accept();
                         System.out.println("[Accepted]");
                         PlayerHandler player = new PlayerHandler(inner_socket);
                         players.add(player);
                         pool.execute(player);   
                }
               server_socket.close();

}
      public static void main(String[] args) throws IOException {
        ApplicationServer server = new ApplicationServer();
    }
}
