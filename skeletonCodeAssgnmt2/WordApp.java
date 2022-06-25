/**
*<h1>WordApp!</h1>
*@ WordApp.java To display and start game
*@ author Zenan Shang
*@ version 1.0
*@ since 30-08-21
*/

package skeletonCodeAssgnmt2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;
import java.util.concurrent.*;
//model is separate from the view.

public class WordApp {
//shared variables
	static int noWords=4;
	static int totalWords;
   static String[] tmpDict;
   static String name;

   static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;

	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

	static WordRecord[] words;
	static volatile boolean done;  //must be volatile
	static 	Score score = new Score();

	static WordPanel w;
	
	static JLabel missed; 
   static JLabel caught;
   static JLabel scr;  
   public static Thread thread;
	
/**
*This is the setupGUI is responsible of setting up the GUI of the app
*@param frameX is the width of the app
*@param frameY is the height of the app
*@param yLimit is the limits for y value
*/
	public static void setupGUI(int frameX,int frameY,int yLimit) {
		// Frame init and dimensions
    	JFrame frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      g.setSize(frameX,frameY);
    	
		w = new WordPanel(words,yLimit);
		w.setSize(frameX,yLimit+100);
	   g.add(w); 
	    
      JPanel txt = new JPanel();
      txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
      caught =new JLabel("Caught: " + score.getCaught() + "    ");
      scr =new JLabel("Score:" + score.getScore()+ "    ");    
      txt.add(caught);
	   missed =new JLabel("Missed:" + score.getMissed()+ "    ");
	   txt.add(scr);
      txt.add(missed);
	    //[snip]
  
	   final JTextField textEntry = new JTextField("",20);
	   textEntry.addActionListener(new ActionListener()
	   {
	      public void actionPerformed(ActionEvent evt) {
	         String text = textEntry.getText();
	          //[snip]
            for (int i = 0; i<words.length; i++) {
               if (words[i].matchWord(text)){
                  score.caughtWord(text.length());
                  scr.setText("Score:" + score.getScore()+" ");
                  caught.setText("Caught:"+WordApp.score.getCaught()+" ");
               }
            } 
            
	         textEntry.setText("");
	         textEntry.requestFocus();
            
	      }
	   });
	   
	   txt.add(textEntry);
	   txt.setMaximumSize( txt.getPreferredSize() );
	   g.add(txt);
	    
	   JPanel b = new JPanel();
      b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	   JButton startB = new JButton("Start");;
		
			// add the listener to the jbutton to handle the "pressed" event
		startB.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            textEntry.setText("");         
		      textEntry.requestFocus();  //return focus to the text entry field
            thread = new Thread(w);
            WordPanel.done=false;
            thread.start();
		   }
		});
		JButton endB = new JButton("End");;
			
				// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            WordPanel.done=true;

            caught.setText("Caught: "+WordApp.score.getCaught()+"    ");
            missed.setText("Missed:"+WordApp.score.getMissed()+"    ");
            scr.setText("Score:" + score.getScore()+"    ");
            

	      	int x_inc=(int)frameX/noWords;
	      	//initialize shared array of current words

		      for (int i=0;i<noWords;i++) {
	      		words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
	      	}
            
		      score.resetScore();
            textEntry.setText("");
	         textEntry.requestFocus();
         }
		});
      
      JButton haltB = new JButton("Pause");
		
      haltB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            WordPanel.done=true;
         }
      });

      JButton quitB = new JButton("Quit");
		
      quitB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            System.exit(0);
         }
      });
      
      JButton HowB = new JButton("Info");
		
      HowB.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Hey!!!!!!!!! Welcome to this game. So the rules are simple. \n you press start to start the game, when the game is started, \n you type the words displayed on the screen and press enter when you finish typing the word. \n The game will end the all the words are either caught or missed. \n Additional feature: You can increase the difficulty of the game by pressing the start button multiple times!!!!!! Enjoy");
         }
      });
                    
		b.add(HowB);
		b.add(startB);
      b.add(haltB);
		b.add(endB);
		b.add(quitB);
		g.add(b);
    	
      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      frame.setContentPane(g);     
       	//frame.pack();  // don't do this - packs it into small space
      frame.setVisible(true);
	}
/**
*This is getting getting data from text files
*@param filename is the name of the file
*@return returns an array that represents all data provided by the text file
*@Exception IOException On input error.
*@see IOException
*/
   public static String[] getDictFromFile(String filename) {
		String [] dictStr = null;
		try {
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			//System.out.println("read '" + dictLength+"'");

			dictStr=new String[dictLength];
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
				//System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + filename + " default dictionary will be used");
	    }
		return dictStr;
	}
/**
*This is the main method of the program
*@param args used for receiving instructions with the format <total words> <words on screen> <dictionary file>
*@return nothing
*/
	public static void main(String[] args) {
    	
		//deal with command line arguments
		totalWords=Integer.parseInt(args[0]);  //total words to fall
		noWords=Integer.parseInt(args[1]); // total words falling at any point
		assert(totalWords>=noWords); // this could be done more neatly
      name = args[2];
		tmpDict=getDictFromFile(args[2]); //file of words
		if (tmpDict!=null)
			dict= new WordDictionary(tmpDict);
		
		WordRecord.dict=dict; //set the class dictionary for the words.
		
		words = new WordRecord[noWords];  //shared array of current words
		
		//[snip]
		
		setupGUI(frameX, frameY, yLimit);  
    	//Start WordPanel thread - for redrawing animation

		int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

		for (int i=0;i<noWords;i++) {
			words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
		}
	}
}