package assignment3;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Defines the IndexEntry class.
 * A IndexEntry object contains an uppercase character and a reference to an 
 * alphabetic list of words beginning with that character
 *
 * @version Assignment #3, 12 March 2014
 */
public class IndexEntry {

    // Declarations of the instance variables of IndexEntry class   
    private char letter;
    private LinkedList<WordEntry> listOfWords;

    /**
     * Default constructor for the IndexEntry class.
     */
    public IndexEntry() {
        this.letter = ' ';
        listOfWords = new LinkedList<>();
    }

    /**
     * Creates a IndexEntry object from a word
     *
     * @param word a word
     */
    public IndexEntry(String word) {
        // The first letter is the capitalized first character of the word
        this.letter = Character.toUpperCase(word.charAt(0));
        listOfWords = new LinkedList<>();
    }

    /**
     * Returns the current value of instance variable 'letter'
     *
     * @return a character
     */
    public char getLetter() {
        return letter;
    }

    /**
     * Returns a list of words that begin with the character stored in instance
     * variable 'letter'
     *
     * @return a list of words beginning with the same letter
     */
    public LinkedList<WordEntry> getListOfWords() {
        return listOfWords;
    }
    
    /**
     * Modifies the character stored in instance variable 'letter'
     *
     * @param letter a character
     */
    public void setLetter(char letter) {
        this.letter = Character.toUpperCase(letter);
    }

    /*  
        Method to assign existing LinkedList to instance variable 'listOfWords'
        (i.e. setListOfWords()) intentionally not included
     */
    
    /**
     * Adds a word to the list that begins with the character stored in instance
     * variable 'letter'.  The word is placed in its alphabetic position.  If the 
     * word already exists on the list, the count is increased by one.  
     *
     * @param w an object of type WordEntry
     */
    public void add(WordEntry w) {
        // If the list is empty, the word is added to the beginning of the list.  
        if (listOfWords.isEmpty()) {
            listOfWords.addFirst(w);
        }
        // If the word exists then the word's count is increased by one.
        else if (wordExists(w)) {
            try {
                WordEntry located = wordFound(w);
                located.setWordCount(located.getWordCount() + 1);
            } catch (NullPointerException e) {
                System.out.println("Error: IndexEntry.add() method");
            }
        }
        // Word is placed in correct alphabetic order
        else if (!isAlphabetized(w)) {
            alphabetize(w);            
        }   
        // If word does not exist and placing it at the end of the list
        // is placing it in its correct alphabetic position
        else {
             listOfWords.addLast(w);
        }
    }

    // This method is for testing.
    /**
     * Counts all words in the list. 
     * 
     * @return total words contained in the list
     */
     public int totalWords() {
        int totalWords = 0;

        for (WordEntry w : listOfWords) {
            totalWords = totalWords + w.getWordCount();
        }
        return totalWords;
    }

    /**
     * Determines if the word already exists on the list of words
     *
     * @param w an object of type WordEntry
     * @return true if the list already contains the word
     */
    private boolean wordExists(WordEntry w) {
        // Comparing a word to words already on the list
        ListIterator<WordEntry> iter = listOfWords.listIterator();

        try {
            while (iter.hasNext()) {
                if (w.getWord().equalsIgnoreCase(iter.next().getWord())) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: IndexEntry.wordExists() method");
        }
        return false;
    }

    /**
     * Searching for an existing WordEntry object that contains the same word
     *
     * @param w an object of type WordEntry
     * @return an object of type WordEntry containing identical word
     */
    private WordEntry wordFound(WordEntry w) {
        ListIterator<WordEntry> iter = listOfWords.listIterator();

        try {
            while (iter.hasNext()) {
                WordEntry searchObject = iter.next();
                if (searchObject.getWord().equalsIgnoreCase(w.getWord())) {
                    return searchObject;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: IndexEntry.wordFound() method");
        }
        return null;
    }

    /**
     * Determines if placing word at the end of the list is the correct
     * alphabetic position for that word
     *
     * @param w an object of type WordEntry
     * @return true if the end of the list is the word's correct alphabetic
     * position
     */
    private boolean isAlphabetized(WordEntry w) {

        if (listOfWords.isEmpty()) {
            return true;
        }

        ListIterator<WordEntry> iter = listOfWords.listIterator();
        try {
            while (iter.hasNext()) {
                if (w.getWord().compareToIgnoreCase((iter.next().getWord())) < 0) {
                    return false;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: IndexEntry.isAlphabetized() method");
        }

        return true;
    }
    
    /**
     * Places a word in its correct alphabetic position
     *
     * @param w an object of type WordEntry
     */
    private void alphabetize(WordEntry w) {
        int tempIndex = -1;
        WordEntry current;
        boolean alphaLocCorrect = false;

        ListIterator<WordEntry> iter = listOfWords.listIterator();

        try {
            while (iter.hasNext() && !alphaLocCorrect) {
                current = iter.next();
                if (w.getWord().compareToIgnoreCase((current.getWord())) < 0) {
                    tempIndex = listOfWords.indexOf(current);
                    alphaLocCorrect = true;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: IndexEntry.alphabetize() method");
        }

        listOfWords.add(tempIndex, w);
    }
      
    /**
     * Returns a text description of a IndexEntry object
     *
     * @return A text description of a IndexEntry object
     */
    @Override
    public String toString() {
        if (listOfWords.isEmpty()) {
            return "Index is empty.";
        }
        StringBuilder message = new StringBuilder(Character.toString(getLetter()));
        for (WordEntry w : listOfWords) {
            message.append("\n  ");
            message.append(w);
        }
        message.append("\n");
        return message.toString();
    }
}
