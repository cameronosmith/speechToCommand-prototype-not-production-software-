package speechToCommand.Grammars;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import speechToCommand.Roboto;

public class JavaDictationCommands {
	public static boolean searchForCommand(String word) throws AWTException {
		boolean resultFound = false;
		switch(word.toLowerCase()) {
		case "iterate": resultFound=true;Roboto.pc("for(int i=0;i<;i++){}",new int[] {KeyEvent.VK_LEFT,KeyEvent.VK_ENTER,KeyEvent.VK_UP,KeyEvent.VK_ESCAPE,KeyEvent.VK_9,KeyEvent.VK_L,KeyEvent.VK_2,KeyEvent.VK_L,KeyEvent.VK_I});
		break;
		case "print": resultFound=true;Roboto.pc("System.out.println()", new int[] {KeyEvent.VK_LEFT});
		break;
		case "intimate": resultFound=true;Roboto.pc("int", new int[] {KeyEvent.VK_LEFT});
		break;
		case "conditional": resultFound=true;Roboto.pc("if()", new int[] {KeyEvent.VK_LEFT});
		break;
	}
		return resultFound;
	}
}
