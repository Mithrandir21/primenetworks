/**
 * 
 */
package graphics.GUI.visualObjectCustomization;


import graphics.ImageLocator;
import graphics.GUI.visualObjectCustomization.previewFileChooser.ImageFilter;
import graphics.GUI.visualObjectCustomization.previewFileChooser.ImagePreview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import objects.Object;
import widgets.WidgetButton;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class VisualCustomListener implements ActionListener
{
	// The VisualCustomFrame that holds the buttons with the Images on
	private VisualCustomFrame frame;

	private JFileChooser fc;

	private Object object;

	/**
	 * A constructor the class that sets the VisualCustomFrame that holds the
	 * buttons with the Images on.
	 * 
	 * @param frame
	 */
	public VisualCustomListener(VisualCustomFrame frame)
	{
		this.frame = frame;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		WidgetButton button = (WidgetButton) e.getSource();

		// Gets the Object the Button represents
		object = button.getObject();



		// Set up the file chooser.
		if ( fc == null )
		{
			fc = new JFileChooser();

			// Add a custom file filter and disable the default
			// (Accept All) file filter.
			fc.addChoosableFileFilter(new ImageFilter());
			fc.setAcceptAllFileFilterUsed(false);

			// Add the preview pane.
			fc.setAccessory(new ImagePreview(fc));
		}

		// Show it.
		int returnVal = fc.showDialog(null, "Attach");

		// Process the results.
		if ( returnVal == JFileChooser.APPROVE_OPTION )
		{
			File file = fc.getSelectedFile();

			ImageIcon image = null;

			try
			{
				image = ImageLocator.createImageIcon(file.toURI().toURL());
			}
			catch ( MalformedURLException e1 )
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			if ( image.getIconHeight() <= 48 && image.getIconWidth() <= 48 )
			{
				frame.tempImageIcons.put(object.getClass(), image);
				frame.updateButtonImages();
			}
			else
			{
				JOptionPane.showMessageDialog(frame,
						"The image choosen must be 48x48 or smaller.",
						"Size Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		fc.setSelectedFile(null);
	}

}
