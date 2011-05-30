/**
 * 
 */
package serverPlugin;


import java.util.ArrayList;

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
public class RunningProcessesProcessing implements PrimeServerPluginInterface
{
	@Override
	public String getPluginName()
	{
		return "Running Processes";
	}

	@Override
	public String getPluginDescription()
	{
		return "Gets all the running processes on a computer(for all users).";
	}

	@Override
	public String getPluginAuthor()
	{
		return "Bahram Malaekeh";
	}

	@Override
	public ImplementationType getImplementationType()
	{
		return ImplementationType.NATIVE;
	}

	@Override
	public String getPluginVersion()
	{
		return "1.0";
	}

	@Override
	public void initiatPlugin(Object obj)
	{

	}

	@Override
	public void agentInputProcessing(String input)
	{
		// The array that will contain all the names of the processes.
		ArrayList<String> processes = new ArrayList<String>();


		System.out.println("Plugin processing input: " + input);
	}
}
