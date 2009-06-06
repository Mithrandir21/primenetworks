/**
 * 
 */
package managment;


import java.io.Serializable;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class Settings implements Serializable
{
	// HARDWARE MESSAGES
	public static boolean showHardwareErrorMessages = true;

	public static boolean showHardwareWarningMessages = false;

	public static boolean showHardwareNoticeMessages = false;


	// SOFTWARE MESSAGES
	public static boolean showSoftwareErrorMessages = true;

	public static boolean showSoftwareWarningMessages = false;

	public static boolean showSoftwareNoticeMessages = false;


	// CONNECTION MESSAGES
	public static boolean showConnectionErrorMessages = true;

	public static boolean showConnectionWarningMessages = false;

	public static boolean showConnectionNoticeMessages = false;


	// NETWORK MESSAGES
	public static boolean showNetworkErrorMessages = true;

	public static boolean showNetworkWarningMessages = false;

	public static boolean showNetworkNoticeMessages = false;


	// CREATING NETWORK CONNECTION
	// A status saying whether or not the system is currently trying to create a connection between two object on
	// the workareaCanvas.
	public static boolean connecting = false;


	// CREATING ROOM BUTTON
	public static boolean roomsManipulation = false;
}
