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
 */
@PluginImplementation
public class NetworkConnectionsProcessing implements PrimeServerPluginInterface
{

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginName()
	 */
	@Override
	public String getPluginName()
	{
		return "Basic Network Connections";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * agentPluginInterface.PrimeAgentPluginInterface#getPluginDescription()
	 */
	@Override
	public String getPluginDescription()
	{
		return "The devices basic network connection information.";
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginAuthor()
	 */
	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * agentPluginInterface.PrimeAgentPluginInterface#getImplementationType()
	 */
	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.MIXED;
	}

	/*
	 * (non-Javadoc)
	 * @see agentPluginInterface.PrimeAgentPluginInterface#getPluginVersion()
	 */
	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * serverPluginInterface.PrimeServerPluginInterface#initiatPlugin(objects
	 * .Object)
	 */
	@Override
	public void initiatPlugin(Object obj)
	{
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * serverPluginInterface.PrimeServerPluginInterface#agentInputProcessing
	 * (java.lang.String)
	 */
	@Override
	public void agentInputProcessing(String input)
	{
		System.out.println("Plugin processing input: " + input);
	}
}
