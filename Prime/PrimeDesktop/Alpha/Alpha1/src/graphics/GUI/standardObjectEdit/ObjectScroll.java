/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import java.awt.Dimension;

import javax.swing.JScrollPane;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ObjectScroll extends JScrollPane
{

	public ObjectScroll()
	{
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		getVerticalScrollBar().setUnitIncrement(10);


		setViewportView(new StandardObjectSelection());

		setPreferredSize(new Dimension(290, 450));
	}
}
