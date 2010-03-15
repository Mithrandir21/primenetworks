/**
 * 
 */
package graphics.GUI.messageArea;


import javax.swing.table.AbstractTableModel;


/**
 * This class overrides some of the methodes of the {@link AbstractTableModel}
 * class. It also holds a 2-dimensional {@link Object} array, that holds the
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
		columnNames = colNames;
		data = tableData;
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
		return columnNames[col];
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
		return data[rowIndex][columnIndex];
	}

	/*
	 * (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
	 * int, int)
	 */
	@Override
	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		this.fireTableCellUpdated(row, col);
	}

}
