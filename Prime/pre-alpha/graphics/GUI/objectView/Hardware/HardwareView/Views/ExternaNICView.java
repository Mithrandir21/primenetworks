/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;
import hardware.ExternalNetworksCard;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.ComponentsManagment;
import objects.Object;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class ExternaNICView extends JPanel implements HardwareViewInterface,
		ActionListener
{
	private JTextField name = new JTextField(25);

	private JTextArea desc = new JTextArea(3, 40);

	private JTextField producer = new JTextField(7);

	private JTextField MAC;

	private JComboBox conType;

	private JComboBox transferSpeed;

	private JComboBox protocol;

	private JList supStandards;

	private String[] standars;

	private JCheckBox supIPv6;


	private Object mainObj;

	private ExternalNetworksCard extNIC;



	public ExternaNICView(Object obj, ExternalNetworksCard NIC)
	{
		mainObj = obj;
		extNIC = NIC;
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);

		ImageIcon icon = ImageLocator.getImageIconObject("NIC");
		JPanel p1 = HardwareEditor.GeneralInfo(extNIC, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(extNIC);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel("Remove this component from this device");

		Button remove = new Button("Remove Component");
		remove.addActionListener(this);
		remove.setActionCommand("removeComp");

		buttons.add(label);
		buttons.add(remove);

		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2, 10, 10, 10);

		this.add(buttons, c);
	}

	
	/**
	 * This method creates and returns a JPanel that contains all the
	 * different settings of the given Hardware object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order
	 * all the different components in the JPanel in grids.
	 * 
	 * @param NIC The Hardware that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */	
	private JPanel createSpesificInfo(ExternalNetworksCard NIC)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];


		labels[0] = new JLabel("Producer");
		labels[0].setToolTipText("The producer of the NIC.");

		labels[1] = new JLabel("MAC");
		labels[1].setToolTipText("The MAC address of the NIC.");

		labels[2] = new JLabel("Type");
		labels[2]
				.setToolTipText("The connection type supported by the NIC, ie. Wired or Wireless.");

		labels[3] = new JLabel("Speed");
		labels[3]
				.setToolTipText("The speed of the NIC. 10 Mbps, 100 Mbps or 1000 Mbps.");

		labels[4] = new JLabel("Protocol");
		labels[4]
				.setToolTipText("The NIC protocol. Ethernet, token and so on.");

		labels[5] = new JLabel("Supported Standars");
		labels[5]
				.setToolTipText("The standards supported by the NIC. IEEE 802.3, IEEE 802.3u and so on.");

		labels[6] = new JLabel("Supports IPv6");
		labels[6]
				.setToolTipText("Whether or not the NIC has support for IP version 6, IPv6.");


		Dimension tfSize = new Dimension(90, 20);


		// The producer
		labels[0].setLabelFor(producer);
		producer.setMaximumSize(tfSize);
		producer.setPreferredSize(tfSize);
		producer.setText(NIC.getProducer());
		producer.setToolTipText(labels[0].getToolTipText());

		panel.add(labels[0]);
		panel.add(producer);



		// The MAC address of the NIC
		labels[1].setLabelFor(MAC);
		MAC = new JTextField(12);
		MAC.setMaximumSize(tfSize);
		MAC.setPreferredSize(tfSize);
		MAC.setText(NIC.getMAC());
		MAC.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[1]);
		panel.add(MAC);


		// The connection type supported by the NIC
		labels[2].setLabelFor(conType);
		String[] conTypeString = { "Wired", "Wireless" };
		conType = new JComboBox(conTypeString);
		conType.setMaximumSize(tfSize);
		conType.setPreferredSize(tfSize);
		conType.setBackground(Color.WHITE);
		conType.setToolTipText(labels[2].getToolTipText());
		conType.setActionCommand("ConnectionType");
		conType.addActionListener(this);

		conType.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				conTypeString, NIC.getConnectionType()));


		panel.add(labels[2]);
		panel.add(conType);



		// The transfer speed supported by the NIC
		labels[3].setLabelFor(transferSpeed);
		String[] speedString = { "", "10", "100", "1000" };
		transferSpeed = new JComboBox(speedString);
		transferSpeed.setMaximumSize(tfSize);
		transferSpeed.setPreferredSize(tfSize);
		transferSpeed.setBackground(Color.WHITE);
		transferSpeed.setToolTipText(labels[3].getToolTipText());
		transferSpeed.setActionCommand("Speed");
		transferSpeed.addActionListener(this);

		transferSpeed.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				speedString, NIC.getSpeed()));


		panel.add(labels[3]);
		panel.add(transferSpeed);



		// The transfer speed supported by the NIC
		labels[4].setLabelFor(protocol);
		String[] protocolString = { "", "Ethernet", "Token Ring", "ATM" };
		protocol = new JComboBox(speedString);
		protocol.setMaximumSize(tfSize);
		protocol.setPreferredSize(tfSize);
		protocol.setBackground(Color.WHITE);
		protocol.setToolTipText(labels[4].getToolTipText());
		protocol.setActionCommand("Protocol");
		protocol.addActionListener(this);

		protocol.setSelectedIndex(GraphicalFunctions.getIndexInJComboBox(
				protocolString, NIC.getProtocol()));


		panel.add(labels[4]);
		panel.add(protocol);


		// The supported standards by the NIC.
		labels[5].setLabelFor(supStandards);
		String[] listData = { "802.3i", "802.3x", "802.3y", "802.3ab",
				"802.3an", "802.11a", "802.11b", "802.11g", "802.11n",
				"802.11y" };
		supStandards = new JList(listData);
		ListSelectionModel listSelectionModel = supStandards
				.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supStandards);
		listPane.setMaximumSize(new Dimension(90, 60));
		listPane.setPreferredSize(new Dimension(90, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( NIC.getSupportedStandards() != null )
		{
			if ( NIC.getSupportedStandards().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supStandards, listData, NIC.getSupportedStandards()));
			}
		}

		panel.add(labels[5]);
		panel.add(listPane);



		// The 64 bit check box
		labels[6].setLabelFor(supIPv6);
		supIPv6 = new JCheckBox();
		supIPv6.setMaximumSize(tfSize);
		supIPv6.setPreferredSize(tfSize);
		supIPv6.setToolTipText(labels[6].getToolTipText());
		supIPv6.setActionCommand("IPv6");
		supIPv6.addActionListener(this);

		supIPv6.setSelected(NIC.getIPv6support());

		panel.add(labels[6]);
		panel.add(supIPv6);

		
		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad



		return panel;
	}


	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Hardware.HardwareView.HardwareView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			extNIC.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			extNIC.setDescription(desc.getText());
		}

		if ( producer.getText() != "" )
		{
			extNIC.setProducer(producer.getText());
		}

		if ( MAC.getText() != "" )
		{
			extNIC.setMAC(MAC.getText());
		}

		if ( conType.getSelectedItem().toString() != "" )
		{
			extNIC.setType(conType.getSelectedItem().toString());
		}

		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			extNIC.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem()
					.toString()));
		}

		if ( protocol.getSelectedItem().toString() != "" )
		{
			extNIC.setSupportedConnectionInterfaces(protocol.getSelectedItem()
					.toString());
		}

		if ( supStandards.getSelectedIndex() == -1 )
		{
			extNIC.setSupportedStandards(standars);
		}

		extNIC.setSupportsIPv6(supIPv6.isSelected());
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof Button )
		{
			Button check = (Button) e.getSource();

			String command = check.getActionCommand();

			if ( command.equals("removeComp") )
			{
				// Will remove the first variable from the list of components
				// that will be returned and set as the components for the main
				// object.
				mainObj.setAllComponents(ComponentsManagment.removeComponent(
						extNIC, mainObj.getComponents(), mainObj
								.getComponents().length));

				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain1.getObjectView(mainObj);
				if(view != null)
				{
					view.updateViewInfo();
				}
			}
		}
	}


	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @author Bahram Malaekeh
	 */
	class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supStandards.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				standars = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				standars = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					standars[i] = (String) supStandards.getSelectedValues()[i];
				}
			}
		}
	}


	@Override
	public boolean validateNecessaryData()
	{
		return true;
	}
}
