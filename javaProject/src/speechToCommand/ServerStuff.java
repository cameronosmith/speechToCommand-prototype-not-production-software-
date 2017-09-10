package speechToCommand;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

public class ServerStuff {
	public ServerStuff() throws AWTException {
		int port = 20222;
		ServerSocket listenSock = null; //the listening server socket
		Socket sock = null;			 //the socket that will actually be used for communication

		try {

			listenSock = new ServerSocket(port);

			while (true) {	   //we want the server to run till the end of times

				sock = listenSock.accept();			 //will block until connection received

				BufferedReader br =	new BufferedReader(new InputStreamReader(sock.getInputStream()));
				BufferedWriter bw =	new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
				String line = "";
				while ((line = br.readLine()) != null) {
					bw.write( line + "\n");
					bw.flush();
					SpeechStringToCommand.classifyAndCompileWord(line);
					System.out.println(line);
				}

				//Closing streams and the current socket (not the listening socket!)
				bw.close();
				br.close();
				sock.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}