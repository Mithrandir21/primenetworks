/**
 * 
 */
package agentPlugin;


import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import utils.ImplementationType;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
@PluginImplementation
public class GeneralSoftware implements PrimeAgentPluginInterface
{
	/**
	 * A boolean on whether or not the plugin has finished.
	 */
	private boolean finished = false;

	/**
	 * The string that will be sent to the server.
	 */
	private String output = "";


	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginName()
	 */
	@Override
	public String getPluginName()
	{
		return "General Software";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginDescription()
	 */
	@Override
	public String getPluginDescription()
	{
		return "General software information about the agent computer.";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginAuthor()
	 */
	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getImplementationType()
	 */
	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.JAVA;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginVersion()
	 */
	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#initiatePluging()
	 */
	@Init
	@Override
	public void initiatePluging()
	{
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#destroy()
	 */
	@Override
	public void destroy()
	{
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginOutput()
	 */
	@Override
	public String getPluginOutput()
	{
		return output;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#setFinished()
	 */
	@Override
	public void setFinished()
	{
		finished = true;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#isFinished()
	 */
	@Override
	public boolean isFinished()
	{
		return finished;
	}

	/* (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#run()
	 */
	@Override
	public void run()
	{
		output += "OS name='" + System.getProperty("os.name") + "' - ";
		output += "OS version='" + System.getProperty("os.version") + "' - ";
		output += "System Arch='" + System.getProperty("os.arch") + "' - ";
		output += "Java Version='" + System.getProperty("java.version") + "'";

		setFinished();
	}
}
