/**
 * 
 */
package graphics.GUI.objectView.Hardware;


import objects.Hardware;



/**
 * An interface class implemented by all the hardware view panels.
 * 
 * @author Bahram Malaekeh
 */
public interface HardwareViewInterface
{


	/**
	 * Here the actual data is written to the object.
	 */
	public void save();


	/**
	 * This function should be used to validate the information set in the view.
	 */
	public boolean validateNecessaryData();


	/**
	 * Returns the hardware the views is displaying.
	 */
	public Hardware getViewHardware();

}
