package speechToCommand.Grammars;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import speechToCommand.Roboto;
import speechToCommand.SpeechStringToCommand;

public class VimNavigation {
	static boolean visualMode=false;
	public static boolean searchForCommand(String word) throws AWTException {
		boolean resultFound = false;
		switch(word.toLowerCase()) {
		//MOVE UP RIGHT DOWN LEFT
			case "right": resultFound=true;np("l");
			break;
			case "write": resultFound=true;np("l");
			break;
			case "left" : resultFound=true;np("h");
			break;
			case "up" : resultFound=true;np("k");
			break;
			case "cup" : resultFound=true;np("k");
			break;
			case "down" : resultFound=true;np("j");
			break;
		//GO TO LINE 
			case "teleport" : resultFound=true;np("G"); 
			break;
		//NAVIGATE LINE
			case "jump" : resultFound=true;p(false,new String[] {"$","prequel","0"}); 
			break;
		//WORD FORWARD BACK AND BEFORE END (e)
			case "forward" : resultFound=true;np("w");
			break;
			case "back" : resultFound=true;np("b");
			break;
			case "bird" : resultFound=true;np("e");
			break;	
		//INSERT FUNCTIONS
					//insert : PARAMETER FUNCTION [nose (front of line insert) - I, extremity||extremities (end of line insert) - A]
			case "insert" : resultFound=true;p(new String[] {"i","nose","I","extremity","A"});
			break;
					//insert new line above
			case "above" : resultFound=true;np("O");
			break;
					//insert new line below
			case "below" : resultFound=true;np("o");
			break;	
					//insert after cursor
			case "after" : resultFound=true;np("a");
			break;
		//DELETE FUNCTIONS
					//general delete
			case "delete" : resultFound=true;np("x");
			break;
					//delete word forward
			case "trash" : resultFound=true;np("dw");
			break;
					//delete word backward
			case "retreat" : resultFound=true;np("db");
			break;
					//delete word to end
			case "before" : resultFound=true;np("de");
			break;
					//delete entire line
			case "nuclear" : resultFound=true;np("dd");
			break;
					//delete right of line
			case "black" : resultFound=true;np("d$");
			break;
					//delete left of line
			case "white" : resultFound=true;np("d^");
			break;	
		//REPEAT COMMAND
			case "repeat" : resultFound=true;np(".");
			break;
		//UNDO COMMAND
			case "undo" : resultFound=true;np("u");
			break;
		// SEARCH  
			case "search" : resultFound=true;p(new String[] {"/","prequel","?"});
			break;
		//ITERATE TO NEXT AND PREVIOUS SEARCH INSTANCES
			case "next" : resultFound=true;np("n");
			break;
			case "last" : resultFound=true;np("N");
			break;
		//SAVE FILE
			//for some reason the colon key does not work
			//case "record" : resultFound=true;Roboto.doType(KeyEvent.VK_SHIFT,KeyEvent.VK_W);
			//break;
		//VISUAL MODE
			case "visual" : resultFound=true;np("v");visualMode=!visualMode;
			break;
		//NORMAL MODE
			case "normal" : resultFound=true; Roboto.doType(KeyEvent.VK_ESCAPE);visualMode=false;
			break;
		//COMMENT LINE
			case "comet" : resultFound=true;np("I//");
			break;
			case "comment" : resultFound=true;np("I//");
			break;
		//COPY AND PASTE LINE (DUPLICATE) 
			case "duplicate" : resultFound=true;np("yyp"); 
			break;
		//COPY : PARAMETER FUNCTION [level (whole line) - yy, subsequent (next word) - yw, prequel (previous word)- yb]
			case "copy" : resultFound=true;p(new String[]{"y","level","yy","subsequent","yw","prequel","yb"});visualMode=false; 
			break;
		//PASTE : PARAMETER FUNCTION [before (paste before cursor) - P]
			case "paste" : resultFound=true;p(new String[]{"p","prequel","P"});
			break;
		//INDENT LINE
			case "indent" : resultFound=true;p(new String[]{">>","prequel","<<"});
			break;
			case "dent" : resultFound=true;p(new String[]{">>","prequel","<<"});
			break;
		//CHECK IF VISUAL MODE IS ON
			case "spectrometer" : resultFound=true;System.out.println("visual mode: "+visualMode);	
			break;
		//CHANGE : PARAMETER FUNCTION [subsequent(next word) - cw, prequel (prev word) - cb, avian (to word end) - ce]
			case "change" : resultFound=true;p(new String[] {"c","subsequent","cw","prequel","cb","avian","ce"});
			break;
		//replace function goes here
			case "replace":resultFound=true;np("r");
			break;
		 //  TOP AND BOTTOM 
			case "top":resultFound=true;np("H");
			break;
			case "bottom":resultFound=true;np("L");
			break;
		}	
		return resultFound;
	}
	////////////////////////////////////////
	//////NON PARAMETER FUNCTIONS
	////////////////////////////////////////
	public static String np(String command) throws AWTException {
		int numberPrecedent=SpeechStringToCommand.numberPrecedent;
		String returnStringCommand=Integer.toString(numberPrecedent);
		//individual function code
		returnStringCommand+=command;
		//robot send keys
		if(!visualMode) {Roboto.doType(KeyEvent.VK_ESCAPE);}
		Roboto.typeString(returnStringCommand);
		//reset parameters
		SpeechStringToCommand.numberPrecedent=1;
		SpeechStringToCommand.parameterPrecedent=null;
		//return command
		System.out.println(returnStringCommand);
		return returnStringCommand;	
	}
	////////////////////////////////////////
	//////METHOD FOR PARAMETER FUNCTIONS
	////////////////////////////////////////
	private static String p(String[] parametersInput) throws AWTException {
		int numberPrecedent=SpeechStringToCommand.numberPrecedent;
		String returnStringCommand=Integer.toString(numberPrecedent);
		//individual function code
		//parameter arguments form as follows: first element is command if no parameter, second is the first parameter, third is the command for the last parameter
		if(SpeechStringToCommand.parameterPrecedent!=null){
			for(int i=0;i<parametersInput.length;i++) {	
				if(SpeechStringToCommand.parameterPrecedent.equalsIgnoreCase(parametersInput[i])){
					returnStringCommand+=parametersInput[i+1];
				}
			}
		}else{
			returnStringCommand+=parametersInput[0];
		}
		//robot send keys
		if(!visualMode) {Roboto.doType(KeyEvent.VK_ESCAPE);}
		Roboto.typeString(returnStringCommand);
		//reset parameters
		SpeechStringToCommand.numberPrecedent=1;
		SpeechStringToCommand.parameterPrecedent=null;
		//return command
		System.out.println(returnStringCommand);
		return returnStringCommand;
	}
	private static String p(boolean numberInclusion, String[] parametersInput) throws AWTException {
		int numberPrecedent=SpeechStringToCommand.numberPrecedent;
		String returnStringCommand="";//=Integer.toString(numberPrecedent);
		//individual function code
		//parameter arguments form as follows: first element is command if no parameter, second is the first parameter, third is the command for the last parameter
		if(SpeechStringToCommand.parameterPrecedent!=null){
			for(int i=0;i<parametersInput.length;i++) {	
				if(SpeechStringToCommand.parameterPrecedent.equalsIgnoreCase(parametersInput[i])){
					returnStringCommand+=parametersInput[i+1];
				}
			}
		}else{
			returnStringCommand+=parametersInput[0];
		}
		//robot send keys
		if(!visualMode) {Roboto.doType(KeyEvent.VK_ESCAPE);}
		Roboto.typeString(returnStringCommand);
		//reset parameters
		SpeechStringToCommand.numberPrecedent=1;
		SpeechStringToCommand.parameterPrecedent=null;
		//return command
		System.out.println(returnStringCommand);
		return returnStringCommand;
	}
}