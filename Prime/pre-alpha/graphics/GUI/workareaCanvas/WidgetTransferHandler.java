/**
 * 
 */
package graphics.GUI.workareaCanvas;

import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;

import widgetManipulation.WidgetObject;

/**
 * @author Bam
 *
 */
public class WidgetTransferHandler extends TransferHandler
{
	public boolean canImport(TransferSupport support) 
	{
		System.out.println("WidgetTransferHandler - Kommer hit 1.");
		DataFlavor flavors[] = support.getDataFlavors();
		if(flavors[0].equals(new DataFlavor(WidgetObject.class,"Widget Object")))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean importData(TransferSupport support) 
	{
	    return true;
	}
	
	
	
}
