package flashcards;

public class Main {
	private static Map<String, String> cards = new LinkedHashMap<>();
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<String> cardsInArray = new ArrayList();
	private static List<String> logs = new ArrayList<>();
	
	public static void main(String[] args) {
	     
		String action;
	    
		while (true) {
			System.out.println(cards);
			System.out.print("Input the action (add, remove, import, export, ask, exit): ");
			action = scanner.next();
			
			switch (action){
				case "add":
					add();
					break;
				case "remove":
					remove();
					break;
				case "import":
					imp();
					break;
				case "export":
					exp();
					break;
				case "ask":
					ask();
					break;
				case "log":
					log();
					break;
				case "hardest card":
					hardestCard();
					break;		
				case "exit":
					System.out.println("Bye bye!");
					System.exit(0);
					break;
				default :
					System.out.println("Unknown operation. Repeat action: ");
			}
		}
	}
	
	private static void add() {
		String card;
		String definition;
		System.out.print("The card: ");
		
		while (true) {
			card = scanner.next();
			if (cards.containsKey(card)) {
				System.out.print("Such card is exist. Input another card: ");
			}
			else {
				System.out.print("The definition of the card: ");
				definition = scanner.next();
				cards.put(card, definition);
				cardsInArray.add(card);
				break;
			}
		}	
		System.out.println("The pair (\""+ card +"\":\""+ definition +"\") is added.");
	}
	
	private static void remove() {
		String card;
		System.out.print("The card: ");
		card = scanner.next();
		if (cards.containsKey(card)) {
			cards.remove(card);
			cardsInArray.remove(card);
		}
		else {
			System.out.println("Can't remove \""+ card +"\": there is no such card.");
		}	
	}
	
	private static void exp() {
		System.out.println("Enter filename: ");
		String filename = scanner.next();
		File file = new File(filename);
		int count = 0;
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			if (!file.exists()) {
				file.createNewFile();
			}
			
			for (Map.Entry<String, String> card : cards.entrySet()) {
				bufferedWriter.write(card.getKey());
				bufferedWriter.append("\n");
				bufferedWriter.write(card.getValue());
				bufferedWriter.append("\n");
				count++;
			}
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		System.out.println(count +" cards have been loaded.");
	}
	
	private static void imp() {
		System.out.println("Enter filename: ");
		String filename = scanner.next();
		File file = new File(filename);
		int count = 0;
		
		try {
			BufferedReader  bufferedReader  = new BufferedReader(new FileReader(file));
			while (bufferedReader.ready()) {
				cards.put(bufferedReader.readLine(), bufferedReader.readLine());
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static void ask() {
		int askNumber;
		String answer;
		Random random = new Random();
		int randomNumber;
		
		System.out.println("How many times to ask?");
		askNumber = scanner.nextInt();
		
		for (int i = 0; i < askNumber; i++ ) {
			randomNumber = random.nextInt(cardsInArray.size());
			System.out.println("Print the definition of \""+ cards.get(cardsInArray.get(randomNumber))+"\":");
			answer = scanner.next();
			if (answer.equals(cards.get(cardsInArray.get(randomNumber)))) {
				System.out.println("Correct answer.");
			}
			else if (cards.containsValue(answer)) {
				System.out.println("Wrong answer. You've just written a definition of another card.");
			}
			else {
				System.out.println("Wrong answer.");
			}
		}
	}
	
	
	private static void checkCards() {
		String answer;
		for (Map.Entry<String, String> card : cards.entrySet()) {
			System.out.println("Print the definition of \""+ card.getKey()+"\":");
			answer = scanner.next();
			if (answer.equals(card.getValue())) {
				System.out.println("Correct answer.");
			}
			else if (cards.containsValue(answer)) {
				System.out.println("Wrong answer (The correct one is \""+ card.getValue() +"\"). You've just written a definition of another card.");
			}
			else {
				System.out.println("Wrong answer (The correct one is \""+ card.getValue() +"\").");
			}
		}
	}
	
	private static void log() {
		 
	}
	
	private static void hardestCard() {
		
	}    
}
