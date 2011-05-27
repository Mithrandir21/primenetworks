/**
 * 
 */
package javaInfoGathering;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import utils.DeviceType;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SoftwareInfo
{
	/**
	 * Gets all the processes running on the local client as String
	 * {@link ArrayList}. Will determine the correct function to call based on
	 * the given {@link DeviceType}. On error, return null.
	 */
	public static ArrayList<String> getRunningProcesses(DeviceType type)
	{
		if ( type == DeviceType.WINDOWS )
		{
			return winGetRunningProcesses();
		}


		return null;
	}


	/**
	 * Gets all the processes running on the Windows client. Uses
	 * "tasklist.exe /fo csv /nh".
	 */
	public static ArrayList<String> winGetRunningProcesses()
	{
		ArrayList<String> processes = new ArrayList<String>();

		try
		{
			String line;
			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
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

		return processes;
	}


	public static ArrayList<String> unixGetRunningProcesses()
	{
		ArrayList<String> processes = new ArrayList<String>();


		try
		{
			String line;
			Process p = Runtime.getRuntime().exec("ps -aux");
			BufferedReader input = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
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

		return processes;
	}
}
