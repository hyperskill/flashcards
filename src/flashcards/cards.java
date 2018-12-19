package flashcards;

import java.util.Scanner;

public class cards {

    protected static void cardInput(){
        Scanner input = new Scanner(System.in);
        //ask user for input number of cards
        System.out.println("Input the number of cards: ");
        int numOfCards = input.nextInt();
        //Array's for storing cards and definitions
        String[] card = new String[numOfCards];
        String[] definition = new String[numOfCards];
        //for loop for getting cards from user and put them in arrays
        for (int i = 0; i < numOfCards; i++){
            System.out.println("The card #" + (i + 1) + ": ");
            card[i] = input.nextLine();
            System.out.println("The definition of the card #" + (i + 1) + ": ");
            definition[i] = input.nextLine();
        }
        //iterate over cards and ask for answer
        for (int i = 0; i < card.length; i++){
            System.out.println("Print the definition of " + card[i] + ": ");
            String answer = input.nextLine();
            if (answer.equals(definition[i])){
                System.out.println("Correct answer.");
            } else {
                System.out.println("Wrong answer (the correct one is \"" + definition[i] + "\")");
            }
        }
    }
}
