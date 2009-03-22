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
public class PrimeService 
{
	/**
	 * 
	 */
	private CanvasService canvasService;

	
	/**
	 * Javadoc-TODO - Description NEEDED!
	 *
	 */
	public PrimeService()
	{
		canvasService = new CanvasService();
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public synchronized void startCanvasService()
	{
		canvasService.run();
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public synchronized void stopCanvasService()
	{
		canvasService.serviceSuspend();
	}
	
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void stopAll()
	{
		canvasService.serviceSuspend();
	}

}
