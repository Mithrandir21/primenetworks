/**
 * 
 */
package graphics.services;


/**
 * The class contains all the service threads this system will be running. All services can be stopped and started, all
 * together or gotten and stop separately.
 * 
 * @author Bahram Malaekeh
 */
public class PrimeService
{
	/**
	 * 
	 */
	private CanvasService canvasService;


	/**
	 * Constructor for this class that creates new instances of the threads in this class.
	 */
	public PrimeService()
	{
		canvasService = new CanvasService();
	}



	/**
	 * Calls the Run function for the this classes threads.
	 */
	public synchronized void startCanvasService()
	{
		canvasService.run();
	}


	/**
	 * Calls the ServiceSuspend function for the this classes threads.
	 */
	public synchronized void stopCanvasService()
	{
		canvasService.serviceSuspend();
	}




	/**
	 * Calls the ServiceSuspend function for the all this classes threads.
	 */
	public void stopAll()
	{
		canvasService.serviceSuspend();
	}



	// GETTERS


	/**
	 * Gets the {@link CanvasService} thread.
	 */
	public CanvasService getCanvasService()
	{
		return canvasService;
	}

}
