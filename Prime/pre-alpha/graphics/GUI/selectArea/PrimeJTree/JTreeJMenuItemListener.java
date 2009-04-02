/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JTreeJMenuItemListener implements ActionListener
{

	/**
	 * The file that was right clicked on
	 */
	private FileTreeNode file;


	/**
	 * The JTree that the file is in represented as a FileTreeNode.
	 */
	private JTree tree;



	/**
	 * A constructor for this class.
	 * 
	 * @param file
	 * @param tree
	 */
	public JTreeJMenuItemListener(FileTreeNode file, JTree tree)
	{
		this.file = file;
		this.tree = tree;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equalsIgnoreCase("Open Network") )
		{
			PrimeJTreeActions.openFile(file);
		}
		else if ( e.getActionCommand().equalsIgnoreCase("Delete Network") )
		{
			PrimeJTreeActions.deleteFile(file, tree);
		}

	}
}
