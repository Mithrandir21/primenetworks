/**
 * 
 */
package graphics.GUI.properties;

import java.awt.Dimension;

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
		createPropertiesTab();
		
//		this.setPreferredSize(new Dimension(200,300));
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void createPropertiesTab()
	{
		scrollArea.createPropertiesTab();
		
		addTab("tab", scrollArea);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void createPropertiesTab(Object object)
	{
		scrollArea.createPropertiesTab(object);
		
		addTab("tab", scrollArea);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		scrollArea.newObjectSelectedPropertiesTab(object);
		
		addTab(object.getName(), scrollArea);
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
