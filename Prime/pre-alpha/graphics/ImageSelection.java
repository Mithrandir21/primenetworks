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
 * @version
 */
public class ImageSelection extends TransferHandler implements Transferable 
{
	private static final DataFlavor flavors[] = { DataFlavor.imageFlavor };

		  private Image image;

		  
		  public int getSourceActions(JComponent c) 
		  {
		    return TransferHandler.COPY;
		  }

		  /*
		  public boolean canImport(TransferSupport support) 
		  {
		    if (!isDataFlavorSupported(DataFlavor.imageFlavor)) 
		    {
		      return false;
		    }
		    
		    // Fetch the drop location
		    //DropLocation loc = support.getDropLocation();

		    // Return whether we accept the location
		    //return shouldAcceptDropLocation(loc);
		    
		    return true;
		  }
		  */

		  
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

		  /*
		  public boolean importData(TransferSupport support) 
		  {
		    if (!canImport(support)) 
		    {
		    	return false;
		    }
		    

		    JLabel label = (JLabel) support.getTransferable();
		    
		    
		    return false;
		  }
		  */

		  // Transferable
		  
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
