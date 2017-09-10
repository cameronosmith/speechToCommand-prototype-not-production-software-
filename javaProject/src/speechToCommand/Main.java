package speechToCommand;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import speechToCommand.gui.TextAreaLogProgram;


public class Main {

	public static void main(String args[]) throws AWTException {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                new TextAreaLogProgram().setVisible(true);
	            }
	        });
		ServerStuff server=new ServerStuff();
	}
}
