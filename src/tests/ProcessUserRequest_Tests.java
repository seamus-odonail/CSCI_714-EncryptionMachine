package tests;

import application.EncryptionMachine;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ProcessUserRequest_Tests {

    @After
    public void restoreSystemInputOutput() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void ProcessUserRequest_KeyInput_HappyPath(){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            System.setIn(new ByteArrayInputStream("testpdq".getBytes()));
            new EncryptionMachine();

        } finally {
            System.setOut(console);
        }
        String exp = "Hello, welcome to the Encryption Machine!\n" +
                "This program encrypts a key and your input message using a Caesar Cipher\n" +
                "The key then may be used by a recipient to decrypt your top secret message!\r\n" +
                "Enter Encryption Key: \n"+
                "Your KEY:\"testpdq\" has been successfully encrypted to : whvwsgt";
        String act = bytes.toString(StandardCharsets.UTF_8).trim();

        char[] expected  = exp.toLowerCase().toCharArray();
        char[] actual = act.toLowerCase().toCharArray();
        int minLength = Math.min(expected.length, actual.length);

        for(int i = 0; i < minLength; i++)
        {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void ProcessUserRequest_KeyNotInput(){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            System.setIn(new ByteArrayInputStream("\r".getBytes()));
            new EncryptionMachine();

        } finally {
            System.setOut(console);
        }
        String exp = "Hello, welcome to the Encryption Machine!\n" +
                "This program encrypts a key and your input message using a Caesar Cipher\n" +
                "The key then may be used by a recipient to decrypt your top secret message!\r\n" +
                "Enter Encryption Key: \n"+
                "Your KEY is blank. Please enter a non-blank KEY.";
        String act = bytes.toString(StandardCharsets.UTF_8).trim();

        char[] expected  = exp.toLowerCase().toCharArray();
        char[] actual = act.toLowerCase().toCharArray();
        int minLength = Math.min(expected.length, actual.length);

        for(int i = 0; i < minLength; i++)
        {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    public void ProcessUserRequest_HappyPath(){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream console = System.out;
        String userInputs = "9\nOne\nTwo\nThree\nFour\nFive\nSix\nSeven\nEight\nNine";
        try {
            System.setOut(new PrintStream(bytes));
            System.setIn(new ByteArrayInputStream("testpdq".getBytes()));
            EncryptionMachine classUnderTest = new EncryptionMachine();
            System.setIn(new ByteArrayInputStream(userInputs.getBytes()));
            classUnderTest.processUserRequest();
        } finally {
            System.setOut(console);
        }
        String exp = "Hello, welcome to the Encryption Machine!\n" +
                "This program encrypts a key and your input message using a Caesar Cipher\n" +
                "The key then may be used by a recipient to decrypt your top secret message!\r\n" +
                "Enter Encryption Key: \n"+
                "Your KEY:\"testpdq\" has been successfully encrypted to : whvwsgt";
        String act = bytes.toString(StandardCharsets.UTF_8).trim();

        char[] expected  = exp.toLowerCase().toCharArray();
        char[] actual = act.toLowerCase().toCharArray();
        int minLength = Math.min(expected.length, actual.length);

        for(int i = 0; i < minLength; i++)
        {
            Assert.assertEquals(expected[i], actual[i]);
        }
    }

}
