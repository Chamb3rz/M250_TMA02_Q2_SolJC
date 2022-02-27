
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * The Thesaurus class provides synonyms for a given word leveraging hashmaps
 * and hashsets. It will be used to create a new sentance by replacing a word
 * with its synonym.
 * 
 * @author Jake Chambers K5467717
 * @version V1.0 15/02/2022
 *          Q1a
 */
public class Thesaurus {
    private HashMap<String, HashSet<String>> synonyms;

    /**
     * Constructor for objects of class Thesaurus
     * Q2c.
     */
    public Thesaurus() {
        // Initialise instance variables
        synonyms = new HashMap<>();
    }

    /**
     * return the synonyms map for testing
     * Q2d.
     */
    public HashMap<String, HashSet<String>> getThesaurus() {
        return synonyms;
    }

    /**
     * populate sample key:value pairs into the synonyms map
     * Q2e.
     */
    public void populate() 
    {
        // initialise hash set variables
        HashSet<String> happyValues = new HashSet<>();
        HashSet<String> angryValues = new HashSet<>();

        // populate happy values hashset
        happyValues.add("joyful");
        happyValues.add("contented");
        happyValues.add("cheerful");

        // populate angry values hashset
        angryValues.add("annoyed");
        angryValues.add("vexed");

        // populate the synonyms hashmap
        synonyms.put("happy", happyValues);
        synonyms.put("angry", angryValues);
    }

    /**
     * print out key:value pairs held in the synonyms hashmap
     * Q2f.
     */
    public void print() 
    {
        // loop through the keys in the synonyms hashmap
        // for each key get the value and print the key:value pair
        for (String aKey : synonyms.keySet()) {
            System.out.println(aKey + " - " + synonyms.get(aKey));
        }
    }

    /*
     * add a new word to the synonym map
     * Q2g.
     */
    public void addSynonym(String aWord, String aSynonym) 
    {
        // initialise a hashset to store existing values if a key is matched
        HashSet<String> values = new HashSet<>();

        // loop through keys in the synonyms hashmap
        for (String aKey : synonyms.keySet()) {

            // if the key passed in already exists
            if (aKey.equals(aWord)) {

                // retrieve the value for the key in the values variable
                // add the new synonyms to the set
                // add the new values with the existing key into the hash map
                values = synonyms.get(aKey);
                values.add(aSynonym);
                synonyms.put(aKey, values);

                // End execution
                return;
            }
        }
        // if there are no matches add the new synonym to the set
        // add the new key:value pair to the map
        values.add(aSynonym);
        synonyms.put(aWord, values);
    }

    /*
     * replace a word in a given string with a synonym from the synonyms map
     * Q2h.
     */
    public void replaceWord(String aWord, String aSentence) 
    {
        // initialise a new hashset to hold the string passed into the method
        HashSet<String> wordSynonyms = new HashSet<>();

        // add the word passed in the to hashset
        wordSynonyms.add(aWord);

        // check the synonyms map keys for the word passed in
        // if is doesnt exist default back the the word passed in
        wordSynonyms = synonyms.getOrDefault(aWord, wordSynonyms);

        // loop through each word in the synonyms
        for (String Word : wordSynonyms) {
            System.out.println(aSentence.replace(aWord, Word));
        }
    }

    /*
     * Iterate over the synonyms map and create new key:value pairs where the synonyms in the 
     * value become the key
     * Q2i.
     */
    public void crossReference() 
    {
        // initialise a new hash set to hold the cross referenced values from the
        // synonyms map
        HashSet<String> currentSet = new HashSet<>();
        HashSet<String> newSet;
        ArrayList<String> keys = new ArrayList<>();

        // for each key in the synonyms map add the key to a string array to stop recurrsion
        // when adding new keys
        for (String aKey : synonyms.keySet()) {
            keys.add(aKey);
        }

        // for each key in the array of keys 
        for (String key : keys) {
            currentSet = synonyms.get(key);

            //loop on each word in the synonyms set and remove the word 
            // add the key to the set and put the new key value pair into the synonyms map
            for (String word : currentSet) {
                newSet = new HashSet<>();
                newSet.addAll(currentSet);
                newSet.remove(word);
                newSet.add(key);
                synonyms.put(word, newSet);
            }
        }
    }
}
