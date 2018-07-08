import java.util.*;


public class Text {
    private List<Sentence> text = new ArrayList<>();

    public Text() {
    }

    public Text(String text) {
        this.text = deserialize(text);
    }

    public Text(List<Sentence> text) {
        this.text = text;
    }

    public void addSentence(Sentence sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException();
        }
        text.add(sentence);
    }

    private List<Sentence> deserialize(String text) {
        text = Util.removeSpaces(text);
        char[] chars = text.toCharArray();
        List<Sentence> sentences = new ArrayList<>();
        Sentence sentence = new Sentence();
        List<Symbol> symbols = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (Character.toString(chars[i]).matches("[a-zA-Z0-9_]")) {
                symbols.add(new Symbol(chars[i]));
            } else {
                if (!symbols.isEmpty()) {
                    sentence.addElement(new Word(symbols));
                    symbols.clear();
                }
                if ((chars[i] == ' ') && !sentence.isEmpty()) {
                    sentence.addElement(new Symbol(chars[i]));
                } else if (chars[i] == '\n') {
                    sentence.addElement(new Symbol(' '));
                } else if (chars[i] != ' ') {
                    sentence.addElement(new Symbol(chars[i]));
                }
            }
            try {
                if ((Character.toString(chars[i]).matches("[.?!]") &&
                        Character.toString(chars[i + 1]).matches("[\n ]"))) {
                    sentences.add(sentence);
                    sentence = new Sentence();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                sentences.add(sentence);
                sentence = new Sentence();
            }
        }
        return sentences;
    }

    public int countMaxSentencesWithSameWords() {
        int result = 1;
        int max = 1;
        for (int i = 0; i < text.size(); i++) {
            List<Word> iList = text.get(i).getAllWords();
            for (int k = 0; k < iList.size(); k++) {
                for (int j = i + 1; j < text.size(); j++) {
                    List<Word> jList = text.get(j).getAllWords();
                    for (int l = 0; l < jList.size(); l++) {
                        if (iList.get(k).toString().equalsIgnoreCase(jList.get(l).toString())) {
                            max++;
                            break;
                        }
                    }
                }
                if (max > result) {
                    result = max;
                }
                max = 1;
            }
        }
        return result;
    }

    public List<Sentence> getTextAscWords() {
        List<Sentence> result = new ArrayList<>(text);
        result.sort(Comparator.comparingInt(Sentence::countWords));
        return result;
    }

    public Word getUniqueWordInFirstSentence() {
        List<Word> firstSentence = text.get(0).getAllWords();
        for (Word word : firstSentence) {
            boolean marker = false;
            for (int i = 1; i < text.size(); i++) {
                if (text.get(i).isContains(word)) {
                    marker = true;
                }
            }
            if (!marker) {
                return word;
            }
        }
        return null;
    }

    public List<Word> getUniqueWordsInInterrogativeSentencesByLength(int length) {
        List<Sentence> interrogative = new ArrayList<>(getInterrogativeSentences());
        Set<Word> unique = new HashSet<>();
        List<Word> result = new ArrayList<>();
        if (!interrogative.isEmpty()) {
            for (Sentence sentence : interrogative) {
                unique.addAll(sentence.getUniqueWords());
            }
            for (Word word : unique) {
                if (word.getWord().length() == length) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    public List<Sentence> getInterrogativeSentences() {
        List<Sentence> result = new ArrayList<>();
        for (Sentence sentence : text) {
            if (sentence.isInterrogative()) {
                result.add(sentence);
            }
        }
        return result;
    }

    public void swapFirstAndLastWords() {
        for (Sentence sentence : text) {
            Word tmp = sentence.getLastWord();
            sentence.setLastWord(sentence.getFirstWord());
            sentence.setFirstWord(tmp);
        }
    }

    public void replaceWordsSpecifiedLength(int sentence, int length, String str) {
        if (text.size() >= sentence) {
            text.get(sentence - 1).replaceWordsSpecifiedLength(length, str);
        }
    }

    public Set<Word> getAllWords() {
        Set<Word> result = new HashSet<>();
        for (Sentence sentence : text) {
            result.addAll(sentence.getAllWords());
        }
        return result;
    }

    public Set<Word> getAllWordsWithFirstVowel() {
        Set<Word> result = new HashSet<>();
        for (Sentence sentence : text) {
            result.addAll(sentence.getAllWordsWithFirstVowel());
        }
        return result;
    }

    public List<Word> getAllWordsInAlphabetOrder() {
        List<Word> words = new ArrayList<>(getAllWords());
        if (!words.isEmpty()) {
            words.sort(Comparator.comparing(Word::toString));
        }
        return words;
    }

    public List<Word> sortByProportionOfVowels() {
        List<Word> result = new ArrayList<>(getAllWords());
        result.sort(Comparator.comparing(Word::getProportionOfVowels));
        return result;
    }

    public List<Word> sortBySecondConsonantWithFirstVowel() {
        List<Word> firstVowel = new ArrayList<>(getAllWordsWithFirstVowel());
        List<Word> result = new ArrayList<>();
        for (Word word : firstVowel) {
            if (word.getWord().length() > 1) {
                result.add(word);
            }
        }
        result.sort(Comparator.comparingInt((Word w) -> w.getWord().charAt(1)));
        return result;
    }

    public List<Word> sortByLetterCount(char ch) {
        List<Word> result = new ArrayList<>(getAllWords());
        result.sort(Comparator.comparingInt((Word w) -> w.countSomeLetter(ch)).thenComparing(Word::toString));
        return result;
    }

    public List<Word> sortByWordCountInSentences(List<Word> words) {
        words.sort(Comparator.comparingInt(this::getWordCount).reversed());
        return words;
    }

    public List<Word> sortByCharCount(Character ch) {
        if (ch == null) {
            throw new IllegalArgumentException();
        }
        List<Word> result = new ArrayList<>(getAllWords());
        result.sort(Comparator.comparingInt((Word w) -> w.countSomeLetter(ch)).reversed().thenComparing(Word::toString));
        return result;
    }

    public String findLongestPalindrome() {
        int maxLength = 1;
        String str = text.toString();
        int start = 0;
        int len = str.length();

        int low, high;

        for (int i = 1; i < len; ++i) {
            low = i - 1;
            high = i;
            while (low >= 0 && high < len
                    && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
            low = i - 1;
            high = i + 1;
            while (low >= 0 && high < len
                    && str.charAt(low) == str.charAt(high)) {
                if (high - low + 1 > maxLength) {
                    start = low;
                    maxLength = high - low + 1;
                }
                --low;
                ++high;
            }
        }
        return str.substring(start, start + maxLength);
    }

    public void removeWordsWithSomeLengthConsonant(int length) {
        for (Sentence sentence : text) {
            sentence.removeWordsWithSomeLengthConsonant(length);
        }
    }

    public void removeSymbolsLikeFirst() {
        for (Sentence sentence : text) {
            sentence.removeSymbolsLikeFirst();
        }
    }

    public int getWordCount(Word word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (Sentence sentence : text) {
            if (sentence.isContains(word)) {
                count++;
            }
        }
        return count;
    }

    public List<Sentence> getText() {
        return text;
    }

    public void setText(List<Sentence> text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text.toString();

    }
}
