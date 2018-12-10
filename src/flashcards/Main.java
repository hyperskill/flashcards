package flashcards;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean exit = false;
        Map<String,String> cardToDefinition = new HashMap<>();
        Map<String,String> definitionToCard = new HashMap<>();
        while (!exit)
        {
            String card;
            String def;
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            switch (br.readLine().toLowerCase())
            {
                case "add":
                    System.out.println("The card:");
                    card = br.readLine();
                    System.out.println("The definition of the card:");
                    def = br.readLine();
                    cardToDefinition.put(card,def);
                    definitionToCard.put(def,card);
                    break;
                case "remove":
                    System.out.println("The card:");
                    card = br.readLine();
                    if (cardToDefinition.containsKey(card)&&definitionToCard.containsValue(card))
                    {
                        definitionToCard.remove(cardToDefinition.get(card));
                        cardToDefinition.remove(card);
                        System.out.println("Card \""+card+"\" has been removed.");
                    }
                    else
                    {
                        System.out.println("Can't remove \""+card+"\": there is no such card.");
                    }
                    break;
                case "import":
                    System.out.println("File name:");
                    try (FileReader fr = new FileReader(br.readLine()))
                    {
                        cardToDefinition.clear();
                        definitionToCard.clear();
                        Scanner s = new Scanner(fr);
                        while (s.hasNextLine())
                        {
                            card = s.nextLine();
                            def = s.nextLine();
                            cardToDefinition.put(card,def);
                            definitionToCard.put(def,card);
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "export":
                    System.out.println("File name:");
                    try (FileWriter fw = new FileWriter(br.readLine(),false))
                    {
                        for (Map.Entry<String, String> entry : cardToDefinition.entrySet()) {
                            fw.write(entry.getKey()+"\n");
                            fw.write(entry.getValue()+"\n");
                        }
                        fw.flush();
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "ask":
                    System.out.println("How many times to ask?");
                    int count = Integer.parseInt(br.readLine());
                    for (int i = 0; i < count; i++) {
                        Random rnd = new Random();
                        int n = rnd.nextInt(cardToDefinition.size());
                        Map.Entry<String, String> cardentry = (Map.Entry<String, String>)cardToDefinition.entrySet().toArray()[n];
                        System.out.println("Print the definition of \""+cardentry.getKey()+"\":");
                        String answer = br.readLine();
                        if (cardentry.getValue().equals(answer)) System.out.println("Correct answer.");
                        else
                            System.out.println("Wrong answer (the correct one is \""+cardentry.getValue()+"\""+(definitionToCard.containsKey(answer)?
                                    ", you've just written a definition of \""+definitionToCard.get(answer) +"\" card).": ")."));
                    }
                    break;
                case "exit":
                    exit = true;
                    System.out.println("Bye bye!");
                    break;
            }
            System.out.println();
        }
    }
}