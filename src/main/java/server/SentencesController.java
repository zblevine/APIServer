package server;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentencesController {
	private SentencesFnGetter _sfg;
	
	public SentencesController() {
		_sfg = new SentencesFnGetter();
	}
	
	@RequestMapping(value="/sentences", method=RequestMethod.POST)
	public @ResponseBody AvgLen sentences(@RequestBody Input in) {
		return new AvgLen(in.getText(), _sfg.getAvgSentenceLength(in.getText()));
	}
}
