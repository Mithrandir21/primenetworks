/**
 * 
 */
package graphics.GUI.selectArea;


import graphics.PrimeMain1;
import graphics.GUI.CreateObjects;

import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import objects.Object;
import widgets.WidgetIcon;
import widgets.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ImageSelection extends TransferHandler
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.TransferHandler#getSourceActions(javax.swing.JComponent)
	 */
	@Override
	public int getSourceActions(JComponent c)
	{
		return TransferHandler.COPY;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.TransferHandler#createTransferable(javax.swing.JComponent)
	 */
	@Override
	public Transferable createTransferable(JComponent comp)
	{
		if ( comp instanceof WidgetIcon )
		{
			WidgetIcon icon = (WidgetIcon) comp;

			if ( PrimeMain1.currentCanvas != null )
			{

				Object newObject = new CreateObjects().CreateObject(icon, PrimeMain1.currentCanvas
						.getNumberOfWidgetsOnTheScene());

				return new WidgetObject(PrimeMain1.currentCanvas.getScene(), newObject, icon.getIconImage());
			}

			return null;
		}
		return null;
	}

}