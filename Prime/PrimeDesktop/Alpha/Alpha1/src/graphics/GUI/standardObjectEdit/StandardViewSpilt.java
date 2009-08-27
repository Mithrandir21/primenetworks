/**
 * 
 */
package graphics.GUI.standardObjectEdit;


import graphics.PrimeMain1;
import graphics.GUI.standardObjectEdit.StandardViews.HardwareObjectView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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


		JPanel objectPanel = new JPanel();
		objectPanel.add(new ObjectScroll());


		JPanel viewPanel = new JPanel();
		viewPanel.add(new HardwareObjectView(PrimeMain1.objectlist.get(10)));


		this.add(objectPanel);
		this.add(viewPanel);
	}
}
