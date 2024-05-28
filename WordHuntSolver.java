import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Comparator;
import java.util.Collections;

public class WordHuntSolver{

//initialize variables
   private String[][] gameBoard;
   private boolean[][] visited;
   private int width;
   private int height;
   private boolean dictLoaded;
   public TreeSet<String> dict;
   private String filename = "words.txt";
   private ArrayList<String> scorableWords;

   public WordHuntSolver() {
      width = 4;
      height = 4;
      gameBoard = new String[width][height];
      visited = new boolean[width][height];
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
   
   //loads in the dictionary
   public void loadDict() {
      dict = new TreeSet<String>();
      
      try {
         Scanner dictScan = new Scanner(new BufferedReader(new FileReader(new File(filename))));
         //read in each word in txt file
         while (dictScan.hasNext()) {
            String word = dictScan.next();
            word = word.toUpperCase();
            if (word.length() >= 3) {
               dict.add(word);
            }
         }
         dictLoaded = true;
      }
      
      catch (java.io.FileNotFoundException e) {
         throw new IllegalArgumentException("File cannot be opened");
      } 
   
      //just for me to see that it actually loaded
      System.out.println("Loaded");
   }
   
   
   //runs dfs on each index in the gameboard
   //returns an array list of scorable word 
   public ArrayList<String> wordsOnBoard() {
      scorableWords = new ArrayList<String>();
      String current = "";
      
      for (int i=0; i<width; i++) {
         for (int j=0; j<height; j++) {
         //set current string to current index
            current = gameBoard[i][j]; 
         //test if it is a valid prefix
            if (validPrefix(current)) {
               searchForWords(i, j, current);
            }
            markAllUnvisited();
            
         }
      }
   
      Collections.sort(scorableWords, new ScoreComparator());
      return scorableWords;
   } 
   
   //test if the current string is a valid prefix to any words in the dictionary
   public boolean validPrefix(String prefix) {
   
   //ceiling function returns the smaller value in the set that is greater than the word
      String word = dict.ceiling(prefix);
      
      //if the ceiling of the word does not exist return false
      if (word == null) {
         return false;
      }
      
      //return whether the ceiling starts with the prefix
      return word.startsWith(prefix);
   }
   
   public void searchForWords(int x, int y, String current) {
   //start off by marking the starting index as visited
      visited[x][y] = true;
     //stack up the neighbors
      Stack<Block> neighbors = new Stack<Block>();
      for (int i=-1; i<2; i++) {
         for (int j=-1; j<2; j++) {
            if (x+i>=0 && x+i<=3 && y+j>=0 && y+j<=3 && !visited[x+i][y+j]) {
               Block temp = new Block(x+i, y+j);
               neighbors.push(temp);
            }
         }
      }
      while (!neighbors.isEmpty()) {
      
         Block last = neighbors.peek();
         if(!visited[last.x][last.y]) {
         //add letter to string if it is not in the path already
            String test = current + last.letter;
         //if in the dictionary add to scorable words
            if (dict.contains(test) && !scorableWords.contains(test)) {
               scorableWords.add(test);
            }
         //if it is a vild prefix search for words with that as a starting point
            if (validPrefix(test)) {
               searchForWords(last.x, last.y, test);
            }
         }
         //remove from neighbors once searching is done
         neighbors.pop();
      }
      
      //end of recursive call, should remove last tried endpoint
      visited[x][y] = false;
   }
   
   //mark every spot as unvisited
   public void markAllUnvisited() {
      visited = new boolean[width][height];
   }
   
   
   //this is supposed to represent a block on the gameboard
   private class Block{
      private int x;
      private int y;
      private String letter;
      
      public Block(int x, int y) {
         this.x = x;
         this.y = y;
         this.letter = gameBoard[x][y];
      }
      
   }
   
   //comparator to return the largest words first
   private class ScoreComparator implements java.util.Comparator<String> {
   
      public ScoreComparator() {
         super();
      }
      
      public int compare(String string1, String string2) {
         return string2.length() - string1.length();
      }
   }
}
