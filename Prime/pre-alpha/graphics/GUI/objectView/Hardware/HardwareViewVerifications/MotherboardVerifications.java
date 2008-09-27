/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareViewVerifications;


import javax.swing.JOptionPane;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class MotherboardVerifications
{
	public static boolean nameTest(String text)
	{

		if ( text.length() < 1 || text.length() > 255 )
		{
			JOptionPane.showMessageDialog(null,
					"The motherboard name must be between 1 and 255 characters.", "Error - Name",
					JOptionPane.INFORMATION_MESSAGE);

			return false;
		}

		return true;
	}


	public static boolean socketTest()
	{

		return true;
	}
}
