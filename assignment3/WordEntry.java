package assignment3;

/**
 * Defines the WordEntry class.
 * A WordEntry object contains a word and a count of how many times the word is
 * repeated in a selected text or list of words
 * 
 * @version Assignment #3, 12 March 2014
 */

public class WordEntry {

    // Declarations of the instance variables of WordEntry class
    private String word;
    private int wordCount;

    /**
     * Default constructor for the WordEntry class.
     */
    public WordEntry() {
        this.word = null;
        wordCount = 0;
    }
    
    /**
     * Creates a WordEntry object from a word
     *
     * @param word a word
     */
    public WordEntry(String word) {
        this.word = word.toLowerCase();
        wordCount = 1;
    }

    /**
     * Returns the current value of instance variable 'word'
     * 
     * @return a word    
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the current value of instance variable 'wordCount'
     * 
     * @return the number of times a word has been listed 
     */
    public int getWordCount() {
        return wordCount;
    }
    
    /**
     * Modifies the String value of a word
     * 
     * @param word a word
     */
    public void setWord(String word) {
        this.word = word.toLowerCase();
    }

    /**
     * Modifies the number of times a word has been listed
     * 
     * @param wordCount the number of times a word has been listed
     */
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * Returns a text description of a WordEntry object
     *
     * @return A text description of a WordEntry object
     */
    @Override
    public String toString() {
        return word + " [" + wordCount + "]";
    }
}
