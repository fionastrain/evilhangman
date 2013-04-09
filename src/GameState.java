import java.util.ArrayList;


public class GameState {
	private String secretWord = "";
	private int numGuessesLeft;
	private String currentstate="";
	private ArrayList<Character> letterGuessHistory;
	private ArrayList<Character> correctLettersGuessed;
	
	public GameState(){
		
	}
	
	public void updateCurrentState(){
		currentstate="";
		for(int i = 0; i<secretWord.length(); i++){
			if(correctLettersGuessed.contains(secretWord.charAt(i))){
				currentstate = currentstate + secretWord.charAt(i);
			}
			else{
				currentstate = currentstate + "_";
			}
		}
	}

}
