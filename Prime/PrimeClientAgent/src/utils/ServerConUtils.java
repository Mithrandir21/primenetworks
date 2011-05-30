/**
 * 
 */
package utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * This class contains variables and functions that assist in the sending and receiving information
 *
 * @author Bahram Malaekeh
 * 
 */
public class ServerConUtils
{
	/**
	 * The socket that will maintain a connection the PrimeDesktop server.
	 */
	public static Socket socket = null;

	/**
	 * The stream that will take the input sent by the server.
	 */
	public static InputStreamReader inputstreamreader = null;

	/**
	 * The {@link BufferedReader} that will contain the server input.
	 */
	public static BufferedReader bufferedreader = null;

	/**
	 * The {@link PrintWriter} that will write to the {@link Socket#getOutputStream()}, ie write to the server.
	 */
	public static PrintWriter printwriter = null;



	/**
	 * This function will make the socket and other variables ready for sending and receiving information from the server. 
	 */
	public static boolean makeSocketReady()
	{
		try
		{
			System.out.println("Connecting to " + AgentMain.serverurl
					+ " on port " + AgentMain.serverport);
			socket = new Socket(AgentMain.serverurl, AgentMain.serverport);


			// Set socket timeout for 10000 milliseconds or 10 seconds just
			// in case the host can't be reached
			socket.setSoTimeout(10000);
			System.out.println("Connected.");


			inputstreamreader = new InputStreamReader(socket.getInputStream());
			bufferedreader = new BufferedReader(inputstreamreader);
			// establish an PrintWriter using the output stream of the
			// socket object and set auto flush on
			printwriter = new PrintWriter(socket.getOutputStream(), true);
		}
		catch ( UnknownHostException e )
		{
			e.printStackTrace();
			return false;
		}
		catch ( SocketException e )
		{
			e.printStackTrace();
			return false;
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			return false;
		}


		return true;
	}



	/**
	 * This function will close the socket connection and other variables.
	 */
	public static void closeSocket()
	{
		try
		{
			if ( socket != null && bufferedreader != null
					&& inputstreamreader != null && printwriter != null )
			{
				System.out.println("Closing connection.");

				bufferedreader.close();
				inputstreamreader.close();
				printwriter.close();

				socket.close();
			}
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
