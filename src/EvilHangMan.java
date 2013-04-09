import java.util.*;
import java.io.*;


public class EvilHangMan extends HangmanGame {
	private ArrayList<String> Wordlist = new ArrayList<String>();// to store the dictionary of possible words
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;
	

	public EvilHangMan(GameState gstate) {
		gameState = gstate;
		secretStringLength = gstate.wordLength;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == secretStringLength) {
				Wordlist.add(temp);
			}
		}
		gameState.updateCurrentState();
		Scanner.close();
	}

	//public boolean isWin() {
		//return false;
	//}

	public boolean makeGuess(char ch) {
		GuessResult = false;
		letterGuess = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			Iterator<String> it = Wordlist.iterator();
			while (it.hasNext()){
				String word = it.next();
				for (int j = 0; j < secretStringLength; j++) {
					if (word.charAt(j) == ch) {
						if(Wordlist.size() > 1){
							it.remove();
						}
						break;
					}
				}
			}
			
			if (Wordlist.size() == 1) {
				gameState.secretWord = Wordlist.get(0);
				GuessResult = true;
				}
			else {
				gameState.secretWord = Wordlist.get(0);
				gameState.numGuessesLeft--;
			}
			if (!GuessResult) {
				gameState.letterGuessHistory.add(letterGuess);
			}

		};
		
		return GuessResult;
	}

}