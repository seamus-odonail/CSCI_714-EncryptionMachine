package application;

import java.util.Scanner;

/**
 * //FormattingError: h1 should be closed with /h1
 * 
 * <h1>application.EncryptionMachine<h1>
 * This application.EncryptionMachine program uses a Caesar Cipher to encrypt a users message.
 * <p>
 * This program works by requesting a encryption key from the user.
 * This key is encrypted using the Caesar Cipher and will allow a second party to decrypt
 * the following message that is input.
 * The user will than input the length of the message (in words) and then enter each word
 * individually followed by the "Enter" key.
 * Finally, the program will output each encrypted word and a goodbyeMessage is the program
 * has successfully completed.
 * 
 * //Formatting Error: p tag should be closed with a /p
 * 
 * @author Alex Milender
 * @version 1.0
 */
public class EncryptionMachine {
	private String KEY = "";
	private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private int SHIFT = 3;
	
	/**
	 * Prints a Welcome Message and short summary of the program to the screen.
	 * <p>
	 * This is a standard message but it is separate for easier changeability.
	 * 
	 * //Formatting Error: p tag should be closed with a /p
	 * 
	 * @param None
	 * @return void
	 */
	private static void welcomeMessage() {

		//Code Review: Consider a construct that allows the message to be changed
		//or localized, such as StringBuilder. Yes, this performs better, but you lose flexibility
		//and have a tougher time with automated scanning and documentation generation applications.

		System.out.println("Hello, welcome to the Encryption Machine!\n"
				+ "This program encrypts a key and your input message using a Caesar Cipher\n"
				+ "The key then may be used by a recipient to decrypt your top secret message!\r");
	}
	
	/**
	 * Prints a Goodbye message to the screen.
	 * <p>
	 * This is a standard message but it is separate for easier changeability.
	 * 
	 * //Formatting Error: p tag should be closed with a /p
	 * 
	 * @param None
	 * @return void
	 */
	private static void goodbyeMessage() {
		
		//Code Review: Consider a construct that allows the message to be changed
		//or localized, such as StringBuilder. Yes, this performs better, but you lose flexibility
		//and have a tougher time with automated scanning and documentation generation applications.

		System.out.println("Your top secret message has been encrypted!\n"
				+ "This program will now self destruct.");
	}
	
	/**
	 * Prints input parameters to the screen, then scans and returns user input.
	 * 
	 * //Code Review: Missing paragraph tag? Might lead to inconsistent formatting.
	 * 
	 * @param promptMessage message that will appear in the screen
	 * @return Input from user
	 */

	 //Defect: possible thrown exception in a static method without try / catch
	 //https://stackoverflow.com/questions/30308669/if-i-call-a-static-method-does-the-constructor-run

	private static String readUserInput(String promptMessage) {

		//Defect: Scanner is never closed. Could be a resource leak especially in static methods.
		//https://stackoverflow.com/questions/40367524/im-receiving-a-resource-leak-in-is-never-closed-warning

		Scanner scan = new Scanner(System.in);
		System.out.println(promptMessage);
		return scan.nextLine();
	}
	
	/**
	 * Gets KEY from user and encrypts it.
	 * 
	 * //Code Review: Missing paragraph tag? Might lead to inconsistent formatting.
	 * 
	 * @param None
	 * @return void
	 */
	private void getEncryptionKey() {
		KEY = readUserInput("Enter Encryption Key: ");
		String encryptedKey = encryptWord(KEY);
		if (encryptedKey.isBlank()) {
			System.err.println("Your KEY is blank. Please enter a non-blank KEY.");
			getEncryptionKey();
		}
		else {

			//Code Review: String.format is preferable when printing concatenated values
			//into a string. It allows you to change locale and be i18n compliant with your
			//messages: https://blog.udemy.com/java-format-string/

			System.out.println("Your KEY:" + "\"" + KEY + "\" has been successfully encrypted"
								+ " to : " + encryptedKey + "\n");
		}
	}
	
	/**
	 * Encrypts a single character using Caesar Cipher
	 * <p>
	 * The shift of the Caesar Cipher is determined by private int SHIFT
	 * 
	 * //Formatting Error: p tag should be closed with a /p
	 * 
	 * @param letter character that will be encrypted
	 * @return char encrypted character
	 */
	private char singleLetterEncrypt(char letter) {
		if(ALPHABET.indexOf(letter) == -1) {
		
		//Code Review: Consider a construct that allows the message to be changed
		//or localized, such as StringBuilder. Yes, this performs better, but you lose flexibility
		//and have a tougher time with automated scanning and documentation generation applications.
		
		//Code Review: This is an opportunity to throw an exception and have that handled higher
		//up the callstack. This would allow the program to continue while rejecting the invalid
		//character.

		System.err.println("Invalid character has been entered!");
			System.err.println("This program will now terminate.");
			System.exit(1);
		}
		int normalizedLetter = letter - 'a';
		normalizedLetter = (normalizedLetter + SHIFT) % ALPHABET.length();
		char encryptedLetter = (char) (normalizedLetter + 'a');
		return encryptedLetter;
	}
	
	/**
	 * Encrypts user input word using Caesar Cipher
	 * <p>
	 * Encryption is performed by looping through each character in the message
	 * encrypting the character and then concatenating all encrypted characters
	 * into the encrypted message.
	 * 
	 * //Formatting Error: p tag should be closed with a /p
	 * 
	 * @param message word to be encrypted
	 * @return String encrypted user word
	 */
	private String encryptWord(String message) {
		message = message.toLowerCase();
		char[] messageLetters = message.toCharArray();
		for(int i = 0; i < messageLetters.length; i++) {
			messageLetters[i] = singleLetterEncrypt(messageLetters[i]);
		}
		return new String(messageLetters);
	}

	/**
	 * Requests number of words in message, Requests each individual word from users
	 * and then encrypts each word using Caesar Cipher.
	 * @param None
	 * @return void
	 */
	public void processUserRequest() {
		
		//Defect: parseInt explodes if you hand it something it can't parse. It throws
		//and that means you should catch. Look at parseInt javadoc for the NumberFormatException
		//explanation.

		int numberOfWords = Integer.parseInt(readUserInput("How many words are in your "
															+ "secret message?: "));
		
		if (numberOfWords <= 0) {

		//Code Review: Consider a construct that allows the message to be changed
		//or localized, such as StringBuilder. Yes, this performs better, but you lose flexibility
		//and have a tougher time with automated scanning and documentation generation applications.

			System.err.println("Invalid number has been entered for your number of words"
								+ " in your secret message");
			System.err.println("Please enter a number greater than 0\n");
			processUserRequest();
		}
		else {
			System.out.println();
			for(int i = 0; i < numberOfWords; i++) {
				String nextWord = readUserInput("Next word: ");
				String encryptedWord = encryptWord(nextWord);
				System.out.println("\"" + nextWord + "\" has been successfully encrypted "
									+ "to: " + encryptedWord + "\n");
			}
			goodbyeMessage();
		}
	}


	//Defect: You are executing code in your constructor that calls a input stream. 
	public EncryptionMachine() {
		welcomeMessage();
		getEncryptionKey();
	}

	public static void main(String[] args) {
		EncryptionMachine encryptionMachine = new EncryptionMachine();
		encryptionMachine.processUserRequest();
	}

}
