package speechToCommand;

public class StringParameterKeywords {
	public static boolean searchForParamterKeyword(String str) {
		String[] listOfParameters = {"level","subsequent", "prequel","before","nose","extremity","extremities"};
		boolean isParameterKeyword=false;
		for(int i=0;i<listOfParameters.length;i++) {
			if(str.equalsIgnoreCase(listOfParameters[i])) {
				isParameterKeyword=true;
			}
		}
		return isParameterKeyword;
	}
}
