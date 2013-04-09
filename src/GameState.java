import java.util.ArrayList;
import java.util.HashSet;

public class GameState {
	protected boolean isEvil;
	protected String secretWord = "";
	protected String currentstate = "";
	protected int numGuessesLeft;
	protected int numLettersLeft;
	protected int wordLength;
	protected ArrayList<Character> uniqueCharsWord;
	protected ArrayList<Character> letterGuessHistory;
	private HashSet<Character> correctLettersGuessed;
	private HashSet<Character> wordLetters = new HashSet<Character>();
	protected boolean gameFinished = false;
	protected boolean gameWon = false;
	
	public GameState(int letters, int guesses){
		wordLength = letters;
		numGuessesLeft = guesses;
		initializeDistinctLetters();
		letterGuessHistory = new ArrayList<Character>();
		correctLettersGuessed = new HashSet<Character>();
		uniqueCharsWord = new ArrayList<Character>();
		updateCurrentState();
	}
	
	public void initializeDistinctLetters() {
		if (secretWord == ""){
			numLettersLeft = wordLength;
		}
		else {
			for (int i = 0; i< secretWord.length(); i++){
				wordLetters.add(secretWord.charAt(i));
			}
		}
		numLettersLeft =  wordLetters.size();
	}
	
	public void updateCurrentState(){
		boolean finished = true;
		currentstate="";
		for(int i = 0; i<wordLength; i++){
			if(i < secretWord.length()){
				if(correctLettersGuessed.contains(secretWord.charAt(i))){
					currentstate = currentstate + secretWord.charAt(i) + " ";
				}
				else{
					finished = false;
					currentstate = currentstate + "_ ";
				}
			}
			else{
				finished = false;
				currentstate = currentstate + "_ ";
			}
		}
		if(finished == true || numGuessesLeft == 0){
			gameFinished = true;
			checkGameOver();
		}
	}
	 
	public boolean checkGameOver(){
	        if(gameFinished && numGuessesLeft > 0){
	            gameWon = true;
	        	return true;
	        }
	        else if(gameFinished){
	            return true;
	        }
	        else{
	        	return false;
	        }
	 }        
	
	public void addToCorrectLetters(Character corrChar){
		correctLettersGuessed.add(corrChar);
		wordLetters.remove(corrChar);
		numLettersLeft = wordLetters.size();
		updateCurrentState();
		if(numLettersLeft == 0){
			gameFinished = true;
		}
	}
	
	  /**
     * A String representing the letters guessed so far in the order they were guessed.
     * Duplicates should not be added.
     * @return a String showing which letters have already been guessed.
     */
 public ArrayList<Character> lettersGuessed(){
        return letterGuessHistory;
    }
	public String getSecretWord() {
		return secretWord;
	}
	public int numGuessesRemaining() {
		return numGuessesLeft;
	}
}
