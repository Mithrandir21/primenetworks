/**
 * 
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.sqlite.SQLite;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class SqlLiteFunctions
{
	private static String agentInfoTableName = "AgentInfo";

	private static String agentIDkeyName = "AgentUID";


	/**
	 * This function will create the {@link Connection} to the local
	 * {@link SQLite} in the "config" folder.
	 */
	public static Connection createConnection()
	{
		Connection con;

		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:config/AgentInfo.db");

			// Checks
			if ( con != null && !con.isClosed() && !con.isReadOnly() )
			{
				return con;
			}
		}
		catch ( ClassNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( SQLException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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

				System.out.println(statementString);

				statement.executeUpdate("BEGIN;");
				statement.executeUpdate(statementString);
				statement.executeUpdate("COMMIT;");

				return true;
			}
			catch ( SQLException e )
			{
				// if the error message is "out of memory",
				// it probably means no database file is found
				System.err.println(e.getMessage());
				return false;
			}
		}

		return false;
	}



	/**
	 * Attempts to create the general info table.
	 */
	public static boolean createTable(Connection con, boolean dropIfExist)
	{
		if ( con != null )
		{
			try
			{
				Statement stat = con.createStatement();
				stat.setQueryTimeout(10); // set timeout to 10 sec.

				// Starts the transaction
				stat.executeUpdate("BEGIN;");

				if ( dropIfExist )
				{
					// Drops the table if it exists
					stat.executeUpdate("drop table if exists "
							+ agentInfoTableName + ";");
				}

				// Attempts to create the AgentInfo table
				stat.executeUpdate("CREATE TABLE IF NOT EXISTS "
						+ agentInfoTableName + " (PropName, value);");

				// Will execute the sql statements above
				stat.executeUpdate("COMMIT;");


				return true;
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return false;
	}



	/**
	 * This function will determine if there exists a table with the agent info
	 * table name.
	 */
	public static boolean generalInfoTableExists(Connection con)
	{
		if ( con != null )
		{
			try
			{
				Statement stat = con.createStatement();
				stat.setQueryTimeout(10); // set timeout to 10 sec.

				ResultSet rs = stat
						.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='"
								+ agentInfoTableName + "';");

				// If there was any table found, the table exists.
				if ( rs.next() )
				{
					return true;
				}
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}



	/**
	 * TODO - Description
	 */
	public static boolean createAndStoreAgentUID(Connection con,
			boolean deleteExistingUID)
	{
		if ( con != null )
		{
			// Must create new UUID.
			UUID newID = UUID.randomUUID();
			String statementString = "";

			boolean keyExists = false;
			boolean valueExists = false;

			/**
			 * 1. Check if UID exists. 1.1 If it does, set that as agent ID.
			 */
			if ( (keyExists = UIDexistsInTable(con)) == true )
			{
				// Gets UUID from SQL table
				UUID id = getUIDInTable(con);

				// If the UUID value exists.
				valueExists = (id != null);

				// If value exists and it is not to be replaced
				if ( valueExists && !deleteExistingUID )
				{
					// Sets the Agent ID
					AgentMain.agentID = id;

					return true;
				}
				else if ( valueExists && deleteExistingUID )
				{
					statementString = "UPDATE " + agentInfoTableName
							+ " SET value='" + newID.toString()
							+ "' WHERE PropName='" + agentIDkeyName + "';";

					// If the placement of the UUID is successful
					if ( databaseStatementExecution(con, statementString) )
					{
						// Sets the Agent ID
						AgentMain.agentID = newID;

						return true;
					}
					else
					{
						return false;
					}
				}
			}


			/**
			 * 2. If the key exists, then the value does not exist.
			 */
			if ( keyExists )
			{
				// 2.2 Key exists, but value does not.
				if ( !valueExists )
				{
					statementString = "UPDATE " + agentInfoTableName
							+ " SET value='" + newID.toString()
							+ "' WHERE PropName='" + agentIDkeyName + "');";

					// If the placement of the UUID is successful
					if ( databaseStatementExecution(con, statementString) )
					{
						// Sets the Agent ID
						AgentMain.agentID = newID;

						return true;
					}
					else
					{
						return false;
					}
				}
			}
			/**
			 * 3. The key does not exist and must be created
			 */
			else
			{
				statementString = "INSERT INTO " + agentInfoTableName
						+ " VALUES ('" + agentIDkeyName + "', '"
						+ newID.toString() + "');";

				// If the placement of the UUID is successful
				if ( databaseStatementExecution(con, statementString) )
				{
					// Sets the Agent ID
					AgentMain.agentID = newID;

					return true;
				}
				else
				{
					return false;
				}
			}
		}


		return false;
	}


	/**
	 * This function looks for the property key agentIDkeyName to see if there
	 * exists a key/value in the agent table. (NOTE: Does not check the actual
	 * key, just that exists.)
	 */
	public static boolean UIDexistsInTable(Connection con)
	{
		if ( con != null )
		{
			try
			{
				Statement stat = con.createStatement();
				stat.setQueryTimeout(10); // set timeout to 10 sec.

				ResultSet rs = stat.executeQuery("SELECT * FROM "
						+ agentInfoTableName + " WHERE PropName='"
						+ agentIDkeyName + "';");

				// If there was anything found, the table contains a uid
				// key/value.
				if ( rs.next() )
				{
					return true;
				}
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}



	/**
	 * This function looks for the property key agentIDkeyName to see if there
	 * exists a key/value in the agent table.
	 */
	public static UUID getUIDInTable(Connection con)
	{
		if ( con != null )
		{
			try
			{
				Statement stat = con.createStatement();
				stat.setQueryTimeout(10); // set timeout to 10 sec.

				ResultSet rs = stat.executeQuery("SELECT * FROM "
						+ agentInfoTableName + " WHERE PropName='"
						+ agentIDkeyName + "';");

				// If there was anything found, the table contains a uid
				// key/value.
				if ( rs.next() )
				{
					return UUID.fromString(rs.getString("value"));
				}
			}
			catch ( IllegalArgumentException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}



	/**
	 * This function attempts to find and match the {@link UUID} given with an
	 * UUID found in the agent info table.
	 */
	public static boolean UIDmatchesAgentUIDinTable(Connection con, UUID id)
	{
		if ( con != null )
		{
			try
			{
				Statement stat = con.createStatement();
				stat.setQueryTimeout(10); // set timeout to 10 sec.

				ResultSet rs = stat.executeQuery("SELECT * FROM "
						+ agentInfoTableName + " WHERE PropName='"
						+ agentIDkeyName + "' AND value='" + id.toString()
						+ "';");

				// If there was anything found, the table contains a uid
				// key/value that matches the id given.
				if ( rs.next() )
				{
					return true;
				}
			}
			catch ( SQLException e )
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}
}
