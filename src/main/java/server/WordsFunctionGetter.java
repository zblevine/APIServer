package server;

import java.text.Collator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class WordsFunctionGetter {
	public String[] tokenize(String sentence) {
		String[] words = sentence.split("\\W+");
		
		/*
		 * If the first character is non-alphanumeric, the first
		 * word will be an empty string. Let's fix that.
		 */
		if(words[0].length() == 0) { 
			return Arrays.copyOfRange(words, 1, words.length);
		}
		else {
			return words;
		}
	}
	
	public double getAvgWordLength(String input) {
		String[] words = this.tokenize(input);
		StringBuilder wordmash = new StringBuilder();
		for(int i = 0; i < words.length; i++) {
			wordmash.append(words[i]);
		}
		String stringmash = wordmash.toString();
		
		/*returning total number of non-punctuation characters 
		 *divided by number of words. 
		 */
		return 1.0*stringmash.length()/words.length;
	}
	
	//Treats capitalization as irrelevant when determining words
	public HashMap<String, Integer> getFrequencies(String[] words, Collator col) {
		HashMap<String, Integer> freq = new HashMap<String, Integer>();
		for(int i = 0; i < words.length; i++) {
			String cWord = this.containsWord(freq, words[i], col);
			if(cWord != null) {
				freq.put(cWord, freq.get(cWord) + 1);
			}
			else {
				freq.put(words[i], 1);
			}
		}
		
		return freq;
	}
	
	/*
	 * Like the HashMap method containsKey, but treats uppercase
	 * and lowercase letters as the same
	 */
	public String containsWord(HashMap<String, Integer> freq, String word, Collator col) {
		Iterator<String> w = freq.keySet().iterator();
		while(w.hasNext()) {
			String curr = w.next();
			if(col.equals(word, curr)) {
				return curr;
			}
		}
		
		return null;
	}
	
	public String getMostCommonWord(String input) {
		String[] words = this.tokenize(input);
		Collator myCol = Collator.getInstance();
		myCol.setStrength(Collator.SECONDARY);
		HashMap<String, Integer> freq = this.getFrequencies(words, myCol);
		int max = 0;
		String mostCommon = ""; //dummy initial value, this will always change
		Iterator<String> w = freq.keySet().iterator();
		while(w.hasNext()) {
			String curr = w.next();
			if(freq.get(curr) > max) {
				max = freq.get(curr);
				mostCommon = curr;
			}
			else if(myCol.compare(curr, mostCommon) < 0 && freq.get(curr) == max) {
				mostCommon = curr;
			}
		}
		
		return mostCommon;
	}
	
	public double getMedian(String input) {
		String[] words = this.tokenize(input);
		Collator myCol = Collator.getInstance();
		myCol.setStrength(Collator.SECONDARY);
		HashMap<String, Integer> freq = this.getFrequencies(words, myCol);
		Iterator<Integer> it = freq.values().iterator();
		int len = freq.size();
		int[] num = new int[len];
		int i = 0;
		while(it.hasNext()) {
			num[i] = it.next();
			i++;
		}
		
		Arrays.sort(num);
		if(len % 2 == 1) {
			return num[(len - 1)/2];
		}
		else {
			return (num[len/2] + num[(len/2) - 1])/2.0;
		}
	}
	
	public String getMedianWords(String input) {
		String[] words = this.tokenize(input);
		Collator myCol = Collator.getInstance();
		myCol.setStrength(Collator.SECONDARY);
		HashMap<String, Integer> freq = this.getFrequencies(words, myCol);
		double median = this.getMedian(input);
		StringBuilder sb = new StringBuilder();
		Iterator<String> w = freq.keySet().iterator();
		while(w.hasNext()) {
			String word = w.next();
			if(freq.get(word) == median) {
				sb.append(word + ", ");
			}
		}
		
		String newString = sb.toString();
		//need to get rid of the last comma and space
		if(newString.length() > 2) {
			return newString.substring(0, newString.length() - 2);
		}
		
		else {
			/*
			 * there's nothing of median value; this returns a
			 * string that would not be a possible word the way
			 * I tokenized it
			 */
			return "no_words_of_median_frequency";
		}
	}
	
	
}
