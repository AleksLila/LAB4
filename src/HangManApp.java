import java.awt.*;

import game.HangmanGame;
import lexicon.HangmanLexicon;

import javax.swing.*;

public class HangManApp {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private HangmanGame hangmanGame;
    private HangmanLexicon hangmanLexicon;
    private JFrame frame;
    private JLabel wordLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    int i=0;

    public void setup() {
        hangmanLexicon = new HangmanLexicon("qwerty");
        hangmanGame = new HangmanGame(new HangmanLexicon("wert"));



        System.out.println("number words : "+hangmanLexicon.getWordCount());
        hangmanGame.word=hangmanLexicon.getRandomWord();

        frame = new JFrame("Hangman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);

        guessTextField = new JTextField("");

        guessButton = new JButton("Guess");
        guessButton.addActionListener(e -> {

            hangmanGame.letterTextField = guessTextField.getText();
            char letter=hangmanGame.letterTextField.charAt(hangmanGame.letterTextField.length()-1);

            if (hangmanGame.guess(letter)==true) {
                hangmanGame.gameWon+=1;
                System.out.println(hangmanGame.gameWon);
                wordLabel.setText(hangmanGame.getPartlyGuessedWord());
                if (hangmanGame.isGameWon()==true) {
                    wordLabel.setText(hangmanGame.getHangmanWord()+"    You WIN!!!\n"+"number attempt= "+hangmanGame.gameWon);
                    System.out.println("Play off. Correct letter:"+hangmanGame.getGuessedLetters());
                }

            }
            else  {
                hangmanGame.gameLost+=1;
                if (hangmanGame.isGameLost()==true) {
                    wordLabel.setText(hangmanGame.getHangmanWord()+"   play off");
                    System.out.println("Play off. Correct letter:"+hangmanGame.getGuessedLetters());
                }
            }

            hangmanGame.getGuessesLeft();
            hangmanGame.getIncorrectGuesses();
            hangmanGame.getCorrectGuesses();
            hangmanLexicon.getWordCount();

        });

        frame.getContentPane().add(guessButton, BorderLayout.SOUTH);

        frame.getContentPane().add(guessTextField, BorderLayout.NORTH);

        wordLabel = new JLabel(hangmanGame.getPartlyGuessedWord());
        wordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.getContentPane().add(wordLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        HangManApp manApp = new HangManApp();
        manApp.setup();
    }

}

