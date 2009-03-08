/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;

import java.io.File;

import javax.swing.JOptionPane;
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
		JOptionPane.showMessageDialog(null, "You have tried to open the " + file.getFile().getName()
				+ " canvas.");
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
