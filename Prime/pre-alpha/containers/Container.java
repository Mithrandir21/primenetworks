package containers;

import java.io.Serializable;
import java.util.ArrayList;

import objects.Object;
import exceptions.ObjectDoesNotExistInContainer;
import exceptions.ObjectExistInContainer;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Container implements Serializable 
{
	// The arraylist that will contain all the objects of a system
	ArrayList<Object> container = new ArrayList<Object>();
	
	
	/**
	 * Description
	 * 
	 */
	public ArrayList<Object> getContainer() 
	{
		return container;
	}



	/**
	 * Description NEEDED!
	 *
	 * @param container the container to set
	 */
	public void setContainer(ArrayList<Object> container) {
		this.container = container;
	}
	


	/**
	 * Description
	 * 
	 */
	public boolean addObject(Object obj) throws ObjectExistInContainer
	{
		// Check to see if the specific object already exist in the container
		if(container.contains(obj))
		{
			throw new ObjectExistInContainer("This object," 
					+ obj.getName() + ", already exist in this container.",obj);
		}

		// Returns true if added and false if not.
		return container.add(obj);
	}
	
	
	
	/**
	 * Description
	 * 
	 */
	public boolean removeObject(Object obj) throws ObjectDoesNotExistInContainer
	{
		// Checks to see if object really exist in the container
		if(!container.contains(obj))
		{
			throw new ObjectDoesNotExistInContainer("This object,"
					+ obj.getName() + ", does not exist in this container.",obj);
		}
		
		
		return container.remove(obj);
	}
	
	
	
	/**
	 * Description
	 * 
	 */
	public boolean containsObject(Object obj)
	{
		
		return container.contains(obj); 
	}
}
