import javax.swing.JOptionPane;
public class SecretNumber {
    // Written by: Nadine Mansour
    // Generates a random secret number between 1 – 100
    // The program will then check if a user can guess the secret number

public static final int SENTINEL = 0;
public static void main(String[] args) {
    int randomNumber, guess;
    String guessMsg;
    
    randomNumber = (int) ((Math.random() * 100) + 1);
    guessMsg = "Guess a number between 1 and 100 " +
               "(or enter " +SENTINEL+ " to terminate): ";
    
    guess = Integer.parseInt(JOptionPane.showInputDialog(null, guessMsg));
    while ((guess != randomNumber) && (guess != SENTINEL)) {
        if (guess > (randomNumber + 30))
            JOptionPane.showMessageDialog(null, "Way Too High");
        else if (guess >= (randomNumber + 10))
            JOptionPane.showMessageDialog(null, "High");
        else if ((guess <= (randomNumber + 10)) && (guess > randomNumber))
            JOptionPane.showMessageDialog(null, "A Little High");
        else if (guess < (randomNumber - 30))
            JOptionPane.showMessageDialog(null, "Way Too Low");
        else if (guess <= (randomNumber - 10))
            JOptionPane.showMessageDialog(null, "Low");
        else if ((guess >= (randomNumber - 10)) && (guess < randomNumber))
            JOptionPane.showMessageDialog(null, "A Little Low");
        
        guess = Integer.parseInt(JOptionPane.showInputDialog(null, guessMsg));
        if (guess == randomNumber)
            JOptionPane.showMessageDialog(null, "Correct!");
    }
}}