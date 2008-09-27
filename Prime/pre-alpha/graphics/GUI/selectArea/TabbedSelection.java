package graphics.GUI.selectArea;



/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */


import graphics.ImageLocator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;


public class TabbedSelection extends JTabbedPane
{
	public TabbedSelection()
	{


		ImageIcon icon = ImageLocator.getImageIconObject("java");



		JComponent panel4 = new ObjectSelection();
		JScrollPane scrollArea = new JScrollPane(panel4);
		this.addTab("Unit Area", icon, scrollArea, "Unit Area");



		JComponent panel1 = new PrimeTree();
		this.addTab("Network Models", icon, panel1, "Network Models");






		// The following line enables to use scrolling tabs.
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}