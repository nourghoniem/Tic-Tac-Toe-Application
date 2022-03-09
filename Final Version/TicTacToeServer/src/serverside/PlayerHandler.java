
package serverside;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import static serverside.ServerRequests.main_User;


public class PlayerHandler implements Runnable, TicTacToeConstants {
     Socket player1;
     Socket player2;
     char [][] cells = new char[3][3];
     DataInputStream from_p1;
     DataInputStream from_p2;
     DataOutputStream to_p1;
     DataOutputStream to_p2;
     boolean continueToPlay = true;
    
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
          // to_p1.writeInt(1);
           while(true){
             int row = from_p1.readInt();
             int col = from_p1.readInt();
             
            System.out.println("row: "+row);
            System.out.println("col: "+col);
             cells[row][col] = 'X';
             if (isWon('X')) {
                     to_p1.writeInt(PLAYER1_WON);
                     to_p2.writeInt(PLAYER1_WON);
                     sendMove(to_p2, row, col);
                     break; // Break the loop
             }
            else if (Full()) { 
                     to_p1.writeInt(DRAW);
                     to_p2.writeInt(DRAW);
                     sendMove(to_p2, row, col);
                     break;
            }
            else {
             to_p2.writeInt(CONTINUE);
             sendMove(to_p2, row, col);
            }
            row = from_p2.readInt();
            col = from_p2.readInt();
            cells[row][col] = 'O';
         
            if (isWon('O')) {
                to_p1.writeInt(PLAYER2_WON);
                to_p2.writeInt(PLAYER2_WON);
                sendMove(to_p1, row, col);
                 break;
            }
            else {
            to_p1.writeInt(CONTINUE);
            sendMove(to_p1, row, col);
            }
           }
        }catch(IOException e){e.printStackTrace();}
       }
   
    public void sendMove(DataOutputStream out, int row, int column) throws IOException {
              out.writeInt(row); 
              out.writeInt(column); 
    }
  
    public boolean Full(){ 
     for (int i = 0; i < 3; i++)
          for (int j = 0; j < 3; j++)
             if (cells[i][j] == ' ')
                 return false; 
     return true;
    }
    public boolean isWon(char token){
      for(int i = 0; i <3 ; i++){
         if ((cells[i][0] == token)
         && (cells[i][1] == token)
       && (cells[i][2] == token)) {
        return true;
     }
    }
    for (int j = 0; j < 3; j++){
     if ((cells[0][j] == token)
          && (cells[1][j] == token)
          && (cells[2][j] == token)) {
          return true; }
     if ((cells[0][0] == token)
          && (cells[1][1] == token)
          && (cells[2][2] == token)) {
          return true;}
     if ((cells[0][2] == token)
          && (cells[1][1] == token)
          && (cells[2][0] == token)) {
          return true;} 
    }
    return false;
    }
}
