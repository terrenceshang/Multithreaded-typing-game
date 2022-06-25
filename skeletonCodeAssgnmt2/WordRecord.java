/**
*<h1>WordRecord!</h1>
*@ WordDictionary.java to store all data relate to the falling words
*@ author Zenan Shang
*@ version 1.0
*@ since 30-08-21
*/

package skeletonCodeAssgnmt2;

public class WordRecord {
	private String text;
	private  int x;
	private int y;
	private int maxY;
	private boolean dropped;
	
	private int fallingSpeed;
	private static int maxWait=1500;
	private static int minWait=100;

	public static WordDictionary dict;
	
/**
*Constructor for WordRecord
*To set all values to default values
*/
	
	WordRecord() {
		text="";
		x=0;
		y=0;	
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}

/**
*Constructor for WordRecord
*To set word to text provided
*@param text the text of the falling word
*/
	WordRecord(String text) {
		this();
		this.text=text;
	}
	
/**
*Constructor for WordRecord
*To set word to text provided at x and maxY
*@param text the text of the falling word
*@param x the location of the word on the x-axis
*@param maxY, the location on the y axis when at rest
*/
	WordRecord(String text,int x, int maxY) {
		this(text);
		this.x=x;
		this.maxY=maxY;
	}
	
// all getters and setters must be synchronized
/**
This setY method is for setting the position of y for the word
*@param y the position of y
*/
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
/**
This setX method is for setting the position of x for the word
*@param x the position of x
*/	
	public synchronized  void setX(int x) {
		this.x=x;
	}
/**
This setWord method is for renameing of the text
*@param text the name of the word
*/	
	public synchronized  void setWord(String text) {
		this.text=text;
	}
/**
This getWord method is getting the word of the class
*@return text the name of the word for this class
*/
	public synchronized  String getWord() {
		return text;
	}
/**
This getX method is getting the location of x axis of the word
*@return x the position of the word on x axis
*/	
	public synchronized  int getX() {
		return x;
	}	
/**
This getY method is getting the location of y axis of the word
*@return y the position of the word on y axis
*/		
	public synchronized  int getY() {
		return y;
	}
/**
This getX method is getting the speed of the word
*@return fallingSpeed the speed at which the word is falling
*/		
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
/**
*This setPos method is for setting the location of the word on the app
*@param x the position on the x axis
*@param y the position on the y axis
*/
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
/**
*This resetPos method is used to set y to 0
*@return nothing
*/
	public synchronized void resetPos() {
		setY(0);
	}
/**
*This resetWord method is to get new word for the class, set all position to resting position and generating random falling speed
*/
	public synchronized void resetWord() {
		resetPos();
		text=dict.getNewWord();
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());

	}

/**
This matchWord method is to chech whether 2 words are the same or not
*@param typedText the word that is used to check
(@return true if words match and false if not
*/	
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {
			resetWord();
			return true;
		}
		else
			return false;
	}
	
/**
*This drop method is to increase the y value of the word
*@return nothing
*/
	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
	
/**
*This dropped method is to check if the word has dropped or not
*@return true if dropped else false
*/
	public synchronized  boolean dropped() {
		return dropped;
	}

}
