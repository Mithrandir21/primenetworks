/**
 * 
 */
package graphics.GUI.properties;

import java.awt.Dimension;

import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import javax.swing.JTabbedPane;

import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class PropertiesArea	extends JTabbedPane
{
	ObjectScrollProperties scrollArea = new ObjectScrollProperties();
	
	/**
	 * TODO - Description NEEDED!
	 *
	 */
	public PropertiesArea()
	{
		
		int width = (int) (PrimeMain1.width*0.11);
		
		int height = (int) (PrimeMain1.height*0.70);


		
		this.setPreferredSize(new Dimension(width,height));
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		scrollArea.newObjectSelectedPropertiesTab(object);
		
		addTab(object.getObjectName(), scrollArea);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(WorkareaCanvas canvas)
	{
		scrollArea.newObjectSelectedPropertiesTab(canvas);
		
		addTab(canvas.getCanvasName(), scrollArea);
		
	}
}
