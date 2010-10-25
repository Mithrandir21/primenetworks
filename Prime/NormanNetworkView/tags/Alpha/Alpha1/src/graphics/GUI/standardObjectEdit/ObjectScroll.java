/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import widgets.WidgetButton;


/**
 * This class extends the {@link JScrollPane} class. Here are contained the
 * {@link WidgetButton WidgetButtons} that represent the systems standard
 * objects.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectScroll extends JScrollPane
{

	/**
	 * A constructor for this class. It sets the vertical scroll bat policy to
	 * "always on", sets the scroll increment to 10, sets the scroll view to a
	 * new {@link StandardObjectSelection} and sets the preferred size of the
	 * scroll pane.
	 */
	public ObjectScroll()
	{
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		getVerticalScrollBar().setUnitIncrement(10);


		setViewportView(new StandardObjectSelection());

		setPreferredSize(new Dimension(290, 450));
	}
}
