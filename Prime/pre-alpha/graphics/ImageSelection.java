/**
 * 
 */
package graphics;

import java.awt.*;
import java.awt.datatransfer.*;

import javax.swing.*;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ImageSelection extends TransferHandler implements Transferable 
{
	private static final DataFlavor flavors[] = { DataFlavor.imageFlavor };

		  private Image image;

		  
		  
		  public int getSourceActions(JComponent c) 
		  {
		    return TransferHandler.COPY;
		  }

		  
		  
		  public Transferable createTransferable(JComponent comp) 
		  {
			  
		    // Clear
		    image = null;

		    if (comp instanceof JLabel) 
		    {
		      JLabel label = (JLabel) comp;
		      Icon icon = label.getIcon();
		      if (icon instanceof ImageIcon) 
		      {
		    	  return new StringSelection (label.getText());
		      }
		    }
		    return null;
		    
		  }

		  
		  
		  public Object getTransferData(DataFlavor flavor) 
		  {
		    if (isDataFlavorSupported(flavor)) 
		    {
		      return image;
		    }
		    return null;
		  }
		  
		  
		  
		  
		  public DataFlavor[] getTransferDataFlavors() 
		  {
		    return flavors;
		  }
		  
		  
		  
		  
		  public boolean isDataFlavorSupported(DataFlavor flavor) 
		  {
		    return flavor.equals(DataFlavor.imageFlavor);
		  }
		
		
	
	
}
