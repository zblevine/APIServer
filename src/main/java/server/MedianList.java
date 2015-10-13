package server;

public class MedianList {
	private final String text;
	private final double median;
	private final String median_words;
	
	public MedianList(String txt, double med, String mw) {
		this.text = txt;
		this.median = med;
		this.median_words = mw;
	}
	
	public String getText() {
		return text;
	}
	
	public double getMedian() {
		return median;
	}
	
	public String getMedianWords() {
		return median_words;
	}
}
