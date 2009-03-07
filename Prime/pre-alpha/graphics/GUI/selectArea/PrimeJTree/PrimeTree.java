/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;



import graphics.ImageLocator;
import graphics.PrimeMain1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.List;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class PrimeTree extends JPanel implements TreeSelectionListener
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

		// File[] roots = File.listRoots();
		File root = new File(".\\Data");
		
		FileTreeNode rootTreeNode = new FileTreeNode(getFiles(root));
		
		this.tree = new JTree(rootTreeNode);
		
		this.tree.addTreeSelectionListener(this);
		
		this.tree.setCellRenderer(new PrimeFileTreeCellRenderer());
		
		this.tree.setRootVisible(false);
		
		final JScrollPane jsp = new JScrollPane(this.tree);
		
		// jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		this.add(jsp, BorderLayout.CENTER);
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param root
	 * @return
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
		
		
		//sum the Object array 
	    for(int i=0;i<array.length;i++)
	    {
	    	fileCount++;;
	    }
		
	    
	    // Creates a file File Array that will hold the final project files that will be returned 		
		File[] projectFiles = new File[fileCount];
		
	    for(int i=0;i<array.length;i++)
	    {
	    	projectFiles[i] = (File) array[i];
	    }

		
		return projectFiles;		
	}
	

	/**
	 * Goes through all files and directories under a given folder. It finds and
	 * sets all files within this given folder with the file extensions *.dat. 
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

			// Adds the files that end with ".dat" to the files arrayList
			if ( name.contains(".dat") )
			{
				files.add(dir);
			}
		}
		
		
		return files;	
	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		FileTreeNode node = (FileTreeNode) this.tree.getLastSelectedPathComponent();

		JOptionPane.showMessageDialog(null, "You have tried to open the " + node.getFile().getName() + " canvas.");
	}
}
