public class WordHuntSolver{

//initialize variables
   private String[][] gameBoard;
   private int width;
   private int height;

   public WordHuntSolver() {
      initializeBoard(gameBoard);
      width = gameBoard.length;
      height = width;
   }
   
   public void initializeBoard(String[][] gameBoard) {
      for (int i=0; i<4; i++) {
         for (int j=0; j<4; j++) {
            gameBoard[i][j] = null;
         }
      }
   }
}
