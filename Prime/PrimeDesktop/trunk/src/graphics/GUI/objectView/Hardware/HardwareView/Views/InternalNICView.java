/**
 * 
 */
package graphics.GUI.objectView.Hardware.HardwareView.Views;


import graphics.GraphicalFunctions;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareEditor;

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
import objects.Hardware;
import objects.Object;
import objects.hardwareObjects.InternalNetworksCard;
import connections.ConnectionUtils;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class InternalNICView extends JPanel implements HardwareViewInterface, ActionListener
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

	private InternalNetworksCard IntNIC;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 * @param intNIC
	 */
	public InternalNICView(Object obj, InternalNetworksCard intNIC)
	{
		mainObj = obj;
		IntNIC = intNIC;
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

		ImageIcon icon = PrimeMain1.objectImageIcons
				.get(InternalNetworksCard.class);
		JPanel p1 = HardwareEditor.GeneralInfo(intNIC, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());





		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(IntNIC);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel(PrimeMain1.texts
				.getString("hwTabRemoveThisComponentLabel"));

		Button remove = new Button(PrimeMain1.texts
				.getString("hwTabRemoveComponentButtonLabel"));
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
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Hardware object. It uses the {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param NIC
	 *            The Hardware that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(InternalNetworksCard NIC)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[7];


		labels[0] = new JLabel(PrimeMain1.texts
				.getString("intNICviewProducerLabel"));
		labels[0].setToolTipText(PrimeMain1.texts
				.getString("intNICviewProcucerTip"));

		labels[1] = new JLabel(PrimeMain1.texts.getString("intNICviewMACLabel"));
		labels[1]
				.setToolTipText(PrimeMain1.texts.getString("intNICviewMACTip"));

		labels[2] = new JLabel(PrimeMain1.texts
				.getString("intNICviewTypeLabel"));
		labels[2].setToolTipText(PrimeMain1.texts
				.getString("intNICviewTypeTip"));

		labels[3] = new JLabel(PrimeMain1.texts
				.getString("intNICviewSpeedLabel"));
		labels[3].setToolTipText(PrimeMain1.texts
				.getString("intNICviewSpeedTip"));

		labels[4] = new JLabel(PrimeMain1.texts
				.getString("intNICviewProtocolLabel"));
		labels[4].setToolTipText(PrimeMain1.texts
				.getString("intNICviewProtocolTip"));

		labels[5] = new JLabel(PrimeMain1.texts
				.getString("intNICviewSupportedStandarsLabel"));
		labels[5].setToolTipText(PrimeMain1.texts
				.getString("intNICviewSupportedStandarsTip"));

		labels[6] = new JLabel(PrimeMain1.texts
				.getString("intNICviewSupportsIPv6Label"));
		labels[6].setToolTipText(PrimeMain1.texts
				.getString("intNICviewSupportsIPv6Tip"));


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
		MAC = new JTextField(7);
		MAC.setMaximumSize(tfSize);
		MAC.setPreferredSize(tfSize);
		MAC.setText(NIC.getMAC());
		MAC.setToolTipText(labels[1].getToolTipText());

		panel.add(labels[1]);
		panel.add(MAC);


		// The connection type supported by the NIC
		labels[2].setLabelFor(conType);
		String[] conTypeString = { ConnectionUtils.Wired,
				ConnectionUtils.Wireless, ConnectionUtils.Coax,
				ConnectionUtils.Fiber };
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



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
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
			IntNIC.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			IntNIC.setDescription(desc.getText());
		}

		IntNIC.setProducer(producer.getText());

		IntNIC.setMAC(MAC.getText());

		IntNIC.setType(conType.getSelectedItem().toString());


		if ( transferSpeed.getSelectedItem().toString() != "" )
		{
			IntNIC.setSpeed(Integer.parseInt(transferSpeed.getSelectedItem()
					.toString()));
		}
		else
		{
			IntNIC.setSpeed(0);
		}


		IntNIC.setSupportedConnectionInterfaces(protocol.getSelectedItem()
				.toString());

		if ( supStandards.getSelectedIndex() != -1 )
		{
			IntNIC.setSupportedStandards(standars);
		}

		IntNIC.setSupportsIPv6(supIPv6.isSelected());
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
						IntNIC, mainObj.getComponents(), mainObj
								.getComponents().length));

				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain1.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}
				// If no view is returned, then the standard object view is open
				// and that should be updated.
				else if ( PrimeMain1.stdObjView != null )
				{
					PrimeMain1.stdObjView.getSplitView().getHardStdObjView()
							.updateTabInfo();
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


	@Override
	public Hardware getViewHardware()
	{
		return IntNIC;
	}
}
