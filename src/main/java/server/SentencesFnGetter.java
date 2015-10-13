package server;

import java.util.Arrays;

public class SentencesFnGetter {
	public String[] splitSentences(String input) {
		/*
		 * This way of splitting sentences is flawed; it treats
		 * "Mr. Jones" as two separate sentences. Is there a better
		 * way to deal with this than to just hardcode the 
		 * edge cases in?
		 * 
		 * The below regex splits on any sentence-ender followed
		 * by a space.
		 */
		return input.split("(\\! |\\. |\\? )"); 
	}
	
	//Same as in the other data getter. Removing all non-alphanumerics.
	public String[] tokenize(String sentence) {
		String[] words = sentence.split("\\W+");
		
		if(words[0].length() == 0) { 
			return Arrays.copyOfRange(words, 1, words.length);
		}
		else {
			return words;
		}
	}
	
	public double getAvgSentenceLength(String input) {
		String[] sentences = this.splitSentences(input);
		int count = 0;
		for(int i = 0; i < sentences.length; i++) {
			count += this.tokenize(sentences[i]).length;
		}
		
		return 1.0*count/sentences.length;
	}
}
