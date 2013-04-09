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
    public NormalHangMan(String secretWord, int numGuesses, ArrayList<Character> LetterHistory){
        this.secretWord = secretWord;
        numGuessesLeft = numGuesses;
        numLettersLeft = secretWord.length();
        for(int i = 0; i < secretWord.length(); i++)
        {
            currentState += "_ ";
            for(int j = i; j > 0; j--)
            {
                if(secretWord.charAt(i) == secretWord.charAt(j-1))
                {
                    numLettersLeft--;//If the letter appears many times in the secret word, it will be counted just once.
                    break;
                }
            }
        }
        letterGuessHistory = LetterHistory;
    }   

 
  
    public boolean isWin(){
        if(numGuessesLeft == 0)
            return false;//if the user have no chance to guess again, it means the user loses.
        else
            return true;
    }
    
    public boolean makeGuess(char ch) {
    	if (Character.isLetter(ch) == false) return false;
        boolean correctLetter = false;
        letterGuess = ch;
        int i;
        for(i = 0; i < secretWord.length(); i++){
            if(secretWord.charAt(i) == letterGuess)//if the user guess right, adjust the current state.
            {
            //    String temp = "";
              //  for(int j = 0; j < secretWord.length(); j++)
                //{
                  //  if(secretWord.charAt(j) == ch)
                   // {
                     //   temp = temp + ch + " ";
                    //}
                    //else
                    //{
                      //  temp = temp + currentState.charAt(2*j) + currentState.charAt(2*j+1);              
                    //}
                //}
                //currentState = temp;
                correctLetter = true;
            }
        }
        if(!RepeatInput(ch))
        {
            letterGuessHistory.add(letterGuess);
            if(correctLetter)
            {
                numLettersLeft--;
            }
            else
            {
                numGuessesLeft--;
            }
        }
        return correctLetter;
    }
    
}
    
       