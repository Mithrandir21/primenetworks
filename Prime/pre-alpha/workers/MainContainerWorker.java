
package workers;

import containers.*;
import objects.Object;
import objects.*;
import connections.*;
import exceptions.*;

/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class MainContainerWorker
{
	
	// The different container that will hold the different objects of the system.
	HardwareContainer hwContainer = new HardwareContainer();
	
	SoftwareContainer swContainer = new SoftwareContainer();
	
	PeripheralContainer perContainer = new PeripheralContainer();
	
	ConnectionContainer conContainer = new ConnectionContainer();
	
	ClientsContainer clientContainer = new ClientsContainer();
	
	ServersContainer serverContainer = new ServersContainer();
	
	
	
	
	
	/**
	 * Description NEEDED!
	 * 
	 */
	public MainContainerWorker()
	{
		
	}



	// GET METHODES

	/**
	 * Description NEEDED!
	 *
	 * @return the conContainer
	 */
	public ConnectionContainer getConContainer()
	{
		return conContainer;
	}





	/**
	 * Description NEEDED!
	 *
	 * @return the hwContainer
	 */
	public HardwareContainer getHwContainer()
	{
		return hwContainer;
	}





	/**
	 * Description NEEDED!
	 *
	 * @return the perContainer
	 */
	public PeripheralContainer getPerContainer()
	{
		return perContainer;
	}





	/**
	 * Description NEEDED!
	 *
	 * @return the swContainer
	 */
	public SoftwareContainer getSwContainer()
	{
		return swContainer;
	}

	
	
	
	/**
	 * Description NEEDED!
	 *
	 * @return the clientContainer
	 */
	public ClientsContainer getClientContainer()
	{
		return clientContainer;
	}



	/**
	 * Description NEEDED!
	 *
	 * @return the serverContainer
	 */
	public ServersContainer getServerContainer()
	{
		return serverContainer;
	}
	
	
	
	
	// CLASS METHODES



	/**
	 * 
	 * Description
	 * @throws ObjectExistInContainer 
	 * 
	 */
	public boolean addObject(Object object) throws ObjectExistInContainer
	{
		
		if(object instanceof Hardware)
		{
			return hwContainer.addObject(object);
		}
		else if(object instanceof Software)
		{
			return swContainer.addObject(object);
		}
		else if(object instanceof Connection)
		{
			return conContainer.addObject(object);
		}
		else if(object instanceof Clients)
		{
			return clientContainer.addObject(object);
		}
		else if(object instanceof Servers)
		{
			return serverContainer.addObject(object);
		}

		
		return false;
	}
	
	
	
	/**
	 *  
	 * Description
	 * @throws ObjectDoesNotExistInContainer 
	 * 
	 */
	public boolean removeObject(Object object) throws ObjectDoesNotExistInContainer
	{
		if(object instanceof Hardware)
		{
			return hwContainer.removeObject(object);
		}
		else if(object instanceof Software)
		{
			return swContainer.removeObject(object);
		}
		else if(object instanceof Connection)
		{
			return conContainer.removeObject(object);
		}
		else if(object instanceof Clients)
		{
			return clientContainer.removeObject(object);
		}
		else if(object instanceof Servers)
		{
			return serverContainer.removeObject(object);
		}

		
		return false;
	}
	

	
	
	/**
	 * Description
	 * 
	 */
	public boolean containsObject(Object object, Class objectClass)
	{
		if(objectClass.equals(Object.class))
		{
			if(object instanceof Hardware)
			{
				return hwContainer.containsObject(object);
			}
			else if(object instanceof Software)
			{
				return swContainer.containsObject(object);
			}
			else if(object instanceof Connection)
			{
				return conContainer.containsObject(object);
			}
			else if(object instanceof Clients)
			{
				return clientContainer.containsObject(object);
			}
			else if(object instanceof Servers)
			{
				return serverContainer.containsObject(object);
			}
		}
		else if(objectClass.equals(Hardware.class))
		{
			return hwContainer.containsObject(object);
		}
		else if(objectClass.equals(Software.class))
		{
			return swContainer.containsObject(object);
		}
		else if(objectClass.equals(Connection.class))
		{
			return conContainer.containsObject(object);
		}
		else if(objectClass.equals(Clients.class))
		{
			return clientContainer.containsObject(object);
		}
		else if(objectClass.equals(Servers.class))
		{
			return serverContainer.containsObject(object);
		}
		
		
		
		return false;
	}
	
}
