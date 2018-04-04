package encoding;

public abstract class Encoder {

    public abstract String encodeString(String s);
    public abstract String decodeString(String s);

    public static final String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-~(){}\"'@#%^&*+/\\\n";

}
