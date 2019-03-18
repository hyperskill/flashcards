package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of cards:\n");
        int numberOfCards = sc.nextInt();
        String[] cards = new String[numberOfCards];
        String[] definitions = new String[numberOfCards];

        for (int i = 0; i < numberOfCards; i++) {
            System.out.printf("The card #%d:\n", i + 1);
            cards[i] = sc.next();
            System.out.printf("The definition of the card #%d:\n", i + 1);
            definitions[i] = sc.next();
        }

        for (int i = 0; i < numberOfCards; i++) {
            System.out.printf("Print the definition of \"%s\"\n", cards[i]);
            String answer = sc.next();
            if (answer.equals(definitions[i])) {
                System.out.printf("Correct answer.");
            } else {
                System.out.printf("Wrong answer (the correct one is \"%s\")", definitions[i]);
            }
        }

    }
}