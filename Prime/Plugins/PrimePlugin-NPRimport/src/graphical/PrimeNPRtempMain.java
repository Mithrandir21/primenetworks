package graphical;


import java.sql.Connection;

import logistical.LogisticalFunctions;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeNPRtempMain
{
	public static int NPRversion;


	/**
	 * TODO - Description
	 * 
	 */
	public static void main(String[] args)
	{
		Connection con = LogisticalFunctions.getConnection();

		NPRversion = LogisticalFunctions.getServerVersion(con);

		if ( NPRversion != 64 && NPRversion != 70 )
		{
			System.out.println("Not supported NPR version - " + NPRversion);
			System.exit(1);
		}

		LogisticalFunctions.getDatabaseObjects(con);

		System.exit(0);
	}
}
