package flashcards;

import java.util.*;

public class cards {
    //main subroutine for choosing menu items
    protected static void mainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the action (add, remove, import, export, ask, exit): ");
        String menuChoice = scan.nextLine();
        switch (menuChoice){
            case "add":
                break;
            case "remove":
                break;
            case "import":
                break;
            case "export":
                break;
            case "ask":
                break;
            case "exit":
                exit();
                break;
        }
    }
    //subroutine for adding cards
    protected static void addCard(){

    }
    //subroutine for removing cards
    protected  static void removeCard(){
    }
    //subroutine for import
    protected static void importCard(){
    }
    //subroutine for export
    protected static void exportCard(){
    }
    //subroutine for asking user
    protected static void askCard(){
    }
    //exit subroutine
    protected static void exit(){
        System.out.println("Bye bye!");
        System.exit(0);
    }

}
