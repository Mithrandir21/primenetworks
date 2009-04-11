/**
 * 
 */
package graphics.GUI.properties;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.awt.Dimension;

import javax.swing.JTabbedPane;

import objects.Object;


/**
 * This is the properties class that is an extension of the JTabbedPane class. It is here the information about either
 * the canvas or the WidgetObject i shown. It will react on changes in what canvas i currently selected or clicks on
 * WidgetObjects on the scene.
 * 
 * @author Bahram Malaekeh
 */
public class PropertiesArea extends JTabbedPane
{
	private ObjectScrollProperties scrollArea = new ObjectScrollProperties();

	/**
	 * The constructor for the class that will set both the height and width of the component depending on the screen
	 * size.
	 */
	public PropertiesArea()
	{

		int width = (int) (PrimeMain1.width * 0.11);

		int height = (int) (PrimeMain1.height * 0.70);


		this.setPreferredSize(new Dimension(width, height));
	}


	/**
	 * Creates and adds a new properties view with the information from the given object.
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		scrollArea.newObjectSelectedPropertiesTab(object);

		if ( object != null )
		{
			addTab(object.getObjectName(), scrollArea);
		}
		else
		{
//			addTab("", scrollArea);
			this.removeAll();
		}
	}



	/**
	 * Creates and adds a new properties view with the information from the given canvas.
	 */
	public void newObjectSelectedPropertiesTab(WorkareaCanvas canvas)
	{
		scrollArea.newObjectSelectedPropertiesTab(canvas);

		if ( canvas != null )
		{
			addTab(canvas.getCanvasName(), scrollArea);
		}
		else
		{
//			addTab("", scrollArea);
			this.removeAll();
		}
	}
	
	
	/**
	 * @return Returns the ObjectPropertie Panel that contains the actual fields and buttons.
	 */
	public ObjectProperties getObjectPropertiePanel()
	{
		return scrollArea.getObjectPropertiePanel();
	}
}
