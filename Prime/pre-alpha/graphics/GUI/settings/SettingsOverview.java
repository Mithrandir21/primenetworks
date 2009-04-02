/**
 * 
 */
package graphics.GUI.settings;


import graphics.ImageLocator;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class SettingsOverview extends JFrame
{


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 */
	public SettingsOverview()
	{
		super("Settings");

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		// Set size for the settings JFrame
		Dimension size = new Dimension(700, 525);

		int initYLocation = (scrnsize.height - size.height) / 2;
		int initXLocation = (scrnsize.width - size.width) / 2;

		ImageIcon frameIcon = ImageLocator.getImageIconObject("Settings");
		this.setIconImage(frameIcon.getImage());
		
		
		
		
		this.add(getSettingsTabs());
		
		
		this.setSize(size);
		this.setLocation(initXLocation, initYLocation);
		// this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}





	private JTabbedPane getSettingsTabs()
	{
		JTabbedPane tabs = new JTabbedPane();

		ImageIcon frameIcon = ImageLocator.getImageIconObject("ProcessingSettings");
		tabs.addTab("Network Processing", frameIcon, null, "Settings for processing of a network");



		return tabs;
	}



}
