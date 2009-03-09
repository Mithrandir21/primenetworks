/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JTreeMouseAdapter extends MouseAdapter
{

	/**
	 * 
	 */
	private JTree tree;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param tree
	 */
	public JTreeMouseAdapter(JTree tree)
	{
		this.tree = tree;
	}


	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param e
	 */
	private void myPopupEvent(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		JTree tree = (JTree) e.getSource();
		TreePath path = tree.getPathForLocation(x, y);

		// If the mouse was right clicked on any of the nodes in the JTree
		if ( path != null )
		{
			tree.setSelectionPath(path);

			FileTreeNode obj = (FileTreeNode) path.getLastPathComponent();

			JPopupMenu popup = getFileMenu(obj);
			
			popup.show(tree, x, y);
		}
		// If the mouse was right clicked on the empty area in the selection area
		else
		{
			JPopupMenu popup = getJTreeMenu();
			
			popup.show(tree, x, y);
		}
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
		
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e)
	{
		// If button1 is double clicked.
		if ( e.getClickCount() == 2 )
		{
			int x = e.getX();
			int y = e.getY();
			JTree tree = (JTree) e.getSource();
			TreePath path = tree.getPathForLocation(x, y);
			if ( path == null )
				return;

			tree.setSelectionPath(path);

			FileTreeNode obj = (FileTreeNode) path.getLastPathComponent();
			
			PrimeJTreeActions.openFile(obj);
		}
	}
	
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param file
	 * @return
	 */
	private JPopupMenu getFileMenu(FileTreeNode file)
	{
		JTreeJMenuItemListener listener = new JTreeJMenuItemListener(file, tree);
		
		JPopupMenu itemPopup = new JPopupMenu();
		
		JMenuItem openFile = new JMenuItem("Open Network");
		openFile.setActionCommand("Open Network");
		openFile.addActionListener(listener);
		
		itemPopup.add(openFile);
		
		
		JMenuItem deleteFile = new JMenuItem("Delete Network");
		deleteFile.setActionCommand("Delete Network");
		deleteFile.addActionListener(listener);
		
		itemPopup.add(deleteFile);

		
		return itemPopup;		
	}
	
	
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	private JPopupMenu getJTreeMenu()
	{
		JTreeJMenuListener listener = new JTreeJMenuListener(tree);
		
		JPopupMenu menuPopup = new JPopupMenu();
		
		JMenuItem newNetwork = new JMenuItem("New Network");
		newNetwork.setActionCommand("New Network");
		newNetwork.addActionListener(listener);
		
		menuPopup.add(newNetwork);
		
		
		JMenu subMenu = new JMenu("A sub menu");
		
		JMenuItem subItem1 = new JMenuItem("Action 1");
		
		JMenuItem subItem2 = new JMenuItem("Action 2");
		
		subMenu.add(subItem1);
		subMenu.add(subItem2);
		
		
		menuPopup.add(subMenu);
		
		
		return menuPopup;
	}
}
