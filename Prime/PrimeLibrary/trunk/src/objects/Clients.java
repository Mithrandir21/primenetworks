/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package objects;


import java.io.Serializable;
import java.util.UUID;


/**
 * An abstract super class for all client objects in the system, including
 * {@link objects.clientObjects.Desktop Desktop} and
 * {@link objects.clientObjects.Laptop Laptop}. MUST ADD INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.0.1
 */
public abstract class Clients extends Object implements Serializable
{

	// FIXME - Create a standalone, singel purpose client class.


	// NETWORK INFORMATION FIELDS

	// Desktop rating
	private int clientRate;

	// Nodes before it reaches the first router outside of the systems own
	// routers, i.e. the Internet.
	private int numberOfNodes;


	/**
	 * The {@link UUID} that belongs to the Agent associated with this client.
	 */
	private UUID agentUUID;




	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 */
	protected Clients(String Name, String Desc)
	{
		super(Name, Desc);
	}



	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 *            The connection interfaces supported by the client.
	 */
	protected Clients(String Name, String Desc, String[] SupConInt)
	{
		super(Name, Desc, SupConInt);
	}



	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	protected Clients(String Name, String Desc, Object[] DesktopComponents)
	{
		super(Name, Desc, DesktopComponents);
	}


	/**
	 * Constructor of a desktop computer.<br>
	 * This constructor also sets the number of components in the system and the
	 * desktop rating to "0"(since the rating system is not yet implemented).
	 * 
	 * @param Name
	 *            The name of the desktop.
	 * @param Desc
	 *            The description of the desktop.
	 * @param SupConInt
	 *            An array of strings that describes the supported connection
	 *            interfaces.
	 * @param DesktopComponents
	 *            The initial components an instance of a desktop has.
	 */
	protected Clients(String Name, String Desc, String[] SupConInt,
			Object[] DesktopComponents)
	{
		super(Name, Desc, SupConInt, DesktopComponents);
	}





	/**
	 * Get the number of nodes between the maskin and the internet.
	 */
	public int getNumberJumps()
	{
		return this.numberOfNodes;
	}



	/**
	 * Set method for number of jumps to the internet.
	 */
	public void setNumberOfJumps(int jumps)
	{
		this.numberOfNodes = jumps;
	}



	/**
	 * Gets the rating of an instance of this class.
	 * 
	 * @return the clientRate
	 */
	public int getClientRate()
	{
		return this.clientRate;
	}



	/**
	 * Sets the rating of an instance of this class.
	 * 
	 * @param clientRate
	 *            the clientRate to set
	 */
	public void setClientRate(int clientRate)
	{
		this.clientRate = clientRate;
	}



	/**
	 * Set the UUID associated with the Agent running on the actual client.
	 */
	public void setAssociatedAgentUUID(UUID agentUUID)
	{
		this.agentUUID = agentUUID;
	}



	/**
	 * Get the UUID associated with the Agent running on the actual client.
	 */
	public UUID getAssociatedAgentUUID()
	{
		return agentUUID;
	}
}
