package graphics.GUI.selectArea;

/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */


import graphics.ImageLocator;
import graphics.GUI.selectArea.PrimeJTree.PrimeTree;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


/**
 * Javadoc-TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class TabbedSelection extends JTabbedPane
{
	
	/**
	 * 
	 */
	PrimeTree primeTree = new PrimeTree();
	
	/**
	 * Javadoc-TODO - Description NEEDED!
	 *
	 */
	public TabbedSelection()
	{


		ImageIcon icon = ImageLocator.getImageIconObject("java");



		JComponent panel4 = new ObjectSelection();
		JScrollPane scrollArea = new JScrollPane(panel4);
		this.addTab("Unit Area", icon, scrollArea, "Unit Area");


		this.addTab("Network Models", null, primeTree, "Network Models");






		// The following line enables to use scrolling tabs.
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return
	 */
	public PrimeTree getPrimeTree()
	{
		return primeTree;
	}
}