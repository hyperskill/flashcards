package flashcards;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input the number of cards:\n");
        int amount = Integer.parseInt(sc.nextLine());
        Map<String, String> cardToDefinition = new HashMap<>();
        Map<String, String> definitionToCard = new HashMap<>();

        for (int i = 0; i < amount; i++) {
            System.out.printf("The card #%d:\n", i + 1);
            String card = sc.nextLine();

            System.out.printf("The definition of the card #%d:\n", i + 1);
            String definition = sc.nextLine();

            cardToDefinition.put(card, definition);
            definitionToCard.put(definition, card);
        }
        for (Map.Entry<String, String> entry : cardToDefinition.entrySet()) {
            System.out.printf("Type the definition of \"%s\":\n", entry.getKey());
            String answer = sc.next();

            if (entry.getValue().equals(answer)) {
                System.out.print("Correct answer. ");
                continue;
            } else if (definitionToCard.containsKey(answer)) {
                System.out.printf("Wrong answer (the correct one is \"%s\")," +
                                " you've just written a definition of \"%s\" card).\n",
                        entry.getValue(),
                        definitionToCard.get(answer));
            } else {
                System.out.printf("Wrong answer (the correct one is \"%s\").\n", entry.getValue());
            }

        }
    }
}