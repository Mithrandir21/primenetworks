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
	 * 
	 */
	private volatile Thread service;
	
	
	
	/**
	 * Javadoc-TODO - Description NEEDED!
	 *
	 */
	public CanvasService()
	{
		service = new Thread(this);
		service.start();
	}
	
	/* (non-Javadoc)
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
				Thread.sleep(1000);
			}
			catch ( InterruptedException e )
			{
				
			}
		}
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public synchronized void serviceResume()
	{
		service = new Thread(this);
		service.start();
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public synchronized void serviceSuspend()
	{
		service = null;
	}
	
}
