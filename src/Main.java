import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

	static void startMenu() {
		System.out.println("WELCOME TO HANGMAN!\n");
		
	}
		
	// random word from words.txt which is an Arraylist
	static String wordPicker(ArrayList<String> a) {
		
		Random rand = new Random();
		
		return a.get(rand.nextInt(a.size()));
	}
	
	
	
	
	
	public static boolean hasDuplicateCharacters(String str) {
	    Set<Character> charSet = new HashSet<>();
	   

	    for (char c : str.toCharArray()) {
	        if (charSet.contains(c)) {
	        	
	            return true; // Found a duplicate character
	        }
	        
	        
	        charSet.add(c);
	        
	    }

	    return false; // No duplicate characters found
	}

	
	
	public static void main(String[] args) {
		// HANGMAN GAME JAVA
		String filename = "words.txt";
		final ArrayList<String> sprites = new ArrayList<String>();
		final ArrayList<String> words = new ArrayList<String>();
		File file = new File(filename);
		Scanner scan;
		try {
			scan = new Scanner(file);
			
			String word;
			
			while(scan.hasNextLine()) {
				word = scan.next();
				words.add(word);
				
				
			}
			scan.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		// intialisation
		// extra \ because its used for escaper characters
		
		
		// sprits for hangman
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "=====");
		
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ " 0  |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "=====");
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ " 0  |\r\n"
				+ " |  |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "=====");
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ " 0  |\r\n"
				+ "/|\\ |\r\n"
				+ "    |\r\n"
				+ "    |\r\n"
				+ "=====");
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ " 0  |\r\n"
				+ "/|\\ |\r\n"
				+ "  \\ |\r\n"
				+ "    |\r\n"
				+ "=====");
		sprites.add(" +--+\r\n"
				+ " |  |\r\n"
				+ " 0  |\r\n"
				+ "/|\\ |\r\n"
				+ "/ \\ |\r\n"
				+ "    |\r\n"
				+ "=====");
		
		startMenu();
		
		String hiddenWord = wordPicker(words);
//		String hiddenWord = "carrot";
		int hiddenWordsize = hiddenWord.length();
		
//		System.out.println(hiddenWord);
		Scanner rguess = new Scanner(System.in);
		String guess = "";
		
		StringBuilder hidden = new StringBuilder();
		
		
		
		System.out.println(hiddenWordsize);
		
		Boolean hasdup = hasDuplicateCharacters(hiddenWord);
	
		int timesFalse = 1;
		
		for(int i = 0; i < hiddenWordsize; i++) {
			hidden = hidden.append("_");
			
		}
		
		
		System.out.println(sprites.get(0));
		
		System.out.println(hidden);
	
		while(!guess.equals(hiddenWord)) {
			guess = rguess.next();
			
			if(hiddenWord.contains(guess)) {
				
				
				
				char guessc = guess.charAt(0); // what the person is guessing (first character)
				
				System.out.println(guessc);
				
				if (hasdup) {
					int index = -1;
					for(char d : hiddenWord.toCharArray()) {
						index++;
						if (d == guessc) {
							System.out.println(index );
							hidden.setCharAt(index, guessc);
							System.out.println(d);
						}
						
					}
					
					
				}
				else if(hasdup == false) {
					hidden.setCharAt(hiddenWord.indexOf(guess), guessc);
				}
				
				
				System.out.println(hidden);
				
			}
			
			if(!hiddenWord.contains(guess)) {
				
				System.out.println(sprites.get(timesFalse));
				
				
				timesFalse ++;
				
				System.out.println(hidden);
			}
			
			if(timesFalse == 6) {
				System.out.println("You've lost!");
				System.out.println("The Right word was :\n" + hiddenWord);
				break; 
				
			}
			
			if (guess.equals(hiddenWord)) {
				break;
			}
		}
		
		
		
		System.out.println(guess);
		
//		
	
		
		

	}

}
