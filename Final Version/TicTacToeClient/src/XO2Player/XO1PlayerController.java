/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XO2Player;



import Handling2Players.PlayerHandler;
import TicTacToeClients.view.SceneController;
import XO2Player.MiniMax;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
public class XO1PlayerController {
  public Label label;
   
  public Label img0;
  
  public Label img1;
  
  public Label img2;
  
  public Label img3;
  
  public Label img4;
  
  public Label img5;
  
  public Label img6;
  
  public Label img7;
  
  public Label img8;
  @FXML
  Label score;
  public String turn = new String("X");
  public String winner;
  public int rowsNum = 3;
  public boolean flag = true;
  public boolean full = true;
  ArrayList<ArrayList<Label>>arr = new ArrayList<>(rowsNum) ;
 // DatabaseConnection database = new DatabaseConnection();
  SceneController s = new SceneController();
  String m = s.getlogin_user();
  //PlayerHandler playerHandler;
  
  String newScore;
public void initialize() {
    try {
    // playerHandler = new PlayerHandler();
     PlayerHandler.getToServer().writeUTF("OnePlayer,");
     String scores = PlayerHandler.getFromServer().readUTF();
     score.setText(scores);
     arr.add(new ArrayList<>(Arrays.asList(img0, img1, img2)));
     arr.add(new ArrayList<>(Arrays.asList(img3, img4, img5)));
     arr.add(new ArrayList<>(Arrays.asList(img6, img7, img8)));
    }catch(Exception e){}
}
  
  public void printImg(MouseEvent mouseEvent) {
    Object clickedLabel = mouseEvent.getSource();

    if (((Label)clickedLabel).getText() == "" && flag) {

      ((Label)clickedLabel).setText("X");
    //  this.turn = this.turn.equals("X") ? "O" : "X";
      winner = checkWinner();
      if(winner == "X" || winner == "O")
       {
         if(winner == "X"){
            try {
            String newScore = PlayerHandler.getFromServer().readUTF();
            score.setText(newScore);
            }catch(Exception e){}
          }
          label.setText("Congratulation!!! The Winner Is : "+winner);
           flag = false;
           full = false;
       }
       
       else if(winner == "-1")
       {
           flag = false;
           full = false;
           
             label.setText("No Winner");
       }
      
      if (full)
      {
          MiniMax.Move bestMove = MiniMax.findBestMove(arr);
       arr.get(bestMove.row).get(bestMove.col).setText("O");
        winner = checkWinner();
      if(winner == "X" || winner == "O")
       {
          label.setText("Congratulation!!! The Winner Is : "+winner);
           flag = false;
       }
       else if(winner == "-1")
       {
             label.setText("No Winner");
              flag = false;
       }
      }
    } 
  }
  
  private String checkWinner() {
    String line = null;
    for (int a = 0; a < 8; a++) {
      switch (a) {
        case 0:
          line = this.img0.getText() + this.img1.getText() + this.img2.getText();
          break;
        case 1:
          line = this.img3.getText() + this.img4.getText() + this.img5.getText();
          break;
        case 2:
          line = this.img6.getText() + this.img7.getText() + this.img8.getText();
          break;
        case 3:
          line = this.img0.getText() + this.img3.getText() + this.img6.getText();
          break;
        case 4:
          line = this.img1.getText() + this.img4.getText() + this.img7.getText();
          break;
        case 5:
          line = this.img2.getText() + this.img5.getText() + this.img8.getText();
          break;
        case 6:
          line = this.img0.getText() + this.img4.getText() + this.img8.getText();
          break;
        case 7:
          line = this.img2.getText() + this.img4.getText() + this.img6.getText();
          break;
      } 
      if (line.equals("XXX"))
        return "X"; 
      if (line.equals("OOO"))
        return "O"; 
    } 

    for(int i =0; i<3; i++)
    {  
       int flag = 0;
       for(int j=0; j<3; j++)
       {
          if(arr.get(i).get(j).getText()=="")
          {
            flag =1;
            break;
          }
       }
       if(flag == 1){break;}
       else if(i == 2){return "-1";}
    }
    return null;
  }
}