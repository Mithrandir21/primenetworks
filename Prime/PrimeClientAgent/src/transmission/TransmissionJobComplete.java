/**
 * 
 */
package transmission;


import java.io.IOException;

import utils.ServerConUtils;


/**
 * This {@link Runnable} class should be used to indicate that all plugins have
 * been added to the transmission queue.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class TransmissionJobComplete implements Runnable
{
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		synchronized (this)
		{
			try
			{
				if ( ServerConUtils.socket != null
						&& ServerConUtils.socket.isConnected() )
				{
					ServerConUtils.printwriter.println("Bye");

					String lineread = "";
					while ( (lineread = ServerConUtils.bufferedreader
							.readLine()) != null )
					{
						System.out.println("Received from Server: " + lineread);
					}
				}
			}
			catch ( IOException ioe )
			{
				System.out.println("IOException: " + ioe.getMessage());
			}
			finally
			{
				this.notify();
			}

		}
	}
}
