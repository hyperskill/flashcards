package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Main {


    private static String userNextLine() {
        String str = scanner.nextLine();
        logList.add(str);
        return str;
    }


    private static void userPrintln(String string) {
        if (string != null) {
            System.out.println(string);
            logList.add(string);
        } else {
            System.out.println();
        }
    }


    private static void userPrint(String string) {
        System.out.print(string);
        logList.add(string);
    }



    private static Map<String, String> cardToDefinition = new LinkedHashMap<>();
    private static Map<String, String> definitionToCard = new LinkedHashMap<>();
    private static Map<String, Integer> cardToMistakes = new LinkedHashMap<>();
    private static ArrayList<String> logList = new ArrayList<>();
    private static boolean isEnabled = true;
    private static boolean needExport = false;
    private static String exportFileName;
    private static final Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();


    private static void chooseAction() {
        userPrintln("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        String action = userNextLine().trim();
        switch (action) {
            case "add":
                add();
                break;
            case "remove":
                remove();
                break;
            case "import":
                importCards();
                break;
            case "export":
                exportCards();
                break;
            case "ask":
                ask();
                break;
            case "exit":
                userPrintln("Bye bye!");
                isEnabled = false;
                if (needExport) {
                    doExport(exportFileName);
                }
                break;
            case "log":
                log();
                break;
            case "hardest card":
                hardestCard();
                break;
            case "reset stats":
                resetStats();
                break;
            default:
                userPrintln("Sorry, I cannot understand. Please try again.");
                break;
        }

        userPrintln(null);

    }


    private static void add() {
        userPrintln("The card:");
        String card = userNextLine();
        userPrintln("The definition:");
        String definition = userNextLine();

        cardToDefinition.put(card, definition);
        definitionToCard.put(definition, card);
        cardToMistakes.put(card, 0);
        userPrintln("The pair (\"" + card + "\":\"" + definition + "\") is added.");
    }


    private static void remove() {
        userPrintln("The card:");
        String card = userNextLine();

        if (cardToDefinition.containsKey(card)) {
            definitionToCard.remove(cardToDefinition.get(card));
            cardToDefinition.remove(card);
            cardToMistakes.remove(card);
            userPrintln("The card \"" + card +"\" was removed.");
        } else {
            userPrintln("Can't remove \"" + card + "\":  there is no such card.");
        }

    }


    private static void importCards() {
        userPrintln("File name:");
        String fileName = userNextLine();

        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            int amount = 0;
            while (reader.hasNextLine()) {
                String card = reader.nextLine();
                String definition = reader.nextLine();
                String mistakes = reader.nextLine();
                cardToDefinition.put(card, definition);
                definitionToCard.put(definition, card);
                cardToMistakes.put(card, Integer.parseInt(mistakes));
                amount++;
            }
            userPrintln(amount + " cards have been loaded.");

        } catch (IOException e) {
            userPrintln("There is no file with this name");
        }
    }


    private static void exportCards() {
        userPrintln("File name:");
        String fileName = userNextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Map.Entry<String, String> couple: cardToDefinition.entrySet()) {
                writer.write(couple.getKey() + "\n");
                writer.write(couple.getValue() + "\n");
                writer.write(cardToMistakes.get(couple.getKey()) + "\n");
            }
            userPrintln(cardToDefinition.size() + " cards have been saved.");

        } catch (IOException e) {
            userPrintln("There is an error with file name.");
        }
    }


    private static void ask() {
        userPrintln("How many times to ask?");
        int amount = Integer.parseInt(userNextLine());

        String answer, key, value;

        if (amount <= cardToDefinition.size()) {
            for (int i = 0; i < amount; i++) {
                key = randomKey();
                value = cardToDefinition.get(key);
                userPrintln("Print the definition of \"" + key + "\":");
                answer = userNextLine();

                if (value.equals(answer)) {
                    userPrint("Correct answer. ");
                } else if (definitionToCard.containsKey(answer)) {
                    cardToMistakes.put(key, cardToMistakes.get(key) + 1);
                    userPrint("Wrong answer (the correct one is \"" + value +
                            "\", you've just written a definition of \"" + definitionToCard.get(answer) + "\" card). ");
                } else {
                    cardToMistakes.put(key, cardToMistakes.get(key) + 1);
                    userPrint("Wrong answer (the correct one is \"" + value + "\"). ");
                }
            }
        } else {
            userPrintln("Sorry, I have only " + amount + " card(s).");
        }

    }


    private static void log() {
        userPrintln("File name:");
        String fileName = userNextLine();

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line: logList) {
                writer.write(line + "\n");
            }
            userPrintln("The log has been saved.");

        } catch (IOException e) {
            userPrintln("There is an error with file name.");
        }

    }


    private static void hardestCard() {

        if (cardToMistakes.size() > 0) {
            int max = -1;
            String cardWithMaxMistakes = null;

            for (Map.Entry<String, Integer> couple: cardToMistakes.entrySet()) {
                if (couple.getValue() > max) {
                    cardWithMaxMistakes = couple.getKey();
                    max = couple.getValue();
                }
            }
            userPrintln("The hardest card is \"" + cardWithMaxMistakes + "\". " +
                    "You have " + max + " errors answering it.");

        } else {
            userPrintln("No cards are here.");
        }

    }


    private static void resetStats() {
        for (String key: cardToMistakes.keySet()) {
            cardToMistakes.put(key, 0);
        }
        userPrintln("Stats reset");
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


    private static void doImport(String fileName) {
        File file = new File(fileName);
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String card = reader.nextLine();
                String definition = reader.nextLine();
                String mistakes = reader.nextLine();
                cardToDefinition.put(card, definition);
                definitionToCard.put(definition, card);
                cardToMistakes.put(card, Integer.parseInt(mistakes));
            }
        } catch (IOException e) {}
    }


    private static void doExport(String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {
            for (Map.Entry<String, String> couple: cardToDefinition.entrySet()) {
                writer.write(couple.getKey() + "\n");
                writer.write(couple.getValue() + "\n");
                writer.write(cardToMistakes.get(couple.getKey()) + "\n");
            }
        } catch (IOException e) {}
    }


    public static void main(String[] args) {

        if (args.length % 2 == 0) {
            for (int i = 0; i < args.length; i+=2) {
                if (args[i].equals("-import")) {
                    doImport(args[i+1]);
                } else if (args[i].equals("-export")) {
                    needExport = true;
                    exportFileName = args[i+1];
                }
            }
        }

        while (isEnabled) {
            chooseAction();
        }

    }
}
