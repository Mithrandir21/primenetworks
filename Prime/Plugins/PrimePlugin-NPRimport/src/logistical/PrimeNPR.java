/**
 * 
 */
package logistical;


import localPluginInterface.PrimeLocalPluginInterface;
import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import utils.ImplementationType;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
@PluginImplementation
public class PrimeNPR implements PrimeLocalPluginInterface
{
	/**
	 * A boolean on whether the plugin has finished.
	 */
	private boolean finished = false;


	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#getPluginName()
	 */
	@Override
	public String getPluginName()
	{
		return "PrimeDesktop - Norman Patch & Remediation Import";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * localPluginInterface.PrimeLocalPluginInterface#getPluginDescription()
	 */
	@Override
	public String getPluginDescription()
	{
		return "Imports network computer information from a Norman Patch & Remediation SQL Server.";
	}

	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#getPluginAuthor()
	 */
	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * localPluginInterface.PrimeLocalPluginInterface#getImplementationType()
	 */
	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.JAVA;
	}

	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#getPluginVersion()
	 */
	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#initiatePluging()
	 */
	@Init
	@Override
	public void initiatePluging()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#destroy()
	 */
	@Override
	public void destroy()
	{
	}

	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#setFinished()
	 */
	@Override
	public void setFinished()
	{
		finished = true;
	}


	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished;
	}


	/*
	 * (non-Javadoc)
	 * @see localPluginInterface.PrimeLocalPluginInterface#run()
	 */
	@Override
	public void run()
	{
		System.out.println("" + getPluginName() + " - " + getPluginAuthor()
				+ " - " + getPluginVersion());
	}
}
