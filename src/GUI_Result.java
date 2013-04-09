import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUI_Result implements ActionListener {
		private JFrame parentFrame;
	    private JFrame frame;
	    private JLabel wordLabel;
	    protected JLabel SecretWord;
	    private JLabel resultLabel;
	    private JButton returnButton;
	    private ImageIcon background;
	    private JPanel imagePanel;
	    private String letters;
	    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
		parentFrame.dispose();
		new Start().createAndShowGUI();
	}
	
	public GUI_Result(String letters, JFrame pframe, boolean won){
		parentFrame = pframe;
		this.letters = letters;
		
		if(won == true){
			setGUIWinner();
		}
		else{
			setGUILoser();
		}
		
	}
	public void setGUIWinner(){
			frame = new JFrame("You are the winner!!!");
			frame.setLayout(new FlowLayout());
			resultLabel = new JLabel("You are winner!");
			wordLabel = new JLabel("The answer is ");
			JLabel secretWord = new JLabel(letters);
			secretWord.setFont(new Font("Default",Font.PLAIN,23));
	        secretWord.setForeground(Color.red);
	        frame.add(wordLabel);
	        frame.add(secretWord);
			bg(frame);
			frame.setSize(background.getIconWidth(), background.getIconHeight());
			frame.setResizable(false);
			returnButton = new JButton("Return to the main menu");
			returnButton.addActionListener(this); 
			frame.add(resultLabel);
			frame.add(returnButton);
			frame.setVisible(true);
		}
	public void setGUILoser(){
		frame = new JFrame("You are the loser!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(300,470));
		frame.setLayout(new FlowLayout());
		wordLabel = new JLabel("The answer is "+letters+".");
		frame.add(wordLabel);
		resultLabel = new JLabel("You are the Loser!");
		
        
        ImageIcon icon = new ImageIcon("loser.gif"); 
        JLabel loserPic = new JLabel(icon);

        
        frame.add(wordLabel);
        frame.add(resultLabel);
        returnButton = new JButton("Return to the main menu");
		returnButton.addActionListener(this); 
        frame.add(returnButton);
        frame.add(loserPic);
        
        frame.setVisible(true);
	}
		
	public void bg(JFrame frame){
        background = new ImageIcon("Congrats.gif");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
        imagePanel = (JPanel) frame.getContentPane();
        imagePanel.setOpaque(false);
        imagePanel.setLayout(new FlowLayout());
        frame.getLayeredPane().setLayout(null);
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
    }

}
