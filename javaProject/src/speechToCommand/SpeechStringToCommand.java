package speechToCommand;

import java.awt.AWTException;

import speechToCommand.Grammars.GeneralDictation;
import speechToCommand.Grammars.JavaDictationCommands;
import speechToCommand.Grammars.VimNavigation;
import speechToCommand.Grammars.WindowsNavigation;

public class SpeechStringToCommand {
	public static int numberPrecedent=1;
	public static String parameterPrecedent=null;	

	public static void classifyAndCompileWord(String word) throws AWTException {
		if(word.length()>0)
			ClassifyWord.classifyWord(word);
	}
}
