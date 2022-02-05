
package serverside;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
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
import sun.security.tools.keytool.Main;

public class TicTacToeServer extends Application implements TicTacToeConstants{
    
         private int sessionNo = 1;
         private int flag = -1;
         private boolean server_status = false;
         private FXMLLoader loader;
         public serverController controller;
         mainThreadForPlayers mainthread = new mainThreadForPlayers();


         public void start(Stage stage) throws Exception{ 
         loader = new FXMLLoader();
         loader.setLocation(Main.class.getResource("/serverside/GUI_server.fxml"));
         Parent root = loader.load();
         controller = loader.getController();
         Scene scene = new Scene(root, 600,550);
         stage.setTitle("TicTacToeServer");
         stage.setScene(scene);
         stage.show();

           
           controller.start.setOnAction(new EventHandler <ActionEvent>(){
           public void handle(ActionEvent e){
                  if(flag == -1)
                  {
                      mainthread.start();
                  }
                  else if(flag == 0)
                  {
                     mainthread = new mainThreadForPlayers();
                     mainthread.start();
                  }
                  flag = 1;
                }
            });

            controller.stop.setOnAction(new EventHandler <ActionEvent>(){
           public void handle(ActionEvent e){
                  if(flag == 1)
                  {
                        Platform.runLater(()->controller.labStatus.setText("server stoped !!!!!!!!!"));
                        try{mainthread.serverSocket.close();} catch(IOException ex){ex.printStackTrace();}
                        mainthread.stop();
                        flag =0;
                  }
                }
            });
              
             }


             



public class mainThreadForPlayers extends Thread {

       
       
       ServerSocket serverSocket;
       public void run(){
          try{

                   serverSocket = new ServerSocket(8000);
                   Platform.runLater(()->controller.labStatus.setText(new Date()+":server Started at socket 8000\n"));

                   while(true){
                       Socket player1 = serverSocket.accept();
                    
                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
                    
                    Socket player2 = serverSocket.accept();

                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    new Thread(new PlayerHandler(player1,player2)).start();

                    }
               }
               catch(IOException ex){ex.printStackTrace();}
        }
    
}

         
}

