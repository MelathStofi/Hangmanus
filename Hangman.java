import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

	private static String[] words = {"caterpillar", "human", "toxin", "crane", "pastel", "queue"};
	
	private static String getRandomWord() {
		int arrayLength = words.length;
        int randomChoice = (int)(Math.random()*arrayLength);
        String word = words[randomChoice];
        return word;
	}
	
	private static char getCharInput() {
		// Getting a char input (guessing letter) from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Guess a letter:");
        char letter = input.next().charAt(0);
        return letter;
	}
	
	public static void main(String[] args) {
	
		System.out.println(Arrays.toString(words));
		String word = getRandomWord();
        System.out.println(word); // shows the word for easier testing
        
        boolean win = false;
        while( win != true ){
        
            char guessedLetter = getCharInput();

            // Iterating trough the random chose word letters
            for(int i = 0; i < word.length(); i++){
                char wordChar = word.charAt(i);

                if(wordChar == guessedLetter){
                    System.out.print(wordChar);
                }
                else{
                    System.out.print("_");
                }
            }
            System.out.println("");
        };
	}
}
