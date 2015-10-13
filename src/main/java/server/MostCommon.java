package server;

public class MostCommon {
	private final String text;
	private final String most_common;
	
	public MostCommon(String txt, String mc) {
		this.text = txt;
		this.most_common = mc;
	}

	public String getText() {
		return text;
	}
	
	public String getMostCommon() {
		return most_common;
	}
}
