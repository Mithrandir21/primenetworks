/**
 * 
 */
package graphics.GUI.workareaCanvas;


import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;

import widgetManipulation.WidgetObject;


/**
 * @author Bam
 */
public class WidgetTransferHandler extends TransferHandler
{
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

	@Override
	public boolean importData(TransferSupport support)
	{
		return true;
	}



}
