package infrastructure;
import java.io.Serializable;

import objects.Infrastructure;


public class RackUnit extends Infrastructure implements Serializable 
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
