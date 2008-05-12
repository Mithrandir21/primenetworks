/**
 * 
 */
package graphics;

import javax.swing.*;

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version
 */
public class WorkareaTabbed extends JTabbedPane
{
	public WorkareaTabbed()
	{
		ImageIcon icon = createImageIcon("images/java.jpg");
		JComponent p = new JPanel();
		p.add(new WorkareaCanvas());
		this.addTab("P1", icon, p, "P2");
		
		
        
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
