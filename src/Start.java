 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.text.SimpleDateFormat;
 
public class Start extends JPanel implements ActionListener {
    static JFrame frame;
    String numLetters, numGuesses;
    private JComboBox numLettersOptBox, numGuessesOptBox;
 
    public Start() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] numLettersOptions = {"4","5","6","7","8","9","10",};
        numLetters = numLettersOptions[0];
 
        String[] numGuessesOptions = { "4", "5", "6", "7", "8","9", "10", "11", "12",
                					"13", "14","15", "16", };
        numGuesses = numGuessesOptions[0];

        JLabel patternLabelLetters1 = new JLabel("Select the number of letters");
        JLabel patternLabelLetters2 = new JLabel("that will be in the word:");
 
        numLettersOptBox = new JComboBox(numLettersOptions);
        numLettersOptBox.setEditable(true);
 
        JLabel patternLabelGuesses1 = new JLabel("Select the number of incorrect");
        JLabel patternLabelGuesses2 = new JLabel("guesses that are allowed:");
 
        numGuessesOptBox = new JComboBox(numGuessesOptions);
        numGuessesOptBox.setEditable(true);
        
        JPanel lettersPanel = new JPanel();
        lettersPanel.setLayout(new BoxLayout(lettersPanel,BoxLayout.PAGE_AXIS));
        lettersPanel.add(patternLabelLetters1);
        lettersPanel.add(patternLabelLetters2);
        numLettersOptBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        lettersPanel.add(numLettersOptBox);
 
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new BoxLayout(guessPanel,
                               BoxLayout.PAGE_AXIS));
        guessPanel.add(patternLabelGuesses1);
        guessPanel.add(patternLabelGuesses2);
        numGuessesOptBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        guessPanel.add(numGuessesOptBox);
        
        lettersPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        add(lettersPanel);
        add(guessPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton button = new JButton("Play!");
        button.addActionListener(this);
        add(button);
 
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    } 
 
    public void actionPerformed(ActionEvent e) {
    	numLetters = (String)(numLettersOptBox.getSelectedItem());
    	numGuesses = (String)(numGuessesOptBox.getSelectedItem());
    	frame.dispose();
    	new Game(Integer.parseInt(numLetters), Integer.parseInt(numGuesses), true);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Evil Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new Start();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
    	createAndShowGUI();
    }
}
