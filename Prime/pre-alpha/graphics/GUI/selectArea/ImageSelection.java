/**
 * 
 */
package graphics.GUI.selectArea;

import graphics.PrimeMain1;

import java.awt.*;
import java.awt.datatransfer.*;

import servers.*;
import widgetManipulation.WidgetObject;
import javax.swing.*;

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
		System.out.println("Sjekker tillatt handling.");
	    return TransferHandler.COPY;
	}

		  
		  
	public Transferable createTransferable(JComponent comp) 
	{
		    if (comp instanceof JLabel) 
		    {
		      JLabel label = (JLabel) comp;
		      Icon icon = label.getIcon();
		      if (icon instanceof ImageIcon) 
		      {
		    	  System.out.println("Lager object");
		    	  return new WidgetObject(PrimeMain1.scene,new HTTPServer("newComponent2","Desc","1","2","3"), createImage("images/objectImages/print-server.png"));
		      }
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
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
