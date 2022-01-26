/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class PlayerHandler extends Thread {
     Socket client;
     DataInputStream input;
     PrintStream output;
     boolean listening = true;
    
     PlayerHandler(Socket client){
      try{
       this.client = client;
       this.input = new DataInputStream(client.getInputStream());
       this.output = new PrintStream(client.getOutputStream(), true);
       start();
      }catch(IOException e){e.printStackTrace();}
   
     }
  
     @Override
     public void run(){
              try{
               while(listening){
                  String str = input.readLine();
                   while(str != null){
                      System.out.println(str);
                  }
               }
                 output.close();
                 input.close();
                 client.close();
             }catch(IOException e){e.printStackTrace();}
     } 
}
