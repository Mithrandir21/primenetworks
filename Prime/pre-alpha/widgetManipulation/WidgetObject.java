/**
 * 
 */
package widgetManipulation;


import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;

import org.netbeans.api.visual.widget.*;

/**
 * @author bam
 *
 */
public class WidgetObject extends ImageWidget implements Transferable
{
	// The object that the widget represents. 
	private Object object = null;
	
	
	private static final DataFlavor flavors[] = new DataFlavor[1];
	
	
	/**
	 * @param canvas
	 * @param obj
	 * @param objImg
	 */
	public WidgetObject(Scene canvas, Object obj, Image objImg)
	{
		super(canvas,objImg);
		object = obj;

		setFlavor();
	}


	
	// GETTERS 
	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}


	// SETTERS
	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}



	
	
	// TRANSFERABLE IMPLEMENTATION
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException 
	{
		System.out.println("Kmr hit 1");
	    if (isDataFlavorSupported(flavor)) 
	    {
	    	return this;
	    }
	    return null;
	}



	public DataFlavor[] getTransferDataFlavors() 
	{
		System.out.println("Kmr hit 2");
		return flavors;
	}



	public boolean isDataFlavorSupported(DataFlavor flavor) 
	{
		System.out.println(flavors[0]);
		System.out.println(flavor);
		return flavors[0].equals(flavor);
	}
	
	
	
	
	private DataFlavor[] setFlavor()
	{
		System.out.println("Kmr hit 3");
		if(flavors[0] == null)
		{
			flavors[0] = new DataFlavor(WidgetObject.class,"Widget Object");
		}
		System.out.println(flavors[0]);
		return flavors;
	}

}
