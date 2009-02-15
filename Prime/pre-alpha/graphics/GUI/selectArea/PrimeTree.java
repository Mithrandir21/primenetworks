/**
 * 
 */
package graphics.GUI.selectArea;


import java.awt.Dimension;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class PrimeTree extends JScrollPane implements TreeSelectionListener
{
	private JTree tree;


	public PrimeTree()
	{
		// Create a tree that allows several selections at a time.
		if ( System.getProperty("os.name").toUpperCase().contains("WINDOWS") )
		{
			tree = new JTree(addNodes(null, new File(System
					.getProperty("user.dir")
					+ "\\servers")));
		}
		else
		{
			tree = new JTree(addNodes(null, new File(System
					.getProperty("user.dir")
					+ "/servers")));
		}

		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.setDragEnabled(true);


		// Listen for when the selection changes.
		tree.addTreeSelectionListener(this);



		this.add(tree);

		this.setPreferredSize(new Dimension(300, 300));

	}


	public void valueChanged(TreeSelectionEvent e)
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath()
				.getLastPathComponent();
		System.out.println("You selected " + node);
	}




	/** Add nodes from under "dir" into curTop. Highly recursive. */
	DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir)
	{
		String curPath = dir.getPath();

		DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);

		if ( curTop != null )
		{ // should only be null at root
			curTop.add(curDir);
		}

		Vector<String> ol = new Vector<String>();

		String[] tmp = dir.list();

		for ( int i = 0; i < tmp.length; i++ )
		{
			ol.addElement(tmp[i]);
		}

		Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);

		File f;

		Vector<String> files = new Vector<String>();

		// Make two passes, one for Dirs and one for Files. This is #1.
		for ( int i = 0; i < ol.size(); i++ )
		{
			String thisObject = ol.elementAt(i);
			String newPath;

			if ( curPath.equals(".") )
			{
				newPath = thisObject;
			}
			else
			{
				newPath = curPath + File.separator + thisObject;
			}
			if ( (f = new File(newPath)).isDirectory() )
			{
				addNodes(curDir, f);
			}
			else
			{
				files.addElement(thisObject);
			}
		}

		// Pass two: for files.
		for ( int fnum = 0; fnum < files.size(); fnum++ )
		{
			curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
		}

		return curDir;
	}

}
