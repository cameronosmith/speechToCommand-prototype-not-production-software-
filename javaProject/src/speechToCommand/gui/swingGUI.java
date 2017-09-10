package speechToCommand.gui;

import java.awt.Color;

import javax.swing.*;

public class swingGUI {
	
	static JFrame frame=new JFrame("SpeechToCommand");
	private static JTextArea textComponent=new JTextArea();
	private static JPanel panel=new JPanel();
	
	private final static String newline = "\n";
	
		public static void createGUI() {
		   frame.setVisible(true);
		   frame.setSize(200,200);
		   
		   frame.add(panel);
		   panel.add(textComponent);
	   }
	   public static void addText(String text) {
		   textComponent.append(text+newline);
		   panel.add(textComponent);
	   }   
	   	
   
}
