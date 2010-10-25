/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

import managment.DesktopFileManagment;


/**
 * A class extending {@link MouseAdapter} and implementing {@link ActionListener} which reacts to user clicking on a
 * given JTree.
 * 
 * @author Bahram Malaekeh
 */
public class JTreeMouseAdapter extends MouseAdapter implements ActionListener
{

	/**
	 * The JTree that the file is in represented as a FileTreeNode.
	 */
	private JTree tree;


	/**
	 * The file that was right clicked on
	 */
	private FileTreeNode file;


	/**
	 * A constructor for the class that gets and sets the JTree field of the class, which is the JTree actions will take
	 * place in.
	 * 
	 * @param tree
	 *            The JTree actions will take place in and listened for.
	 */
	public JTreeMouseAdapter(JTree tree)
	{
		this.tree = tree;
	}


	/**
	 * This function determines the location of the {@link MouseEvent}(the click) and, depending on what the user has
	 * clicked on, will instigate a JPopupMenu to show itself.
	 * 
	 * @param e
	 *            The {@link MouseEvent} that will determine what and where a JPopupMenu will be shown.
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


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}

	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if ( e.isPopupTrigger() )
		{
			myPopupEvent(e);
		}
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
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
	 * Creates and returns a JPopupMenu to be shown for a {@link FileTreeNode}.
	 * 
	 * @param file
	 *            The {@link FileTreeNode} the return JPopupMenu will be applied to.
	 * @return A JPopupMenu with actions for a {@link FileTreeNode}.
	 */
	private JPopupMenu getFileMenu(FileTreeNode file)
	{
		this.file = file;

		JPopupMenu itemPopup = new JPopupMenu();

		JMenuItem openFile = new JMenuItem("Open Network");
		openFile.setActionCommand("Open Network");
		openFile.addActionListener(this);

		itemPopup.add(openFile);


		JMenuItem deleteFile = new JMenuItem("Delete Network");
		deleteFile.setActionCommand("Delete Network");
		deleteFile.addActionListener(this);

		itemPopup.add(deleteFile);


		return itemPopup;
	}





	/**
	 * Creates and returns a JPopupMenu meant to be shown for no specific object in a JTree.
	 * 
	 * @return A JPopupMenu meant for a JTree.
	 */
	private JPopupMenu getJTreeMenu()
	{
		JPopupMenu menuPopup = new JPopupMenu();

		JMenuItem newNetwork = new JMenuItem("New Network");
		newNetwork.setActionCommand("New Network");
		newNetwork.addActionListener(this);

		menuPopup.add(newNetwork);


		return menuPopup;
	}



	/*
	 * (non-Javadoc)
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
			PrimeJTreeActions.deleteFile(file);
		}
		else if ( e.getActionCommand().equalsIgnoreCase("New Network") )
		{
			DesktopFileManagment.newWorkareaCanvas();
		}
	}
}
