
package Handling2Players;



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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import sun.security.tools.keytool.Main;
public class TicTacToeClient extends Application implements TicTacToeConstants{


     private String myToken =" ";    
     private String otherToken = " ";
     private int rowSelected;
     private int columnSelected;
     private DataInputStream fromServer;
     private DataOutputStream toServer;
     private boolean continueToPlay = true;

     private String host = "localhost";
     private FXMLLoader loader;
     private XO2PlayerController controller;
     public void start(Stage stage) throws Exception {
         
//    Parent root = FXMLLoader.<Parent>load(getClass().getResource("/GamePage/GUI.fxml"));       

      
//    FXMLLoader loader = new FXMLLoader();
    loader = new FXMLLoader();
    loader.setLocation(Main.class.getResource("/TicTacToeClients/view/GUI.fxml"));
    Parent root = loader.load();
    

    Scene scene = new Scene(root);
    controller = loader.getController();
    stage.setScene(scene);
    stage.show();
    connectToServer();
  }
     
       private void connectToServer(){
       try{
          Socket socket = new Socket(host , 8000);
          
          fromServer = new DataInputStream(socket.getInputStream());
          toServer = new DataOutputStream(socket.getOutputStream());

      }
      catch (Exception ex){ ex.printStackTrace();}
      
     new Thread(()-> {
        
         try{
                
                int player = fromServer.readInt();
                if(player == PLAYER1)
                {
                    
                    myToken = "X";
                    otherToken = "O";

                    Platform.runLater(()->{
                         
                          controller.printToken(myToken);
                          controller.printStatus("Wating for player 2 to join");     
                    });
                    
                    fromServer.readInt();
                    
                    Platform.runLater(()->
                           controller.printStatus("player2 has joined, i start"));
//                    myTurn = true;
                   controller.turn = true;

                 }
                else if (player == PLAYER2)
                {
                       myToken = "O";
                       otherToken = "X";
                       Platform.runLater(()-> {
                          controller.printToken(myToken);
                          controller.printStatus("Wating for player 1 to move");
                         
                        });
                }
                while(continueToPlay){
                      if(player == PLAYER1){
                          waitForPlayerAction();
                          sendMove();
                          checkInfo();
                          
                      }
                      else if(player == PLAYER2)
                      {
                           checkInfo();
                           waitForPlayerAction();
                           sendMove();
                      }
                }
            }
         catch(Exception ex){ex.printStackTrace();}
    }).start();
}
       private void waitForPlayerAction() throws InterruptedException{
          while(controller.waitingForoccurAction){

              Thread.sleep(100);
        }
        controller.waitingForoccurAction = true;
}
private void sendMove(){
         try {
             toServer.writeInt(controller.selectedRow);
             toServer.writeInt(controller.selectedCol);
         } catch (IOException ex) {
             Logger.getLogger(TicTacToeClient.class.getName()).log(Level.SEVERE, null, ex);
         }
    
}

private void checkInfo() throws IOException{
     int status = fromServer.readInt();

     if(status == PLAYER1_WON)
     {
         continueToPlay = false;
         if(myToken == "X"){
           Platform.runLater(()-> controller.printStatus("i won! (X)"));
         }
         else if (myToken == "O"){
           Platform.runLater(()-> controller.printStatus("player 1 (x) has won!"));
           receiveMove();
         }    
     }
     else if(status == PLAYER2_WON)
     {
           continueToPlay = false;
           if(myToken == "O"){
           Platform.runLater(()-> controller.printStatus("i won! (O)"));
         }
         else if (myToken == "X"){
           Platform.runLater(()-> controller.printStatus("player 2 (O) has won!"));
           receiveMove();
         }  
     }
     else if(status == DRAW){
        continueToPlay = false;
        Platform.runLater(()-> controller.printStatus("Game is Over , no winner"));
        if (myToken == "O"){
             receiveMove();
        }
     }
     else{
        receiveMove();
        Platform.runLater(()->controller.printStatus("My turn"));
        controller.turn = true;

     }
}
private void receiveMove() throws IOException{
           
           int row = fromServer.readInt();
           int column = fromServer.readInt();
           Platform.runLater(()-> controller.arr.get(row).get(column).setText(otherToken));
        }     
}

