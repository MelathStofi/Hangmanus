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
	// Getting a char input from the user
        Scanner input = new Scanner(System.in);
        char letter = input.next().charAt(0);
        return letter;
	}
	
	private static void fillResultArray(char[] result, int length) {
		for (int i = 0; i < length; i++) {
			result[i] = '_';
		}
	}
	
	private static boolean insertLetter(char[] result, char guessedLetter, String word) {
		// Iterating trough the letters of the randomly chosen word
		boolean isInWord = false;
        for(int i = 0; i < word.length(); i++){
            char wordChar = word.charAt(i);
            if(wordChar == guessedLetter){
                result[i] = wordChar;
                isInWord = true;
            }
        }
        return isInWord;
	}
	
	private static boolean checkWin(char[] result, int length) {
		for (int i = 0; i < length; i++) {
			if (result[i] == '_') {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean playTime = true;
		while (playTime == true) {
			System.out.println(Arrays.toString(words));
			String word = getRandomWord();
			int wrongGuessNumber = 0;
			char[] wrongGuesses = new char[6];
			char result[] = new char[word.length()];
			fillResultArray(result, word.length());
		    System.out.println(word); // shows the word for easier testing
		    
		    boolean isWin = false;
		    while( isWin != true ){
		    	System.out.println("\nGuess a letter:");
		        char guessedLetter = getCharInput();
				boolean isRightGuess = insertLetter(result, guessedLetter, word);
				if (isRightGuess == false) {
					wrongGuesses[wrongGuessNumber] = guessedLetter;
					wrongGuessNumber++;
				}
				if (wrongGuessNumber != 0) {
					System.out.println("Wrong letters: ");
					for (int i = 0; i < wrongGuessNumber; i++) {
			   			System.out.print(wrongGuesses[i] + "  ");
			   		}
			   		System.out.println("");
		   		}
		   		System.out.println("");
		        System.out.println(result);
		        System.out.println("");
				isWin = checkWin(result, word.length());
		    }
		    System.out.println("\nVictory!\n");
		    System.out.println("Another game? (y/n): ");
		    char playAgain = ' ';
		    while (playAgain != 'y' && playAgain != 'Y') {
				playAgain = getCharInput();
				if (playAgain == 'n' || playAgain == 'N') {
					playTime = false;
					break;
				}
		    }
        }
	}
}
