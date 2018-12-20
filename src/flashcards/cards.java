package flashcards;

import java.util.*;

import static java.lang.System.*;

public class cards {

    protected static void cardInput(){
        Scanner input = new Scanner(in);
        //ask user for input number of cards
        out.println("Input the number of cards: ");
        int numOfCards = input.nextInt();
        //HashMap's for storing cards and definitions
        Map<String, String> cardToDefinition = new HashMap();
        Map<String, String> definitionToCard = new HashMap();
        //loop for populating HashMap with values
        for(int i = 0; i < numOfCards; i++){
            out.println("The card #" + i +":");
            String cardValue = input.nextLine();
            out.println("The definition of the card #" + i +":");
            String defValue = input.nextLine();
            if(cardToDefinition.containsKey(cardValue) || definitionToCard.containsKey(defValue)){
                out.println("Please reenter new value");
                if (i > 0) {
                    i--;
                } else {
                    i = 0;
                }
            } else {
                cardToDefinition.put(cardValue, defValue);
                definitionToCard.put(defValue, cardValue);
            }
        }
        //loop for answering questions
        cardToDefinition.forEach((key, value) ->{
            System.out.println("Print the definition of: " + key);
            String userAnswer = input.nextLine();
            if (!value.equals(userAnswer) && definitionToCard.containsKey(userAnswer)){
                System.out.println("Wrong answer (the correct one is \"" + value +"\", you've just written a definition of \"" + definitionToCard.get(userAnswer) + "\" card). Print the definition of \"" + definitionToCard.get(userAnswer) + "\":");
                userAnswer = input.nextLine();
                if (cardToDefinition.containsKey(userAnswer)){
                    out.println("Correct");
                } else {
                    System.out.println(cardToDefinition.get(userAnswer));
                }
            } else {
                System.out.println("Correct");
            }

        });
    }
}
