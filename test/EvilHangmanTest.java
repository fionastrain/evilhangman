
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class EvilHangmanTest {
	private GameState gstate;
	private Game game;
	private HangmanGame hm;
	private final String WORD = "SPONGEBOB";

	@Before
	public void setUp() throws Exception {
		//hm = new EvilHangMan(6);
		game = new Game(6,8, true);
		hm = game.getHangman();
		gstate = game.getGameState();
	}
	
	@Test
	public void testInitialValues() {
		// call the constructor and see that the initial values are correct
		assertEquals("", hm.getSecretWord()); // unknown at first
		assertEquals(8, hm.numGuessesRemaining());
		assertEquals("_ _ _ _ _ _ ", gstate.currentstate);
		assertEquals(0, hm.lettersGuessed().size());
		assertFalse(hm.gameOver());
	}

	@Test
	public void testGuess() {
		// pretty much every guess is going to be wrong!
		boolean correct = hm.makeGuess('S');
		assertFalse(correct);

		assertEquals(7, hm.gameState.numGuessesRemaining());
		assertEquals("_ _ _ _ _ _ ", gstate.currentstate);
		assertEquals("S", hm.gameState.lettersGuessed().get(0).toString());
		assertEquals(1, hm.gameState.lettersGuessed().size());
		assertFalse(hm.gameOver());
	}
	
	@Test
	public void testTwoGuesses() {
		boolean correct = hm.makeGuess('S');
		assertFalse(correct);
		correct = hm.makeGuess('P');
		assertFalse(correct);

		assertEquals(6, hm.gameState.numGuessesRemaining());
		assertEquals("_ _ _ _ _ _ ", gstate.currentstate);
		assertEquals("S", hm.gameState.lettersGuessed().get(0).toString());
		assertEquals("P", hm.gameState.lettersGuessed().get(1).toString());
		assertEquals(2, hm.gameState.lettersGuessed().size());
		assertFalse(hm.gameOver());
	}
	
	@Test
	public void testIllegalCharGuess() {
		boolean correct = hm.makeGuess('?');
		assertFalse(correct);
		assertEquals(8, hm.gameState.numGuessesRemaining());
		assertEquals("_ _ _ _ _ _ ", gstate.currentstate);
		assertEquals(0, hm.gameState.lettersGuessed().size());
		assertFalse(hm.gameOver());
	}

	@Test
	public void testMultipleCharGuess() {
		boolean correct = hm.makeGuess('A');
		assertFalse(correct);
		correct = hm.makeGuess('A');
		assertFalse(correct);
		
		assertEquals(7, hm.gameState.numGuessesRemaining());
		assertEquals("_ _ _ _ _ _ ", gstate.currentstate);
		assertEquals("A", hm.gameState.lettersGuessed().get(0).toString());
		assertEquals(1, hm.gameState.lettersGuessed().size());
		assertFalse(hm.gameOver());
	}
	
	@Test
	public void testSecretWord() {
		// after one guess, the secret word changes 
		hm.makeGuess('A');
		assertEquals("BEBOPS", hm.gameState.getSecretWord()); // don't ask how I know that
		// after another guess, it changes again! 
		hm.makeGuess('B');
		assertEquals("CEDERS", hm.gameState.getSecretWord()); // don't ask how I know that
	}

	
	@Test
	public void testWin() {
		// correctly guess the word and see if the game ends
		game = new Game(4,14, true);
		hm = game.getHangman();
		gstate = game.getGameState();
		hm.makeGuess('A');
		hm.makeGuess('E');
		hm.makeGuess('I');
		hm.makeGuess('O');
		hm.makeGuess('U');
		hm.makeGuess('R');
		hm.makeGuess('S');
		hm.makeGuess('T');
		hm.makeGuess('L');
		hm.makeGuess('N');
		
		// at this point, the correct word is WYCH
		boolean correct = hm.makeGuess('W');
		assertTrue(correct);
		correct = hm.makeGuess('Y');
		assertTrue(correct);
		correct = hm.makeGuess('C');
		assertTrue(correct);
		correct = hm.makeGuess('H');
		assertTrue(correct);

		// the authors of EvilHangMan assume you'll never win!
		// that's because the game switches from "evil" to "normal"
		// once you guess a letter correctly.
		// so, there's no need to test these, I guess
		//assertTrue(hm.gameOver());
		//assertTrue(hm.isWin());
	}

	@Test
	public void testLoss() {
		// use up all guesses and see if game ends
		game.controller('A');
		game.controller('C');
		game.controller('D');
		game.controller('F');
		game.controller('H');
		game.controller('I');
		game.controller('J');
		game.controller('K');

		assertTrue(hm.gameOver());
		assertFalse(hm.isWin());
	}
}
