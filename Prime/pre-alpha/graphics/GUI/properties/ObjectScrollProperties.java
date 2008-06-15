/**
 * 
 */
package graphics.GUI.properties;

import graphics.GUI.workareaCanvas.WorkareaCanvas;

import javax.swing.JScrollPane;

import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ObjectScrollProperties extends JScrollPane
{
	public ObjectScrollProperties()
	{
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
	}

	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		ObjectProperties objProp = new ObjectProperties(object);
		
		this.setViewportView(objProp);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(WorkareaCanvas canvas)
	{
		ObjectProperties objProp = new ObjectProperties(canvas);
		
		this.setViewportView(objProp);
	}
}
