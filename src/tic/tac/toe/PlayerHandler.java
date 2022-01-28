/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class PlayerHandler implements Runnable, TicTacToeConstants {
     Socket player1;
     Socket player2;
     char [][] cells = new char[3][3];
     DataInputStream from_p1;
     DataInputStream from_p2;
     DataOutputStream to_p1;
     DataOutputStream to_p2;
    
     PlayerHandler(Socket player1, Socket player2){
        this.player1 = player1;
        this.player2 = player2 ;
       
        for(int i = 0; i < 3; i++){
           for(int j =0; j < 3 ; j++){
              cells[i][j] = ' ';
           }
        }
     }
  
     @Override
     public void run(){
        try{
           from_p1 = new DataInputStream(player1.getInputStream());
           from_p2 = new DataInputStream(player2.getInputStream());
           to_p1 = new DataOutputStream(player1.getOutputStream());
           to_p2 = new DataOutputStream(player2.getOutputStream());

           to_p1.writeInt(1);
           while(true){
             int row = from_p1.readInt();
             int col = from_p1.readInt();
             cells[row][col] = 'X';
         
             
           }
        }catch(IOException e){e.printStackTrace();}
            
     }
    public void sendMove(DataOutputStream out, int row, int column) throws IOException {
              out.writeInt(row); 
              out.writeInt(column); 
    }
  
    public void Full(){}
     
}
