
package XO2Player;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class XO2PlayerController {
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
  
  public String turn = new String("X");
  
  public String winner;
  
  public void printImg(MouseEvent mouseEvent) {

    Object clickedLabel = mouseEvent.getSource();

    if (((Label)clickedLabel).getText() == "") {

      ((Label)clickedLabel).setText(this.turn);
      this.turn = this.turn.equals("X") ? "O" : "X";
      winner = checkWinner();
      if(winner == "X" || winner == "O")
       {
          label.setText("Congratulation!!! The Winner Is : "+winner);
       }
       else if(winner == "-1")
       {
             label.setText("No Winner");
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
    if (this.img0.getText() != "" && this.img1.getText() != "" && this.img2.getText() != "" && this.img3.getText() != "" && 
        this.img4.getText() != "" && this.img5.getText() != "" && this.img6.getText() != "" && this.img7.getText() != "" && 
        this.img8.getText() != "")
      return "-1"; 
    return null;
  }
}
