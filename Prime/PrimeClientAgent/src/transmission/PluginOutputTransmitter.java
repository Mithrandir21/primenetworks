/**
 * 
 */
package transmission;


import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import utils.ServerConUtils;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * This class contains a {@link Queue} that will hold all
 * {@link TransmissionJob TransmissionJobs} that will send information to the
 * PrimeAgentListener on the PrimeDesktop server.
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PluginOutputTransmitter implements Runnable
{
	/**
	 * The Queue of {@link TransmissionJob} jobs, that will be run sequentially.
	 */
	private Queue<Runnable> queue;

	/**
	 * A constructor that initiates this classes {@link Queue} by creating a
	 * {@link LinkedList} of {@link Runnable Runnables}.
	 */
	public PluginOutputTransmitter()
	{
		queue = new LinkedList<Runnable>();
	}


	/**
	 * This function adds the given {@link PrimeAgentPluginInterface} to this
	 * classes {@link Queue} of jobs waiting for information delivery.
	 */
	public void addTransmissionJob(PrimeAgentPluginInterface plugin)
	{
		if ( plugin != null )
		{
			TransmissionJob job = new TransmissionJob(plugin);

			queue.add(job);
		}
	}


	/**
	 * This functions add a {@link TransmissionJobComplete} job to this classes
	 * {@link Queue} to show when all transmissions have been completed.
	 * 
	 * NOTE: SHOULD ONLY BE ADDED AFTER ALL TRANSMISSIONS!
	 */
	public void addTransmissionCompleteJob()
	{
		queue.add(new TransmissionJobComplete());
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		System.out.println("\n\n\nStarting transmissions job at " + new Date());

		// Creates the connection
		boolean connectionMade = ServerConUtils.makeSocketReady();

		// If there is a connection and the queue is not empty
		while ( connectionMade && !queue.isEmpty() )
		{
			// Gets the top TransmissionJob
			Runnable job = queue.poll();

			Thread jobThread = new Thread(job);

			/**
			 * This part is synchronized so the server only gets transmissions
			 * from on TransmissionJob at a time. When the TransmissionJob is
			 * finished with transmitting, it will send notify() and the wait()
			 * in this part with continue.
			 */
			synchronized (jobThread)
			{
				try
				{
					System.out.println("\n\n");
					System.out
							.println("Starting transmission at " + new Date());
					// Starts the transmission
					jobThread.start();

					// Waits for the notify() from the transmission so it can go
					// on to the next TransmissionJob.
					jobThread.wait();
				}
				catch ( InterruptedException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Finished transmission at " + new Date());
			}
		}

		ServerConUtils.closeSocket();
	}
}
