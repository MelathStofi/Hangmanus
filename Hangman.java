import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

	private static String[] words = {
		"caterpillar",
		"human",
		"toxin",
		"crane",
		"pastel",
		"queue"
	};
	
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
	
	private static void fillResultArray(char[] result, int length) {
		for (int i = 0; i < length; i++) {
			result[i] = '_';
		}
	}
	
	private static void checkLetter(char[] result, char guessedLetter, String word) {
		// Iterating trough the letters of the randomly chosen word
        for(int i = 0; i < word.length(); i++){
            char wordChar = word.charAt(i);
            if(wordChar == guessedLetter){
                result[i] = wordChar;
            }
        }
	}
	
	public static void main(String[] args) {
		boolean playTime = true;
		while (playTime == true) { 
	
			System.out.println(Arrays.toString(words));
			String word = getRandomWord();
			char result[] = new char[word.length()];
			fillResultArray(result, word.length());
		    System.out.println(word); // shows the word for easier testing
		    
		    boolean win = false;
		    while( win != true ){
		    
		        char guessedLetter = getCharInput();
				checkLetter(result, guessedLetter, word);
		        System.out.println(result);
		        System.out.println("");
		    }
        }
	}
}
