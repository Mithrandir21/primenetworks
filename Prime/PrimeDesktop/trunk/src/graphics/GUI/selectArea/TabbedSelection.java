/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010 Bahram Malaekeh
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.selectArea;


/*
 * images/middle.gif.
 */
import graphics.ImageLocator;
import graphics.PrimeMain;
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
	JPanel objectPanel = new ObjectSelection(true);


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

		this.addTab(PrimeMain.texts.getString("selectAreaUnitAreaLabel"),
				objects, objectPanel,
				PrimeMain.texts.getString("selectAreaUnitAreaTip"));



		ImageIcon networks = ImageLocator.getImageIconObject("Networks");
		this.addTab(PrimeMain.texts.getString("selectAreaNetworkModelsLabel"),
				networks, primeTree,
				PrimeMain.texts.getString("selectAreaNetworkModelsTip"));


		// The following line enables to use scrolling tabs.
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}


	/**
	 * Updates the selection area where the ImageIcons of the systems objects
	 * are placed.
	 */
	public void updateObjectArea()
	{
		// objectPanel = new ObjectSelection(true);
		// scrollArea.setViewportView(objectPanel);


		ImageIcon objects = ImageLocator.getImageIconObject("Objects");

		this.insertTab(PrimeMain.texts.getString("selectAreaUnitAreaLabel"),
				objects, objectPanel,
				PrimeMain.texts.getString("selectAreaUnitAreaTip"), 0);
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