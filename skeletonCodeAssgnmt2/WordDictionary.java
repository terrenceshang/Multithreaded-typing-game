/**
*<h1>WordDictionary!</h1>
*@ WordDictionary.java To store all the words used for falling words
*@ author Zenan Shang
*@ version 1.0
*@ since 30-08-21
*/
package skeletonCodeAssgnmt2;

public class WordDictionary {
	int size;
/**
*stores the array for the dictionary
*/	
	static String [] theDict= {"litchi","banana","apple","mango","pear","orange","strawberry",
		"cherry","lemon","apricot","peach","guava","grape","kiwi","quince","plum","prune",
		"cranberry","blueberry","rhubarb","fruit","grapefruit","kumquat","tomato","berry",
		"boysenberry","loquat","avocado"}; //default dictionary
	
/**
*Constructor for WordDictionary
*@param tmp, an array that is needed to update the default dictionary
*/
	WordDictionary(String [] tmp) {
		size = tmp.length;
		theDict = new String[size];
		for (int i=0;i<size;i++) {
			theDict[i] = tmp[i];
		}
		
	}
/**
*Constructor for WordDictionary
*/
	WordDictionary() {
		size=theDict.length;
		
	}
	
/**
*This is the method for getting new words
*/
	public synchronized String getNewWord() {
		int wdPos= (int)(Math.random() * size);
		return theDict[wdPos];
	}
	
}
