
public class Game {
	private GameState gstate;
	private HangmanGame game;
	private GUI_PlayGame gui;
	private boolean isEvil;
	
	public Game(int numLetters, int numGuesses, boolean Evil) {
		gstate = new GameState(numLetters, numGuesses);
		if(Evil){
			game = new EvilHangMan(gstate);
			isEvil = true;
		}
		else{
			game = new NormalHangMan(gstate);
			isEvil = false;
		}
		gui = new GUI_PlayGame(gstate, this);
	}
	
	 public void setGame(HangmanGame newGame){
	    	game = newGame;
	    	gstate.initializeDistinctLetters();
    }
	 public void setIsEvil(Boolean evil){
	    isEvil = evil;
 	}
	 public boolean getIsEvil(){
		 return isEvil;
	 }
		 
	 public GameState getGameState(){
		return gstate;
	 }
	
	 public boolean check(char input) {
		 //do the input check. Player can just input the English letters.
		 if((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z')){
			 return true;
		 }  
		 else{
		            return false;
		 }
	  }

		
	public void controller(Character InputLetter) {
		if(check(InputLetter)){
			char nextLetter = Character.toUpperCase(InputLetter);
			if(game.makeGuess(nextLetter)){
				if(isEvil){//judge whether the hangman is evil
	                //if in the evil statement, and the user guess right, 
	            	// it means it is the time to turn the evil to the regular hangmam
					setGame(new NormalHangMan(gstate));//turn the evil to regular hangman
					setIsEvil(false);
					game.makeGuess(nextLetter);//re-value the user guess when turn to the regular hangman for the first time
					}
				gui.setResult("Yes!");
			}
				else{
					gui.setResult("Nope!");

				}
				gstate.updateCurrentState();
				gui.updateGUI();
		}
	}

		public HangmanGame getHangman() {
			return game;
		}

		public GUI_PlayGame getGUI() {
			return gui;
		}

		public void setWord(String string) {
			gstate.secretWord = string;	
			gstate.initializeDistinctLetters();
		}
}
