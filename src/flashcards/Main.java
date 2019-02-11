package flashcards;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int numOfCards = scanner.nextInt();
        String[] cards = new String[numOfCards];
        String[] definition = new String[numOfCards];
        for (int i=0; i<numOfCards; i++) {
            System.out.println("The card #" + (i+1) + ":");
            cards[i] = scanner.next();
            System.out.println("The definition of the card #" + (i+1) + ":");
            definition[i] = scanner.next();
        }
        for (int n=0; n<numOfCards; n++) {
            System.out.println("Print the definition of (" + cards[n] + "):");
            String answer = scanner.next();
            if (answer.equals(definition[n])) {
                System.out.print("Correct answer. ");
            } else {
                System.out.print("Wrong answer (the correct one is " + definition[n] + ").");
            }
        }
    }
}