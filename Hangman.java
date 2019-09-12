import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import com.codecool.termlib.*;

public class Hangman {

	private static String[] files = {
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows1.txt",
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows2.txt",
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows3.txt",
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows4.txt",
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows5.txt",
		"/home/melath/codecool/OOP/Hangmanus/gallows/gallows6.txt"
	};

	private static String[] easy = {
		"pear",
		"strawberry",
		"melon",
		"apple",
		"banana",
		"orange",
		"mango"
	};

	private static String[] medium = {
		"caterpillar",
		"human",
		"toxin",
		"crane",
		"pastel",
		"queue",
		"asdffddg"
		
	};

	private static String[] hard = {
		"rhythmic",
		"oxygen",
		"fishhook",
		"loci",
		"yacht",
		"rogue"

	};
	
	private static String getRandomWord(String[] words) {
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

	private static boolean sameLetterCheck(char[] checkingArray, char guessedLetter){
		int length = checkingArray.length;
		boolean alreadyGuessed = false;
		for (int i = 0; i < length; i++){
			if(checkingArray[i] == guessedLetter){
				System.out.println("You already guessed this letter!");
				alreadyGuessed = true;
				return alreadyGuessed;
			}
		}
		alreadyGuessed = false;
		return alreadyGuessed;
	}
	
	private static boolean checkWin(char[] result, int length) {
		for (int i = 0; i < length; i++) {
			if (result[i] == '_') {
				return false;
			}
		}
		return true;
	}

	private static boolean playAgainCheck(){

		char playAgain = ' ';
		while (playAgain != 'y' && playAgain != 'Y') {
			playAgain = getCharInput();
			if (playAgain == 'n' || playAgain == 'N') {
				boolean playTime = false;
				return playTime;
			}
		}
		boolean playTime = true;
		return playTime;
	}
	
	private static void rajzolosFuggveny(int index, Terminal term) throws java.io.FileNotFoundException {
		File file = new File(files[index]);
		Scanner reader = new Scanner(file);
		while (reader.hasNextLine()) {
			term.setColor(Color.RED);
			System.out.println(reader.nextLine());
			term.setColor(Color.BLUE);
		}
	}

	public static String[] difficultyChoose(){
		System.out.println("Ohh, you are here to play a good-old Hangman?\nPlease choose a difficulty level!");
		System.out.println("Press 1 for easy gameplay\nPress 2 for medium gameplay\nPress 3 for hard gameplay");
		char difficultyChoice = getCharInput();
		if(difficultyChoice == '1'){
			return easy;
		}
		if(difficultyChoice == '2'){
			return medium;
		}
		if(difficultyChoice == '3'){
			return hard;
		}

		return null;
	}
	
	public static void main(String[] args) throws java.io.FileNotFoundException {
		Terminal term = new Terminal();
		term.setColor(Color.BLUE);
		
		boolean playTime = true;
		while (playTime == true) {
			String[] words = difficultyChoose();
			String word = getRandomWord(words);
			int wrongGuessNumber = 0;

			char[] wrongGuesses = new char[6];
			char result[] = new char[word.length()];
			fillResultArray(result, word.length());
		    boolean isWin = false;
			boolean gameLose = false;
		    while( isWin != true && gameLose != true ){
		    	System.out.println("\nGuess a letter:");
		        char guessedLetter = getCharInput();

				boolean letterCheck = sameLetterCheck(result,guessedLetter);
				boolean isRightGuess = insertLetter(result, guessedLetter, word);

				if (isRightGuess == false) {
					boolean wrongLetterCheck = sameLetterCheck(wrongGuesses,guessedLetter);
					if(wrongLetterCheck == false){
						wrongGuesses[wrongGuessNumber] = guessedLetter;
						++wrongGuessNumber;
					}
				}
				
				if(wrongGuessNumber == wrongGuesses.length){
					gameLose = true;
				}
				
				if (wrongGuessNumber != 0) {
					System.out.println("Wrong letters: ");
					for (int i = 0; i < wrongGuessNumber; i++) {
						System.out.print(wrongGuesses[i] + "  ");
					}
					System.out.println("");
				}
				if (wrongGuessNumber > 0) {
					System.out.println(wrongGuessNumber);
					rajzolosFuggveny(wrongGuessNumber-1, term);
				}
				System.out.println("");
				System.out.println(result);
				System.out.println("");
				isWin = checkWin(result, word.length());
		    }
		    if(isWin == true){
		    	term.setColor(Color.YELLOW);
				System.out.println("\nVictory!\n");
				term.setColor(Color.BLUE);
				
			}
			else if(gameLose == true){
				term.setColor(Color.RED);
				System.out.println("\nYou lost this one!:(\n");
				term.setColor(Color.BLUE);
			}
			System.out.println("Another game? (y/n): ");
			playTime = playAgainCheck();
			term.clearScreen();
        }
	}
}
