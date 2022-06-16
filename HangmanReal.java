import java.awt.*;
import java.io.*;
import java.util.*;
public class HangmanReal  {
   public static void main(String[] args) throws FileNotFoundException {
      DrawingPanel panel =  new DrawingPanel(650,650);
      Graphics g = panel.getGraphics();
      Scanner console = new Scanner(System.in);
      
      Intro();
      double losses = 0;
      double wins = 0;
      double winPercent = 0;
      Newgame(g, console, losses, wins, winPercent);
   }
   public static void Newgame(Graphics g, Scanner console, double losses, double wins, double winPercent) throws FileNotFoundException {
      if (wins >=1 || losses >= 1) {  
         System.out.println("Your record is " + wins + " wins and " + losses + " losses.");
         double games = wins + losses;
         winPercent = (wins / games) * 100.0;
         System.out.println("Your win percentage is " + winPercent + "%.");
      }
      ArrayList<String> allWords = new ArrayList<String>();
      Scanner input = new Scanner(new File("hangamn.txt"));
      while (input.hasNext()) {
         String word = input.next();
         allWords.add(word);
        
      }
   
      Random rand = new Random();
      int j = rand.nextInt(2200);
      String gameWord = allWords.get(j);
      
      
      Drawer(g);
      Wordy(gameWord, console, g, losses, wins, winPercent);
      //Stats();  
   
   }
   public static void Intro() {
      System.out.print("Hello!");
      System.out.println("This game will test your wits and ability to problem solve!");
      System.out.println("This game is centered around the classic model of hangman that gets harder as you continue to solve the puzzles.");
      System.out.println("Playing this game is pretty simple, simply follow the instructions below:");
      System.out.println();
      System.out.println("1.) Guess a letter");
      System.out.println("2.) See if the letter guessed is in the  mystery word. If it is not found in the word, a body part will be added to the man");
      System.out.println("once the entire body of the man is drawn, you lose the game.");
      System.out.println("3.) Repeat the previous steps until the word has been found or the man is completed");
      System.out.println("4.) If the letter is found you willl move on to the next, harder level. If the level is not found, the game will end.");
   
   }
   
   public static void Drawer(Graphics g) {
      //draws the gallows, the letters the player guesses, or crosses them out of the wordbank  
      g.drawLine(400, 330, 400, 50);
      g.drawLine(270, 50, 400, 50); 
      g.drawLine(270, 50, 270, 110);
      g.drawLine(300, 330, 500, 330);
      g.drawString("LETTER BANK", 265, 500);
      g.drawLine(260, 505, 350, 505);
      g.drawLine(25, 515, 625, 515);
      g.drawLine(25, 515, 25, 640);
      g.drawLine(625, 515, 625, 640);
      g.drawLine(25, 640, 625, 640);
      int y = 0;
      int x = 0;
      for(int a = 0; a <=25; a++) {
         String[] Letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
         int v = a % 13;
         if(v == 0){
            x = 0;
            y++;
         }
         else { 
            x++;
         }
         String h = Letters[a];
         g.drawString(h, 45 * x + 50, 45 * y + 515);
      }
   }
   public static void Guesses(Scanner console, Graphics g, String gameWord, ArrayList<String> Word, int l, double losses, double wins, double winPercent) throws FileNotFoundException {
      int q = Word.size();
      for(int y = 0; y < 5; y++) {
         int z = 310 - (5 * 15) + (y * 45);
         g.drawLine(z, 360, z + 30, 360);
      }
      int m = 0;
      int t = 0;
      int lll = 0;
      for(int x = 1; x <= 26; x++) {
         int c = 0;
         if(t == 0) {
            for(int i = 1; i <= 26; i++) {
               c = 0;
               System.out.println("What is your guess?");
               String a = console.nextLine();
               int yy = 0;
               int xx = 0;
               int vv = 0;
               for(int uu = 0; uu <=25; uu++) {
                  String[] Letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
               String ww = Letters[uu];
               vv = uu % 13;
               if(vv == 0) {
                  xx = 0;
                  yy++;
               }
               else { 
                  xx++;
               }
               if (a.equals(ww)) {
                  g.setColor(Color.WHITE);
                  g.fillRect(45 * xx + 40, 45 * yy + 505, 20, 20);
                  g.setColor(Color.BLACK);
                  
                     
               }
            }              
               for(int u = 0; u < q; u++) {
                  String r = Word.get(u);
                  if(a.equals(r)) {
                     //draw letters and cross them out in the word bank
                     for(int b = 0; b <= gameWord.length()-1; b++) {
                        char p = gameWord.charAt(b);
                        String e = String.valueOf(p);
                        if (e.equals(a)) {
                           g.drawString(a, 321 - (q * 15) + (b * 45), 360);
                        }
                     }
                     lll++;
                     u = 10;
                     c = 1;
                     if (l == lll) {
                        System.out.println("You won, congratulations!");
                        u = 100;
                        i = 100;
                        x = 100;
                        System.out.println("Would you like to play again?");
                        String p = "yes";
                        String pp = "no";
                        String ppp = console.nextLine();
                        if(ppp.equals(p)) {
                           g.setColor(Color.WHITE);
                           g.fillRect(220, 110, 100, 170);
                           g.fillRect(227, 336, 250, 30);
                           g.setColor(Color.BLACK);
                           wins++;
                           Newgame(g, console, losses, wins, winPercent);
                        }
                     }
                  }
               } 
               if (c == 0) {
                  if(m == 0) {
                     g.drawOval(245, 110, 50, 50);
                     m++;
                  }
                  else if(m == 1) {
                     g.drawLine(270, 160, 270, 230);
                     m++;
                  }
                  else if(m == 2) {
                     g.drawLine(240, 170, 270, 200);
                     m++;
                  }
                  else if(m == 3) {
                     g.drawLine(300, 170, 270, 200);
                     m++;
                  }
                  else if(m == 4) {
                     g.drawLine(270, 230, 300, 260);
                     m++;
                  }
                  else if(m == 5) {
                     g.drawLine(270, 230, 240, 260);
                     m++;
                     System.out.println("You lost, sorry.");
                     x = 27;
                     i = 100;
                     System.out.println("The word was " + gameWord + ".");
                     System.out.println("Would you like to play again?");
                     String p = "yes";
                     String pp = "no";
                     String ppp = console.nextLine();
                     if(ppp.equals(p)) {
                        g.setColor(Color.WHITE);
                        g.fillRect(220, 110, 100, 170);
                        g.fillRect(227, 336, 250, 30);
                        g.setColor(Color.BLACK);
                        losses++;
                        Newgame(g, console, losses, wins, winPercent);
                     }
                  }
               }
            }
         }
         else if (t == 1) {
            System.out.println("You got the word, congratulations!");
            x = 100;
         }
         if (l == 5) {
            t = 1;
         }
      }
   }  
   public static void Wordy(String gameWord, Scanner console, Graphics g, double losses, double wins, double winPercent) throws FileNotFoundException {
      //gets the word and counts the letters
      String[] Letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
      ArrayList<String> Word = new ArrayList<String>();
      int l = 0;
      for(int j = 0; j <= 25; j++) {
         String q = Letters[j];
         for(int b = 0; b <= gameWord.length()-1; b++) {
            char c = gameWord.charAt(b);
            String e = String.valueOf(c);
            if (e.equals(q)) {
               l++;
               b = 99999;
               Word.add(e);
            }
         }            
      }
      Guesses(console, g, gameWord, Word, l, losses, wins, winPercent);
   }
}