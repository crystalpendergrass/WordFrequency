package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Program creates an alphabetical index of words from a text file and displays
 * how many times each word is repeated in the text. Words are listed
 * alphabetically under the appropriate letter.
 *
 * @version Assignment #3, 12 March 2014
 */
public class IndexRunner {

    private static final boolean TESTING = false;

    public static void main(String[] args) {

        // Create a mainIndex Object
        MainIndex mainIndex = new MainIndex();
        
        // Adding interactive component to allow user to choose a file
        JOptionPane.showMessageDialog(null,"Locate file containing word list.", "LOCATE FILE", JOptionPane.PLAIN_MESSAGE);
        String storeFileName = chooseFile();

        // Try to open a file and if file missing display error
        try {
            // Opening a file containing a list of words.  
            // File wordFile = new File("dataStuctures.txt");
            // File wordFile = new File("seuss.txt");
            // File wordFile = new File("loremIpsum.txt");
            File wordFile = new File (storeFileName);
            Scanner in = new Scanner(wordFile);
            // Only reading letters, numbers, apostrophes and hyphens
            in.useDelimiter("[^\\p{Alnum}-']+");
            // If the file contains a word, add it to the index
            while (in.hasNext()) {
                String word = in.next();
                // Making sure that if an apostrophe begins and/or ends a word 
                // the apostrophe(s) is removed
                // Apostrophe(s) at beginning of word
                if (word.length() > 0 && word.charAt(0) == '\'') {
                    word = removeBeginApos(word);
                }
                // Apostrophe(s) at end of word only
                if (word.length() > 0 && word.charAt(word.length() - 1) == '\'') {
                    word = removeEndApos(word);
                }
                // Finally add word to index
                if (word.length() > 0) {
                    mainIndex.add(word);
                }
            }
            
            if (TESTING) {
                int numberOfWords = 0;
                System.out.println("The file name is " + wordFile.getName());
                if (wordFile.getName().equals("dataStuctures.txt")) {
                    numberOfWords = 99;
                }
                if (wordFile.getName().equals("seuss.txt")) {
                    numberOfWords = 687;
                }
                if (wordFile.getName().equals("loremIpsum.txt")) {
                // if (extractFilename(wordFile.getName()).equals("loremIpsum.txt")) {
                    numberOfWords = 238;
                }
                System.out.println("***** TOTAL NUMBER OF WORDS *****\nExpected: " + numberOfWords);
                System.out.println("Total Words: " + mainIndex.totalWordsInIndex());
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("File containing word list not found.");
        }

        // Print the index
        System.out.print(mainIndex);
    }

    /**
     * Removes apostrophes from the beginning of a word
     *
     * @param s a String value
     * @return a String value
     */
    public static String removeBeginApos(String s) {
        if (s.length() == 0) {
            return "";
        } 
        else if (s.charAt(0) != '\'') {
            return s;
        } 
        else {
            return removeBeginApos(s.substring(1));
        }
    }

    /**
     * Removes apostrophes from the end of a word
     *
     * @param s a String value
     * @return a String value
     */
    public static String removeEndApos(String s) {
        int i = s.length() - 1;
        
        if (s.length() == 0) {
            return "";
        } 
        else if (s.charAt(i) != '\'') {
            return s;
        } 
        else {
            return removeEndApos(s.substring(0, i));
        }
    }
    
    /**
     * Allows the user to select a file and then returns the absolute path to
     * that file.
     *
     * @return the path to a selected file
     */
    public static String chooseFile() {
        try {
            JFileChooser chooser = new JFileChooser();
            // User can only select files
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.showOpenDialog(null);

            String path = chooser.getSelectedFile().getAbsolutePath();

            return path;

        } catch (NullPointerException e) {
            System.out.println("Error: No file selected! Operation canceled by user?");
        }

        return "File not selected!";
    }
    
    // Modified code from:
    // http://stackoverflow.com/questions/4838730/regex-to-strip-all-directorynames-from-path-leave-filename
    /**
     * Returns a file name
     *
     * @param path path to a file
     * @return a file name
     */
    public static String extractFilename(String path) {
        Pattern p = Pattern.compile("^[/\\\\]?(?:.+[/\\\\]+?)?(.+?)[/\\\\]?$");
        Matcher matcher = p.matcher(path);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
