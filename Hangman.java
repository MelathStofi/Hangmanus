import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

	private static String[] words = {"caterpillar", "human", "toxin", "crane", "pastel", "queue"};
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(words));

		String[] fruits = { "orange", "apple", "strawberry", "banana", "pear" };
        int arrayLength = fruits.length;
        int randomChoice = (int)(Math.random()*arrayLength);
        String fruit = fruits[randomChoice];
        System.out.println(fruit); // shows the fruit for easier testing
        
        boolean win = false;
        while( win = true ){
        
            // Getting a char input (guessing letter) from the user
            Scanner letter = new Scanner(System.in);
            System.out.println("Guess a letter:");
            char guessedLetter = letter.next().charAt(0); 


            // Iterating trough the random choosed fruit letters
            for(int i = 0; i < fruit.length(); i++){
                char fruitChar = fruit.charAt(i);

                if(fruitChar == guessedLetter){
                    System.out.print(fruitChar);
                }
                else{
                    System.out.print("-");
                }
            }
            System.out.println("");

           // System.out.println(guessedFruitLetters);
        };
	}
}
