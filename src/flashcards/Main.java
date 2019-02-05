package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Map<String, String> cardToDefinition = new LinkedHashMap<>();
    private static Map<String, String> definitionToCard = new LinkedHashMap<>();
    private static boolean isEnabled = true;
    private final static Scanner sc = new Scanner(System.in);
    private static Random random = new Random();


    private static void chooseAction() {
        System.out.println("Input the action (add, remove, import, export, ask, exit):");
        String action = sc.nextLine().trim();
        if (action.equals("add")) {
            add();
        } else if (action.equals("remove")) {
            remove();
        } else if (action.equals("import")) {
            importCards();
        } else if (action.equals("export")) {
            exportCards();
        } else if (action.equals("ask")) {
            ask();
        } else if (action.equals("exit")) {
            System.out.println("Bye bye!");
            isEnabled = false;
        } else {
            System.out.println("Sorry, I cannot understand.");
        }
        System.out.println();
    }


    private static void add() {
        System.out.println("The card:");
        String card = sc.nextLine();
        System.out.println("The definition:");
        String definition = sc.nextLine();
        cardToDefinition.put(card, definition);
        definitionToCard.put(definition, card);
        System.out.println("The pair (\"" + card + "\":\"" + definition + "\") is added.");
    }


    private static void remove() {
        System.out.println("The card:");
        String card = sc.nextLine();

        if (cardToDefinition.containsKey(card)) {
            definitionToCard.remove(cardToDefinition.get(card));
            cardToDefinition.remove(card);
            System.out.println("The card \"" + card +"\" was removed.");
        } else {
            System.out.println("Can't remove \"" + card + "\":  there is no such card.");
        }

    }


    private static void importCards() {
        System.out.println("File name:");
        String fileName = sc.nextLine();
        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            int amount = 0;
            while (reader.hasNextLine()) {
                String card = reader.nextLine();
                String definition = reader.nextLine();
                cardToDefinition.put(card, definition);
                definitionToCard.put(definition, card);
                amount++;
            }
            System.out.println(amount + " cards have been loaded.");

        } catch (IOException e) {
            System.out.println("There is no file with this name");
        }
    }


    private static void exportCards() {
        System.out.println("File name:");
        String fileName = sc.nextLine();
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Map.Entry<String, String> couple: cardToDefinition.entrySet()) {
                writer.write(couple.getKey() + "\n");
                writer.write(couple.getValue() + "\n");
            }
            System.out.println(cardToDefinition.size() + " cards have been saved.");

        } catch (IOException e) {
            System.out.println("There is an error with file name.");
        }
    }


    private static void ask() {
        System.out.println("How many times to ask?");
        int amount = Integer.parseInt(sc.nextLine());
        String answer, key, value;

        for (int i = 0; i < amount; i++) {
            key = randomKey();
            value = cardToDefinition.get(key);
            System.out.println("Print the definition of \"" + key + "\":");
            answer = sc.nextLine();
            if (value.equals(answer)) {
                System.out.print("Correct answer. ");
            } else if (definitionToCard.containsKey(answer)) {
                System.out.print("Wrong answer (the correct one is \"" + value +
                        "\", you've just written a definition of \"" + definitionToCard.get(answer) + "\" card).");
            } else {
                System.out.print("Wrong answer (the correct one is \"" + value + "\").");
            }
        }

    }


    private static String randomKey() {
        int number = random.nextInt(cardToDefinition.size());
        int i = 0;

        for (String key: cardToDefinition.keySet()) {
            if (i == number) {
                return key;
            }
            i++;
        }
        return null;
    }


    public static void main(String[] args) {

        while (isEnabled) {
            chooseAction();
        }


        /*
        int n;
        String card;
        String definition;
        String answer;
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
        */
    }
}
