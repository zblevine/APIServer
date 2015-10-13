package server;

public class AvgLen {
	private final String text;
	private final double avg_len;
	
	public AvgLen(String txt, double al) {
		this.text = txt;
		this.avg_len = al;
	}

	public String getText() {
		return text;
	}
	
	public double getAvgLen() {
		return avg_len;
	}
}