/**
 * 
 */
package agentPlugin;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.events.Init;
import utils.DeviceType;
import utils.ImplementationType;
import agentPluginInterface.PrimeAgentPluginInterface;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
@PluginImplementation
public class RunningProcesses implements PrimeAgentPluginInterface
{
	// The type of computer
	DeviceType devType;

	// A list processes
	ArrayList<String> processes;


	/**
	 * A boolean on whether or not the runnable is finished.
	 */
	boolean finished = false;


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

	@Init
	@Override
	public void initiatePluging()
	{
		// Gets the computer type
		devType = DeviceType.getLocalHostType();
	}

	@Override
	public void destroy()
	{
		devType = null;
	}


	@Override
	public String getPluginOutput()
	{
		String processes = appendAllProcesses();
		return processes;
	}


	@Override
	public void setFinished()
	{
		finished = true;
		this.notify();
	}


	@Override
	public boolean isFinished()
	{
		return finished;
	}



	@Override
	public void run()
	{
		// IF WINDOWS
		/**
		 * Gets all the processes running on the Windows client. Uses
		 * "tasklist.exe /fo csv /nh".
		 */
		if ( devType == DeviceType.WINDOWS )
		{
			try
			{
				processes = new ArrayList<String>();

				String line;
				Process p = Runtime.getRuntime().exec(
						"tasklist.exe /fo csv /nh");
				BufferedReader input = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				while ( (line = input.readLine()) != null )
				{
					if ( !line.trim().equals("") )
					{
						// keep only the process name
						line = line.substring(1);
						processes.add(line.substring(0, line.indexOf("\"")));
					}

				}
				input.close();
			}
			catch ( Exception err )
			{
				err.printStackTrace();
			}
		}
		// IF MAC
		else if ( devType == DeviceType.MAC )
		{
		}
		// IF UNIX/LINUX
		else if ( devType == DeviceType.UNIX )
		{
		}

		setFinished();
	}


	/**
	 * TODO - Description
	 */
	private String appendAllProcesses()
	{
		String processString = "";

		if ( processes != null )
		{
			Object[] procs = processes.toArray();

			for ( int i = 0; i < procs.length; i++ )
			{
				// Adds the process to the text string
				processString += procs[i].toString();

				// If i is not at the last index
				if ( (i - (procs.length - 1)) != 0 )
				{
					processString += ", ";
				}
			}
		}

		return processString;
	}
}
