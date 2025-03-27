import java.util.*;
import java.util.concurrent.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApplication {
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds
    private List<Question> questions;
    private int score;
    private Scanner scanner;
    private List<String> results;

    public QuizApplication() {
        this.questions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.score = 0;
        this.results = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"}, 2));
        questions.add(new Question("Who developed Java?", new String[]{"1. Microsoft", "2. Apple", "3. Sun Microsystems", "4. Google"}, 3));
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz! You have " + TIME_LIMIT + " seconds to answer each question.");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }
            
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<Integer> future = executor.submit(() -> scanner.nextInt());
            int userAnswer = -1;

            try {
                userAnswer = future.get(TIME_LIMIT, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                System.out.println("Time's up! Moving to the next question.");
                results.add("Q" + (i + 1) + " - No Answer (Time Out)");
            } catch (Exception e) {
                System.out.println("Invalid input. Skipping question.");
                results.add("Q" + (i + 1) + " - Invalid Input");
            } finally {
                executor.shutdownNow();
            }

            if (userAnswer == q.correctAnswer) {
                System.out.println("Correct!");
                score++;
                results.add("Q" + (i + 1) + " - Correct");
            } else if (userAnswer != -1) {
                System.out.println("Wrong answer. The correct answer was: " + q.correctAnswer);
                results.add("Q" + (i + 1) + " - Incorrect (Your Answer: " + userAnswer + ")");
            }
        }
        showResults();
    }

    private void showResults() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("\nSummary of Answers:");
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        QuizApplication quiz = new QuizApplication();
        quiz.startQuiz();
    }
}
