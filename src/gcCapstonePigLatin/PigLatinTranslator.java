package gcCapstonePigLatin;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PigLatinTranslator {

	public static Scanner scn = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(true) {
			
			System.out.println(lineTranslator(scn,"Enter a line to translate to pig latin: "));
			if(onwards(scn, "Would you like to translate another line? (y/n)")) {
			} else {
				break;
			}
		}
		
		/*
		 * The application prompts the user for a word. 
		 * ● The application translates the
		 * text to Pig Latin and displays it on the console. 
		 * ● The application asks the
		 * user if he or she wants to translate another word
		 */
		/*
		 * 1. Convert each word to a lowercase before translating. 
		 * 2. If a word starts
		 * with a vowel, just add “way” onto the ending. 
		 * 3. If a word starts with a
		 * consonant, move all of the consonants that appear before the first vowel to
		 * the end of the word, then add “ay” to the end of the word.
		 * y is a consonant
		 */
}
	
	private static int vowelIndex(StringBuilder word) {
		int indexOfFirstVowel = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i)=='a' || word.charAt(i)=='i' || word.charAt(i)=='e' || word.charAt(i)=='o' || word.charAt(i)=='u') {
				indexOfFirstVowel = i;
				break;
			}
		}
		return indexOfFirstVowel;
	}
	
	private static StringBuilder wordTranslator(StringBuilder word, int indexOfFirstVowel) {
		StringBuilder newWord = new StringBuilder();
		StringBuilder newWordCase = new StringBuilder();
		
		if (word.length()>1 && wordIsProper(word)) {
		if (Pattern.compile( "[0-9]" ).matcher(word.toString()).find() || Pattern.compile( "[%$#@&^)(+=~]" ).matcher(word.toString()).find()) {
			newWord = wordToProper(word);
			
		} else if (indexOfFirstVowel == 0) {
			
			if (punctuationFinder(word)) {
				newWord = wordToProper(word.insert(word.length()-1, "way"));
			} else {
			newWord = wordToProper(word.append("way"));
			}
		} else if (indexOfFirstVowel == -1){
			if (punctuationFinder(word)) {
				newWord = wordToProper(word.insert(word.length(), "ay"));
			} else {
				newWord = wordToProper(word.append("ay"));
			}
		} else {
			if (punctuationFinder(word)) {
				newWord = word.insert(word.length()-word.subSequence(0,indexOfFirstVowel).length(), (word.subSequence(0,indexOfFirstVowel) + "ay"));
				newWord = newWord.delete(0,indexOfFirstVowel);
				newWord = wordToProper(newWord);
			} else {
			newWord = word.append(word.subSequence(0,indexOfFirstVowel) + "ay");
			newWord = newWord.delete(0,indexOfFirstVowel);
			newWord = wordToProper(newWord);
			}
		}
		} else if (wordIsUpper(word)){
			if (Pattern.compile( "[0-9]" ).matcher(word.toString()).find() || Pattern.compile( "[%$#@&^)(+=~]" ).matcher(word.toString()).find()) {
				newWord = newWord.append(word.toString().toUpperCase());
				
			} else if (indexOfFirstVowel == 0) {
				
				if (punctuationFinder(word)) {
					newWord = newWord.append(word.insert(word.length()-1, "way"));
				} else {
				newWord = newWord.append(word.append("way"));
				}
			} else if (indexOfFirstVowel == -1){
				if (punctuationFinder(word)) {
					newWord = newWord.append(word.insert(word.length(), "ay"));
				} else {
					newWord = newWord.append(word.append("ay"));
				}
			} else {
				if (punctuationFinder(word)) {
					newWord = newWord.append(word.insert(word.length()-word.subSequence(0,indexOfFirstVowel).length(), (word.subSequence(0,indexOfFirstVowel) + "ay")));
					newWord = newWord.delete(0,indexOfFirstVowel);
				} else {
				newWord = newWord.append(word.append(word.subSequence(0,indexOfFirstVowel) + "ay"));
				newWord = newWord.delete(0,indexOfFirstVowel);
				}
			} 
		} else {
				if (Pattern.compile( "[0-9]" ).matcher(word.toString()).find() || Pattern.compile( "[%$#@&^)(+=~]" ).matcher(word.toString()).find()) {
					newWord = word;
					
				} else if (indexOfFirstVowel == 0) {
					
					if (punctuationFinder(word)) {
						newWord = word.insert(word.length()-1, "way");
					} else {
					newWord = word.append("way");
					}
				} else if (indexOfFirstVowel == -1){
					if (punctuationFinder(word)) {
						newWord = word.insert(word.length(), "ay");
					} else {
						newWord = word.append("ay");
					}
				} else {
					if (punctuationFinder(word)) {
						newWord = word.insert(word.length()-word.subSequence(0,indexOfFirstVowel).length(), (word.subSequence(0,indexOfFirstVowel) + "ay"));
						newWord = newWord.delete(0,indexOfFirstVowel);
					} else {
					newWord = word.append(word.subSequence(0,indexOfFirstVowel) + "ay");
					newWord = newWord.delete(0,indexOfFirstVowel);
					}
				}
		}
		if (wordIsUpper(newWord)) {
			newWordCase = newWordCase.append(newWord.toString().toUpperCase());
		} else if (wordIsLower(newWord)) {
			newWordCase = newWordCase.append(newWord.toString().toLowerCase());
		} else {
			newWordCase = newWord;
		}
		
		return newWordCase;
	}
	
	private static String lineTranslator(Scanner scn, String prompt) {
		
		System.out.println(prompt);
		String userInput = scn.nextLine();
		StringBuilder word = new StringBuilder();
		
		int x = userInput.indexOf(" ",0);
		int y;
		
		String fin = "";
		
		if (x > 0) {
			word = word.append(userInput,0,x);
			fin = wordTranslator(word,vowelIndex(word)).toString() + " ";
						
			while (x < userInput.length() && x > 0) {
				
				y = userInput.indexOf(" ",x+1);
				
				if (y > 0) {
					word.setLength(0);
					word = word.append(userInput, (x+1),y);
					fin = fin + wordTranslator(word,vowelIndex(word)).toString() + " ";

					x = y;
				} else {
					word.setLength(0);
					word = word.append(userInput, (x+1),userInput.length());
					fin = fin + wordTranslator(word,vowelIndex(word)).toString();

					x = userInput.length();

				}
					
			}
		} else if (userInput.length()==0){
			lineTranslator(scn,"Your input was invalid.  Please try again. \r\nEnter a line to translate to pig latin: ");
			
		}
		else {
			word.setLength(0);
			word = word.append(userInput,0,userInput.length());
			fin = wordTranslator(word,vowelIndex(word)).toString();
		}
		
		return fin;
	}
	
	private static boolean punctuationFinder(StringBuilder word) {
		return Pattern.compile( "[!.?]" ).matcher(word.toString()).find();
	}
	
	private static boolean wordIsUpper(StringBuilder word) {
		if (Character.isUpperCase(word.toString().charAt(0)) && Character.isUpperCase(word.toString().charAt(2))) {
			return true;
		} return false;
	}
	private static boolean wordIsLower(StringBuilder word) {
		if (word.toString().equals(word.toString().toLowerCase())) {
			return true;
		} return false;
	}
	private static boolean wordIsProper(StringBuilder word) {
		if (Character.isUpperCase(word.toString().charAt(0)) && Character.isLowerCase(word.toString().charAt(1))) {
			return true;
		} return false;
	}
	private static StringBuilder wordToProper(StringBuilder word) {
		StringBuilder newWord = new StringBuilder();
		newWord = newWord.append(word.toString().toUpperCase().charAt(0) + word.toString().toLowerCase().substring(1));
		return newWord;
	}	
	private static boolean onwards(Scanner scn, String prompt) {
		System.out.println(prompt);
		String input = scn.nextLine().toLowerCase();
		boolean yesNo = false;
		while (true)
		{					
				if (input.equals("yes") || input.equals("yeah") || input.equals("y") || input.equals("yep"))
				{
					yesNo = true;
					break;
				}
				else if (input.equals("no") || input.equals("nada") || input.equals("n") || input.equals("nope"))
				{
					yesNo = false;
					break;
				} 		
				else
				{
					System.out.println("Your answer was invalid.");
					System.out.println(prompt);
					input = scn.nextLine().toLowerCase();
				}
		}
		return yesNo;
	}
}