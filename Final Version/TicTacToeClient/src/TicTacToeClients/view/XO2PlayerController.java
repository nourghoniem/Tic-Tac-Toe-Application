
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
  public boolean createArr = false;
  public ArrayList<ArrayList<Label>>arr = new ArrayList<>(rowsNum) ;
  
  
  
  public void initialize() {
     arr.add(new ArrayList<>(Arrays.asList(lab0, lab1, lab2)));
     arr.add(new ArrayList<>(Arrays.asList(lab3, lab4, lab5)));
     arr.add(new ArrayList<>(Arrays.asList(lab6, lab7, lab8)));
}


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


public void printToken(String c)
{
      token = c;
      lblToken.setText(c);
}
public void printStatus(String s)
{
       status.setText(s);
}

}
