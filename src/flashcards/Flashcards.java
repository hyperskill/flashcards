package flashcards;
import java.util.*;
import java.io.*;
//class for create cards. behavior; add,ask,remove,import,export

class Flashcards {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, String> cardToDefinition = new HashMap<>();
    private Map<String, String> definitionToCard = new HashMap<>();

    //add cards and definition
    void add() {
        System.out.println("The card:");
        String userCard = scanner.next();
        System.out.println("The definition of the card");
        String userDefinition = scanner.next();
        if (cardToDefinition.containsKey(userCard) || cardToDefinition.containsValue(userDefinition)) {
            System.out.println("Don't use a duplicated card or a definition");
        } else {
            cardToDefinition.put(userCard, userDefinition);
            definitionToCard.put(userDefinition, userCard);
            System.out.println("The pair (" + userCard + ":" +userDefinition + ") is added.");
        }
    }

    //asking user and checking it
    void ask() {
        System.out.println("How many times to ask?");
        int numOfAsk = scanner.nextInt();
        for (Map.Entry<String, String> card : cardToDefinition.entrySet()) {
            System.out.println("Print the definition of (" + card.getKey() + "):");
            String answer = scanner.next();
            if (answer.equals(card.getValue())) {
                System.out.print("Correct answer.");
            } else if (cardToDefinition.containsValue(answer)) {
                // If the definition is wrong but it is presented in another card
                System.out.print("Wrong answer (the correct one is " + card.getValue()+ ", you've just written a definition of " + definitionToCard.get(answer) + " card).");
            } else {
                System.out.print("Wrong answer (the correct one is " + card.getValue() + ").");
            }
            if (--numOfAsk==0) {
                break;
            }
        }
    }

    //remove card
    void remove() {
        System.out.println("The card:");
        String userCard = scanner.nextLine();
        if (cardToDefinition.containsKey(userCard)) {
            definitionToCard.remove(cardToDefinition.get(userCard));
            cardToDefinition.remove(userCard);
            System.out.println("(" + userCard + ") removed.");
        } else {
            System.out.println("Can't remove (" +userCard + "): there is no such card.");
        }
    }


    // import cards from txt
    void importCards() {
        String pathToFile = "E:/_LearnProgramming/IdeaProjects/flashcards/capitals.txt";
        File file = new File(pathToFile);
        int counter=0;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                String userCard = reader.next();
                String userDefinition = reader.next();
                counter++;
                if (cardToDefinition.containsKey(userCard) || cardToDefinition.containsValue(userDefinition)) {
                    System.out.println("Don't use a duplicated card or a definition");
                    counter--;
                } else {
                    cardToDefinition.put(userCard, userDefinition);
                    definitionToCard.put(userDefinition, userCard);
                }
            }
            System.out.println(counter + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
    }

    void exportCards() {
        String pathToFile = "E:/_LearnProgramming/IdeaProjects/flashcards/capitals_export.txt";
        File file = new File(pathToFile);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (Map.Entry<String, String> card : cardToDefinition.entrySet()) {
                printWriter.println(card.getKey());
                printWriter.println(card.getValue());
            }
            System.out.println(cardToDefinition.size() + " cards have been saved.");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }
}
