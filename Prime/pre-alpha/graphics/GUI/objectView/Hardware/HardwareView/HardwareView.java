/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView;



/**
 * This interface class is used by the different hardware views like
 * {@link MotherboardView MotherboardView} and {@link CPUView CPUView}.
 * 
 * @author Bahram Malaekeh
 * 
 */
public interface HardwareView
{
	/**
	 * This interface method is set up so that all necessary data be validated
	 * and checked before it be written to the object.
	 * 
	 */
	public boolean validateNecessaryData();


	/**
	 * This method is where the changed data in the view is checked and
	 * necessary questions are asked. If changes that will affect other
	 * components are made, such as socket type, the user should verify the
	 * change.
	 * 
	 */
	public boolean validateChangedData();


	/**
	 * Here the actual data is written to the object.
	 * 
	 */
	public void save();
}
