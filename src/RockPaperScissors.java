import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissors extends JFrame implements ActionListener {

    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;

    public RockPaperScissors() {
        this.setLocationRelativeTo(null);
        setTitle("Rock Paper Scissors");
        setSize(400, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        rockButton = new JButton(new ImageIcon("resources/rock.png"));
        paperButton = new JButton(new ImageIcon("resources/paper.png"));
        scissorsButton = new JButton(new ImageIcon("resources/scissors.png"));

        rockButton.setActionCommand("Rock");
        paperButton.setActionCommand("Paper");
        scissorsButton.setActionCommand("Scissors");

        rockButton.addActionListener(this);
        paperButton.addActionListener(this);
        scissorsButton.addActionListener(this);

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);

        add(buttonPanel, BorderLayout.CENTER);

        resultLabel = new JLabel("Make your choice!", SwingConstants.CENTER);
        add(resultLabel, BorderLayout.NORTH);

        scoreLabel = new JLabel("Score: Player 0 - Computer 0", SwingConstants.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerChoice = e.getActionCommand();
        String computerChoice = getComputerChoice();
        String result = determineWinner(playerChoice, computerChoice);
        resultLabel.setText("You chose " + playerChoice + ". Computer chose " + computerChoice + ". " + result);
        scoreLabel.setText("Score: Player " + playerScore + " - Computer " + computerScore);

    }

    private String getComputerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int index = random.nextInt(3);
        return choices[index];
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerScore++;
            return "You win!";
        } else {
            computerScore++;
            return "You lose!";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissors game = new RockPaperScissors();
            game.setVisible(true);
        });
    }
}
