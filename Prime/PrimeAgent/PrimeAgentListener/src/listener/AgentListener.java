/**
 * 
 */
package listener;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import main.ListenerMain;
import utils.PluginProcessing;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class AgentListener implements Runnable
{
	private Socket socket;


	/**
	 * A constructor that takes the URL (String) and port (int) to listen to.
	 */
	public AgentListener(Socket socket)
	{
		this.socket = socket;
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			// set up communications for sending and receiving lines of text
			// data establish a BufferedReader from the input stream provided by
			// the socket object
			InputStreamReader inputstreamreader = new InputStreamReader(
					socket.getInputStream());
			BufferedReader bufferedreader = new BufferedReader(
					inputstreamreader);

			// establish an printwriter using the output stream of the socket
			// object and set auto flush on
			PrintWriter printwriter = new PrintWriter(socket.getOutputStream(),
					true);

			// for binary data use
			// DataInputStream and DataOutputStream

			// for serialized objects use
			// ObjectInputStream and ObjectOutputStream

			String datetimestring = (Calendar.getInstance()).getTime()
					.toString();
			printwriter.println("You connected to the PrimeDesktop Server at "
					+ datetimestring);
			printwriter.println("Send Bye to disconnect.");

			String lineread = "";
			boolean done = false;
			while ( !done )
			{
				lineread = bufferedreader.readLine();
				if ( lineread != null )
				{
					System.out.println("Received from Client: " + lineread);
					printwriter.println("You sent: " + lineread);

					if ( lineread.compareToIgnoreCase("START--") == 0 )
					{
						// Initialize the Runnable
						PluginProcessing processing = new PluginProcessing(
								bufferedreader);

						synchronized (processing)
						{
							try
							{
								// Starts the processing
								new Thread(processing).start();

								// Waits for the processing to finish
								processing.wait();

								// Gets the result of the processing
								boolean success = processing.success;

								if ( success )
								{
									printwriter.println("--Received");
								}
								else
								{
									printwriter.println("--ERROR-RECEIVING");
								}
							}
							catch ( InterruptedException e )
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else if ( lineread.compareToIgnoreCase("bye") == 0 )
					{
						done = true;
					}
				}
			}
			System.out.println("Closing connection.");
			bufferedreader.close();
			inputstreamreader.close();
			printwriter.close();
			socket.close();
		}
		catch ( UnknownHostException unhe )
		{
			System.out.println("AgentListener - UnknownHostException: "
					+ unhe.getMessage());
		}
		catch ( InterruptedIOException intioe )
		{
			System.out
					.println("AgentListener - Timeout while attempting to establish socket connection.");
		}
		catch ( IOException ioe )
		{
			System.out.println("AgentListener - IOException: "
					+ ioe.getMessage());
		}
		finally
		{
			try
			{
				ListenerMain.activeCons--;
				socket.close();
			}
			catch ( IOException ioe )
			{
				System.out.println("AgentListener - IOException2: "
						+ ioe.getMessage());
			}
		}
	}

}
