import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

		
	// function that picks a random word from the words.txt
	static String wordPicker(ArrayList<String> a) {
		
		Random rand = new Random();
		
		// returns the random string 
		return a.get(rand.nextInt(a.size()));
	}
	
	
	
	
	// for duplicate characters
	public static boolean hasDuplicateCharacters(String str) {
	    Set<Character> charSet = new HashSet<>();
	   
	   
	    for (char c : str.toCharArray()) {
	    	
	    	// if the charset already has the character 
	        if (charSet.contains(c)) {
	        	System.out.print(c + "\n");
	            return true; 
	        }
	        
	        // otherwise add the character 
	        charSet.add(c);
	        
	    }
	    // if there are no double characters
	    return false; 
	}

	
	
	public static void main(String[] args) {
		
		// Arraylist for sprites that are gonna be used for the hangman character
		final ArrayList<String> sprites = new ArrayList<String>();
		
		// an arraylist that will contain all words from the words.txt
		final ArrayList<String> words = new ArrayList<String>();
		
		// the file thats going to be used
		File file = new File("words.txt");
		Scanner scan;
		try {
			
			// new scanner with the file as input
			scan = new Scanner(file);
			
			// word to add to the words arraylist
			String word;
			
		
			while(scan.hasNextLine()) {
				
				// adds the word from the specific line to the arraylist
				words.add(scan.next());
			}
			// stop scanning for words when there is nothing to scan
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		// sprites for hangman in ascii characters
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ " =====");
		
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "  0  |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ " =====");
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "  0  |\r\n"
				+ "  |  |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ " =====");
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "  0  |\r\n"
				+ " /|\\ |\r\n"
				+ "     |\r\n"
				+ "     |\r\n"
				+ " =====");
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "  0  |\r\n"
				+ " /|\\ |\r\n"
				+ "   \\ |\r\n"
				+ "     |\r\n"
				+ " =====");
		sprites.add("  +--+\r\n"
				+ "  |  |\r\n"
				+ "  0  |\r\n"
				+ " /|\\ |\r\n"
				+ " / \\ |\r\n"
				+ "     |\r\n"
				+ " =====");
		
		System.out.println("WELCOME TO HANGMAN!\n");
		
		// the hiddenword that must be guessed
//		String hiddenWord = wordPicker(words);
//		System.out.println(hiddenWord);
		
		// testword only for debugging 
		String hiddenWord = "carrot";
		
		int hiddenWordsize = hiddenWord.length();
		
		// scanning for input from the player
		Scanner Playerguess = new Scanner(System.in);
		
		// sill be used for comparsions instead of playerguess
		String guess = "";
		
		// String for the player to see how big the word is and where the characters are succesfully guessed
		// used a StringBuilder so the characters can be appended with .append much easier this way
		StringBuilder hidden = new StringBuilder();
		
		
		

		
		// true or false if the word has a duplicated character. see linenumber 24 
		Boolean hasdup = hasDuplicateCharacters(hiddenWord);
	
		// too keep track of the players wrong guesses
		int timesFalse = 1;
		
		
		for(int i = 0; i < hiddenWordsize; i++) {
			hidden = hidden.append("_");
			
		}
		
		
		System.out.println(sprites.get(0));
		
		System.out.println(hidden);
	
		// game begins 
		while(true) {
			guess = Playerguess.next();
			
			if(hiddenWord.contains(guess)) {
				
				
				// what the person is guessing (first character)
				char guessedChar = guess.charAt(0); 
				

				// if the word has an duplicate 
				if (hasdup) {
					int index = -1;
					for(char d : hiddenWord.toCharArray()) {
						index++;
						if (d == guessedChar) {
							// puts the character at the right spot
							hidden.setCharAt(index, guessedChar);

						}
						
					}
					
					
				}
				
				// if the word has no duplicate
				else{
					hidden.setCharAt(hiddenWord.indexOf(guess), guessedChar);
				}
				
				
				// print the updated version of the hiddenword
				System.out.println(hidden);
				
			}
			
			// if the player has takes the wrong guess
			if(!hiddenWord.contains(guess)) {
				
				System.out.println(sprites.get(timesFalse));
				
				
				timesFalse ++;
				
				System.out.println(hidden);
			}
			
			
			// player loses
			if(timesFalse == 6) {
				System.out.println("You've lost!");
				System.out.println("The Right word was :\n" + hiddenWord);
				break; 
				
			}
			
			//player wins
			if (hiddenWord.equals(hidden.toString())) {
				Playerguess.close();
				System.out.println("!!!You've won!!!");
				break;
			}
		}
		

	}

}
