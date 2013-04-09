import java.util.ArrayList;
import java.util.LinkedList;

public abstract class HangmanGame {
	protected char letterGuess;// the currently guessed letter
	protected GameState gameState;
	protected String secretWord;
	
    public abstract boolean makeGuess(char ch);
       
    public boolean RepeatInput(char c){
	 	if(gameState.letterGuessHistory.contains(c)){
	 		return true;
	 	}
	 	else{
	 		return false; 
	 	}
    }
     
    public abstract boolean isWin(); 
    
    public String getSecretWord(){
    	return gameState.getSecretWord();
    }
    
    public int numGuessesRemaining(){
    	return gameState.numGuessesRemaining();
    }
    
    public int numLettersRemaining(){
    	return gameState.numLettersLeft;
    }
    
    public String displayGameState(){
    	return gameState.currentstate;
    }
    
    public boolean gameOver(){
    	return gameState.gameFinished;
    }
    
    public ArrayList<Character> lettersGuessed(){
    	return gameState.lettersGuessed();
    }
	   
}
	     


