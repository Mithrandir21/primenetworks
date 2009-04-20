/**
 * 
 */
package graphics.GUI.messageArea.NetworkTab;


import graphics.GUI.messageArea.MessageJTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import objects.Object;


/**
 * @author Bahram Malaekeh
 */
public class NetworkMessages extends JScrollPane
{
	private MessageJTable table = null;


	/**
	 * A constructor for the class that creates a JScrollPane that will contains a JTable with possible messages for the
	 * user about the given network.
	 * 
	 * @param objects
	 *            The objects that are to be examined.
	 * @param data
	 *            The multidimentional String array with possible previous messages for the user.
	 */
	public NetworkMessages(Object[] objects, String[][] data)
	{
		String[] columnNames = { "Name", "Type", "Description", "Message Type" };

		table = new MessageJTable(data, columnNames);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		setColumnWidths();

		// table.setPreferredScrollableViewportSize(new Dimension(700,300));
		table.setFillsViewportHeight(false);

		setViewportView(table);
	}



	/**
	 * Sets the width of the column that contains the actual message information.
	 */
	private void setColumnWidths()
	{
		TableColumn column = null;
		for ( int i = 0; i < 4; i++ )
		{
			column = table.getColumnModel().getColumn(i);
			if ( i == 2 )
			{
				column.setPreferredWidth(700); // third column is bigger
			}
			else
			{
				column.setPreferredWidth(150);
			}
		}
	}
}
