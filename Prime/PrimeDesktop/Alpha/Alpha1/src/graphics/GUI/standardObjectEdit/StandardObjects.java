/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * This JFrame will show a selection JScrollPane that show all the systems
 * Standard Objects and a JPanel that shows all components of the selected
 * standard object.
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjects extends JFrame
{
	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// The JPanel that holds both the array of object buttons and the
	// visual standard object view.
	private StandardViewSpilt splitView;

	/**
	 * A constructor for this class that sets the JFrame name, size and the
	 * location of the top left corner of the frame. It also adds a new
	 * {@link StandardViewSpilt} to the JFrame.
	 */
	public StandardObjects()
	{
		super("Standard Objects");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(850, 550);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		splitView = new StandardViewSpilt();
		splitView.setBorder(grayline);

		panel.add(splitView);

		c.add(panel);


		this.setSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setResizable(false);

		this.setVisible(true);


		// Resets the objectView object when closed.
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				// Removes the pointer to this Object in the system registry.
				PrimeMain1.stdObjView = null;
			}
		});
	}



	/**
	 * Gets the pointer to the splitview tat holds the views for the standard
	 * Objects.
	 * 
	 * @return the splitView
	 */
	public StandardViewSpilt getSplitView()
	{
		return splitView;
	}
}
