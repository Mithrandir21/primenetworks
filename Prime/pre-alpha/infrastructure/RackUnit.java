package infrastructure;
import java.io.Serializable;
import objects.Object;


public class RackUnit extends Object implements Serializable 
{
	

	
	
	
	/**
	 * Constructor for a rack unit class.
	 *
	 * @param Name The name of the rackunit.
	 * @param Desc The description of the rackunit.
	 */
	public RackUnit(String Name, String Desc, String[] SupConInt)
	{
		super(Name,Desc,SupConInt);
	}
	
	
	
}
