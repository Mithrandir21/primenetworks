/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjects extends JFrame implements ActionListener
{
	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// The JPanel that holds both the array of object buttons and the
	// visual standard object view.
	private StandardViewSpilt splitView;

	/**
	 * TODO - Description NEEDED!
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



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(grayline);

		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button("cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



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



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{

		}
		else if ( e.getActionCommand().equals("apply") )
		{

		}
		else
		{
			assert e.getActionCommand().equals("cancel");

			// Removes the pointer to this Object in the system registry.
			PrimeMain1.stdObjView = null;

			this.dispose();
		}
	}

}
