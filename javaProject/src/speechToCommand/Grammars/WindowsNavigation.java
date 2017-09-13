package speechToCommand.Grammars;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import speechToCommand.Roboto;
import speechToCommand.SpeechStringToCommand;

public class WindowsNavigation implements GrammarLibraryExtend {
	public boolean searchForCommand(String word) throws AWTException {
		boolean resultFound = false;
		switch(word.toLowerCase()) {
		//CHANGE FILE (control tab, control shift tab)
			case "application": resultFound=true;changeApplicationInFocus();
			break;
		//SAVE FILE (control s) 
			case "shave": resultFound=true;Roboto.doType(KeyEvent.VK_CONTROL, KeyEvent.VK_S);
			break;
		// REMOVE FILE FROM ENVIRONMENT (control w)
			case "impeach": resultFound=true;Roboto.doType(KeyEvent.VK_CONTROL, KeyEvent.VK_W);
			break;
		// REDO ( shift-y)
			case "redo": resultFound=true;Roboto.doType(KeyEvent.VK_CONTROL, KeyEvent.VK_Y);
			break;
		//CHANGE FILE IN FOCUS (control-y)  
			case "cambridge": resultFound=true;changeFileInFocus();
			break;
					 	}
		return resultFound;
}
//CUSTOM FUNCTIONS
	private static void changeApplicationInFocus() throws AWTException {
		int numberPrecedent=SpeechStringToCommand.numberPrecedent;
		String returnStringCommand=Integer.toString(numberPrecedent);
		//individual function code
		if(SpeechStringToCommand.parameterPrecedent!=null&&SpeechStringToCommand.parameterPrecedent.equalsIgnoreCase("prequel")) {
			for(int i=0;i<numberPrecedent;i++) {
			Roboto.doType(KeyEvent.VK_ALT,KeyEvent.VK_SHIFT,KeyEvent.VK_TAB);
			returnStringCommand+="(control)(shift)(tab)";
			}
		}else {
			for(int i=0;i<numberPrecedent;i++) {
			Roboto.doType(KeyEvent.VK_ALT,KeyEvent.VK_TAB);
			returnStringCommand+="(control)(tab)";
			}
		}
		//reset parameters
		SpeechStringToCommand.numberPrecedent=1;
		SpeechStringToCommand.parameterPrecedent=null;
		//return command
		System.out.println(returnStringCommand);
	}
	private static void changeFileInFocus() throws AWTException {
		int numberPrecedent=SpeechStringToCommand.numberPrecedent;
		String returnStringCommand=Integer.toString(numberPrecedent);
		//individual function code
		if(SpeechStringToCommand.parameterPrecedent!=null&&SpeechStringToCommand.parameterPrecedent.equalsIgnoreCase("prequel")) {
			for(int i=0;i<numberPrecedent;i++) {
			Roboto.doType(KeyEvent.VK_CONTROL,KeyEvent.VK_SHIFT,KeyEvent.VK_TAB);
			returnStringCommand+="(control)(shift)(tab)";
			}
		}else {
			for(int i=0;i<numberPrecedent;i++) {
			Roboto.doType(KeyEvent.VK_CONTROL,KeyEvent.VK_TAB);
			returnStringCommand+="(control)(tab)";
			}
		}
		//reset parameters
		SpeechStringToCommand.numberPrecedent=1;
		SpeechStringToCommand.parameterPrecedent=null;
		//return command
		System.out.println(returnStringCommand);
	}
}
