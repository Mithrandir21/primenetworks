/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.userGroups;


import javax.swing.table.AbstractTableModel;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PermissionsModel extends AbstractTableModel
{
	private String[] columnNames;

	private java.lang.Object[][] data;


	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public PermissionsModel(java.lang.Object[][] data, String[] columnNames)
	{
		this.columnNames = columnNames;
		this.data = data;
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public int getRowCount()
	{
		return data.length;
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public java.lang.Object getValueAt(int row, int col)
	{
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell. If we didn't implement this method,
	 * then the last column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}


	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col)
	{
		// Note that the data/cell address is constant,
		// no matter where the cell appears onscreen.
		if ( col < 1 )
		{
			return false;
		}

		return true;
	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(java.lang.Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableRowsUpdated(row, col);
	}


	/**
	 * This function is all called when the "All Rights" button is pressed.
	 * It sets the values for all the other access rights.
	 */
	public void selectAll(int row, boolean rights)
	{
		data[row][1] = rights;
		data[row][2] = rights;
		data[row][3] = rights;
		data[row][4] = rights;
	}


	public void selectRead(int row, boolean rights)
	{
		data[row][1] = rights;
	}


	public void selectWrite(int row, boolean rights)
	{
		data[row][2] = rights;
	}


	public void selectExecute(int row, boolean rights)
	{
		data[row][3] = rights;
	}
}
