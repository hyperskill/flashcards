package flashcards;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Input the number of cards:");

        int cardAmount = scan.nextInt();

        String[] cards = new String[cardAmount];
        String[] definitions = new String[cardAmount];

        for (int i = 1; i <= cardAmount; i++) {
            System.out.println("The card #" + i + ":");
            cards[i-1] = scan.nextLine();
            System.out.println("The definition of the card #" + i + ":");
            definitions[i-1] = scan.nextLine();
        }

        String userAnswer;

        for (int j = 0; j < cardAmount; j++) {
            System.out.println("Print the definition of \"" + cards[j] + "\":");
            userAnswer = scan.nextLine();

            if (userAnswer != null && userAnswer.equals(definitions[j])) {
                System.out.print("Correct answer. ");
            } else {
                System.out.println("Wrong answer (the correct one is \"" + definition[j] + "\").");
            }
        }
    }
}
