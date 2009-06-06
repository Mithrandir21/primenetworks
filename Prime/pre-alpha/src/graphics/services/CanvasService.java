/**
 * 
 */
package graphics.services;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class CanvasService implements Runnable
{
	/**
	 * The thread instance that will be run by the JVM.
	 */
	private volatile Thread service;



	/**
	 * The constructor that when called will create and start a new tread.
	 */
	public CanvasService()
	{
		service = new Thread(this);
		service.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		Thread thisThread = Thread.currentThread();

		while ( service == thisThread )
		{
			try
			{
				// DO SOMETHING

				// Makes the thread wait for a bit
				Thread.sleep(1000);
			}
			catch ( InterruptedException e )
			{

			}
		}
	}



	/**
	 * A resume function that will start a new tread and run it(just as the constructor). This function is necessary
	 * because you might want to a variables to this class and might not want to create a new instance of the entire
	 * class to be able to start the thread again.
	 */
	public synchronized void serviceResume()
	{
		service = new Thread(this);
		service.start();
	}


	/**
	 * This function sets the thread to null, which will get the JVM garbage collector to remove the entire thread.
	 */
	public synchronized void serviceSuspend()
	{
		service = null;
	}

}
