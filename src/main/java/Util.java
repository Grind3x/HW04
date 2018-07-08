public class Util {
    public static String removeSpaces(String text) {
        return text.trim().replaceAll("\\s{2,}|\t", " ");
    }
}
