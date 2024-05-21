import java.io.FileReader;
import java.io.File;

public class WordHuntSolver{

//initialize variables
   private String[][] gameBoard;
   private int width;
   private int height;

   public WordHuntSolver() {
      width = 4;
      height = 4;
      gameBoard= new String[width][height];
   }
   
   
   //this allows the game board to be set by the user
   public void setBoard(String[] letters) {
      if (letters.length != 16) {
         return;
      }
      int index = 0;
      for (int i=0; i<4; i++) {
         for (int j=0; j<4; j++) {
            gameBoard[i][j] = letters[index];
            index++;
         }
      }
   }
}
