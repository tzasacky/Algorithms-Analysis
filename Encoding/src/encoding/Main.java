package encoding;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static String readFile(String filename) {
        String filepath = "prob1/files/";
        Path path = Paths.get(filepath + filename);

        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(path, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line + '\n');
        }

        return sb.toString();
    }

    private static HashMap<Character, Float> buildFrequencyTable(String text) {
        HashMap<Character, Float> frequencies = new HashMap<Character, Float>();

        // Iterate through the characters in the file, incrementing the
        // counts or setting to 1 if the character is new
        for (char c : text.toCharArray()) {
            if (frequencies.containsKey(c)) {
                frequencies.put(c, frequencies.get(c) + 1);
            } else {
                frequencies.put(c, 1.0f);
            }
        }

        // Iterate through the characters in the frequency dictionary,
        // dividing the count by the total number of characters to get
        // the frequencies
        int charCount = text.length();
        for (char c : frequencies.keySet()) {
            frequencies.put(c, frequencies.get(c) / charCount);
        }

        return frequencies;
    }
    
    private static void runEncoder(Encoder encoder, String text) {
        System.out.println("\nLength of original string: " + text.length());

        String encodedString = encoder.encodeString(text);
        System.out.println("Length of encoded string: " + encodedString.length());

        String decodedString = encoder.decodeString(encodedString);
        System.out.println("Length of decoded string: " + decodedString.length());
    }

    private static void testEncoder(Encoder encoder, String expectedEncodedMessage) {
        String text = "Hello, world";
        System.out.println("\nEncoding: " + text);

        String encodedString = encoder.encodeString(text);
        System.out.println("Encoded message: " + encodedString);
        if (encodedString.equals(expectedEncodedMessage)) {
            System.out.println("\tSuccess!  Correctly encoded the message!");
        } else {
            System.out.println("\tFailed to encode the message correctly! :(");
        }

        String decodedString = encoder.decodeString(encodedString);
        System.out.println("Decoded message: " + decodedString + " (should be the same as the original)");
        if (text.equals(decodedString)) {
            System.out.println("\tSuccess!  Correctly decoded the message!");
        } else {
            System.out.println("\tFailed to encode the message correctly! :(");
        }
    }

    public static void main(String[] args) {
        // Read in the files
        String randomText = readFile("random.txt");
        String assignmentText = readFile("assignment.txt");
        String syllabusText = readFile("syllabus.txt");

        // Fixed-length encoder
        FixedLengthEncoder fwe = new FixedLengthEncoder();
        testEncoder(fwe, "010010000110010101101100011011000110111100101100001000000111011101101111011100100110110001100100");
        runEncoder(fwe, randomText);

        // Huffman encoder based on assignment.txt character frequencies
        // Note: I got a different encoding but it should be valid.
        HashMap<Character, Float> assignmentFrequencies = buildFrequencyTable(randomText);
        HuffmanEncoder he = new HuffmanEncoder(assignmentFrequencies);
        testEncoder(he, "01111100101111011100111000000101101101110110000000100111000111");
        runEncoder(he, randomText);

        // Huffman encoder based on syllabus.txt character frequencies
        HashMap<Character, Float> syllabusFrequencies = buildFrequencyTable(randomText);
        HuffmanEncoder he2 = new HuffmanEncoder(syllabusFrequencies);
        testEncoder(he2, "100111110111111101011010100011101001011110110100000001101010010");
        runEncoder(he2, randomText);
    }
}
