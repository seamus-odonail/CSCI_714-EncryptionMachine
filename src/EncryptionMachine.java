import java.util.Scanner;

/**
 * <h1>EncryptionMachine<h1>
 * This EncryptionMachine program uses a Caesar Cipher to encrypt a users message.
 * <p>
 * This program works by requesting a encryption key from the user.
 * This key is encrypted using the Caesar Cipher and will allow a second party to decrypt
 * the following message that is input.
 * The user will than input the length of the message (in words) and then enter each word
 * individually followed by the "Enter" key.
 * Finally, the program will output each encrypted word and a goodbyeMessage is the program
 * has successfully completed.
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
	 * @param None
	 * @return void
	 */
	private static void welcomeMessage() {
		System.out.println("Hello, welcome to the Encryption Machine!\n"
				+ "This program encrypts a key and your input message using a Caesar Cipher\n"
				+ "The key then may be used by a recipient to decrypt your top secret message!\r");
	}
	
	/**
	 * Prints a Goodbye message to the screen.
	 * <p>
	 * This is a standard message but it is separate for easier changeability.
	 * @param None
	 * @return void
	 */
	private static void goodbyeMessage() {
		System.out.println("Your top secret message has been encrypted!\n"
				+ "This program will now self destruct.");
	}
	
	/**
	 * Prints input parameters to the screen, then scans and returns user input.
	 * 
	 * @param promptMessage message that will appear in the screen
	 * @return Input from user
	 */
	private static String readUserInput(String promptMessage) {
		Scanner scan = new Scanner(System.in);
		System.out.println(promptMessage);
		return scan.nextLine();
	}
	
	/**
	 * Gets KEY from user and encrypts it.
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
			System.out.println("Your KEY:" + "\"" + KEY + "\" has been successfully encrypted"
								+ " to : " + encryptedKey + "\n");
		}
	}
	
	/**
	 * Encrypts a single character using Caesar Cipher
	 * <p>
	 * The shift of the Caesar Cipher is determined by private int SHIFT
	 * @param letter character that will be encrypted
	 * @return char encrypted character
	 */
	private char singleLetterEncrypt(char letter) {
		if(ALPHABET.indexOf(letter) == -1) {
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
		int numberOfWords = Integer.parseInt(readUserInput("How many words are in your "
															+ "secret message?: "));
		
		if (numberOfWords <= 0) {
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
	
	public EncryptionMachine() {
		welcomeMessage();
		getEncryptionKey();
	}

	public static void main(String[] args) {
		EncryptionMachine encryptionMachine = new EncryptionMachine();
		encryptionMachine.processUserRequest();
	}

}
