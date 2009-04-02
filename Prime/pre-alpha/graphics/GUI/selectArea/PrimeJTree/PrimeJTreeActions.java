/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import graphics.PrimeMain1;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

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
	 * This method attempts to open the file inside the given {@link FileTreeNode}. This will should contain a
	 * {@link WorkareaCanvas}. If the {@link WorkareaCanvas} is already opened, the method will do nothing.
	 * 
	 * @param file
	 *            The {@link FileTreeNode} that should contains a File, which will be attempted read and opened.
	 */
	public static void openFile(FileTreeNode file)
	{
		// Gets the name of the canvas depending on the file
		String canvasName = FileManagment.getCanvasName(file.getFile());

		// If a name was retrieved
		if ( canvasName != null )
		{
			// Whether or not
			boolean exists = PrimeMain1.getWorkarea().existsTabWithGivenName(canvasName);

			// There does not exist a tab with that name already
			if ( exists == false )
			{
				FileManagment.openWorkareaCanvas(file.getFile());
			}
		}
	}


	/**
	 * The method calls on the DeleteWorkareaCanvas function in {@link FileManagment}.
	 * 
	 * @param file
	 */
	public static void deleteFile(FileTreeNode file, JTree tree)
	{
		FileManagment.deleteWorkareaCanvas(file, tree);
	}



}
