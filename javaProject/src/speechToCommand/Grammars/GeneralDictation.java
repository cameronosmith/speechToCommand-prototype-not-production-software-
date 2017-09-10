package speechToCommand.Grammars;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import speechToCommand.Roboto;
import speechToCommand.SpeechStringToCommand;

public class GeneralDictation {
	static boolean dictationMode=true;
	static boolean capital=false;
	static boolean capsLock=false;
	static boolean automaticSpacing=false;
	static boolean typeMode=false;
	//CHECK IF USER IS TRYING TO TURN DICTATION MODE ON OR OFF
	public static void toDictation(String word) throws AWTException {		
		if(word.equalsIgnoreCase("dictate")) {
			dictationMode=!dictationMode;
			if(dictationMode) {System.out.println("Dictation:"+dictationMode);}
		}
		else if (dictationMode) {
			scribe(word.toLowerCase());
		}
	}
	//DICTATION MODE IS ON, PRINT WORD
	public static void scribe(String word) throws AWTException {
		if(isDictationCommand(word)) {System.out.println("Dictation command: "+word);}
		else {
			if(capital) {
				System.out.println("uppercasing: "+word);
				if(word.length()>2) {
					word=Character.toUpperCase(word.charAt(0)) + word.substring(1);	
				}
			} 
			if(automaticSpacing) {
				word=word+" ";
			}
			if(typeMode) {
				if(word.length()>1) {
					word=word.substring(0,1);	
				}
			} 
			if(capsLock) {
				 if(word.length()>1) {
					word=word.toUpperCase();
				}
			}
			Roboto.typeString(word);
			System.out.println("Dictation printing "+word);
		}
	}
	//CHECK IF WORD IS A DICTATION COMMAND
	private static boolean isDictationCommand(String word) throws AWTException { 
		boolean isDictationCommand=false;
		switch(word.toLowerCase()) {
////////////////////////////
// dictation command options   
////////////////////////////
		case "capital" : isDictationCommand=true; capital=!capital;System.out.println("capital: "+capital);
		break;
		case "capitol" : isDictationCommand=true; capital=!capital;System.out.println("capital: "+capital);
		break;
		case "caps" : isDictationCommand=true; capsLock=!capsLock;System.out.println("caps: "+capsLock);
		break;
		case "spacing" : isDictationCommand=true; automaticSpacing=!automaticSpacing;System.out.println("automaticSpacing: "+automaticSpacing);
		break;
		case "type" : isDictationCommand=true; typeMode=!typeMode;System.out.println("typeMode: "+typeMode); 
		break;
		case "motherboard" : isDictationCommand=true; System.out.println("dictation mode: "+dictationMode+", capital: "+capital+", capsLock: "+capsLock+", automaticSpacing: "+automaticSpacing+", typeMode: "+typeMode);
		break;
		case "reset" : isDictationCommand=true; capital=false;capsLock=false;automaticSpacing=false;typeMode=false; 
		break;
// general keyboard commands  
		// PRESS ENTER KEY (enter)
			case "infiltrate": isDictationCommand=true;Roboto.doType(KeyEvent.VK_ENTER);
			break;
		// PRESS  BACKSPACE KEY (backspace)
			case "erase": isDictationCommand=true;
			for(int i=0;i<SpeechStringToCommand.numberPrecedent;i++) {
				Roboto.doType(KeyEvent.VK_BACK_SPACE); 
			}
			break;
		// TYPE "TO" (gets ignored by  number precedent )
			case "taboo": isDictationCommand=true;
			if(capital) {Roboto.typeString("To");}
			else{Roboto.typeString("to");}	
			break;
			//CHANGE BRAKE TO BREAK (break more common)
			case "brake": isDictationCommand=true;Roboto.typeString("break");
			break;
		// PRESS SINGLE DBL QUOTE KEY (double quote)	
			case "quote": isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_QUOTE);
			break; 
		// PRESS SPACEBAR KEY (space bar)
			case "valley": isDictationCommand=true;Roboto.doType(KeyEvent.VK_SPACE);
			break;
		// PRESS COLON KEY (colon)
			case "colon": isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_SEMICOLON);
			break;
		// PRESS SEMICOLON KEY (semicolon)
			case "semicolon": isDictationCommand=true;Roboto.doType(KeyEvent.VK_SEMICOLON);
			break;
		// PRESS PERIOD KEY (period)
			case "period": isDictationCommand=true;Roboto.doType(KeyEvent.VK_PERIOD);
			break;
		// PRESS UNDERSCORE KEY (underscore)
			case "underscore": isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_MINUS);
			break;	
		// PRESS  EQUALS KEY (=)
			case "equals": isDictationCommand=true;Roboto.doType(KeyEvent.VK_EQUALS);
			break;
			case "equal": isDictationCommand=true;Roboto.doType(KeyEvent.VK_EQUALS);
			break; 
		//  PRINT NUMBER ( print number stored in number precedent) 
			case "number": isDictationCommand=true;Roboto.typeString(Integer.toString(SpeechStringToCommand.numberPrecedent));
			break;
		// PARANTHESES ( parenthesis and left key command)
			case "parents": isDictationCommand=true;Roboto.pc("()", new int[] {KeyEvent.VK_LEFT});
			break;
			case "parent": isDictationCommand=true;Roboto.pc("()", new int[] {KeyEvent.VK_LEFT});
			break;		
		//CURLY BRACKETS (curly brackets and left key command)
			case "bracket": isDictationCommand=true;Roboto.pc("{}",new int[] {KeyEvent.VK_LEFT});
			break;
			case "brackets": isDictationCommand=true;Roboto.pc("{}",new int[] {KeyEvent.VK_LEFT});
			break;
		//REGULAR BRACKETS ( brackets and left command)
			case "index": isDictationCommand=true;Roboto.pc("[]",new int[] {KeyEvent.VK_LEFT});
			break;
		// PRESS TAB ( tab )
			case "tab ": isDictationCommand=true;Roboto.doType(KeyEvent.VK_TAB); 
			break;
		//TAB (press tab key)
			case "tab":isDictationCommand=true;Roboto.doType(KeyEvent.VK_TAB);
			break;
		// PLUS MINUS  	
			case "plus":isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_EQUALS);
			break;
			case "minus":isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_EQUALS);
			break;
		 // CORRECTION FOR TWO BACK  
			case "tubac":isDictationCommand=true;SpeechStringToCommand.numberPrecedent=2;VimNavigation.np("b");
			break;
		 //GREATER THAN AND LESS THAN  
			case "less":isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_COMMA);
			break;
			case "more":isDictationCommand=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_PERIOD);
			break;
		//COMPUTER MISTAKING BOOLEAN 
			case "binary":isDictationCommand=true;Roboto.typeString("boolean");
			break;
	 	}
		return isDictationCommand;
	}
	
}
