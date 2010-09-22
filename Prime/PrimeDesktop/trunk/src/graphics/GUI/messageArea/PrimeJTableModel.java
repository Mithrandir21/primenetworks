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
package graphics.GUI.messageArea;


import javax.swing.table.AbstractTableModel;


/**
 * This class overrides some of the methodes of the {@link AbstractTableModel} class. It also holds a 2-dimensional {@link Object}
 * array, that holds the
 * data of the table.
 * 
 * @author Bahram Malaekeh
 */
public class PrimeJTableModel extends AbstractTableModel
{
	private String[] columnNames;

	private Object[][] data;



	/**
	 * A constructor that takes the column names and the data that is to be
	 * displayed.
	 * 
	 * @param colNames
	 *            The names of the tables columns.
	 * @param tableData
	 *            The data that will be displayed.
	 */
	public PrimeJTableModel(String[] colNames, Object[][] tableData)
	{
		this.columnNames = colNames;
		this.data = tableData;
	}



	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int col)
	{
		return this.columnNames[col];
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount()
	{
		return data.length;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return this.data[rowIndex][columnIndex];
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		this.data[row][col] = value;
		this.fireTableCellUpdated(row, col);
	}

}
