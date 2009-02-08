/**
 * 
 */
package graphics.GUI.messageArea;

import javax.swing.table.AbstractTableModel;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class PrimeJTableModel extends AbstractTableModel
{
	private String[] columnNames;
	
	private Object[][] data;
	
	
	
	/**
	 * TODO - Description NEEDED!
	 *
	 * @param colNames
	 * @param tableData
	 */
	public PrimeJTableModel(String[] colNames, Object[][] tableData)
	{
		columnNames = colNames;
		data = tableData;
	}
	
	

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) 
	{
        return columnNames[col];
    }

	@Override
	public int getRowCount()
	{
		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return data[rowIndex][columnIndex];
	}
	
	@Override
    public void setValueAt(Object value, int row, int col) 
    {
        data[row][col] = value;
        this.fireTableCellUpdated(row, col);
    }

}
