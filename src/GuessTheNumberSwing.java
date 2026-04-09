import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessTheNumberSwing extends JFrame {

    private int randomNumber;
    private int attemptsLeft;
    private int score = 0;
    private int round = 1;

    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel, scoreLabel, roundLabel;

    public GuessTheNumberSwing() {
        setTitle("Guess The Number Game");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(199, 105, 174));
        panel.setLayout(null);
        add(panel);

        JLabel title = new JLabel("🎯 GUESS THE NUMBER 🎯");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBounds(90, 20, 300, 30);
        panel.add(title);

        roundLabel = new JLabel("Round: 1");
        roundLabel.setBounds(30, 70, 100, 25);
        panel.add(roundLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(330, 70, 100, 25);
        panel.add(scoreLabel);

        JLabel instruction = new JLabel("Guess a number between 1 and 100");
        instruction.setBounds(100, 100, 250, 25);
        panel.add(instruction);

        guessField = new JTextField();
        guessField.setBounds(150, 140, 150, 30);
        panel.add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(170, 180, 100, 35);
        panel.add(guessButton);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(50, 230, 350, 25);
        panel.add(messageLabel);

        attemptsLabel = new JLabel("Attempts Left: 7", SwingConstants.CENTER);
        attemptsLabel.setBounds(150, 260, 150, 25);
        panel.add(attemptsLabel);

        guessButton.addActionListener(e -> checkGuess());

        startNewRound();
        setVisible(true);
    }

    private void startNewRound() {
        randomNumber = new Random().nextInt(100) + 1;
        attemptsLeft = 7;
        attemptsLabel.setText("Attempts Left: " + attemptsLeft);
        messageLabel.setText("");
        guessField.setText("");
        roundLabel.setText("Round: " + round);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attemptsLeft--;

            if (guess == randomNumber) {
                int points;
                if (attemptsLeft >= 4) points = 10;
                else if (attemptsLeft >= 2) points = 5;
                else points = 2;

                score += points;
                scoreLabel.setText("Score: " + score);

                JOptionPane.showMessageDialog(this,
                        "🎉 Correct Guess!\nYou earned " + points + " points.");

                round++;
                startNewRound();
            } else if (guess < randomNumber) {
                messageLabel.setText("📉 Too Low!");
            } else {
                messageLabel.setText("📈 Too High!");
            }

            attemptsLabel.setText("Attempts Left: " + attemptsLeft);

            if (attemptsLeft == 0) {
                JOptionPane.showMessageDialog(this,
                        "❌ Out of attempts!\nNumber was: " + randomNumber);
                round++;
                startNewRound();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new GuessTheNumberSwing();
    }
}
