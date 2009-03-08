/**
 * 
 */
package managment;

import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaCanvas;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class FileManagment
{
	public static void saveWorkareaCanvas(WorkareaCanvas canvas)
	{
		
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param file
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas, File file)
	{
		
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 */
	public static void deleteWorkareaCanvas(FileTreeNode fileNode, JTree tree)
	{
		// Checks on the file before any work is done
		File file = fileNode.getFile();
		
		
		// If the file is a directory
		if( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName() + "\n" +
					"is a directory at\n" +
					file.getAbsolutePath());
			
			return;
		}
		
		
		// If the file does not exist
		if( !file.exists() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" +
					"does not exist in location\n" +
					file.getAbsolutePath());
			
			return;
		}
		
		// If the file can not be written to(hence not deleted)
		if( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n" +
					"is write protected.");
			
			return;
		}
		
		
		
		// Attempt to delete it
	    boolean success = file.delete();
		
	    if( !success )
	    {
	    	JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" +
				"was NOT successfully deleted.");
	    }
	    else
	    {
	    	JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n" +
				"was successfully deleted.");

	    	DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
	    	model.removeNodeFromParent(fileNode);
	    }
	}
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 */
	public static void deleteWorkareaCanvas(WorkareaCanvas canvas)
	{
		
	}
}
