import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class NextWordPredictor {
    private Map<String, List<String>> wordMap;
    private Random random;

    public NextWordPredictor() {
        wordMap = new HashMap<>();
        random = new Random();
    }

    public void train(String sentence) {
        String[] words = sentence.split("\\s+");
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];
            wordMap.putIfAbsent(currentWord, new ArrayList<>());
            wordMap.get(currentWord).add(nextWord);
        }
    }

    public String predictNextWord(String currentWord) {
        List<String> possibleWords = wordMap.get(currentWord);
        if (possibleWords == null || possibleWords.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(possibleWords.size());
        return possibleWords.get(randomIndex);
    }

    public static void main(String[] args) {
        NextWordPredictor predictor = new NextWordPredictor();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Next Word Predictor");
        System.out.println("Enter a sentence to train the model (or 'q' to quit):");

        String input = scanner.nextLine();

        while (!input.equals("q")) {
            predictor.train(input);

            System.out.println("Enter another sentence (or 'q' to quit):");
            input = scanner.nextLine();
        }

        System.out.println("Training complete!");

        System.out.println("\nNext Word Prediction");
        System.out.println("Enter a word to predict the next word (or 'q' to quit):");

        input = scanner.nextLine();

        while (!input.equals("q")) {
            String nextWord = predictor.predictNextWord(input);

            if (nextWord != null) {
                System.out.println("Predicted next word: " + nextWord);
            } else {
                System.out.println("No prediction available for the given word.");
            }

            System.out.println("\nEnter another word (or 'q' to quit):");
            input = scanner.nextLine();
        }

        System.out.println("Goodbye!");

        scanner.close();
    }
