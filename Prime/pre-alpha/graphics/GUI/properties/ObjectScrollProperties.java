/**
 * 
 */
package graphics.GUI.properties;


import graphics.GUI.workareaCanvas.WorkareaCanvas;

import javax.swing.JScrollPane;

import objects.Object;


/**
 * An extension of the JScrollPane class that is used to show the properties of
 * any chosen canvas or object.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectScrollProperties extends JScrollPane
{
	public ObjectScrollProperties()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}



	/**
	 * Sets the view inside the JScrollPane to a new ObjectProperties with the
	 * given object as a parameter.
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		ObjectProperties objProp = new ObjectProperties(object);

		this.setViewportView(objProp);
	}
	
	

	/**
	 * Sets the view inside the JScrollPane to a new ObjectProperties with the
	 * given canvas as a parameter.
	 */
	public void newObjectSelectedPropertiesTab(WorkareaCanvas canvas)
	{
		ObjectProperties objProp = new ObjectProperties(canvas);

		this.setViewportView(objProp);
	}
}
