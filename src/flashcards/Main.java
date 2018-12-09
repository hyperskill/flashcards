package flashcards;

import java.util.*;
import java.io.*;

public class Main {

    private static Scanner scn = new Scanner(System.in);
    private static Map<String, String> cardsMap = new LinkedHashMap<>();
    private static Map<String, Integer> errorsMap = new HashMap<>();
    private static String exportFileName;

    public static void main(String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < args.length; i += 2) {
                switch (args[i]) {
                    case "-import":
                        importCmd(args[i + 1]);
                        break;
                    case "-export":
                        exportFileName = args[i + 1];
                        break;
                }
            }
        } else {
            System.out.println("The program must have 2 or more args");
            return;
        }
        String command = "";
        do {
            System.out.println("Input the action (add, remove, ask, exit, log, hardest card, reset stats):");
            command = scn.nextLine();
            switch (command) {
                case "add":
                    addCmd();
                    break;
                case "remove":
                    removeCmd();
                    break;
                case "ask":
                    askCmd();
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
                case "print":
                    System.out.println(cardsMap);
            }
        } while (!command.equals("exit"));
        exportCmd();
    }

    private static void resetStats() {
        errorsMap = new HashMap<>();
    }

    private static void hardestCard() {
        scn = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scn.nextLine();
        File file = new File(fileName);
        Map<String, Integer> tmpMap = new HashMap<>();
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNextLine()) {
                tmpMap.put(scn.nextLine(), Integer.parseInt(scn.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String hardestCard = "";
        int errors = 0;
        int tmpEr = 0;
        for (Map.Entry<String, Integer> error : tmpMap.entrySet()) {
            tmpEr = error.getValue();
            if (errors < tmpEr) {
                errors = tmpEr;
                hardestCard = error.getKey();
            }
        }
        System.out.println("The hardest card is " + hardestCard + ". You have " + errors + " errors answering it.");
    }

    private static void log() {
        scn = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = scn.nextLine();
        File file = new File(fileName);
        try (FileWriter printWriter = new FileWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            for (Map.Entry<String, Integer> card : errorsMap.entrySet()) {
                printWriter.write(card.getKey() + "\n");
                printWriter.write(card.getValue() + "\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The log has been saved");
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
                if (!errorsMap.containsKey(card.getKey())) {
                    errorsMap.put(card.getKey(), 1);
                } else {
                    int errors = errorsMap.get(card.getKey());
                    errorsMap.put(card.getKey(), errors + 1);
                }
            } else {
                System.out.println("Wrong answer (the correct one is \"" + card.getValue() + "\").");
                if (!errorsMap.containsKey(card.getKey())) {
                    errorsMap.put(card.getKey(), 1);
                } else {
                    int errors = errorsMap.get(card.getKey());
                    errorsMap.put(card.getKey(), errors + 1);
                }
            }
            if (count == i) {
                break;
            }
        }
    }

    private static void exportCmd() {
        String fileName = exportFileName;
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

    private static void importCmd(String nameFile) {
        String fileName = nameFile;
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