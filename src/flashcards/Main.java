package flashcards;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        String card;
        String definition;
        String answer;
        Map<String, String> cardToDefinition = new LinkedHashMap<>();
        Map<String, String> definitionToCard = new LinkedHashMap<>();

        System.out.println("Input the number of cards:");

        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("There is an error (input is not an integer number). Please try again.");
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("The card #" + i + ":");
            card = sc.nextLine();
            System.out.println("The definition of the card #" + i + ":");
            definition = sc.nextLine();
            cardToDefinition.put(card, definition);
            definitionToCard.put(definition, card);
        }

        for (Map.Entry<String, String> couple: cardToDefinition.entrySet()) {
            System.out.println("Print the definition of \"" + couple.getKey() + "\":");
            answer = sc.nextLine();
            if (couple.getValue().equals(answer)) {
                System.out.print("Correct answer. ");
            } else if (definitionToCard.containsKey(answer)) {
                System.out.print("Wrong answer (the correct one is \"" + couple.getValue() +
                        "\", you've just written a definition of \"" + definitionToCard.get(answer) + "\" card).");
            } else {
                System.out.print("Wrong answer (the correct one is \"" + couple.getValue() + "\").");
            }
        }



    }
}
