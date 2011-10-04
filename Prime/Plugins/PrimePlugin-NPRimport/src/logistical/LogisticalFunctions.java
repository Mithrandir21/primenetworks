/**
 * 
 */
package logistical;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


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
			determineWhatMessageToShow(e.getMessage());
			e.printStackTrace();
			return null;
		}
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
}
