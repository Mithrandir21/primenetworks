/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.messageArea.NetworkTab;


import graphics.PrimeMain;
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
	 * A constructor for the class that creates a JScrollPane that will contains
	 * a JTable with possible messages for the user about the given network.
	 * 
	 * @param objects
	 *            The objects that are to be examined.
	 * @param data
	 *            The multidimentional String array with possible previous
	 *            messages for the user.
	 */
	public NetworkMessages(Object[] objects, String[][] data)
	{
		String[] columnNames = { PrimeMain.texts.getString("tableNETName"),
				PrimeMain.texts.getString("tableNETType"),
				PrimeMain.texts.getString("tableNETdescription"),
				PrimeMain.texts.getString("tableNETmsgType") };

		this.table = new MessageJTable(data, columnNames);

		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

		setColumnWidths();

		// table.setPreferredScrollableViewportSize(new Dimension(700,300));
		this.table.setFillsViewportHeight(false);

		setViewportView(this.table);
	}



	/**
	 * Sets the width of the column that contains the actual message
	 * information.
	 */
	private void setColumnWidths()
	{
		TableColumn column = null;
		for ( int i = 0; i < 4; i++ )
		{
			column = this.table.getColumnModel().getColumn(i);
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
