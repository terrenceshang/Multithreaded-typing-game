/**
*<h1>WordPanel!</h1>
*@ WordPanel.java To store all the values for caught, missed and score
*@ author Zenan Shang
*@ version 1.0
*@ since 30-08-21
*/

package skeletonCodeAssgnmt2;

import skeletonCodeAssgnmt2.WordApp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {
		public static volatile boolean done;
		private int noWords;
		private int maxY;
      private WordRecord[] words;
/**
*This paintComponent method is used to set up the graphics of the game
*@param g is the graphic of the app
*/
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);   
		    g.fillRect(0,maxY-10,width,height);
		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		    
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		   
		  }
/**
*Constructor WordPanel
*@param words, the words for the dictionary
*@param maxY, the maximum value for y
*/		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;		
		}
/**
run method for starting threading of the program
*@return nothing
*/		
		public void run() {
			//add in code to animate this
         while (!done) {
            try {
			   	Thread.sleep(100);
			   } catch (Exception e) {
				// TODO: handle exception
			   }
			
			   for (int i = 0; i < words.length; i++) {
				
				   Integer ds =  (int) (words[i].getSpeed());
               int temp = ds / 50;
               ds = ds / temp / 10 + (int) (Math.random() * (10) + 1);
               
				   words[i].drop(ds);
				   repaint();
				
				   if(words[i].dropped()) {
				   	words[i].resetWord();
				   	WordApp.score.missedWord();
				   	WordApp.missed.setText("Missed: " + WordApp.score.getMissed());
				   }
		   	}
			   if (WordApp.totalWords == WordApp.score.getTotal()) {
			   	done = true;
			   }
		   }
      }

}


