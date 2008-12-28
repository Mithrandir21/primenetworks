/**
 * 
 */
package graphics.GUI.objectView.Hardware;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public interface HardwareViewInterface
{
	/**
	 * This method is where the changed data in the view is checked and
	 * necessary questions are asked. If changes that will affect other
	 * components are made, such as socket type, the user should verify the
	 * change.
	 */
	public boolean validateData();


	/**
	 * Here the actual data is written to the object.
	 */
	public void save();


	/**
	 * TODO - Description
	 * 
	 */
	boolean validateNecessaryData();

}
