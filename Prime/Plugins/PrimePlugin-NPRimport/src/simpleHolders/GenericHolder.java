/**
 * 
 */
package simpleHolders;


import java.util.ArrayList;
import java.util.Iterator;

import objects.Hardware;
import objects.Software;
import objects.softwareObjects.OperatingSystem;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class GenericHolder
{
	/**
	 * The agent ID of the generic object.
	 */
	private int agentID = -1;


	/**
	 * This variable holds all the information about the agent found in the
	 * UP_Agents table.
	 */
	private ArrayList<String> agentInfo = new ArrayList<String>();


	/**
	 * This variable will hold all the found {@link OperatingSystem
	 * OperatingSystems} that belong to the agent.
	 */
	private ArrayList<OperatingSystem> agentOSs = new ArrayList<OperatingSystem>();


	/**
	 * This variable will hold all the found {@link Hardware} that belong to the
	 * agent.
	 */
	private ArrayList<Hardware> agentHardware = new ArrayList<Hardware>();


	/**
	 * This variable will hold all the found {@link Software} that belong to the
	 * agent.
	 */
	private ArrayList<Software> agentSoftware = new ArrayList<Software>();



	public GenericHolder(ArrayList<String> info)
	{
		agentInfo = info;
		setAgentID();
	}



	private void setAgentID()
	{
		Iterator<String> itr = agentInfo.iterator();

		int i = 0;
		while ( itr.hasNext() )
		{
			i++;
			String s = itr.next();

			if ( s != null && i == 24 )
			{
				try
				{
					agentID = Integer.parseInt(s);
				}
				catch ( NumberFormatException e )
				{
					// TODO: handle exception
				}
			}
		}
	}


	public int getAgentID()
	{
		return agentID;
	}



	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the agentInfo
	 */
	public ArrayList<String> getAgentInfo()
	{
		return agentInfo;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the agentOSs
	 */
	public ArrayList<OperatingSystem> getAgentOSs()
	{
		return agentOSs;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the agentHardware
	 */
	public ArrayList<Hardware> getAgentHardware()
	{
		return agentHardware;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the agentSoftware
	 */
	public ArrayList<Software> getAgentSoftware()
	{
		return agentSoftware;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param agentInfo
	 *            the agentInfo to set
	 */
	public void setAgentInfo(ArrayList<String> agentInfo)
	{
		this.agentInfo = agentInfo;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param agentOSs
	 *            the agentOSs to set
	 */
	public void setAgentOSs(ArrayList<OperatingSystem> agentOSs)
	{
		this.agentOSs = agentOSs;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param agentHardware
	 *            the agentHardware to set
	 */
	public void setAgentHardware(ArrayList<Hardware> agentHardware)
	{
		this.agentHardware = agentHardware;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param agentSoftware
	 *            the agentSoftware to set
	 */
	public void setAgentSoftware(ArrayList<Software> agentSoftware)
	{
		this.agentSoftware = agentSoftware;
	}

}
