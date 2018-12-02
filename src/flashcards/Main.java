package flashcards;

import java.util.*;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static Map<String, String> cardsMap = new LinkedHashMap<>();


    public static void main(String[] args) {
        getCardsAndDefinition();
        checksUserInput();
    }

    private static void checksUserInput() {
        String answer;
        for (Map.Entry<String, String> card : cardsMap.entrySet()) {
            System.out.println("Print the definition of \"" + card.getKey() + "\"");
            answer = scn.nextLine();
            if (answer.equals(card.getValue())) {
                System.out.println("Correct answer.");
            } else if (cardsMap.containsValue(answer)) {
                for (Map.Entry<String, String> secondCard : cardsMap.entrySet()) {
                    if (secondCard.getValue().equals(answer)) {
                        System.out.println("Wrong answer (the correct one is \"" + card.getValue() + "\", " +
                                "you've just written a definition of \"" + secondCard.getKey() + "\" card).");
                    }
                }
            } else {
                System.out.println("Wrong answer (the correct one is \"" + card.getValue() + "\").");
            }
        }
    }

    private static void getCardsAndDefinition() {
        System.out.println("Input the number of cards: ");
        int num = scn.nextInt();
        scn = new Scanner(System.in);
        for (int i = 0; i < num; i++) {
            String card;
            do {
                System.out.println("The card #" + (i + 1) + ":");
                card = scn.nextLine();
                if (cardsMap.containsKey(card)) {
                    System.out.println("This card is exist");
                } else {
                    System.out.println("The definition of the card #" +  + (i + 1) + ":");
                    String definition = scn.nextLine();
                    cardsMap.put(card, definition);
                    break;
                }
            } while (cardsMap.containsKey(card));
        }
    }

}