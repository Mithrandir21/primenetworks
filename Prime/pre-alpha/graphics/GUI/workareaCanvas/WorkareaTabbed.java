/**
 * 
 */
package graphics.GUI.workareaCanvas;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import org.netbeans.api.visual.widget.Scene;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class WorkareaTabbed extends JTabbedPane
{
	public WorkareaTabbed()
	{		
		// Creates the image for the tab
		ImageIcon icon = createImageIcon("images/java.jpg");
		
		this.setPreferredSize(new Dimension(600,500));
		
		
		// Creates a new JScrollPane with the viewport set to the workareaCanvas
		WorkareaSceneScroll canvasScroll = new WorkareaSceneScroll();
		
		
		addTab("Canvas", icon, canvasScroll);
		
		
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	}

	
    
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
