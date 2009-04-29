package graphics.GUI.objectView.Network;


import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import widgetManipulation.WidgetObject;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class NetworkView extends JPanel
{
	/**
	 * TODO - Description NEEDED!
	 */
	public NetworkView(WidgetObject obj)
	{
		JLabel jLabel1 = new JLabel();
		jLabel1.setText("IP");
		JTextField jTextField1 = new JTextField();

		if ( obj.getWidgetNetworkInfo().getIp() != null )
		{
			jTextField1.setText(obj.getWidgetNetworkInfo().getIp());
		}


		JLabel jLabel2 = new JLabel();
		jLabel2.setText("Subnet");
		JTextField jTextField2 = new JTextField();

		if ( obj.getWidgetNetworkInfo().getNetmask() != null )
		{
			jTextField2.setText(obj.getWidgetNetworkInfo().getNetmask());
		}



		JLabel jLabel4 = new JLabel();
		jLabel4.setText("MAC");
		JTextField jTextField4 = new JTextField();

		if ( obj.getWidgetNetworkInfo().getMAC() != null )
		{
			jTextField4.setText(obj.getWidgetNetworkInfo().getMAC());
		}


		JLabel jLabel6 = new JLabel();
		jLabel6.setText("Default Gateway");
		JTextField jTextField5 = new JTextField();

		if ( obj.getWidgetNetworkInfo().getDefaultGateway() != null )
		{
			jTextField5.setText(obj.getWidgetNetworkInfo().getDefaultGateway());
		}


		JLabel jLabel3 = new JLabel();
		jLabel3.setText("Network Name");
		JTextField jTextField3 = new JTextField();

		if ( obj.getWidgetNetworkInfo().getNetworkName() != null )
		{
			jTextField3.setText(obj.getWidgetNetworkInfo().getNetworkName());
		}



		JLabel jLabel5 = new JLabel();
		jLabel5.setText("Network Notes");
		JTextArea jTextArea1 = new JTextArea();
		JScrollPane jScrollPane1 = new JScrollPane();

		if ( obj.getWidgetNetworkInfo().getWidgetNotes() != null )
		{
			jTextArea1.setText(obj.getWidgetNetworkInfo().getWidgetNotes());
		}








		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(39, 39, 39)
										.addGroup(
												layout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout.createParallelGroup(
																						GroupLayout.Alignment.TRAILING)
																						.addComponent(jLabel1)
																						.addComponent(jLabel4)
																						.addComponent(jLabel2)
																						.addComponent(jLabel6))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												jTextField4,
																												GroupLayout.PREFERRED_SIZE,
																												152,
																												GroupLayout.PREFERRED_SIZE)
																										.addContainerGap())
																						.addGroup(
																								layout
																										.createParallelGroup(
																												GroupLayout.Alignment.LEADING)
																										.addGroup(
																												layout
																														.createSequentialGroup()
																														.addGroup(
																																layout
																																		.createParallelGroup(
																																				GroupLayout.Alignment.TRAILING,
																																				false)
																																		.addComponent(
																																				jTextField2,
																																				GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				jTextField1,
																																				GroupLayout.Alignment.LEADING,
																																				GroupLayout.PREFERRED_SIZE,
																																				152,
																																				GroupLayout.PREFERRED_SIZE))
																														.addPreferredGap(
																																LayoutStyle.ComponentPlacement.RELATED,
																																55,
																																Short.MAX_VALUE)
																														.addComponent(
																																jLabel3)
																														.addPreferredGap(
																																LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																jTextField5,
																																GroupLayout.PREFERRED_SIZE,
																																152,
																																GroupLayout.PREFERRED_SIZE)
																														.addGap(
																																78,
																																78,
																																78))
																										.addGroup(
																												layout
																														.createSequentialGroup()
																														.addComponent(
																																jTextField3,
																																GroupLayout.PREFERRED_SIZE,
																																152,
																																GroupLayout.PREFERRED_SIZE)
																														.addContainerGap()))))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane1,
																								GroupLayout.PREFERRED_SIZE,
																								553,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(jLabel5))
																		.addContainerGap(43, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(49, 49, 49).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(
								jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE).addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 14,
								GroupLayout.PREFERRED_SIZE).addComponent(jTextField5, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(
								jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(
								jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(
								jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)).addGap(67, 67, 67).addComponent(jLabel5).addPreferredGap(
						LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE,
						182, Short.MAX_VALUE).addContainerGap()));
	}
	
	
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public void save(boolean closeObjectView)
	{
		
	}
}
