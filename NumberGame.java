import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attempts = 0;
            boolean guessedCorrectly = false;
            int maxAttempts = 7; // Limit attempts
            
            System.out.println("\nA new number has been generated! Try to guess it.");
            
            while (!guessedCorrectly && attempts < maxAttempts) {
                System.out.print("Enter your guess (1-100): ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all attempts. The number was " + numberToGuess + ".");
            }
            
            System.out.println("Your current score: " + totalScore);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }
        
        System.out.println("Thanks for playing! Your final score: " + totalScore);
        scanner.close();
    }
}