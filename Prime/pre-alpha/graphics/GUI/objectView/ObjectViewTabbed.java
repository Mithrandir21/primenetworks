package graphics.GUI.objectView;

import graphics.GUI.objectView.General.GeneralObjectView;

import javax.swing.JTabbedPane;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ObjectViewTabbed extends JTabbedPane
{
	public ObjectViewTabbed()
	{
//		 this.addTab("Unit Area", icon, scrollArea, "Unit Area");
		String genDesc = "General information and option";
		
		this.addTab("General", null, new GeneralObjectView(), genDesc);
		
	}
}
