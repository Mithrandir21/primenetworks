/**
 * 
 */
package graphics.GUI.selectArea;

import graphics.PrimeMain1;
import graphics.WidgetIcon;
import graphics.GUI.selectArea.CreateObjectDragged;

import java.awt.Image;
import java.awt.datatransfer.Transferable;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import widgetManipulation.WidgetObject;
import objects.Object;
//import servers.HTTPServer;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ImageSelection extends TransferHandler 
{
	
	public int getSourceActions(JComponent c) 
	{
//		System.out.println("ImageSelection - Sjekker tillatt handling.");
	    return TransferHandler.COPY;
	}

		  
		  
	public Transferable createTransferable(JComponent comp) 
	{
		if (comp instanceof WidgetIcon) 
		{
			WidgetIcon icon = (WidgetIcon) comp;
		    
			Object newObject = new CreateObjectDragged().CreateObject(icon);
			
		    return new WidgetObject(PrimeMain1.scene, newObject, icon.getIconImage());
		}
	return null;
	}

	
	/** Returns an Image, or null if the path was invalid. */
	protected Image createImage(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL).getImage();
	    } else {
	        System.err.println("ImageSelection - Couldn't find file: " + path);
	        return null;
	    }
	}
}
