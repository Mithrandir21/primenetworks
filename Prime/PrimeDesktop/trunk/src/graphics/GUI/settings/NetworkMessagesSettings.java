/**
 * 
 */
package graphics.GUI.settings;


import graphics.PrimeMain;

import java.awt.Checkbox;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;



/**
 * The JPanel extension that contains all the different check boxes for the
 * various program variables.
 * 
 * @author Bahram Malaekeh
 */
public class NetworkMessagesSettings extends JPanel
{
	/**
	 * A constructor that takes a {@link Checkbox} array, holding all program
	 * variables.
	 */
	public NetworkMessagesSettings(JCheckBox[] checkBox, ActionListener lis)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// d.weighty = 1.0; // request any extra vertical space
		// d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 20, 5, 20); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column



		JCheckBox selectAll = new JCheckBox(PrimeMain.texts
				.getString("settingsSelectAllLabel"));
		selectAll.setToolTipText(PrimeMain.texts
				.getString("settingsSelectAllTip"));
		selectAll.setActionCommand(PrimeMain.texts
				.getString("settingsSelectAllLabel"));
		selectAll.addActionListener(lis);
		this.add(selectAll, d);



		checkBox[0].setName("showHardwareErrors");
		checkBox[1].setName("showHardwareWarnings");
		checkBox[2].setName("showHardwareNotices");

		JPanel hardPanel = getPanel(checkBox[0], checkBox[1], checkBox[2]);
		hardPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgHardwareLabel")));
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.insets = new Insets(0, 10, 10, 10); // padding
		d.gridy = 1; // row
		this.add(hardPanel, d);



		checkBox[3].setName("showSoftwareErrors");
		checkBox[4].setName("showSoftwareWarnings");
		checkBox[5].setName("showSoftwareNotices");


		JPanel softPanel = getPanel(checkBox[3], checkBox[4], checkBox[5]);
		softPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgSoftwareLabel")));
		d.insets = new Insets(10, 10, 10, 10); // padding
		d.gridy = 2; // row
		this.add(softPanel, d);



		checkBox[6].setName("showConnectionErrors");
		checkBox[7].setName("showConnectionWarnings");
		checkBox[8].setName("showConnectionNotices");

		JPanel conPanel = getPanel(checkBox[6], checkBox[7], checkBox[8]);
		conPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgConnectionsLabel")));
		d.gridy = 3; // row
		this.add(conPanel, d);


		checkBox[9].setName("showNetworkErrors");
		checkBox[10].setName("showNetworkWarnings");
		checkBox[11].setName("showNetworkNotices");

		JPanel netPanel = getPanel(checkBox[9], checkBox[10], checkBox[11]);
		netPanel.setBorder(BorderFactory.createTitledBorder(PrimeMain.texts
				.getString("settingsShowMsgNetworkLabel")));
		d.gridy = 4; // row
		this.add(netPanel, d);
	}





	/**
	 * Creates a JPanel where the checkboxes given are placed vertical in a row.
	 */
	private JPanel getPanel(JCheckBox box1, JCheckBox box2, JCheckBox box3)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.HORIZONTAL;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // row
		d.gridx = 0; // column


		box1.setText(PrimeMain.texts
				.getString("settingsShowMsgShowErrorsLabel"));
		panel.add(box1, d);


		box2.setText(PrimeMain.texts
				.getString("settingsShowMsgShowWarningsLabel"));
		d.gridx = 1; // column
		panel.add(box2, d);

		box3.setText(PrimeMain.texts
				.getString("settingsShowMsgShowNoticesLabel"));
		d.gridx = 2; // column
		panel.add(box3, d);



		return panel;
	}
}
