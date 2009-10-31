package logistical;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public interface SystemActionInterface
{
	/**
	 * Method that will perform the classes action, with a boolean saying
	 * whether or not the action should be set as undoable.
	 */
	public void performAction(boolean undoable);
}
