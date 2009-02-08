/**
 * 
 */
package graphics.GUI.messageArea;



import javax.swing.JTable;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class MessageJTable extends JTable
{
	/**
	 * TODO - Description NEEDED!
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
		// return getParent() instanceof JViewport
		// && getPreferredSize().height < getParent().getHeight();
	}
}
