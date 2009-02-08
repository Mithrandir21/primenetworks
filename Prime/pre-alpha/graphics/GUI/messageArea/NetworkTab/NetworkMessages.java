/**
 * 
 */
package graphics.GUI.messageArea.NetworkTab;


import graphics.GUI.messageArea.MessageJTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import managment.ArrayManagment;
import objects.Object;


/**
 * @author Bahram Malaekeh
 */
public class NetworkMessages extends JScrollPane
{
	private MessageJTable table = null;


	/**
	 * TODO - Description NEEDED!
	 */
	public NetworkMessages(Object[] objects)
	{
		String[] columnNames = { "Object Name", "Network(?) Type",
				"Description", "Message Type" };

		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = NetworkProcessing.processNetwork(data, objects[i], true,
					true, true);
		}
		
		data = ArrayManagment.removeEmptyIndexes(data);

		table = new MessageJTable(data, columnNames);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		setColumnWidths();

		// table.setPreferredScrollableViewportSize(new Dimension(700,300));
		table.setFillsViewportHeight(false);

		setViewportView(table);
	}



	/**
	 * TODO - Description
	 */
	private void setColumnWidths()
	{
		TableColumn column = null;
		for ( int i = 0; i < 4; i++ )
		{
			column = table.getColumnModel().getColumn(i);
			if ( i == 2 )
			{
				column.setPreferredWidth(1000); // third column is bigger
			}
			else
			{
				column.setPreferredWidth(150);
			}
		}
	}


	/**
	 * TODO - Description
	 */
	public void setData()
	{
	}
}
