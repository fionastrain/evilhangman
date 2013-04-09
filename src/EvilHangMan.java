import java.util.*;
import java.io.*;


public class EvilHangMan extends HangmanGame {
	private ArrayList<String> Wordlist = new ArrayList<String>();// to store the dictionary of possible words
	private int secretStringLength;// the length of the secret string
	private boolean GuessResult = false;

	public EvilHangMan(int StringLength, int numGuesses) {
		//numLettersLeft=26;
		numGuessesLeft = numGuesses;
		secretStringLength = StringLength;
		Scanner Scanner = null;
		try {
			Scanner = new Scanner(new File("dictionary.txt"));// read the dictionary
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		while (Scanner.hasNext()) {
			String temp = Scanner.nextLine().toUpperCase();
			if (temp.length() == StringLength) {
				Wordlist.add(temp);
			}
		}

		for (int i = 0; i < StringLength; i++) {
			currentState += "_ ";
		}
		Scanner.close();
	}

	public boolean isWin() {
		return false;
	}

	public boolean makeGuess(char ch) {
		GuessResult = false;
		letterGuess = ch;
		if (Character.isLetter(ch) && !RepeatInput(ch)) {
			// adjust the Wordlist in order to avoid the word with the letter
			// user guessed
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
				secretWord = Wordlist.get(0);
				GuessResult = true;
				}
			else {
				secretWord = Wordlist.get(0);
				numGuessesLeft--;
			}
			if (!GuessResult) {
				letterGuessHistory.add(letterGuess);
			}

		} else return false;
		
		return GuessResult;
	}

}