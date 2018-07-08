import java.util.*;

public class Sentence {
    private List<SentenceElement> elements = new ArrayList<>();

    public Sentence() {
    }

    public Sentence(List<SentenceElement> elements) {
        this.elements = elements;
    }

    public List<SentenceElement> getElements() {
        return elements;
    }

    public int countWords() {
        int result = 0;
        for (SentenceElement element : elements) {
            if (isWord(element)) {
                result++;
            }
        }
        return result;
    }

    private boolean isWord(SentenceElement element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (element.getClass().equals(Word.class)) {
            return true;
        }
        return false;
    }

    public void setElements(List<SentenceElement> elements) {
        this.elements = elements;
    }

    public void addElement(SentenceElement element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        elements.add(element);
    }

    public void addElements(SentenceElement... elements) {
        this.elements.addAll(Arrays.asList(elements));
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean isContains(SentenceElement element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        for (SentenceElement sentenceElement : elements) {
            if (isWord(element)) {
                if (sentenceElement.toString().equalsIgnoreCase(element.toString())) {
                    return true;
                }
            }
            if (sentenceElement.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int countContainsWord(Word word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (SentenceElement element : elements) {
            if (isWord(element) && element.equals(word)) {
                count++;
            }
        }
        return count;
    }

    public void removeWordsWithSomeLengthConsonant(int length) {
        for (int i = 0; i < elements.size(); i++) {
            if (isWord(elements.get(i)) &&
                    elements.get(i).toString().length() == length &&
                    !((Word) elements.get(i)).isFirstVowel()) {
                elements.remove(i);
            }
        }
    }

    public void removeSymbolsLikeFirst() {
        for (SentenceElement element : elements) {
            if (isWord(element)) {
                ((Word) element).removeSymbolsLikeFirst();
            }
        }
    }

    public void replaceWordsSpecifiedLength(int length, String str) {
        for (SentenceElement element : elements) {
            if (isWord(element) && ((Word) element).getWord().length() == length) {
                ((Word) element).replace(new Word(str));
            }
        }
    }

    public Word getFirstWord() {
        for (SentenceElement element : elements) {
            if (isWord(element)) {
                return (Word) element;
            }
        }
        return null;
    }

    public void setFirstWord(Word word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < elements.size(); i++) {
            if (isWord(elements.get(i))) {
                elements.set(i, word);
                break;
            }
        }
    }

    public Word getLastWord() {
        for (int i = elements.size() - 1; i >= 0; i--) {
            if (isWord(elements.get(i))) {
                return (Word) elements.get(i);
            }
        }
        return null;
    }

    public void setLastWord(Word word) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            if (isWord(elements.get(i))) {
                elements.set(i, word);
                break;
            }
        }
    }


    public List<Word> getAllWords() {
        List<Word> result = new ArrayList<>();
        for (SentenceElement element : elements) {
            if (isWord(element)) {
                result.add(new Word(element.toString().toLowerCase()));
            }
        }
        return result;
    }

    public List<Word> getAllWordsWithFirstVowel() {
        List<Word> result = new ArrayList<>();
        for (SentenceElement element : elements) {
            if (isWord(element) && ((Word) element).isFirstVowel()) {
                result.add(new Word(element.toString().toLowerCase()));
            }
        }
        return result;
    }


    public List<Word> getUniqueWords() {
        List<Word> result = new ArrayList<>();
        Set<SentenceElement> set = new HashSet<>(elements);
        for (SentenceElement sentenceElement : set) {
            if (isWord(sentenceElement)) {
                result.add((new Word(sentenceElement.toString().toLowerCase())));
            }
        }
        return result;
    }

    public boolean isInterrogative() {
        if (elements.get(elements.size() - 1).toString().equals("?")) {
            return true;
        }
        return false;
    }

    public void clear() {
        elements.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(elements, sentence.elements);
    }

    @Override
    public int hashCode() {

        return Objects.hash(elements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SentenceElement element : elements) {
            sb.append(element);
        }

        return sb.toString();
    }
}
