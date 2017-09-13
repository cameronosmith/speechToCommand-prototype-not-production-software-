package speechToCommand;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import speechToCommand.Grammars.*;

public class ClassifyWord {
	//main classify word function
	public static void classifyWord(String word) throws AWTException {
		if(isNumeric(word)) {
			System.out.println("Number recognized: "+word);
		}
		else if(isParameter(word)) {
			System.out.println("Parameter recognized: "+word);
		}
		else {
			referenceLibraries(word);
		}
	}
	//references the word by all grammar libraries (must manually insert library with if-library.searchForCommand etc)
		public static void referenceLibraries(String word) throws AWTException {
			List<GrammarLibraryExtend> listOfLibraries = new ArrayList();
			listOfLibraries.add(new VimNavigation());
			listOfLibraries.add(new WindowsNavigation());
			listOfLibraries.add(new JavaDictationCommands());

			boolean isGrammarLibrary=false;	
			for(int i=0;i<listOfLibraries.size();i++) {
				if((listOfLibraries.get(i)).searchForCommand(word)) {
					isGrammarLibrary=true;
					break;
				}
			}
			if(!isGrammarLibrary) {
				(new GeneralDictation()).toDictation(word);
			}
			isGrammarLibrary=false;
		}
	//determine if word sent is a number and store it in the number placeholder if so
	private static boolean isNumeric(String str){
		  boolean isNumber=false;
		if(str.matches("-?\\d+(\\.\\d+)?")){
			SpeechStringToCommand.numberPrecedent=Integer.parseInt(str);
			isNumber=true;
		} else if(str.equalsIgnoreCase("one")||
				  str.equalsIgnoreCase("two")||str.equalsIgnoreCase("too")||str.equalsIgnoreCase("to")||str.equalsIgnoreCase("three")||
				  str.equalsIgnoreCase("four")||str.equalsIgnoreCase("for")||str.equalsIgnoreCase("five")||
				  str.equalsIgnoreCase("six")||str.equalsIgnoreCase("seven")||
				  str.equalsIgnoreCase("eight")||str.equalsIgnoreCase("nine")) {
							isNumber=true;
							switch(str.toLowerCase()) {
							  case "one" : SpeechStringToCommand.numberPrecedent=1; break;
							  case "to" : SpeechStringToCommand.numberPrecedent=2; break;
							  case "too" : SpeechStringToCommand.numberPrecedent=2; break;
							  case "two" : SpeechStringToCommand.numberPrecedent=2; break;
							  case "three" : SpeechStringToCommand.numberPrecedent=3; break;
							  case "four" : SpeechStringToCommand.numberPrecedent=4; break;
							  case "for" : SpeechStringToCommand.numberPrecedent=4; break;
							  case "five" : SpeechStringToCommand.numberPrecedent=5; break;
							  case "six" : SpeechStringToCommand.numberPrecedent=6; break;
							  case "seven" : SpeechStringToCommand.numberPrecedent=7; break;
							  case "eight" : SpeechStringToCommand.numberPrecedent=8; break;
							  case "nine" : SpeechStringToCommand.numberPrecedent=9; break;
							  }
		}
		  return isNumber;
	}
	//determine if a word sent is a set parameter and store it in the parameter placeholder if so
	private static boolean isParameter(String str) {
		boolean isParameter=false;
		String[] listOfParameters = {"level","subsequent", "prequel","before","nose","extremity","extremities"};
		for(int i=0;i<listOfParameters.length;i++) {
			if(str.equalsIgnoreCase(listOfParameters[i])) {
			SpeechStringToCommand.parameterPrecedent=str.toLowerCase();
			isParameter=true;
			}
		}
	return isParameter;
	}
}
