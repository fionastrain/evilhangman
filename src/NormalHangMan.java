/**
 * <p>A class that keeps track of the current state of a game of hangman.</p>
 * <p> The class is constructed with a secret word and some number of guesses.</p>
 * <p>Every time a letter is guessed, the state of the game is updated appropriately
 * integrating the guessed letter into the word, updating the number of guesses
 * remaining, etc.</p>
 * 
 * <p>This class can then be used by a user interface to administer a regular game of Hangman.</p>
 */
import java.util.*;
public class NormalHangMan extends HangmanGame 
{
    /**
     * Constructor sets up the game to be played with a word and some number of
     * guesses.  The class should have private variables that keep track of:
     * <li>The original secret word
     * <li>The number of guesses remaining
     * <li>The number of letters that still need to be guessed
     * <li>The current state of word to be guessed (e.g. "L A B _ R A _ _ R Y")
     * @param secretWord the word that the player is trying to guess
     * @param numGuesses the number of guesses allowed
     */
    public NormalHangMan(GameState gstate){
    	gameState = gstate;
    	this.secretWord = gameState.secretWord;
    }   

    public boolean isWin(){
        if(gameState.numGuessesLeft == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    
    public boolean makeGuess(char ch) {
    	if (Character.isLetter(ch) == false) return false;
        boolean correctLetter = false;
        Character lg = ch;
        if(gameState.secretWord.contains(lg.toString())){
        	correctLetter = true;
        	//GameState add to correct letters guessed, update current state
        }
        if(!RepeatInput(ch)) {
           gameState.letterGuessHistory.add(lg);
            if(correctLetter)
            {
              gameState.addToCorrectLetters(ch); 
            }
            else
            {
               gameState.numGuessesLeft--;
            }
        }
        else{
        	correctLetter = false;
        }
       gameState.updateCurrentState();
        return correctLetter;
    }
    
}
    
       