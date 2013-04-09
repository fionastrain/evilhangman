import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class HangmanGameTest {
	private HangmanGame hm;
	private GUI_PlayGame gui;
	
	@Before
	public void setUp() throws Exception {
		hm = new EvilHangMan(4, 16);
		gui = new GUI_PlayGame(4,16);
		gui.show();
		
	}
	
	@Test
	public void testOneWordLeft() {
		//This tests game.makeGuess(nextLetter) == false for Evil Hangman
		hm.controller('A', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('E', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('I', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('O', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
	}
	
	@Test
	public void testTransitionToNormal(){
		//This guesses on an evil hangman until only one word is left, testing the transition to normal 
		//hangman
		hm.controller('A', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('E', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('I', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('O', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertFalse(hm.gameOver());
		assertTrue(hm.getIsEvil());
		hm.controller('U', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('R', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('S', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('T', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('L', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		hm.controller('N', true, gui);
		assertEquals("Nope!",hm.getResult());
		assertTrue(hm.getIsEvil());
		assertFalse(hm.gameOver());
		
		//this is the guess that makes only one word left
		hm.controller('W', true, gui);
		assertEquals("Yes!", hm.getResult());
		assertFalse(hm.getIsEvil());
		assertEquals(hm.secretWord, "WYCH");
		assertFalse(hm.gameOver());
	}
	//this tests guessing a makeGuess == true when normal hangman
	@Test
	public void testNormalHangman(){
		hm = new NormalHangMan("PLAY", 8, new ArrayList<Character>());
		hm.controller('P',false, gui);
		assertEquals("Yes!", hm.getResult());
	}
	
	//this texts guessing a wrong letter under normal hangman
	@Test
	public void testNormalHangmanWrong(){
		hm = new NormalHangMan("PLAY", 8, new ArrayList<Character>());
		hm.controller('E',false, gui);
		assertEquals("Nope!", hm.getResult());
	}
	
	//this tests game over by winning
	@Test
	public void testWinning(){
		ArrayList<Character> lettersGuessed = new ArrayList<Character>();
		lettersGuessed.add('P');
		lettersGuessed.add('L');
		lettersGuessed.add('A');
		hm = new NormalHangMan("PLAY", 8, new ArrayList<Character>());
		hm.controller('E',false, gui);
		assertEquals("Nope!", hm.getResult());
		hm.controller('Y', false, gui);
		assertEquals("Yes!", hm.getResult());
		assertTrue(hm.gameOver());
		assertTrue(hm.isWin());
		assertNotNull(hm.winner);
		assertEquals(hm.winner.SecretWord.getText(), "PLAY");
		assertNull(hm.loser);
	}
	//this tests game over by losing
		@Test
		public void testLosing(){
			ArrayList<Character> lettersGuessed = new ArrayList<Character>();
			lettersGuessed.add('P');
			lettersGuessed.add('L');
			lettersGuessed.add('A');
			hm = new NormalHangMan("PLAY", 2, new ArrayList<Character>());
			hm.controller('E',false, gui);
			assertEquals("Nope!", hm.getResult());
			hm.controller('M', false, gui);
			assertEquals("Nope!", hm.getResult());
			assertTrue(hm.gameOver());
			assertFalse(hm.isWin());
			assertNull(hm.winner);
			assertNotNull(hm.loser);
			assertEquals(hm.loser.secretWordLabel.getText(), "PLAY");
			
		}
	
}
