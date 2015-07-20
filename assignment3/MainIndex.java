package assignment3;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Defines the MainIndex class
 * A MainIndex object is a collection of IndexEntry objects listed in 
 * alphabetical order.
 * 
 * @version Assignment #3, 12 March 2014
 */

public class MainIndex {

    // Declarations of the instance variable of MainIndex class
    private LinkedList<IndexEntry> listOfEntries;

    /**
     * Default constructor for the MainIndex class.
     */
    public MainIndex() {
        listOfEntries = new LinkedList<>();
    }

    /*  
        Method to assign existing LinkedList to instance variable 'listLetters'
        (i.e. setListOfLetters()) intentionally not included
     */
    
    /**
     * Adds an index entry to the list which contains words beginning with the
     * first character of a word.  The index entry is placed in its alphabetic
     * position. The word is then added to the index entry. 
     *
     * @param word a word
     */
    public void add(String word) {
        // If the list of entries is empty, then add index to the beginning of 
        // the list. Then add the word to the list.  
        if (listOfEntries.isEmpty()) {
            listOfEntries.addFirst(new IndexEntry(word));
            listOfEntries.getFirst().add(new WordEntry(word));
        } 
        // If a list already exists for the first of a word, just add the word
        // to the existing list.
        else if (indexExists(word)) {
             try {
                IndexEntry located = indexFound(word);
                located.add(new WordEntry(word));
            } catch (NullPointerException e) {
                System.out.println("Error: MainIndex.add() method");
            }
        } 
        // Index entry is placed in correct alphabetic order based on character
        // value in instance variable 'letter'.
        else if (!isAlphabetized(word)) {
            alphabetize(word);
            indexFound(word).add(new WordEntry(word));
        } 
        // If index entry does not exist and placing it at the end of the index
        // is placing it in its correct alphabetic position
        else {
            listOfEntries.addLast(new IndexEntry(word));
            listOfEntries.getLast().add(new WordEntry(word));
        }
    }
    
    // This method is for testing.
    /**
     * Counts all words in the index. 
     * 
     * @return total words contained in the index
     */
    public int totalWordsInIndex() {
        int total = 0;
        for (IndexEntry iE : listOfEntries) {
            total = total + iE.totalWords();
        }
        return total;
    }

    /**
     * Determines if the word already exists on the list of words
     *
     * @param word a word
     * @return true if an index list already exists for the first character of 
     * the word
     */
    private boolean indexExists(String word) {
        if (listOfEntries.isEmpty()) {
            return false;
        }
        ListIterator<IndexEntry> iter = listOfEntries.listIterator();
        try{
            
        while(iter.hasNext()){
            if (Character.toUpperCase(iter.next().getLetter()) == Character.toUpperCase((word.charAt(0)))) {
                return true;
            }
        }
        } catch(NoSuchElementException e){
            System.out.println("Error: MainIndex.indexExists() method");
        }
        return false;
    }

    /**
     * Searching for an existing IndexEntry object that contains words beginning
     * with the same character that is the first character in a word
     *
     * @param word a word
     * @return an object of type IndexEntry type whose character value stored in
     * instance variable 'letter' is equal to the first character of word
     */
        private IndexEntry indexFound(String word) {
        if (listOfEntries.isEmpty()) {
            return null;
        }
        ListIterator<IndexEntry> iter = listOfEntries.listIterator();

        try {
            while (iter.hasNext()) {
                IndexEntry compareObject = iter.next();
                if (compareObject.getLetter() == (Character.toUpperCase(word.charAt(0)))) {
                    return compareObject;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: MainIndex.indexFound() method");
        }
        return null;
    }
    
    /**
     * Determines if placing an index entry at the end of the index is the 
     * correct alphabetic position for that index entry
     *
     * @param w a word
     * @return true if the end of the list is the index entry's correct 
     * alphabetic position
     */
    private boolean isAlphabetized(String word) {
        if (listOfEntries.isEmpty()){
            return true;
        }
        ListIterator<IndexEntry> iter = listOfEntries.listIterator();
        try{
            while(iter.hasNext()){
            if (Character.toUpperCase((word.charAt(0))) < Character.toUpperCase(iter.next().getLetter())) {
                return false;
            }
        }
        } catch(NoSuchElementException e){
            System.out.println("Error: MainIndex.isAlphabetized() method");
        }
        return true;
    }

    /**
     * Places an index entry in its correct alphabetic position based
     * on the letter which begins all the words in a list
     *
     * @param word a word
     */
    private void alphabetize(String word) {
        int tempIndex = -1;
        IndexEntry current;
        boolean alphaLocCorrect = false;

        ListIterator<IndexEntry> iter = listOfEntries.listIterator();

        try {
            while (iter.hasNext() && !alphaLocCorrect) {
                current = iter.next();
            if (Character.toUpperCase((word.charAt(0))) < Character.toUpperCase(current.getLetter())) {
                tempIndex = listOfEntries.indexOf(current);
                alphaLocCorrect = true;
            }
        }
            } catch (NoSuchElementException e) {
                System.out.println("Error: MainIndex.alphabetize() method");
        }
        listOfEntries.add(tempIndex, new IndexEntry(word));
    }
    
    /**
     * Returns a text description of a MainIndex object
     *
     * @return A text description of a MainIndex object
     */
    @Override
    public String toString() {
        if (listOfEntries.isEmpty()) {
            return "Index is empty.";
        }
        StringBuilder message = new StringBuilder("\n***** THE INDEX *****");
        for (IndexEntry iE : listOfEntries) {
            message.append("\n");
            message.append(iE);
        }
        return message.toString();
    }
}
