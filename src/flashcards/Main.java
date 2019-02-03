package flashcards;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the number of cards: ");
        int n = sc.nextInt();
        int i;
        String possible = sc.nextLine();
        String[] questions = new String[n];
        String[] answers = new String[n];

        for (i = 0; i < n; i++) {
            System.out.println("The card #" + (i+1) + ":");
            questions[i] = sc.nextLine();
            System.out.println("The definition of the card #" + (i+1) + ":");
            answers[i] = sc.nextLine();
        }

        for (i = 0; i < n; i++) {
            System.out.println("Print the definition of \"" + questions[i] + "\":");
            possible = sc.nextLine();
            if (possible.equalsIgnoreCase(answers[i])) {
                System.out.print("Correct answer. ");
            } else {
                System.out.println("Wrong answer (the correct one is \"" + answers[i] +"\").");
            }
        }

    }
}
