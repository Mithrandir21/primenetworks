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




		ImageIcon objects = ImageLocator.getImageIconObject("Objects");
		JComponent panel4 = new ObjectSelection();
		JScrollPane scrollArea = new JScrollPane(panel4);
		scrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.addTab("Unit Area", objects, scrollArea, "Unit Area");

		
		ImageIcon networks = ImageLocator.getImageIconObject("Networks");
		this.addTab("Network Models", networks, primeTree, "Network Models");






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