package graphics.GUI.objectView.Network;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;
import widgets.WidgetObject;


/**
 * This class sets up the information displayed to the user about the network
 * status of a given {@link Object}.
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




	public NetworkView(WidgetObject obj)
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra horizontal space
		d.anchor = GridBagConstraints.NORTH; // location
		d.insets = new Insets(10, 10, 10, 10); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide
		// d.gridy = 0; // row
		// d.gridx = 0; // column





		JPanel fieldsPanel = new JPanel();

		fieldsPanel.setLayout(new GridBagLayout());
		GridBagConstraints fieldD = new GridBagConstraints();


		fieldD.fill = GridBagConstraints.BOTH;
		// fieldD.ipady = 0; // reset to default
		// fieldD.ipadx = 0; // reset to default
		// fieldD.weighty = 1.0; // request any extra vertical space
		fieldD.weightx = 0; // request any extra horizontal space
		fieldD.anchor = GridBagConstraints.CENTER; // location
		fieldD.insets = new Insets(5, 5, 5, 5); // padding
		// fieldD.gridwidth = 1; // 2 row wide
		// fieldD.gridheight = 1; // 2 columns wide




		fieldD.gridy = 0; // row
		fieldD.gridx = 0; // column
		widgetIPlabel = new JLabel("IP");
		fieldsPanel.add(widgetIPlabel, fieldD);

		fieldD.gridx = 1; // column
		widgetIPfield = new JTextField();
		fieldsPanel.add(widgetIPfield, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetIPexamplelabel = new JLabel("Example: 192.168.1.1");
		fieldsPanel.add(widgetIPexamplelabel, fieldD);




		fieldD.gridy = 1; // row
		fieldD.gridx = 0; // column
		widgetSubnetLabel = new JLabel("Subnet");
		fieldsPanel.add(widgetSubnetLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetSubnetField = new JTextField();
		fieldsPanel.add(widgetSubnetField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetSubnetExampleLabel = new JLabel("Example: 255.255.0.0");
		fieldsPanel.add(widgetSubnetExampleLabel, fieldD);




		fieldD.gridy = 2; // row
		fieldD.gridx = 0; // column
		widgetMacLabel = new JLabel("MAC");
		fieldsPanel.add(widgetMacLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetMacField = new JTextField();
		fieldsPanel.add(widgetMacField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetMacExampleLabel = new JLabel("Example: 01:23:45:67:89:AB");
		fieldsPanel.add(widgetMacExampleLabel, fieldD);




		fieldD.gridy = 3; // row
		fieldD.gridx = 0; // column
		widgetDefaultGatewayLabel = new JLabel("Default Gateway");
		fieldsPanel.add(widgetDefaultGatewayLabel, fieldD);

		fieldD.gridx = 1; // column
		widgetDefaultGatewayField = new JTextField();
		fieldsPanel.add(widgetDefaultGatewayField, fieldD);

		fieldD.gridx = 2; // column
		JLabel widgetDefaultGatewayExampleLabel = new JLabel(
				"Example: 192.168.4.1");
		fieldsPanel.add(widgetDefaultGatewayExampleLabel, fieldD);




		fieldD.gridy = 4; // row
		fieldD.gridx = 0; // column
		widgetNetworkNameLabel = new JLabel("Network Name");
		fieldsPanel.add(widgetNetworkNameLabel, fieldD);

		fieldD.weightx = 1.0; // request any extra horizontal space
		fieldD.gridx = 1; // column
		widgetNetworkNameField = new JTextField();
		fieldsPanel.add(widgetNetworkNameField, fieldD);

		// fieldD.weightx = 1.0; // request any extra horizontal space
		fieldD.gridx = 2; // column
		JLabel widgetNetworkNameExampleLabel = new JLabel("Example: Office");
		fieldsPanel.add(widgetNetworkNameExampleLabel, fieldD);



		d.gridy = 0; // row
		d.gridx = 0; // column
		this.add(fieldsPanel, d);





		JPanel notePanel = new JPanel();

		notePanel.setLayout(new GridBagLayout());
		GridBagConstraints noteD = new GridBagConstraints();


		noteD.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		// fieldD.weighty = 1.0; // request any extra vertical space
		// fieldD.weightx = 1.0; // request any extra horizontal space
		// d.anchor = GridBagConstraints.NORTH; // location
		// d.insets = new Insets(5, 5, 5, 5); // padding
		// d.gridwidth = 1; // 2 row wide
		// d.gridheight = 1; // 2 columns wide




		noteD.gridy = 0; // row
		noteD.gridx = 0; // column
		widgetNotesLabel = new JLabel("Network Notes");
		notePanel.add(widgetNotesLabel, noteD);

		noteD.weighty = 1.0; // request any extra vertical space
		noteD.weightx = 1.0; // request any extra horizontal space
		noteD.gridy = 1; // row
		widgetNotesArea = new JTextArea();
		JScrollPane notesScroll = new JScrollPane(widgetNotesArea);
		notePanel.add(notesScroll, noteD);



		d.gridwidth = 2;
		d.gridy = 1; // row
		d.gridx = 0; // column
		this.add(notePanel, d);
	}
}
