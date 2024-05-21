import java.io.FileReader;
import java.io.File;

public class WordHuntSolver{

//initialize variables
   private String[][] gameBoard;
   private int width;
   private int height;

   public WordHuntSolver() {
      initializeBoard();
      width = gameBoard.length;
      height = width;
   }
   
   //this sets the game board to be null in all indexes
   public void initializeBoard() {
      for (int i=0; i<4; i++) {
         for (int j=0; j<4; j++) {
            gameBoard[i][j] = null;
         }
      }
   }
   
   //this allows the game board to be set by the user
   public void setBoard(String[] letters) {
      if (letters.length != 16) {
         return;
      }
      for (int i=0; i<16; i++) {
         for (int j=0; j<4; j++) {
            for (int k=0; k<4; k++) {
               gameBoard[j][k] = letters[i];
            }
         }
      }
   }
}
