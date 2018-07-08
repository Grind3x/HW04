import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String text = "Basic Python syntax was introduced in the previous chapter. Here, a list is created by simply\n" +
                "surrounding a comma-separated sequence of objects with square brackets. The result is an\n" +
                "object with a runtime type of list (the output of the print statements is shown as comments\n" +
                "on the same line). The result of printing a list is the same as that of using\n" +
                "Arrays.toString() in Java.\n" +
                "Creating a sub-sequence of a list is accomplished with \"slicing,\" by placing the’:’ operator\n" +
                "inside the index operation. The list type has many more builtin operations.\n" +
                "MyList is a class definition; the base classes are placed within the parentheses. Inside the\n" +
                "class, def statements produce methods, and the first argument to the method is\n" +
                "automatically the equivalent of this in Java, except that in Python it’s explicit and the\n" +
                "identifier self is used by convention (it’s not a keyword). Notice how the constructor is\n" +
                "automatically inherited.\n" +
                "Although everything in Python really is an object (including integral and floating point\n" +
                "types), you still have an escape hatch in that you can optimize performance-critical portions\n" +
                "of your code by writing extensions in C, C++ or a special tool called Pyrex, which is designed\n" +
                "to easily speed up your code. This way you can have object purity without being prevented\n" +
                "from performance improvements?\n" +
                "The PHP language5\n" +
                " goes even further by having only a single array type, which acts as both\n" +
                "an int-indexed array and an associative array (a Map).\n" +
                "It’s interesting to speculate, after this many years of Java evolution, whether the designers\n" +
                "would put primitives and low-level arrays in the language if they were to start over again. If\n" +
                "these were left out, it would be possible to make a truly pure object-oriented language\n" +
                "(despite claims, Java is not a pure 0 0 language, precisely because of the low-level detritus).\n" +
                "The initial argument for efficiency always seems compelling, but over time we have seen an\n" +
                "evolution away from this idea and towards the use of higher-level components like\n" +
                "containers. Add to this the fact that if containers can be built into the core language as they\n" +
                "are in some languages, then the compiler has a much better opportunity to optimize.\n" +
                "Green-fields speculation aside, we are certainly stuck with arrays, and you will see them\n" +
                "when reading code. Containers, however, are almost always a better choice. ";
        Text tOne = new Text(text);

        System.out.println("1. Найти наибольшее количество предложений текста, в которых есть одинаковые слова.");
        System.out.println(tOne.countMaxSentencesWithSameWords());
        System.out.println();

        System.out.println("2. Вывести все предложения заданного текста в порядке возрастания количества слов в каждом из них.");
        System.out.println(tOne.getTextAscWords());
        System.out.println();

        System.out.println("3. Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений.");
        System.out.println(tOne.getUniqueWordInFirstSentence());
        System.out.println();

        System.out.println("4. Во всех вопросительных предложениях текста найти и напечатать без повторений слова заданной длины.");
        System.out.println(tOne.getUniqueWordsInInterrogativeSentencesByLength(7));
        System.out.println();

        System.out.println("5. В каждом предложении текста поменять местами первое слово с последним, не изменяя длины предложения.");
        tOne.swapFirstAndLastWords();
        System.out.println(tOne);
        System.out.println();

        System.out.println("6. Напечатать слова текста в алфавитном порядке по первой букве. Слова, начинающиеся с новой " +
                "буквы, печатать с красной строки.");
        System.out.println(tOne.getAllWordsInAlphabetOrder());
        System.out.println();

        System.out.println("7. Рассортировать слова текста по возрастанию доли гласных букв (отношение количества гласных" +
                " к общему количеству букв в слове).");
        System.out.println(tOne.sortByProportionOfVowels());
        System.out.println();

        System.out.println("8. Слова текста, начинающиеся с гласных букв, рассортировать в алфавитном порядке по первой " +
                "согласной букве слова.");
        System.out.println(tOne.sortBySecondConsonantWithFirstVowel());
        System.out.println();

        System.out.println("9. Все слова текста рассортировать по возрастанию количества заданной буквы в слове. Слова с " +
                "одинаковым количеством расположить в алфавитном порядке.");
        System.out.println(tOne.sortByLetterCount('e'));
        System.out.println();

        System.out.println("10. Существует текст и список слов. Для каждого слова из заданного списка найти, сколько раз " +
                "оно встречается в каждом предложении, и рассортировать слова по убыванию общего количества вхождений.");
        List<Word> words = new ArrayList<>();
        words.add(new Word("the"));
        words.add(new Word("and"));
        words.add(new Word("a"));

        System.out.println(tOne.sortByWordCountInSentences(words));
        System.out.println();

//        11. В каждом предложении текста исключить подстроку максимальной длины,
//        начинающуюся и заканчивающуюся заданными символами.

        System.out.println("12. Из текста удалить все слова заданной длины, начинающиеся на согласную букву.");
        tOne.removeWordsWithSomeLengthConsonant(4);
        System.out.println(tOne);
        System.out.println();

        System.out.println("13. Отсортировать слова в тексте по убыванию количества вхождений заданного символа, а в" +
                " случае равенства – по алфавиту.");
        System.out.println(tOne.sortByCharCount('a'));
        System.out.println();

        System.out.println("14. В заданном тексте найти подстроку максимальной длины, являющуюся палиндромом, т.е. " +
                "читающуюся слева направо и справа налево одинаково.");
        System.out.println(tOne.findLongestPalindrome());
        System.out.println();

        System.out.println("15. Преобразовать каждое слово в тексте, удалив из него все последующие (предыдущие)" +
                " вхождения первой (последней) буквы этого слова.");
        tOne.removeSymbolsLikeFirst();
        System.out.println(tOne);
        System.out.println();

        System.out.println("16. В некотором предложении текста слова заданной длины заменить указанной подстрокой, " +
                "длина которой может не совпадать с длиной слова.");
        tOne.replaceWordsSpecifiedLength(1, 5, "oh-yeah");
        System.out.println(tOne);
        System.out.println();






    }
}
