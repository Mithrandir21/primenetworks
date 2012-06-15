/**
 * 
 */
package logistical;


import graphical.PrimeNPRtempMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import objects.Object;
import objects.softwareObjects.OperatingSystem;
import simpleHolders.GenericHolder;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class LogisticalFunctions
{
	/**
	 * TODO - Description
	 */
	public static Connection getConnection()
	{
		try
		{
			String url = "jdbc:jtds:sqlserver://NPR1-SERVER:1433/PLUS;instance=UPC";
			String user = "Testing";
			String pass = "Norman123!";

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("connected");

			return conn;
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
			return null;
		}
		catch ( SQLException e )
		{
			Connection con = getCon2();
			if ( con == null )
			{
				determineWhatMessageToShow(e.getMessage());
				return null;
			}

			return con;
		}
	}


	/**
	 * TODO - MUST DELETE BEFORE RELEASE
	 */
	private static Connection getCon2()
	{
		try
		{
			String url = "jdbc:jtds:sqlserver://NRP-SERVER:1433/PLUS";
			String user = "Testing";
			String pass = "Norman123!";

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("connected");

			return conn;
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
			return null;
		}
		catch ( SQLException e )
		{
			determineWhatMessageToShow(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}





	/**
	 * This function returns an Integer that presents the version of the NPR
	 * server.
	 * 
	 * Returns -1 if con is NULL.
	 */
	public static int getServerVersion(Connection con)
	{
		int version = 0;

		if ( con != null )
		{
			try
			{
				Statement stmt = con.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String query = "SELECT InstallerConfiguration_Value "
						+ "FROM PLUS.dbo.InstallerConfiguration "
						+ "WHERE PLUS.dbo.InstallerConfiguration.InstallerConfiguration_Name='InstallerConfiguration_version'";

				ResultSet rs = stmt.executeQuery(query);

				// If there is only one entry in the result set.
				if ( onlyOneResultSetRow(rs) )
				{
					// Resets the index.
					rs.beforeFirst();

					while ( rs.next() )
					{
						version = determineServerVersion(rs.getString(1));
					}
				}

				return version;
			}
			catch ( SQLException e )
			{
				System.err.println(e.getMessage());
			}
		}

		return -1;
	}



	/**
	 * This function determines the version of the NPR server by the content of
	 * the given string.
	 * 
	 * (This function should only by used by getServerVersion.)
	 */
	private static int determineServerVersion(String version)
	{
		if ( version != null && version != "" )
		{
			if ( version.startsWith("6.4") )
			{
				return 64;
			}
			else if ( version.startsWith("7.0") )
			{
				return 70;
			}
			else if ( version.startsWith("7.1") )
			{
				return 71;
			}
		}

		return -1;
	}


	private static void determineWhatMessageToShow(String text)
	{
		if ( text.contains("Server") && text.contains("has no instance named") )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Error: "
									+ text
									+ "\n"
									+ "Reason: SQL server instance has a different name or TCP/IP protocol is not enabled.");
		}
		else if ( text.contains("Unable to get information from SQL Server") )
		{
			JOptionPane
					.showMessageDialog(
							null,
							"Error: "
									+ text
									+ "\n"
									+ "Reason: Either SQL Server or SQL Server Browser is not running.");
		}
	}



	public static ArrayList<Object> getDatabaseObjects(Connection con)
	{
		if ( con != null )
		{
			// The list that will hold all the objects
			ArrayList<Object> objects = new ArrayList<Object>();

			/**
			 * 0. Determine what version NPR is running.
			 */

			/**
			 * ------Up Agents------
			 * 1. Get all lines in the UP_Agents table (expect
			 * "IsInitialized=0").
			 * 
			 * ------Operating System------
			 * 2. Go through each agent found and get InventoryType_ID=1 (or 5)
			 * (Operating System) for the AgentID_INT (ID for individual Agent)
			 * in InventoryItems.
			 * (Create a OperatingSystem object.)
			 * 
			 * ------Hardware------
			 * 3. Go through each agent found and get all InventoryType_ID=7
			 * (Hardware) for the AgentID_INT (ID for individual Agent) in
			 * InventoryItems.
			 * (Creates well-known Hardware objects.)
			 * 
			 * ------Software------
			 * 4. Go through each agent found and get all InventoryType_ID=6
			 * (Software) for the AgentID_INT (ID for individual Agent) in
			 * InventoryItems.
			 * (Creates well-known Software objects.)
			 * 
			 * ------Network------
			 * 5. Go through each agent found and get connection information,
			 * IP, MAC, NIC (InventoryClass_ID=25) and Subnet Mask
			 * (InventoryClass_ID=39).
			 * (Creates WidgetNetworkInfo and adds info to NICs.)
			 */

			// Step 1.
			ArrayList<GenericHolder> agents = getUpAgents(con);


			// Step 2
			getAgentOS(con, agents);


			// Step 3




			return objects;
		}


		return null;
	}



	/**
	 * This function returns and arraylist of string arraylists that represent
	 * the information gotten about the agents from the database.
	 */
	private static ArrayList<GenericHolder> getUpAgents(Connection con)
	{
		if ( con != null )
		{
			// The ArrayList that will contain info about all the agents.
			ArrayList<GenericHolder> agentInfos = new ArrayList<GenericHolder>();

			try
			{
				Statement stmt = con.createStatement();

				String query = "SELECT * " + "FROM [PLUS].[dbo].[UP_Agents]";

				if ( PrimeNPRtempMain.NPRversion > 64 )
				{
					query += " WHERE IsInitialized=1";
				}

				ResultSet rs = stmt.executeQuery(query);
				while ( rs.next() )
				{
					ArrayList<String> agentInfo = new ArrayList<String>();

					// Gets the number of columns
					ResultSetMetaData rsmd = rs.getMetaData();
					int NumOfCol = rsmd.getColumnCount();

					for ( int i = 1; i < NumOfCol + 1; i++ )
					{
						agentInfo.add(rs.getString(i));
					}

					// If the agentID is not blank ("")
					if ( agentInfo.get(0) != "" )
					{
						agentInfos.add(new GenericHolder(agentInfo));
					}
				}

				return agentInfos;
			}
			catch ( SQLException e )
			{
				System.err.println(e.getMessage());
			}
		}

		return null;
	}


	/**
	 * Returned NULL means that the AgentID was either <0, con was NULL or that
	 * the AgentID was not found.
	 */
	private static void getAgentOS(Connection con,
			ArrayList<GenericHolder> agents)
	{
		if ( con != null && !agents.isEmpty() )
		{
			// The InventoryType_ID matching the NPR server type for OS
			int InventoryType_ID = 0;

			if ( PrimeNPRtempMain.NPRversion == 64 )
			{
				InventoryType_ID = 1;
			}
			else if ( PrimeNPRtempMain.NPRversion > 64 )
			{
				InventoryType_ID = 5;
			}


			Iterator<GenericHolder> itr = agents.iterator();

			while ( itr.hasNext() )
			{
				GenericHolder agent = itr.next();

				// The arraylist that will hold all the OS objects for the agent
				ArrayList<OperatingSystem> agentOSs = new ArrayList<OperatingSystem>();

				if ( agent != null )
				{
					// Gets the ID of the agent
					int agentID = agent.getAgentID();

					if ( agentID > 0 )
					{
						try
						{
							Statement stmt = con.createStatement();

							String query = "SELECT InventoryItem_Name "
									+ "FROM [PLUS].[dbo].[InventoryItems] "
									+ "INNER JOIN [PLUS].[dbo].[Agent_To_InventoryItemBase] "
									+ "ON [PLUS].[dbo].[InventoryItems].InventoryItem_ID="
									+ "[PLUS].[dbo].[Agent_To_InventoryItemBase].InventoryItem_ID "
									+ "WHERE [PLUS].[dbo].[Agent_To_InventoryItemBase].AgentID_INT="
									+ agentID
									+ " AND [PLUS].[dbo].[InventoryItems].InventoryType_ID="
									+ InventoryType_ID;


							ResultSet rs = stmt.executeQuery(query);
							while ( rs.next() )
							{
								// Gets text string representing.
								String text = rs.getString(1);

								if ( text != null && text != "" )
								{
									OperatingSystem os = InfoProcessing
											.determineOS(text);

									System.out.println(os.getObjectName()
											+ " - " + os.getVersion() + " - "
											+ os.getBase());

									agentOSs.add(os);
								}
							}
						}
						catch ( SQLException e )
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					// Adds the found OSs to agent holder
					agent.setAgentOSs(agentOSs);
				}
			}
		}
	}



	/**
	 * This function attempts to run the given string as a statement on the
	 * given database. If either the the given connection is null or the string
	 * is "" false will be returned. It also returns false if there is a
	 * {@link SQLException}.
	 */
	public static boolean databaseStatementExecution(Connection con,
			String statementString)
	{
		if ( con != null && statementString != "" )
		{
			try
			{
				Statement statement = con.createStatement();
				statement.setQueryTimeout(30); // set timeout to 30 sec.

				statement.executeUpdate("BEGIN;");
				statement.executeUpdate(statementString);
				statement.executeUpdate("COMMIT;");

				return true;
			}
			catch ( SQLException e )
			{
				System.err.println(e.getMessage());
				return false;
			}
		}

		return false;
	}


	/**
	 * This function determines if there is more then one entry in the given
	 * {@link ResultSet}.
	 * 
	 * @throws SQLException
	 */
	public static boolean onlyOneResultSetRow(ResultSet rs) throws SQLException
	{
		if ( rs != null )
		{
			boolean foundOne = false;
			while ( rs.next() )
			{
				if ( foundOne )
				{
					return false;
				}

				foundOne = true;
			}

			return true;
		}

		return false;
	}

}
