/**
 * 
 */
package graphics.GUI.messageArea;



import javax.swing.JTable;


/**
 * This class simply overrides some of the standard methods in the
 * {@link JTable} class, like getScrollableTracksViewportHeight(which is set to
 * return false).
 * 
 * @author Bahram Malaekeh
 */
public class MessageJTable extends JTable
{
	/**
	 * This constructor takes the given data and passes to the super class,
	 * JTable, a new {@link PrimeJTableModel} created with the given parameters.
	 * 
	 * @param rowData
	 * @param columnNames
	 */
	public MessageJTable(final Object[][] rowData, final String[] columnNames)
	{
		super(new PrimeJTableModel(columnNames, rowData));
	}


	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}
}
