package graphics.GUI.selectArea;


/*
 * TabbedPaneDemo.java requires one additional file:
 *   images/middle.gif.
 */


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.selectArea.PrimeJTree.PrimeTree;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import objects.Object;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;


/**
 * This JTabbedPane extension is where the selection area and Networkmodels area
 * is located.
 * The selection area is where the user can click'n'drag new
 * {@link WidgetObject WidgetObjects} into an open {@link WorkareaCanvas}.
 * The Networkmodels area is where the user can see all the network models he
 * can open and edit.
 * 
 * @author Bahram Malaekeh
 */
public class TabbedSelection extends JTabbedPane
{
	/**
	 * Object selection area, where the ImageIcons of the objects can be clicked
	 * and dragged.
	 */
	JPanel objectPanel = new ObjectSelection();


	PrimeTree primeTree = new PrimeTree();


	/**
	 * The JScrollPane that holds the object selection area and the file area.
	 */
	JScrollPane scrollArea = null;


	/**
	 * A constructor for the class that adds both the selection area tab for the
	 * {@link Object Objects} and the network models tabs for the
	 * {@link WorkareaCanvas}.
	 */
	public TabbedSelection()
	{
		ImageIcon objects = ImageLocator.getImageIconObject("Objects");
		scrollArea = new JScrollPane(objectPanel);
		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollArea.getVerticalScrollBar().setUnitIncrement(10);

		this.addTab(PrimeMain1.texts.getString("selectAreaUnitAreaLabel"),
				objects, scrollArea, PrimeMain1.texts
						.getString("selectAreaUnitAreaTip"));



		ImageIcon networks = ImageLocator.getImageIconObject("Networks");
		this.addTab(PrimeMain1.texts.getString("selectAreaNetworkModelsLabel"),
				networks, primeTree, PrimeMain1.texts
						.getString("selectAreaNetworkModelsTip"));


		// The following line enables to use scrolling tabs.
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}


	/**
	 * Updates the selection area where the ImageIcons of the systems objects
	 * are placed.
	 */
	public void updateObjectArea()
	{
		objectPanel = new ObjectSelection();
		scrollArea.setViewportView(objectPanel);


		ImageIcon objects = ImageLocator.getImageIconObject("Objects");

		this.insertTab(PrimeMain1.texts.getString("selectAreaUnitAreaLabel"),
				objects, scrollArea, PrimeMain1.texts
						.getString("selectAreaUnitAreaTip"), 0);
		this.setSelectedIndex(0);
	}


	/**
	 * Returns the {@link PrimeTree} that shows all the {@link WorkareaCanvas
	 * WorkareaCanvases} available.
	 */
	public PrimeTree getPrimeTree()
	{
		return primeTree;
	}
}