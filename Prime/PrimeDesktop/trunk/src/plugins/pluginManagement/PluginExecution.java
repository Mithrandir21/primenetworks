/**
 * 
 */
package plugins.pluginManagement;


import graphics.PrimeMain;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import localPluginInterface.PrimeLocalPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PluginExecution implements Runnable
{
	/**
	 * The Queue of {@link PluginJob} jobs, that will be run sequentially.
	 */
	private Queue<Runnable> queue;

	/**
	 * A boolean on whether all the jobs have finished.
	 */
	private boolean jobsFinished = false;


	/**
	 * A constructor that initiates this classes {@link Queue} by creating a
	 * {@link LinkedList} of {@link Runnable Runnables}.
	 */
	public PluginExecution()
	{
		queue = new LinkedList<Runnable>();

		// Adds the first job so that the queue is not empty.
		queue.add(new SleepJob());
	}


	/**
	 * This function adds the given {@link PluginJob} to this
	 * classes {@link Queue} of jobs waiting for information delivery.
	 */
	public void addPluginJob(PrimeLocalPluginInterface plugin)
	{
		if ( plugin != null )
		{
			// PluginJob job = new PluginJob(plugin);

			queue.add(plugin);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		System.out.println("\n\n\nStarting plugin jobs at " + new Date());

		while ( !queue.isEmpty() )
		{
			// Gets the top TransmissionJob
			Runnable job = queue.poll();

			Thread jobThread = new Thread(job);

			/**
			 * This part is synchronized so the server only gets plugin
			 * from on PluginJob at a time. When the PluginJob is
			 * finished, it will send notify() and the wait()
			 * in this part with continue.
			 */
			synchronized (jobThread)
			{
				try
				{
					jobThread.start();

					jobThread.wait();
				}
				catch ( InterruptedException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


			/**
			 * If the queue is empty and the "EndQueue" is not true, which
			 * means that the system does not want to end the plugin running.
			 */
			if ( queue.isEmpty() && (PrimeMain.pluginMan.endQueue == false) )
			{
				queue.add(new SleepJob());
			}
		}

		jobsFinished = true;
	}


	public boolean arePluginsFinished()
	{
		return jobsFinished;
	}
}
