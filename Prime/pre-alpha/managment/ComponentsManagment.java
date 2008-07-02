package managment;
import logistical.cleanup;
import objects.Object;
import exceptions.*;

/**
 * Class that contains different function that add, remove and replace components from a 
 * given array. It is used in the different parts of the program, specially 
 * {@link  clients.Desktop  Desktops}, {@link  clients.Laptop  Laptops}, 
 * {@link  objects.Servers  Servers} and {@link  infrastructure.Rack  Racks}.
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ComponentsManagment
{

	/**
	 * Function to add component(s) to the the components list.
	 * 
	 * @param NewComponents An array of new components.
	 * @param componentCounter The counter that tells how many components are in the 
	 * current components array.
	 * @param components The current components list.
	 * @throws Exception Throws an exception with a message which says that the component is 
	 * already present.
	 */
	public static Object[] addComponents(Object[] NewComponents, Object[] components
			, int componentCounter)
	{	
		
		// The number of new components to be added to the components array
		int numberOfNewComponents = 0;
		
		// Checks to see whether any of the indexes in the new components array is null.
		for(int i = 0;i<NewComponents.length;i++)
		{
			if(NewComponents[i] != null)
			{
				numberOfNewComponents++;
			}
		}
		
		// Makes a new components array with the added number of indexs
		Object[] tempComponents = new Object[componentCounter + numberOfNewComponents];
		
		// The boolean array that tells whether or not any of the objects already are in the array
		boolean[] areFound = arrayContains(components,NewComponents);
		
		// Checks to see if any of the 
		for(int i = 0;i<areFound.length;i++)
		{
			if(areFound[i] == true)
			{
				// A try/catch incase the object is null.
				try 
				{
					throw new Exception("The component " + NewComponents[i].getObjectName() 
							+ " is already present.");
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		// Addes the old components to the new array
		for(int i = 0;i<componentCounter;i++)
		{
			tempComponents[i] = components[i];
		}
		
		
		// Addes the new components to the end of the new array 
		for(int i = 0;i<numberOfNewComponents;i++)
		{
			tempComponents[componentCounter] = NewComponents[i];
			
			componentCounter++;
		}
		
		return tempComponents;
	}
	
	
	// TODO - add a addComponent function so that one single component can be
	//        added at a time.
	
	
	
	/**
	 * Function to remove a component from the array of components.
	 * 
	 * @param ToBeRemoved Component to be removed.
	 * @param componentCounter The counter that tells how many components are in the 
	 * current components array.
	 * @param components The current components list.
	 * @throws ObjectNotFoundInArrayException Throws an exception which states
	 * that one or more of the object that to be removed do not exist in the 
	 * components object they are trying to be removed from.
	 */
	public static Object[] removeComponents(Object[] ToBeRemoved, Object[] components,
			int componentCounter) throws ObjectNotFoundInArrayException
	{
		// booleans to check whether the objects to be removed are found or not
		boolean[] objectFound = new boolean[ToBeRemoved.length];
		
		// Number of components removed
		int componentsRemoved = 0;
		
		// Goes through all the components and removes the one(s) to be removed
		for(int i = 0;i<componentCounter;i++)
		{
			if(components[i] != null)
			{
				for(int j = 0;j<ToBeRemoved.length;j++)
				{
					if(ToBeRemoved[j] != null)
					{
						if(components[i].equals(ToBeRemoved[j]))
						{
							components[i] = null;
					
							objectFound[j] = true;  
					
							componentsRemoved++;
						}
					}
				}
			}
		}
		
		// Checks whether all the objects were found and removed
		for(int i = 0;i<objectFound.length;i++)
		{
			if(objectFound[i]==false)
			{
				ObjectNotFoundInArrayException exception = new 
				ObjectNotFoundInArrayException("Object was not found, hence cannot " +
						"be deleted. Contact systemadminstrator.", ToBeRemoved[i]);
				
				throw exception;
			}
		}
		
		// Cleans the array of any null pointers
		components = cleanup.cleanObjectArray(components);

		return components;
	}
	
	
	
	/**
	 * Function for replacing a spesific given component with a given new component.
	 * 
	 * @param NewComponent The component to replace the previous one.
	 * @param OldComponent The component to be replaced.
	 * @param componentCounter The counter that tells how many components are in the 
	 * current components array.
	 * @param components The current components list.
	 */
	public static Object[] changeComponent(Object NewComponent, Object OldComponent,
			Object[] components, int componentCounter)
	{
		// The boolean array that tells whether or not any of the object already are in the array
		boolean isFound = arrayContains(components,NewComponent);
		
		// Checks to see if any of the 
		if(isFound == true)
		{
			// A try/catch incase the object is null.
			try 
			{
				throw new Exception("The component " + NewComponent.getObjectName() 
						+ " is already present.");
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		// Goes through all the components and replaces the old component with the new one
		for(int i = 0;i<componentCounter;i++)
		{
			if(components[i].equals(OldComponent))
			{
				components[i] = NewComponent;
				
				// Sets the index to the lenght of the component to get out of the loop
				i = componentCounter;
			}
		}
		
		
		components = cleanup.cleanObjectArray(components);
		
		
		return components;
	}
	
	
	
	
	
	// SEARCH FUNCTIONS
	/**
	 * Get spesific components by searching for components with the give class type.
	 * 
	 * @return Returns an array of components that match with the given class.
	 * @param componentCounter The counter that tells how many components are in the 
	 * current components array.
	 * @param components The current components list.
	 * @throws ObjectNotFoundException Throws an exception which states that there were
	 * not objects found with the given class.
	 */
	@SuppressWarnings("unchecked")
	public static Object[] getSpesificComponents(Class ComponentClass,
			Object[] components, int componentCounter) throws ObjectNotFoundException
	{		
		// boolean to check whether the object is found or not
		boolean objectNotFound = true;
		
		// Counter for number of components found
		int tempCounter = 0;
		
		// Container that will hold all the found components
		Object[] componentsFound = new Object[componentCounter];
		
		
		// Searches for components of the given class
		for(int i = 0;i<componentCounter;i++)
		{
			/* 
			 * If the given components class matches the present components class, 
			 * it will be added to the container
			*/
			if(components[i].getClass().equals(ComponentClass))
			{
				componentsFound[tempCounter] = components[i];
				
				objectNotFound = false;
			}
		}
		
		
		// Checks whether all the objects were found and removed
		if(objectNotFound==true)
		{
			ObjectNotFoundException exception = new ObjectNotFoundException("Object(s) with the given component, "+ ComponentClass.getCanonicalName() + " were not found.", ComponentClass);
			
			throw exception;
		}
		
		
		
		// Cleans the array of any null pointers at the end
		componentsFound = cleanup.cleanObjectArray(componentsFound);
		
		return componentsFound;
	}
	
	
	
	
	// CHECK FUNCTIONS
	/**
	 * Check function to determine whether or not the the given array contains the given object.
	 * 
	 * @param array The array that is to be checked.
	 * @param searchObject The object that is to be searched for.
	 */
	public static boolean arrayContains(Object[] array, Object searchObject)
	{
		// Boolean to tell whether or not the given object is found within the given array.
		boolean foundObject = false;
		
		
		for(int i = 0;i<array.length;i++)
		{
			if(array[i].equals(searchObject))
			{
				foundObject = true;
				
				// Sets i to array length to get out of the loop.
				i = array.length;
			}
		}		
		
		return foundObject;
	}
	
	
	
	/**
	 * Check function to determine whether or not the the given array contains any of the 
	 * given objects in the searchObjects array.
	 * 	 
	 * @param array The array that is to be checked.
	 * @param searchObjects The objects that is to be searched for.
	 * @return The array of booleans that tells, by way of the index, which object are found.
	 */
	public static boolean[] arrayContains(Object[] array, Object[] searchObjects)
	{
		// Boolean to tell whether or not the given object is found within the given array.
		boolean[] foundObject = new boolean[searchObjects.length];
		
		
		for(int i = 0;i<array.length;i++)
		{
			// See if the array index is not null
			if(array[i]!=null)
			{
				for(int j = 0;j<searchObjects.length;j++)
				{
					// See if the array index is not null
					if(searchObjects[j]!=null)
					{
						if(array[i].equals(searchObjects[j]))
						{
							foundObject[j] = true;
						}
					}
				}
			}
		}		
		
		return foundObject;
	}
}
