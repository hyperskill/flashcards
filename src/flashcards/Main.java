/*
Stage 4: There is a common situation that the answer is wrong for the given card but it's correct for another card. Let's consider situations like this.
Remove array-based storage. Use two maps (Map<String, String>): cardToDefinition and definitionToCard. If the definition is wrong but it is presented
in definitionToCard, output the original card.
When the user tries to add a duplicated card or a definition, forbid it. For now you are able to implement this without a try catch construction.
Use the rule: if you can avoid exception-based logic, avoid it!

Stage 5: Improve the application’s interactivity. Ask the user for an action and do it.
Support these actions:
add a card: add,
remove a card: remove,
load cards from file ("deserialization"): import,
save cards to file ("serialization"): export,
ask for definition of some random cards: ask,
exit the program: exit.
You can use the following file format. The file consists of pairs of lines. The first line of each pair is a card, and the second line is a definition of the card.
 */

package flashcards;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        usingFlashcards();
    }

    private static void usingFlashcards() {
        Scanner scanner = new Scanner(System.in);
        Flashcards flashcards = new Flashcards();
        String action;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.next();
            switch (action) {
                case "add":
                    flashcards.add();
                    break;
                case "remove":
                    flashcards.remove();
                    break;
                case "ask":
                    flashcards.ask();
                    break;
                case "import":
                    flashcards.importCards();
                    break;
                case "export":
                    flashcards.exportCards();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Wrong action!");
                    break;
            }
        } while (!action.equals("exit"));
        scanner.close();
        System.out.println("Bye bye!");
    }
}