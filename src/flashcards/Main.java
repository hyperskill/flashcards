/*
There is a common situation that the answer is wrong for the given card but it's correct for another card. Let's consider situations like this.
Remove array-based storage. Use two maps (Map<String, String>): cardToDefinition and definitionToCard. If the definition is wrong but it is presented
in definitionToCard, output the original card.
When the user tries to add a duplicated card or a definition, forbid it. For now you are able to implement this without a try catch construction.
Use the rule: if you can avoid exception-based logic, avoid it!
 */

package flashcards;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int numOfCards = scanner.nextInt();
        Map<String, String> cardToDefinition = new HashMap<>();
        Map<String, String> definitionToCard = new HashMap<>();

        //creating 2 Map's with cards and definition (and opposite)
        for (int i=0; i<numOfCards; i++) {
            System.out.println("The card #" + (i+1) + ":");
            String userCard = scanner.next();
            System.out.println("The definition of the card #" + (i+1) + ":");
            String userDefinition = scanner.next();
            if (cardToDefinition.containsKey(userCard) || cardToDefinition.containsValue(userDefinition)) {
                System.out.println("Don't use a duplicated card or a definition");
                return;
            } else {
                cardToDefinition.put(userCard, userDefinition);
                definitionToCard.put(userDefinition, userCard);
            }
        }
        // checking user answer
        for (Map.Entry<String, String> card : cardToDefinition.entrySet()) {
            System.out.println("Print the definition of (" + card.getKey() + "):");
            String answer = scanner.next();
            if (answer.equals(card.getValue())) {
                System.out.print("Correct answer. ");
            } else if (cardToDefinition.containsValue(answer)) {
                System.out.print("Wrong answer (the correct one is " + card.getValue()+ ", you've just written a definition of " + definitionToCard.get(answer) + " card).");
            } else {
                System.out.print("Wrong answer (the correct one is " + card.getValue() + ").");
            }
        }
    }
}