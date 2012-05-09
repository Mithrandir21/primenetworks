/**
 * 
 */
package graphics.GUI.programGUI;


import graphics.PrimeMain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class PrimeSplashScreen extends JWindow
{
	public PrimeSplashScreen(int d)
	{
		ImageIcon image = new ImageIcon("resource/splash.png");

		// If an image was found and loaded.
		if ( image.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE )
		{
			JPanel content = (JPanel) getContentPane();

			// Set the window's bounds, centering the window
			int width = image.getIconWidth();
			int height = image.getIconHeight();
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			int x = (screen.width - width) / 2;
			int y = (screen.height - height) / 2;
			this.setBounds(x, y, width, height);

			// Build the splash screen
			JLabel label = new JLabel(image);
			content.add(label, BorderLayout.CENTER);

			// Display it
			this.setVisible(true);

			// Wait a little while, maybe while loading resources
			try
			{
				Thread.sleep(d);
			}
			catch ( Exception e )
			{
			}

			this.setVisible(false);
		}
		else
		{
			PrimeMain.ioLog
					.warning("Splash Screen was not loaded because image file was not found or loaded correctly.");
		}
	}
}
