/**
 * 
 */
package serverPlugin;


import net.xeoh.plugins.base.annotations.PluginImplementation;
import objects.Object;
import serverPluginInterface.PrimeServerPluginInterface;
import utils.ImplementationType;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
@PluginImplementation
public class GeneralSoftwareProcessing implements PrimeServerPluginInterface
{

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
	 * @see serverPluginInterface.PrimeServerPluginInterface#initiatPlugin(java.lang.Object)
	 */
	@Override
	public void initiatPlugin(Object obj)
	{

	}

	/* (non-Javadoc)
	 * @see serverPluginInterface.PrimeServerPluginInterface#agentInputProcessing(java.lang.String)
	 */
	@Override
	public void agentInputProcessing(String input)
	{
		System.out.println("Plugin processing input: " + input);
	}
}
