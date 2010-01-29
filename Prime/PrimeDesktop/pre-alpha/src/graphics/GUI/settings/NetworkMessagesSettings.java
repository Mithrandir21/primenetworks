/**
 * 
 */
package graphics.GUI.settings;


import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class NetworkMessagesSettings extends JPanel
{
	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 */
	public NetworkMessagesSettings(JCheckBox[] checkBox)
	{
		setPreferredSize(new Dimension(700, 525));




		checkBox[0].setName("showHardwareErrors");
		checkBox[1].setName("showHardwareWarnings");
		checkBox[2].setName("showHardwareNotices");


		JPanel hardPanel = getPanel(checkBox[0], checkBox[1], checkBox[2]);
		hardPanel.setBorder(BorderFactory.createTitledBorder("Hardware"));



		checkBox[3].setName("showSoftwareErrors");
		checkBox[4].setName("showSoftwareWarnings");
		checkBox[5].setName("showSoftwareNotices");

		JPanel softPanel = getPanel(checkBox[3], checkBox[4], checkBox[5]);
		softPanel.setBorder(BorderFactory.createTitledBorder("Software"));



		checkBox[6].setName("showConnectionErrors");
		checkBox[7].setName("showConnectionWarnings");
		checkBox[8].setName("showConnectionNotices");

		JPanel conPanel = getPanel(checkBox[6], checkBox[7], checkBox[8]);
		conPanel.setBorder(BorderFactory.createTitledBorder("Connections"));


		checkBox[9].setName("showNetworkErrors");
		checkBox[10].setName("showNetworkWarnings");
		checkBox[11].setName("showNetworkNotices");

		JPanel netPanel = getPanel(checkBox[9], checkBox[10], checkBox[11]);
		netPanel.setBorder(BorderFactory.createTitledBorder("Network"));







		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(hardPanel,
								GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(softPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(conPanel,
										GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(netPanel,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(hardPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(softPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(conPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(netPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)));
	}





	/**
	 * Javadoc-TODO - Description
	 * 
	 * @param box1
	 * @param box2
	 * @param box3
	 * @return
	 */
	private JPanel getPanel(JCheckBox box1, JCheckBox box2, JCheckBox box3)
	{
		JPanel panel = new JPanel();

		JLabel showErroes = new JLabel("Show Errors");

		JLabel showWarnings = new JLabel("Show Warnings");

		JLabel showNotices = new JLabel("Show Notices");

		GroupLayout jPanel3Layout = new GroupLayout(panel);
		panel.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addGap(15, 15, 15).addComponent(showErroes).addGap(18, 18, 18)
						.addComponent(box1).addGap(74, 74, 74).addComponent(showWarnings).addGap(5, 5, 5).addComponent(
								box2).addGap(74, 74, 74).addComponent(showNotices).addGap(5, 5, 5).addComponent(box3)
						.addContainerGap(79, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(
						jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(showErroes)
								.addComponent(box1).addComponent(showWarnings).addComponent(box2).addComponent(
										showNotices).addComponent(box3)).addContainerGap(22, Short.MAX_VALUE)));


		return panel;
	}
}
