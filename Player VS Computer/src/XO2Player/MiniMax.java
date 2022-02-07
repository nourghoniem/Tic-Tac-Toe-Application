/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XO2Player;

import java.util.ArrayList;
import javafx.scene.control.Label;

/**
 *
 * @author mahmoud shokry
 */
public class MiniMax {
       // Java program to find the
// next optimal move for a player                        


static class Move
{
    int row, col;
};
 
static String player = "X", opponent = "O";
 
// This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.
static Boolean isMovesLeft(ArrayList<ArrayList<Label>> arr)
{
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (arr.get(i).get(j).getText() == "")
                return true;
    return false;
}
 
// This is the evaluation function

static int evaluate(ArrayList<ArrayList<Label>> arr)
{
    // Checking for Rows for X or O victory.
    for (int row = 0; row < 3; row++)
    {
        if (arr.get(row).get(0).getText() == arr.get(row).get(1).getText()  &&
           arr.get(row).get(1).getText() == arr.get(row).get(2).getText())
        {
            if (arr.get(row).get(0).getText() == player)
                return +10;
            else if (arr.get(row).get(0).getText() == opponent)
                return -10;
        }
    }
 
    // Checking for Columns for X or O victory.
    for (int col = 0; col < 3; col++)
    {
        if (arr.get(col).get(0).getText() == arr.get(col).get(1).getText() &&
           arr.get(col).get(1).getText() == arr.get(col).get(2).getText())
        {
            if (arr.get(col).get(0).getText() == player)
                return +10;
 
            else if (arr.get(col).get(0).getText() == opponent)
                return -10;
        }
    }
 
    // Checking for Diagonals for X or O victory.
    if (arr.get(0).get(0).getText() == arr.get(1).get(1).getText() && arr.get(1).get(1).getText() == arr.get(2).get(2).getText())
    {
        if (arr.get(0).get(0).getText() == player)
            return +10;
        else if (arr.get(0).get(0).getText() == opponent)
            return -10;
    }
 
    if (arr.get(0).get(2).getText() == arr.get(1).get(1).getText() && arr.get(1).get(1).getText() == arr.get(2).get(0).getText())
    {
        if (arr.get(0).get(2).getText() == player)
            return +10;
        else if (arr.get(0).get(2).getText() == opponent)
            return -10;
    }
 
    // Else if none of them have won then return 0
    return 0;
}
 
// This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
static int minimax(ArrayList<ArrayList<Label>> arr,
                    int depth, Boolean isMax)
{
    int score = evaluate(arr);
 
    // If Maximizer has won the game
    // return his/her evaluated score
    if (score == 10)
        return score;
 
    // If Minimizer has won the game
    // return his/her evaluated score
    if (score == -10)
        return score;
 
    // If there are no more moves and
    // no winner then it is a tie
    if (isMovesLeft(arr) == false)
        return 0;
 
    // If this maximizer's move
    if (isMax)
    {
        int best = -1000;
 
        // Traverse all cells
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (arr.get(i).get(j).getText()=="")
                {
                    // Make the move
                    arr.get(i).get(j).setText(player)  ;
 
                    // Call minimax recursively and choose
                    // the maximum value
                    best = Math.max(best, minimax(arr,
                                    depth + 1, !isMax));
 
                    // Undo the move
                   arr.get(i).get(j).setText("")  ;
                }
            }
        }
        return best;
    }
 
    // If this minimizer's move
    else
    {
        int best = 1000;
 
        // Traverse all cells
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if ( arr.get(i).get(j).getText() == "")
                {
                    // Make the move
                    arr.get(i).get(j).setText(opponent)  ;
 
                    // Call minimax recursively and choose
                    // the minimum value
                    best = Math.min(best, minimax(arr,
                                    depth + 1, !isMax));
 
                    // Undo the move
                     arr.get(i).get(j).setText ("");
                }
            }
        }
        return best;
    }
}
 
// This will return the best possible
// move for the player
static Move findBestMove( ArrayList<ArrayList<Label>> arr)
{
    int bestVal = -1000;
    Move bestMove = new Move();
    bestMove.row = -1;
    bestMove.col = -1;
 
    // Traverse all cells, evaluate minimax function
    // for all empty cells. And return the cell
    // with optimal value.
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            // Check if cell is empty
            if (arr.get(i).get(j).getText() == "")
            {
                // Make the move
                arr.get(i).get(j).setText(player)  ;
                                                         
                // compute evaluation function for this
                // move.
                int moveVal = minimax(arr, 0, false);
 
                // Undo the move
              arr.get(i).get(j).setText("") ;
 
                // If the value of the current move is
                // more than the best value, then update
                // best/
                if (moveVal > bestVal)
                {
                    bestMove.row = i;
                    bestMove.col = j;
                    bestVal = moveVal;
                }
            }
        }
    }
 
   // System.out.printf("The value of the best Move " +
                       //      "is : %d\n\n", bestVal);
 
    return bestMove;
}
}
