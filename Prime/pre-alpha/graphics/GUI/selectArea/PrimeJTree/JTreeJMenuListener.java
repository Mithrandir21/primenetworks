/**
 * 
 */
package graphics.GUI.selectArea.PrimeJTree;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;

import managment.FileManagment;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class JTreeJMenuListener implements ActionListener
{
	/**
	 * The JTree of the menu.
	 */
	private JTree tree;


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param tree
	 */
	public JTreeJMenuListener(JTree tree)
	{
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
		if ( e.getActionCommand().equalsIgnoreCase("New Network") )
		{
			FileManagment.newWorkareaCanvas();
		}
	}

}
