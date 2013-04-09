import java.util.ArrayList;
import java.util.LinkedList;

public abstract class HangmanGame {
	protected String secretWord = "";// To store the secret word
	protected int numGuessesLeft;// to store the number of guess for the user
	protected String currentState = "";// store the current guessing situation
	protected ArrayList<Character> letterGuessHistory = new ArrayList<Character>() ;// store the letters user has tried
	protected char letterGuess;// the currently guessed letter
    protected int numLettersLeft;//to store the number of the letters in the secret word has not been guessed correctly
    protected GUI_Winner winner;
    protected GUI_Loser loser;
    private GUI_PlayGame pgGui;
    
    /**
     * @return the original secret word.
     */
	public String getSecretWord() {
	        return secretWord;
	    }
	
	  /**
     * Simulates the player guessing a letter in the word and updates the state of game
     * accordingly -- number of guesses remaining, number of letters 
     * if the guessed letter is not in the word, and hasn't been guessed yet, numGuesses is decremented
     * if the guessed letter is in the word, and hasn't been guessed yet, then update the current state of
     * the guessed word to reveal the position(s) of the letter(s) that was just guessed, and update numLettersRemaining.
     * There should be no guess penalty for guessing a letter that has already been 
     * guessed, just return false.  
     * @param ch the char that is the next letter to be guessed on the word
     * @return true if the game isn't over and the guessed letter was in the word, false otherwise
     */
    public abstract boolean makeGuess(char ch);
    
    /**
     * @return the number of guesses the player has left
     */
	public int numGuessesRemaining() {
		return numGuessesLeft;
	}
	
	  /**
     * The number of letters remaining to be guessed in the secret word - i.e.
     * the number of blank spaces the player sees, which may be different from
     * the actually number of letters it will take to fill those blanks.
     * @return the number of letters in the secret word that still have to be guessed
     */
	 public int numLettersRemaining(){
	        return numLettersLeft;
	    }
	 
	  /** 
	     * @return true if either num guesses remaining is 0 or num letters remaining to be guessed is 0.
	     */
	 public boolean gameOver(){
	        if(numGuessesLeft == 0 || numLettersLeft == 0)
	            return true;
	        else
	            return false;
	    }
	
	    /**
	     * A String representing the letters guessed so far in the order they were guessed.
	     * Duplicates should not be added.
	     * @return a String showing which letters have already been guessed.
	     */
	 public ArrayList<Character> lettersGuessed(){
	        return letterGuessHistory;
	    }
	 /**
	     * Gives a display-ready String-ified version of the current state of the secret word, showing letters
	     * that have been guessed and blank spaces for letters that still need to be guessed.
	     * For example if the secrect word is "LABORATORY" and the player has guessed L, A, B, R, and Y
	     * the method might return something like "L A B _ R A _ _ R Y"
	     * @return a String of the current state of the secret word.
	     */
	 public String displayGameState() {
			return currentState;
		}
	 public boolean RepeatInput(char c){
		 	if(letterGuessHistory.contains(c)){
		 		return true;
		 	}
		 	else{
		 		return false; 
		 	}
	    }
	 
	    /**
	     * @return true if there are no more letters to be guessed and there is still at least one guess remaining
	     */
	    public abstract boolean isWin(); 
	    
	    /*
	     * This handles the logic of sending info to the Game object.
	     */
	    public void controller(Character InputLetter, boolean IsEvil, GUI_PlayGame gui)
	    {
	    	pgGui = gui;
	        char nextLetter = Character.toUpperCase(InputLetter);
	        if(makeGuess(nextLetter))
	        {
	            if(IsEvil)//judge whether the hangman is evil
	            {
	                //if in the evil statement, and the user guess right, 
	            	// it means it is the time to turn the evil to the regular hangmam
	                gui.setResult("Yes!");
	                String RealSecretString = getSecretWord();
	                int GuessRemaining = numGuessesRemaining();
	                ArrayList<Character> LetterHistory = lettersGuessed();
	                gui.setGame(new NormalHangMan(RealSecretString, GuessRemaining,LetterHistory));//turn the evil to regular hangman
	                gui.setIsEvil(false);
	                makeGuess(nextLetter);//re-value the user guess when turn to the regular hangman for the first time
	            }
	            else
	            {
	                gui.setResult("Yes!");
	            }
	        }
	        else
	        {
	            gui.setResult("Nope!");
	        }

	        gui.setLabelText(2,"Secret Word: "+displayGameState());
	        gui.setLabelText(3,String.valueOf("Guesses Remaining: "+ numGuessesRemaining()));
	        if(gameOver())
	        {
	            if(isWin())
	            {
	                winner = new GUI_Winner(displayGameState(),gui.getFrame());
	            }
	            else
	            {
	                loser = new GUI_Loser(getSecretWord(),gui.getFrame());
	            }
	        }
	    }
	public String getResult(){
		return pgGui.result.getText();
	}
	
	public boolean getIsEvil(){
		return pgGui.IsEvil;
	}
	    
	    
}
