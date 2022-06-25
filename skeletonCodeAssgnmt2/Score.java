/**
*<h1>Score!</h1>
*@ Score.java To store all the values for caught, missed and score
*@ author Zenan Shang
*@ version 1.0
*@ since 30-08-21
*/

package skeletonCodeAssgnmt2;

public class Score {
/**
*stores the number of missed words
*/
	private int missedWords;
/**
*stores the number of caught words
*/
	private int caughtWords;
/**
*stores the final score
*/
	private int gameScore;
	
/**
*Constructor for setting all values to 0
*/   
	Score() {
		missedWords=0;
		caughtWords=0;
		gameScore=0;
	}
		
	// all getters and setters must be synchronized
/** 
*this getMissed
*@return the number of words missed
*/
	public int getMissed() {
		return missedWords;
	}
/** 
*this getCaught
*@return number of words caught
*/
	public int getCaught() {
		return caughtWords;
	}
/** 
*this getTotal
*@return number of words caught and missed
*/	
	public int getTotal() {
		return (missedWords+caughtWords);
	}
/** 
*this getScore
*@return the score of the game
*/
	public int getScore() {
		return gameScore;
	}
/** 
*this missWord increase the number of missed word by 1
*@return nothing
*/	
	public void missedWord() {
		missedWords++;
	}
/** 
*this caughtWord increase the number of caught word by 1 and score by length of the word
*@return nothing
*/	
	public void caughtWord(int length) {
		caughtWords++;
		gameScore+=length;
	}
/** 
*this resetScore sets all the values to 0
*@return nothing
*/	
	public void resetScore() {
		caughtWords=0;
		missedWords=0;
		gameScore=0;
	}
}
