package wumpus.wmap;

import java.util.Objects;

/**
 * Tools for WMap.
 */
public class WMapTools {

    static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Convert letter to Integer.
     */
    public static int integerFromLetter(String letter) {
        for (int i = 0; i <= alphabet.length(); i++) {
            if (Objects.equals(alphabet.split("")[i], letter)) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * Convert Integer to letter.
     */
    public static String letterFromInteger(int number) {
        return alphabet.substring(number, number + 1);
    }
}
