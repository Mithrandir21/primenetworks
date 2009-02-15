/**
 * 
 */
package graphics.GUI.workareaCanvas;


import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;

import widgetManipulation.WidgetObject;


/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class WidgetTransferHandler extends TransferHandler
{
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#canImport(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean canImport(TransferSupport support)
	{
		DataFlavor flavors[] = support.getDataFlavors();
		if ( flavors[0].equals(new DataFlavor(WidgetObject.class,
				"Widget Object")) )
		{
			return true;
		}

		return false;
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.TransferHandler#importData(javax.swing.TransferHandler.TransferSupport)
	 */
	@Override
	public boolean importData(TransferSupport support)
	{
		return true;
	}



}
