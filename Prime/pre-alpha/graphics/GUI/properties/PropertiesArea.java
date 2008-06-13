/**
 * 
 */
package graphics.GUI.properties;

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
	/**
	 * TODO - Description NEEDED!
	 *
	 */
	public PropertiesArea()
	{
		createPropertiesTab();
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void createPropertiesTab()
	{
		ObjectProperties objProp = new ObjectProperties();
		
//		this.setPreferredSize(new Dimension(600, 500));
		
		addTab("tab", objProp);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void createPropertiesTab(Object object)
	{
		ObjectProperties objProp = new ObjectProperties(object);
		
//		this.setPreferredSize(new Dimension(600, 500));
		
		addTab("tab", objProp);
	}
	
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public void newObjectSelectedPropertiesTab(Object object)
	{
		ObjectProperties objProp = new ObjectProperties(object);
		
		this.removeAll();
		
		addTab(object.getName(), objProp);
	}
}
