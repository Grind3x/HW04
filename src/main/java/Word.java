import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Word implements SentenceElement {
    private String word;
    private List<Symbol> symbols;

    public Word() {
    }

    public Word(String word) {
        word = removeSpaces(word);
        this.word = word;
        this.symbols = getSymbolListRepresentation(word);
    }

    public Word(List<Symbol> symbols) {
        symbols = getSymbolListRepresentation(removeSpaces(getStringRepresentation(symbols)));
        this.symbols = symbols;
        this.word = getStringRepresentation(symbols);
    }

    private String getStringRepresentation(List<Symbol> symbols) {
        StringBuilder builder = new StringBuilder(symbols.size());

        for (Symbol symbol : symbols) {
            builder.append(symbol.getCharacter());
        }
        return builder.toString();
    }

    private List<Symbol> getSymbolListRepresentation(String word) {
        List<Symbol> result = new ArrayList<>();
        char[] charArray = word.toCharArray();
        for (char c : charArray) {
            result.add(new Symbol(c));
        }
        return result;
    }

    private String removeSpaces(String text) {
        return text.trim().replaceAll("\\s{2,}|\t", " ");
    }

    public void replace(Word word) {
        this.word = word.toString();
        this.symbols = getSymbolListRepresentation(this.word);
    }

    public double getProportionOfVowels() {
        double d = countVowels() / (double) word.length();
        return d;
    }

    public int countVowels() {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i'
                    || word.charAt(i) == 'o' || word.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }

    public int countChar(Character ch) {
        int count = 0;
        for (Symbol symbol : symbols) {
            if (symbol.getCharacter().equals(ch)) {
                count++;
            }
        }
        return count;
    }

    public void removeSymbolsLikeFirst() {
        for (int i = 1; i < symbols.size(); ) {
            if (symbols.get(0).equals(symbols.get(i))) {
                symbols.remove(i);
                continue;
            }
            i++;
        }
        word = getStringRepresentation(symbols);
    }

    public boolean isFirstVowel() {
        if (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i'
                || word.charAt(0) == 'o' || word.charAt(0) == 'u') {
            return true;
        }
        return false;
    }

    public int countSomeLetter(char ch) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        word = removeSpaces(word);
        this.word = word;
        this.symbols = getSymbolListRepresentation(word);
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        symbols = getSymbolListRepresentation(removeSpaces(getStringRepresentation(symbols)));
        this.symbols = symbols;
        this.word = getStringRepresentation(symbols);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return word.equalsIgnoreCase(word1.getWord());
    }

    @Override
    public int hashCode() {

        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return word;
    }
}
