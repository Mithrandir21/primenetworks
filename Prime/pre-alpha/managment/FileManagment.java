/**
 * 
 */
package managment;


import graphics.PrimeMain1;
import graphics.GUI.selectArea.PrimeJTree.FileTreeNode;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.WorkareaSceneScroll;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTree;


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
		try
		{
			FileOutputStream fout = new FileOutputStream(canvas.getCanvasName() + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(canvas);
			oos.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param canvas
	 * @param file
	 */
	public static void saveWorkareaCanvas(WorkareaCanvas canvas, File file)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(canvas);
			oos.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
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
		if ( file.isDirectory() )
		{
			JOptionPane.showMessageDialog(null, "This \n" + file.getName() + "\n"
					+ "is a directory at\n" + file.getAbsolutePath());

			return;
		}


		// If the file does not exist
		if ( !file.exists() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n"
					+ "does not exist in location\n" + file.getAbsolutePath());

			return;
		}

		// If the file can not be written to(hence not deleted)
		if ( !file.canWrite() )
		{
			JOptionPane.showMessageDialog(null, "This file\n" + file.getName() + "\n"
					+ "is write protected.");

			return;
		}



		// Attempt to delete it
		boolean success = file.delete();

		if ( !success )
		{
			JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n"
					+ "was NOT successfully deleted.");
		}
		else
		{
			// Reloads
			PrimeMain1.updatePrimeTree();

			JOptionPane.showMessageDialog(null, "The file\n" + file.getName() + "\n"
					+ "was successfully deleted.");


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




	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public static void newWorkareaCanvas()
	{
		String nameOfCanvas = (String) JOptionPane.showInputDialog(null, "Network Name",
				"New Network Name", JOptionPane.QUESTION_MESSAGE);

		// First creates the WorkareaSceneScroll object that will hold 
		WorkareaSceneScroll newScroll = new WorkareaSceneScroll(nameOfCanvas);
		
		// Then we add the JScrollPane to the Screen
		PrimeMain1.workTab.createNewCanvasTab(newScroll);
		PrimeMain1.workTab.revalidate();
		PrimeMain1.workTab.repaint();
		
		
		Maybe add the new canvas to the JTree and save it? 
	}







}
