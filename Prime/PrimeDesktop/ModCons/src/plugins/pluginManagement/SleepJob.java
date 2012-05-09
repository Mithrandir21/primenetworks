/**
 * 
 */
package plugins.pluginManagement;


import java.util.Date;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class SleepJob implements Runnable
{

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			// System.out.println("Start - " + new Date());
			// Sleep for a second
			Thread.sleep(1000);
			// System.out.println("End - " + new Date());
		}
		catch ( InterruptedException e )
		{
			System.out.println("interrupted - " + new Date());
		}
	}
}
