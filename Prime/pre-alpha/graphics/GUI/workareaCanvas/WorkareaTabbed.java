/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;

import java.awt.*;

import javax.swing.*;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class WorkareaTabbed extends JTabbedPane
{
	public static WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll();
	
	public WorkareaTabbed()
	{
		// Creates the image for the tab
		ImageIcon icon = ImageLocator.getImageIconObject("java");

		this.setPreferredSize(new Dimension(600, 500));


		// Creates a new JScrollPane with the viewport set to the workareaCanvas
		


		addTab("Canvas", icon, canvasScroll);


//		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}
