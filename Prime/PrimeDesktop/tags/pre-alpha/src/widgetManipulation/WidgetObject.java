/**
 * 
 */
package widgetManipulation;



import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import objects.Object;

import org.netbeans.api.visual.widget.ImageWidget;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.modules.visual.util.GeomUtil;


/**
 * @author bam
 */
public class WidgetObject extends ImageWidget implements Transferable
{
	// The object that the widget represents.
	private Object object = null;

	// The information regarding the widgets in the network
	private WidgetNetworkInfo widNetInfo = new WidgetNetworkInfo();

	private static final DataFlavor flavors[] = new DataFlavor[1];


	private String label;


	/**
	 * @param scene
	 * @param obj
	 * @param objImg
	 */
	public WidgetObject(Scene scene, Object obj, Image objImg)
	{
		super(scene, objImg);
		object = obj;

		setFlavor();
	}



	// GETTERS
	/**
	 * @return the object
	 */
	public Object getObject()
	{
		return object;
	}



	/**
	 * @return The information regarding the widget in a network.
	 */
	public WidgetNetworkInfo getWidgetNetworkInfo()
	{
		return widNetInfo;
	}



	/**
	 * @return A new Dimension with the size of the image.
	 */
	public Dimension getImageDimension()
	{
		return new Dimension(getImage().getHeight(null), getImage().getWidth(null));
	}


	/**
	 * Returns a label.
	 * 
	 * @return the label
	 */
	public String getLabel()
	{
		return label;
	}



	// SETTERS
	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(Object object)
	{
		this.object = object;
	}


	/**
	 * Sets the {@link WidgetNetworkInfo} of this Object.
	 * 
	 * @param netInfo
	 */
	public void setWidgetNetworkInfo(WidgetNetworkInfo netInfo)
	{
		widNetInfo = netInfo;
	}


	/**
	 * Sets a label.
	 * 
	 * @param label
	 *            the label
	 */
	public void setLabel(String label)
	{
		if ( GeomUtil.equals(this.label, label) )
		{
			return;
		}

		this.label = label;
		revalidate();
	}



	// TRANSFERABLE IMPLEMENTATION
	public WidgetObject getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
	{
		// System.out.println("WidgetCanvas - getTransferData");
		if ( isDataFlavorSupported(flavor) )
		{
			return this;
		}
		return null;
	}



	public DataFlavor[] getTransferDataFlavors()
	{
		// System.out.println("WidgetCanvas - getTransferDataFlavors");
		return flavors;
	}



	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return flavors[0].equals(flavor);
	}




	private DataFlavor[] setFlavor()
	{
		// System.out.println("WidgetCanvas - setFlavor");
		if ( flavors[0] == null )
		{
			flavors[0] = new DataFlavor(WidgetObject.class, "Widget Object");
		}
		// System.out.println("WidgetCanvas - " + flavors[0]);
		return flavors;
	}




}
