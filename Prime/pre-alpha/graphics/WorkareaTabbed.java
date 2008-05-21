/**
 * 
 */
package graphics;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

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
		
		// A simple border that is gray 
		Border grayline = BorderFactory.createLineBorder(Color.GRAY);
		
		
		ImageIcon icon = createImageIcon("images/java.jpg");
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		
		JScrollPane pane = new JScrollPane();
		//pane.setPreferredSize(new Dimension(800,600));
		pane.setHorizontalScrollBarPolicy(pane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setVerticalScrollBarPolicy(pane.VERTICAL_SCROLLBAR_ALWAYS);
		
		WorkareaCanvas canvas = new WorkareaCanvas();
		
		pane.setViewportView(canvas);
		
		p.add(pane);

		
		this.addTab("Canvas", null, p, "P2");
		
		
        
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
