/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;
import graphics.GUI.standardObjectEdit.StandardViews.HardwareObjectView;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardViewSpilt extends JPanel
{
	private ObjectScroll objScroll;

	private HardwareObjectView hardStdObjView;


	/**
	 * TODO - Description NEEDED!
	 */
	public StandardViewSpilt()
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 0.1; // request any extra vertical space
		c.weighty = 1.0; // request any extra vertical space
		// c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		// c.insets = new Insets(10,0,0,0); //top padding
		c.gridx = 1; // aligned with button 2
		c.gridwidth = 1; // 2 columns wide
		c.gridheight = 3;
		c.gridy = 1; // third row


		JPanel objectPanel = new JPanel();

		objScroll = new ObjectScroll();

		objectPanel.add(objScroll);

		this.add(objectPanel, c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.weightx = 1.0; // request any extra vertical space
		// c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		// c.insets = new Insets(10,0,0,0); //top padding
		c.gridx = 2; // aligned with button 2
		// c.gridwidth = 1; //2 columns wide
		// c.gridheight = 1;
		c.gridy = 1; // third row


		JPanel viewPanel = new JPanel();
		viewPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		hardStdObjView = new HardwareObjectView(PrimeMain1.objectlist.get(0));

		viewPanel.add(hardStdObjView);



		this.add(viewPanel, c);
	}


	/**
	 * Gets the {@link JScrollPane} that holds the JButton array with the
	 * selectable standard objects.
	 */
	public ObjectScroll getObjScroll()
	{
		return objScroll;
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
