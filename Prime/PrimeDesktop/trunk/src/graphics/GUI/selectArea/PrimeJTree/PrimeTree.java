/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;



import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import widgets.WorkareaCanvas;


/**
 * This JPanel extension is where the network models, {@link WorkareaCanvas},
 * are placed and can be opened or deleted.
 * 
 * @author Bahram Malaekeh
 * @version 1.0
 */
public class PrimeTree extends JPanel
{
	/**
	 * The file tree.
	 */
	private JTree tree;


	/**
	 * Creates the file tree panel.
	 */
	public PrimeTree()
	{
		this.setLayout(new BorderLayout());

		createTree();
	}



	/**
	 * A constructor that sets up the {@link JTree} that contains the nodes
	 * pointing to the {@link WorkareaCanvas} files.
	 */
	public void createTree()
	{
		// Removes all the previous jscrolls with jtrees
		this.removeAll();

		// File[] roots = File.listRoots();
		File root = new File("./resource/Data");

		FileTreeNode rootTreeNode = new FileTreeNode(getFiles(root));

		this.tree = new JTree(rootTreeNode);

		this.tree.setCellRenderer(new PrimeFileTreeCellRenderer());

		this.tree.setRootVisible(false);

		this.tree.addMouseListener(new JTreeMouseAdapter(tree));

		final JScrollPane jsp = new JScrollPane(this.tree);

		// this.tree.setBorder(BorderFactory.createLineBorder(Color.black));

		this.add(jsp, BorderLayout.CENTER);
	}


	/**
	 * Returns the {@link JTree} with all the {@link WorkareaCanvas} files.
	 */
	public JTree getJTree()
	{
		return tree;
	}



	/**
	 * This method returns an array of files, starting from given root file,
	 * that contain {@link WorkareaCanvas WorkareaCanvases}.
	 * 
	 * @param root
	 */
	private File[] getFiles(File root)
	{
		// Creates an ArrayList that will temporarily hold the project files
		ArrayList<File> files = new ArrayList<File>();


		files = visitAllFiles(root, files);



		// Converts the ArrayList to an Object array
		Object[] array = files.toArray();

		// The number of files found
		int fileCount = 0;


		// sum the Object array
		for ( int i = 0; i < array.length; i++ )
		{
			fileCount++;
			;
		}


		// Creates a file File Array that will hold the final project files that
		// will be returned
		File[] projectFiles = new File[fileCount];

		for ( int i = 0; i < array.length; i++ )
		{
			projectFiles[i] = (File) array[i];
		}


		return projectFiles;
	}


	/**
	 * Goes through all files and directories under a given folder. It finds and
	 * sets all files within this given folder
	 * with the file extensions *.dat.
	 */
	public static ArrayList<File> visitAllFiles(File dir, ArrayList<File> files)
	{
		if ( dir.isDirectory() )
		{
			String[] children = dir.list();
			for ( int i = 0; i < children.length; i++ )
			{
				files = visitAllFiles(new File(dir, children[i]), files);
			}
		}
		else
		{
			String name = dir.getName();

			String[] parts = dir.getName().split("\\.");

			// Adds the files that end with ".dat" to the files arrayList
			if ( name.contains(".dat") && parts.length == 2 )
			{
				files.add(dir);
			}
		}


		return files;
	}
}
