package encoding;

import java.util.Arrays;
import java.util.HashMap;

public class FixedLengthEncoder extends Encoder {

    private final int CHAR_LENGTH = 8;

    private HashMap<Character, String> encodingTable;
    private HashMap<String, Character> decodingTable;

    public FixedLengthEncoder() {
        this.encodingTable = new HashMap<Character, String>();
        this.decodingTable = new HashMap<String, Character>();

        this.buildEncodingTable();
    }

    /**
     * Encodes the provided string using the stored encoding table.
     */
    @Override
    public String encodeString(String s) {
    	String encodedMessage = "";
    	HashMap<Character, String> encoder = encodingTable;
        for(char c : s.toCharArray()) {
        	encodedMessage += encoder.get(c);	
        }
        return encodedMessage;
    }

    /**
     * Decodes the provided string using the stored decoding table.
     */
    @Override
    public String decodeString(String s) {
    	String decodedMessage = "";
    	HashMap<String, Character> decoder = decodingTable;
    	 for(int i = 0; i < s.length(); i += CHAR_LENGTH) {
    		String code = s.substring(i, i + CHAR_LENGTH);
         	char decodedChar = decoder.get(code);
         	decodedMessage += decodedChar;	
         }
        return decodedMessage;
    }

    private void buildEncodingTable() {
        for (char c : Encoder.characters.toCharArray()) {
            int i = Integer.valueOf(c);
            String s = Integer.toBinaryString(i);
            s = String.format("%" + CHAR_LENGTH + "s", s).replace(' ', '0');

            this.encodingTable.put(c, s);
            this.decodingTable.put(s, c);
        }
    }
}
