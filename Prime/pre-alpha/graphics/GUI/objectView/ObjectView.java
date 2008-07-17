package graphics.GUI.objectView;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ObjectView extends JFrame
{
	
	public ObjectView()
	{
		super("Object View");
		
		
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) scrnsize.getWidth()) / 2;

		int height = ((int) scrnsize.getHeight()) / 2;
		
		
		// Get the content pane for this object
		Container c = this.getContentPane();

		
		c.add(new ObjectViewTabbed(), BorderLayout.CENTER);
		
		
		this.setSize(width, height);
		this.setVisible(true);
	}
}
