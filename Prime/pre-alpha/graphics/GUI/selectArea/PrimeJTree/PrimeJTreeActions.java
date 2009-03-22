/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;

import graphics.PrimeMain1;

import javax.swing.JTree;

import managment.FileManagment;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class PrimeJTreeActions
{
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 */
	public static void openFile(FileTreeNode file)
	{
//		System.out.println("Starting proccess...");
//		System.out.println("Getting canvasName...");
		// Gets the name of the canvas depending on the file
		String canvasName = FileManagment.getCanvasName(file.getFile());
//		System.out.println("CanvasName is \"" + canvasName + "\".");
		
		// If a name was retrieved 
		if( canvasName != null )
		{
//			System.out.println("Checking if tab exists....");
			// Whether or not
			boolean exists = PrimeMain1.getWorkarea().existsTabWithGivenName(canvasName);
//			System.out.println("The answer is " + exists);
			
			// There does not exist a tab with that name already
			if( exists == false )
			{
				FileManagment.openWorkareaCanvas(file.getFile());
			}
		}
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 */
	public static void deleteFile(FileTreeNode file, JTree tree)
	{
		FileManagment.deleteWorkareaCanvas(file, tree);
	}
	
	
	
}
