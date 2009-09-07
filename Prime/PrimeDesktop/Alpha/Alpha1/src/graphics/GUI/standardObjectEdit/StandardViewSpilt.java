/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;
import graphics.GUI.standardObjectEdit.StandardViews.HardwareObjectView;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardViewSpilt extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 */
	public StandardViewSpilt()
	{
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 0.1;   //request any extra vertical space
		c.weighty = 1.0;   //request any extra vertical space
//		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 1;   //2 columns wide
		c.gridheight = 3;
		c.gridy = 1;       //third row


		JPanel objectPanel = new JPanel();
		objectPanel.add(new ObjectScroll());
		
		this.add(objectPanel, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.weightx = 1.0;   //request any extra vertical space
//		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 2;       //aligned with button 2
//		c.gridwidth = 1;   //2 columns wide
//		c.gridheight = 1;
		c.gridy = 1;       //third row


		JPanel viewPanel = new JPanel();
		viewPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		viewPanel.add(new HardwareObjectView(PrimeMain1.objectlist.get(0)));


		
		this.add(viewPanel, c);
	}
}
