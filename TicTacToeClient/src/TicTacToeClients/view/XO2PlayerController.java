
package TicTacToeClients.view;




import java.util.ArrayList;
import java.util.Arrays;
import Handling2Players.TicTacToeConstants;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class XO2PlayerController implements TicTacToeConstants{

  public Label status;
  public Label lblToken;
  public Button start;

  public Label lab0;
  public Label lab1;
  public Label lab2;
  public Label lab3;
  public Label lab4;
  public Label lab5;
  public Label lab6;
  public Label lab7;
  public Label lab8;
  

  public String token;
  public int rowsNum = 3;
  public boolean waitingForoccurAction = true;
  public int selectedRow;
  public int selectedCol;
  public boolean turn =false;
  public ArrayList<ArrayList<Label>>arr = new ArrayList<>(rowsNum) ;
  
  
  public void StartPlaying(ActionEvent event) {

     arr.add(new ArrayList<>(Arrays.asList(lab0, lab1, lab2)));
     arr.add(new ArrayList<>(Arrays.asList(lab3, lab4, lab5)));
     arr.add(new ArrayList<>(Arrays.asList(lab6, lab7, lab8)));
}
  
//  public void printImg(MouseEvent mouseEvent) {
//    Object clickedLabel = mouseEvent.getSource();
//
//    if (((Label)clickedLabel).getText() == "" && turn) {
//
//      ((Label)clickedLabel).setText(this.token);
//       waitingForoccurAction = false;
//       selectedRow = 1;
//       selectedCol = 1;
//       turn = false;
//       status.setText("Waiting for the other player to move");
//       
//    } 
//  }
  
public void printLab0(MouseEvent mouseEvent) {

  if (lab0.getText() == "" && turn) {
      lab0.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 0;
      selectedCol = 0;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab1(MouseEvent mouseEvent) {
     if (lab1.getText() == "" && turn) {
      lab1.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 0;
      selectedCol = 1;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab2(MouseEvent mouseEvent) {

     if (lab2.getText() == "" && turn) {
      lab2.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 0;
      selectedCol = 2;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab3(MouseEvent mouseEvent) {

    if (lab3.getText() == "" && turn) {
      lab3.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 1;
      selectedCol = 0;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab4(MouseEvent mouseEvent) {

   if (lab4.getText() == "" && turn) {
      lab4.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 1;
      selectedCol = 1;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab5(MouseEvent mouseEvent) {

      if (lab5.getText() == "" && turn) {
      lab5.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 1;
      selectedCol = 2;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab6(MouseEvent mouseEvent) {
    if (lab6.getText() == "" && turn) {
      lab6.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 2;
      selectedCol = 0;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab7(MouseEvent mouseEvent) {
   
    if (lab7.getText() == "" && turn) {
      lab7.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 2;
      selectedCol = 1;
      status.setText("Waiting for the other player to move");
   }
}
public void printLab8(MouseEvent mouseEvent) {

     if (lab8.getText() == "" && turn) {
      lab8.setText(this.token);
      waitingForoccurAction = false;
      turn = false;
      selectedRow = 2;
      selectedCol = 2;
      status.setText("Waiting for the other player to move");
   }
}


//  public int checkWinner() {
//    String line = null;
//    for (int a = 0; a < 8; a++) {
//      switch (a) {
//        case 0:
//          line = this.lab0.getText() + this.lab1.getText() + this.lab2.getText();
//          break;
//        case 1:
//          line = this.lab3.getText() + this.lab4.getText() + this.lab5.getText();
//          break;
//        case 2:
//          line = this.lab6.getText() + this.lab7.getText() + this.lab8.getText();
//          break;
//        case 3:
//          line = this.lab0.getText() + this.lab3.getText() + this.lab6.getText();
//          break;
//        case 4:
//          line = this.lab1.getText() + this.lab4.getText() + this.lab7.getText();
//          break;
//        case 5:
//          line = this.lab2.getText() + this.lab5.getText() + this.lab8.getText();
//          break;
//        case 6:
//          line = this.lab0.getText() + this.lab4.getText() + this.lab8.getText();
//          break;
//        case 7:
//          line = this.lab2.getText() + this.lab4.getText() + this.lab6.getText();
//          break;
//      } 
//      if (line.equals("XXX"))
//        return PLAYER1_WON; 
//      if (line.equals("OOO"))
//        return PLAYER2_WON; 
//    } 
//
//    for(int i =0; i<3; i++)
//    {  
//       int flag = 0;
//       for(int j=0; j<3; j++)
//       {
//          if(arr.get(i).get(j).getText()=="")
//          {
//            flag =1;
//            break;
//          }
//       }
//       if(flag == 1){break;}
//       else if(i == 2){return DRAW;}
//    }
//    return CONTINUE;
//  }

public void printToken(String c)
{
      token = c;
      lblToken.setText(c);
}
public void printStatus(String s)
{
       status.setText(s);
}
//public boolean playerAction()
//{ 
//    boolean flag = waitingForoccurAction;
//    waitingForoccurAction = false;
//    return flag;
//}
//public void setMyTurn()
//{
//      turn = true;
//}
}
