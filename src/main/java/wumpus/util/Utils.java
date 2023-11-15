package wumpus.util;

/**
 * Tools for this project.
 */
public class Utils {
    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYW";

    /**
     * Convert letter to Integer.
     */
    public static int integerFromLetter(char letter) {
        for (int i = 0; i <= alphabet.length(); i++) {
            if (alphabet.charAt(i) == letter) {
                return i + 1;
            }
        }
        return 0;
    }

    public static String letterFromInteger(int number) {
       return alphabet.substring(number, number + 1);
    }
}
