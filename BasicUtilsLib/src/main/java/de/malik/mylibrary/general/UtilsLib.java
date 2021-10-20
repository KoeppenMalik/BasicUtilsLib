package de.malik.mylibrary.general;

public class UtilsLib {

    /**
     * checks if the argument String <code>source</code> contains the chars in <code>values</code>
     * @param source String that will be checked
     * @param values values which will be checked if source contains one of the them
     * @return true if source contains one of the chars, otherwise false
     */
    public static boolean containsChar(String source, char... values) {
        for (int i = 0; i < source.length(); i++) {
            for (char c : values) {
                if (source.charAt(i) == c) {
                    return true;
                }
            }
        }
        return false;
    }
}
