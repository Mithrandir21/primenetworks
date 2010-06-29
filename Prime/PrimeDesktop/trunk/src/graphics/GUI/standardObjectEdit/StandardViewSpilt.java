/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain;
import graphics.GUI.selectArea.ObjectSelection;
import graphics.GUI.standardObjectEdit.StandardViews.HardwareObjectView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import widgets.WidgetButton;


/**
 * This extended JPanel is split in two(30/70), where the first part is a
 * JScrollPane that holds {@link WidgetButton WidgetButtons} of all the standard
 * objects, and the second part will hold component information for each
 * selected standard object.
 * 
 * @author Bahram Malaekeh
 */
public class StandardViewSpilt extends JPanel
{
	private HardwareObjectView hardStdObjView;


	/**
	 * A constructor for this class.
	 */
	public StandardViewSpilt()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		// c.weighty = 0.1; // request any extra vertical space
		c.weightx = 1.0; // request any extra vertical space
		// c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		// c.insets = new Insets(10,0,0,0); //top padding
		// c.gridwidth = 1; // 2 columns wide
		// c.gridheight = 3;
		c.gridx = 0; // aligned with button 2
		c.gridy = 0; // third row



		this
				.add(
						new ObjectSelection(new StandardViewMouseListener(),
								false), c);


		// c.fill = GridBagConstraints.BOTH;
		// c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 0.6; // request any extra vertical space
		// c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		// c.insets = new Insets(10,0,0,0); //top padding
		// c.gridwidth = 1; // 2 columns wide
		// c.gridheight = 3;
		c.gridx = 1; // aligned with button 2
		c.gridy = 0; // third row


		hardStdObjView = new HardwareObjectView(PrimeMain.objectlist.get(0));

		Dimension dim = new Dimension(450, 1);
		hardStdObjView.setMinimumSize(dim);


		this.add(hardStdObjView, c);
	}


	/**
	 * Gets the view that holds the hardware info about the currently selected
	 * object.
	 */
	public HardwareObjectView getHardStdObjView()
	{
		return hardStdObjView;
	}
}
