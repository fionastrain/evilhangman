import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class HangmanGameTest {
	private Game game;
	private HangmanGame hm;
	private GUI_PlayGame gui;
	private GameState gstate;
	
	@Before
	public void setUp() throws Exception {
		game = new Game(4,16, true);
		gstate = game.getGameState();
		gui = game.getGUI();
		hm = game.getHangman();
	}
	
	@Test
	public void testOneWordLeft() {
		//This tests game.makeGuess(nextLetter) == false for Evil Hangman
		game.controller('A');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('E');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('I');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('O');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
	}
	
	//@Test
	public void testTransitionToNormal(){
		//This guesses on an evil hangman until only one word is left, testing the transition to normal 
		//hangman
		game.controller('A');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('E');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('I');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('O');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('U');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('R');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('S');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('T');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('L');
		assertEquals("Nope!",gui.getResult());
		assertTrue(game.getIsEvil());
		assertFalse(hm.gameOver());
		game.controller('N');
		assertEquals("Yes!",gui.getResult());
		assertFalse(game.getIsEvil());
		assertFalse(hm.gameOver());
		
		//this is the guess that makes only one word left
		game.controller('W');
		assertEquals("Yes!", gui.getResult());
		assertFalse(game.getIsEvil());
		assertEquals(gstate.getSecretWord(), "WYCH");
		assertFalse(hm.gameOver());
	}
	//this tests guessing a makeGuess == true when normal hangman
	@Test
	public void testNormalHangman(){
		Game game = new Game(4,16,false);	
		game.setWord("PLAY");
		game.controller('P');
		gui = game.getGUI();
		assertEquals("Yes!", gui.getResult());
	}
	
	//this texts guessing a wrong letter under normal hangman
	@Test
	public void testNormalHangmanWrong(){
		Game game = new Game(4,16,false);
		game.controller('E');
		gui = game.getGUI();
		assertEquals("Nope!", gui.getResult());
	}
	
	//this tests game over by winning
	@Test
	public void testWinning(){
		Game game = new Game(4,5, false);
		game.setWord("PLAY");
		game.controller('P');
		game.controller('L');
		game.controller('A');
		game.controller('E');
		gui = game.getGUI();
		assertEquals("Nope!", gui.getResult());
		game.controller('Y');
		gui = game.getGUI();
		assertEquals("Yes!", gui.getResult());
		System.out.println(hm.gameState.currentstate);
		System.out.println(hm.gameState.numLettersLeft);
		assertTrue(game.getHangman().gameOver());
		assertTrue(game.getHangman().isWin());
	///	assertNotNull(hm.winner);
	//	assertEquals(hm.winner.SecretWord.getText(), "PLAY");
	//	assertNull(hm.loser);
	}
	//this tests game over by losing
		@Test
		public void testLosing(){
			Game game = new Game(4,5, true);
			gstate = game.getGameState();
			game.controller('P');
			game.controller('L');
			game.controller('A');
			gui = game.getGUI();
			hm = game.getHangman();
			game.controller('E');
			assertEquals("Nope!", gui.getResult());
			game.controller('M');
			assertEquals("Nope!", gui.getResult());
			assertTrue(hm.gameOver());
			assertFalse(hm.isWin());
			//assertNull(hm.winner);
			//assertNotNull(hm.loser);
			//assertEquals(hm.loser.secretWordLabel.getText(), "PLAY");
			
		}
	
}
