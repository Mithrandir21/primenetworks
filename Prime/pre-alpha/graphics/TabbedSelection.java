package graphics;



/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */

import javax.swing.*;

import java.awt.*;
import java.io.IOException;

public class TabbedSelection extends JTabbedPane
{
    public TabbedSelection() 
    {
        
        
        ImageIcon icon = createImageIcon("images/java.jpg");
        
        
        
        JComponent panel1 = new PrimeTree();
        this.addTab("Network Models", icon, panel1, "Network Models");
        
        
        
        JComponent panel4 = new ObjectSelection();
        JScrollPane scrollArea = new JScrollPane(panel4);
        this.addTab("unit Area", icon, scrollArea, "Unit Area");
        
        
        
        
        //The following line enables to use scrolling tabs.
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
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