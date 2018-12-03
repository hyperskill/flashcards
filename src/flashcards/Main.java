package flashcards;

import java.util.*;
import java.io.*;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static Map<String, String> cardsMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        String command = "";
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            command = scn.next();
            switch (command) {
                case "import":
                    importCmd();
                    break;
                case "add":
                    addCmd();
                    break;
                case "remove":
                    removeCmd();
                    break;
                case "export":
                    exportCmd();
                    break;
                case "ask":
                    askCmd();
                    break;
                case "print":
                    System.out.println(cardsMap);
            }
        } while (!command.equals("exit"));
    }

    private static void askCmd() {
        scn = new Scanner(System.in);
        String answer;
        System.out.println("How many times to ask?");
        int count = scn.nextInt();
        int i = 0;
        scn = new Scanner(System.in);
        for (Map.Entry<String, String> card : cardsMap.entrySet()) {
            i++;
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
            if (count == i) {
                break;
            }
        }
    }

    private static void exportCmd() {
        scn = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scn.nextLine();
        File file = new File(fileName);
        try (FileWriter printWriter = new FileWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            for (Map.Entry<String, String> card : cardsMap.entrySet()) {
                printWriter.write(card.getKey() + "\n");
                printWriter.write(card.getValue() + "\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void importCmd() {
        scn = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scn.nextLine();
        File file = new File(fileName);
        int count = 0;
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNextLine()) {
                count++;
                cardsMap.put(scn.nextLine(), scn.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count + " cards have been loaded.");
    }

    private static void addCmd() {
        scn = new Scanner(System.in);
        System.out.println("The card:");
        String card = "";
        while (true) {
            card = scn.nextLine();
            if (!cardsMap.containsKey(card)) {
                break;
            }
            System.out.println("Cant add. The card is exist.");
        }
        System.out.println("The definition of the card:");
        String definition = scn.nextLine();
        cardsMap.put(card, definition);
        System.out.println("The pair (\"" + card + "\":\"" + definition + "\") is added.");

    }

    private static void removeCmd() {
        scn = new Scanner(System.in);
        System.out.println("The card:");
        String card = scn.nextLine();
        if (cardsMap.containsKey(card)) {
            cardsMap.remove(card);
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.\n");
        }
    }

}