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
	private JLabel widgetIPlabel = new JLabel();

	public JTextField widgetIPfield = new JTextField();


	private JLabel widgetSubnetLabel = new JLabel();

	public JTextField widgetSubnetField = new JTextField();


	private JLabel widgetMacLabel = new JLabel();

	public JTextField widgetMacField = new JTextField();


	private JLabel widgetDefaultGatewayLabel = new JLabel();

	public JTextField widgetDefaultGatewayField = new JTextField();


	private JLabel widgetNetworkNameLabel = new JLabel();

	public JTextField widgetNetworkNameField = new JTextField();


	private JLabel widgetNotesLabel = new JLabel();

	public JTextArea widgetNotesArea = new JTextArea();



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public NetworkView(WidgetObject obj)
	{

		widgetIPlabel.setText("IP");

		if ( obj.getWidgetNetworkInfo().getIp() != null )
		{
			widgetIPfield.setText(obj.getWidgetNetworkInfo().getIp());
		}



		widgetSubnetLabel.setText("Subnet");

		if ( obj.getWidgetNetworkInfo().getNetmask() != null )
		{
			widgetSubnetField.setText(obj.getWidgetNetworkInfo().getNetmask());
		}




		widgetMacLabel.setText("MAC");

		if ( obj.getWidgetNetworkInfo().getMAC() != null )
		{
			widgetMacField.setText(obj.getWidgetNetworkInfo().getMAC());
		}


		widgetDefaultGatewayLabel.setText("Default Gateway");

		if ( obj.getWidgetNetworkInfo().getDefaultGateway() != null )
		{
			widgetDefaultGatewayField.setText(obj.getWidgetNetworkInfo().getDefaultGateway());
		}



		widgetNetworkNameLabel.setText("Network Name");

		if ( obj.getWidgetNetworkInfo().getNetworkName() != null )
		{
			widgetNetworkNameField.setText(obj.getWidgetNetworkInfo().getNetworkName());
		}




		widgetNotesLabel.setText("Network Notes");
		JScrollPane jScrollPane1 = new JScrollPane();

		if ( obj.getWidgetNetworkInfo().getWidgetNotes() != null )
		{
			widgetNotesArea.setText(obj.getWidgetNetworkInfo().getWidgetNotes());
		}







		widgetNotesArea.setColumns(20);
		widgetNotesArea.setRows(5);
		jScrollPane1.setViewportView(widgetNotesArea);

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
																				layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																						.addComponent(widgetIPlabel)
																						.addComponent(widgetMacLabel)
																						.addComponent(widgetSubnetLabel)
																						.addComponent(
																								widgetDefaultGatewayLabel))
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
																												widgetDefaultGatewayField,
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
																																				widgetSubnetField,
																																				GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				widgetIPfield,
																																				GroupLayout.Alignment.LEADING,
																																				GroupLayout.PREFERRED_SIZE,
																																				152,
																																				GroupLayout.PREFERRED_SIZE))
																														.addPreferredGap(
																																LayoutStyle.ComponentPlacement.RELATED,
																																55,
																																Short.MAX_VALUE)
																														.addComponent(
																																widgetNetworkNameLabel)
																														.addPreferredGap(
																																LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																widgetNetworkNameField,
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
																																widgetMacField,
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
																						.addComponent(widgetNotesLabel))
																		.addContainerGap(43, Short.MAX_VALUE)))));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addGap(49, 49, 49).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widgetIPlabel)
								.addComponent(widgetIPfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE).addComponent(widgetNetworkNameLabel,
										GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addComponent(
										widgetNetworkNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widgetSubnetLabel)
								.addComponent(widgetSubnetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(widgetMacLabel)
								.addComponent(widgetMacField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
								widgetDefaultGatewayLabel).addComponent(widgetDefaultGatewayField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(67, 67, 67).addComponent(widgetNotesLabel).addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1,
								GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addContainerGap()));
	}
}
