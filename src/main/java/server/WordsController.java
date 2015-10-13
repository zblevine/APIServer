package server;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordsController {
	
	private WordsFunctionGetter _wfg;
	
	public WordsController() {
		_wfg = new WordsFunctionGetter();
	}
	
	@RequestMapping(value="/avg_len", method=RequestMethod.POST)
	public @ResponseBody AvgLen avg_len(@RequestBody Input in) {
		return new AvgLen(in.getText(), _wfg.getAvgWordLength(in.getText()));
	}
	
	@RequestMapping(value="/most_com", method=RequestMethod.POST)
	public @ResponseBody MostCommon most_com(@RequestBody Input in) {
		return new MostCommon(in.getText(), _wfg.getMostCommonWord(in.getText()));
	}
	
	@RequestMapping(value="/median", method=RequestMethod.POST)
	public @ResponseBody MedianList median(@RequestBody Input in) {
		return new MedianList(in.getText(), _wfg.getMedian(in.getText()),
				_wfg.getMedianWords(in.getText()));
	}
}