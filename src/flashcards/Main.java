package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Input the number of cards: ");
        int num = scn.nextInt();
        String[] cards = new String[num];
        String[] definitions = new String[num];
        for (int i = 0; i < num; i++) {
            System.out.println("The card #" + (i + 1) + ":");
            cards[i] = scn.next();
            System.out.println("The definition of the card #" +  + (i + 1) + ":");
            definitions[i] = scn.next();
        }
        for (int i = 0; i < num; i++) {
            System.out.println("Print the definition of \"" + cards[i] + "\":");
            String answer = scn.next();
            if (answer.equals(definitions[i])) {
                System.out.print("Correct answer. ");
            } else {
                System.out.println("Wrong answer (the correct one is \"" + definitions[i] + "\").");
            }
        }
    }
}