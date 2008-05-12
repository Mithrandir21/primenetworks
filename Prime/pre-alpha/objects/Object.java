package objects;


import java.io.Serializable;


/**
 * Object is a super class for all objects created within the system. Both
 * hardware and software. MUST ADD INFO!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public abstract class Object implements Serializable
{

	/**
	 * The name of an object
	 */
	private String name;


	/**
	 * The description of any object
	 */
	private String description;


	/**
	 * Connection interfaces supported by a device
	 */
	private String[] supportedConnectionInterfaces;





	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 */
	public Object(String Name, String Desc)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = new String[1];
		supportedConnectionInterfaces[0] = "RJ-45";
	}
	
	
	/**
	 * The constructor of the object superclass. All objects must have both a
	 * name and description.
	 * 
	 * @param Name
	 *            The name of an object
	 * @param Desc
	 *            The description of any object
	 */
	public Object(String Name, String Desc, String[] SupConInt)
	{
		name = Name;
		description = Desc;
		supportedConnectionInterfaces = SupConInt;
	}



	// Get and Set methodes for retrieving all datafields.

	// GET METHODES

	/**
	 * Returns the name of the object.
	 */
	public String getName()
	{

		return name;
	}

	/**
	 * Returns the description of the object.
	 */
	public String getDescription()
	{

		return description;
	}

	/**
	 * Description NEEDED!
	 * 
	 * @return the supportedConnectionInterfaces
	 */
	public String[] getSupportedConnectionInterfaces()
	{
		return supportedConnectionInterfaces;
	}



	// SET METHODES






	/**
	 * Sets the name of the object.
	 */
	public void setName(String ObjectName)
	{

		name = ObjectName;
	}


	/**
	 * Sets the description of the object.
	 */
	public void setDescription(String ObjectDescription)
	{

		description = ObjectDescription;
	}



	/**
	 * Description NEEDED!
	 * 
	 * @param supportedConnectionInterfaces
	 *            the supportedConnectionInterfaces to set
	 */
	public void setSupportedConnectionInterfaces(
			String[] supportedConnectionInterfaces)
	{
		this.supportedConnectionInterfaces = supportedConnectionInterfaces;
	}






}
