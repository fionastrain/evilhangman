import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI_PlayGame implements ActionListener{
    private JFrame frame;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private char InputLetter;
    protected boolean IsEvil = true;
    protected JLabel result;
    protected GameState gstate;
    //protected HangmanGame game;
    protected Game game;

 //   public GUI_PlayGame(int letters, int guesses){
    public GUI_PlayGame(GameState gstate, Game game) {	
    	this.gstate = gstate;
    	this.game = game;
    	//gstate = new GameState(letters, guesses);
    	//game = new EvilHangMan(letters, gstate);
    	//game = new EvilHangMan(letters, guesses);
    	frame = new JFrame("Evil Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(360,370));
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        
        label1 = new JLabel("Let's play Evil Hangman!");
        label2 = new JLabel("Secret Word: "+gstate.currentstate);
        label2.setFont(new Font("Default",Font.PLAIN,23));
        label3 = new JLabel(String.valueOf("Guesses Remaining: "+ gstate.numGuessesLeft +"\n"));
        result = new JLabel("");
        result.setForeground(Color.red);
        
        //this generates an image
        ImageIcon icon = new ImageIcon("blank.gif"); 
        JLabel hangmanPic = new JLabel(icon);
        
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(result);
        frame.add(hangmanPic);
        
        //add user choice
        for(int i = 65; i<91;i++){
            char x = (char)i;
            JButton tempBtn = new JButton(String.valueOf(x));
            tempBtn.addActionListener(this);
            frame.add(tempBtn);     
        }
        frame.setResizable(false);
        frame.setVisible(true);
    }
     
    /*
     * This is called when the user clicks any of the buttons in the UI.
     */
    public void actionPerformed(ActionEvent e) {
        //to figure out which button the user pressed
        JButton temp = (JButton)e.getSource();
        temp.setEnabled(false);
        InputLetter = temp.getText().charAt(0);
        game.controller(InputLetter);
    }
         
    public void setResult(String res){
    	result.setText(res);
    	System.out.println(result.getText());
    }
    public String getResult(){
    	return result.getText();
    }
    
    public void updateGUI(){
    	label2.setText("Secret Word: "+ gstate.currentstate);
    	label3.setText("Guesses Remaining: "+ gstate.numGuessesLeft);
    	if(gstate.gameFinished){
    		if(gstate.gameWon){
    			new GUI_Result(gstate.currentstate, getFrame(),true);
    		}
    		else{
    			new GUI_Result(gstate.secretWord,getFrame(),false);
    		}
    	}
    	
    }
   
    public JFrame getFrame(){
    	return frame;
    }
    
    
}