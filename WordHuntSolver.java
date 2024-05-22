import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.TreeSet;

public class WordHuntSolver{

//initialize variables
   private String[][] gameBoard;
   private int width;
   private int height;
   private boolean dictLoaded;
   private TreeSet<String> dict;
   private String filename = "words.txt";

   public WordHuntSolver() {
      width = 4;
      height = 4;
      gameBoard= new String[width][height];
      loadDict();
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
   
   public void loadDict() {
   
      dict = new TreeSet<String>();
      
      try {
         Scanner dictScan = new Scanner(new BufferedReader(new FileReader(new File(filename))));
         
         while (dictScan.hasNext()) {
            String word = dictScan.next().toUpperCase();
            dict.add(word);
            dictScan.next();
         }
         dictLoaded = true;
      }
      
      catch (java.io.FileNotFoundException e) {
         throw new IllegalArgumentException("File cannot be opened");
      } 
   
      
      System.out.println("Loaded");
   }
}
