package graphics.GUI.objectView.Software.EditSoftware.EditViews;


import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

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

import objects.Object;
import objects.softwareObjects.Firewall;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Firewall Firewall} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class FirewallEditView extends JPanel implements SoftwareView,
		ActionListener
{
	// The name of the software object
	JTextField name = new JTextField(25);

	// The description of the software object.
	JTextArea desc = new JTextArea(3, 40);

	// Supported Operating systems
	private JList supportedOS;

	// List of operating systems
	private String[] OSs;

	// Has network-layer firewall feature
	private JCheckBox hasNetworkFirewall;

	// Has stateful firewall feature
	private JCheckBox hasStatefulFirewall;

	// Has application-layer firewall feature
	private JCheckBox hasApplicationFirewall;

	// Has DPI, deep package inspections, firewall feature
	private JCheckBox hasDPI;



	// NON-FIREWALLING FEATURE
	// Has proxy feature
	private JCheckBox hasProxy;

	// Has NAT feature
	private JCheckBox hasNAT;

	// Has VPN feature
	private JCheckBox hasVPN;

	// Has antivirus feature
	private JCheckBox hasAntivirus;

	// Has IDS, Intrusion Detection System, feature
	private JCheckBox hasIDS;

	// DIFFERENT SUPPORT FEATURES
	// Supports Modularity, third-party modules to extend functionality
	private JCheckBox supportsModularity;

	// Supports IP version 6
	private JCheckBox supportsIPv6;

	// Supports TTL, Transparent to traceroute
	private JCheckBox supportsTTL;

	// Supports RWA, Reject-with-answer
	private JCheckBox supportsRWA;

	// Supports a DMZ, de-militarized zone
	private JCheckBox supportsDMZ;

	// Supports ToDFilter, Time of day filter
	private JCheckBox supportsToD;

	// Supports forwarding
	private JCheckBox supportsForwarding;

	// Supports port forwarding
	private JCheckBox supportsPortForwarding;

	// Supports QoS, quality of service
	private JCheckBox supportsQos;

	// Supports TP, tarpit
	private JCheckBox supportsTarpit;




	private Object mainObj;

	private Firewall mainFW;


	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param fw
	 *            The {@link Firewall Firewall} software.
	 */
	public FirewallEditView(Object obj, Firewall fw)
	{
		mainObj = obj;
		mainFW = fw;
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

		ImageIcon icon = ImageLocator.getImageIconObject("CPU");
		JPanel p1 = SoftwareEditor.GeneralInfo(mainFW, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainFW);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);



		JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		buttons.setBorder(BorderFactory.createEtchedBorder());

		JLabel label = new JLabel("Remove this component from this software");

		Button remove = new Button("Remove Software");
		remove.addActionListener(this);
		remove.setActionCommand("removeSoft");

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
	 * different settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order
	 * all the different components in the JPanel in grids.
	 * 
	 * @param fw The Software that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */	
	private JPanel createSpesificInfo(Firewall fw)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[19];


		labels[0] = new JLabel("Supported OS");
		labels[0]
				.setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Network Firewall");
		labels[1]
				.setToolTipText("Whether or not the software supports network firewall.");

		labels[2] = new JLabel("Stateful Firewall");
		labels[2]
				.setToolTipText("Whether or not the software supports stateful firewall.");

		labels[3] = new JLabel("DPI");
		labels[3]
				.setToolTipText("Whether or not the software supports DPI, Deep package inspection.");

		labels[4] = new JLabel("Proxy");
		labels[4]
				.setToolTipText("Whether or not the software has proxy feature.");

		labels[5] = new JLabel("NAT");
		labels[5].setToolTipText("Whether or not the software has NAT feature");

		labels[6] = new JLabel("VPN");
		labels[6].setToolTipText("Whether or not the software has VPN feature");

		labels[7] = new JLabel("Antivirus");
		labels[7]
				.setToolTipText("Whether or not the software has antivirus feature");

		labels[8] = new JLabel("IDS");
		labels[8]
				.setToolTipText("Whether or not the software has IDS, Intrusion Detection System, feature.");

		labels[9] = new JLabel("Modularity");
		labels[9]
				.setToolTipText("Whether or not the software supports Modularity, third-party modules to extend functionality.");

		labels[10] = new JLabel("IPv6");
		labels[10]
				.setToolTipText("Whether or not the software supports IP version 6.");

		labels[11] = new JLabel("TTL");
		labels[11]
				.setToolTipText("Whether or not the software supports TTL, Transparent to traceroute.");

		labels[12] = new JLabel("RWA");
		labels[12]
				.setToolTipText("Whether or not the software supports RWA, Reject-with-answer.");

		labels[13] = new JLabel("DMZ");
		labels[13]
				.setToolTipText("Whether or not the software supports a DMZ, de-militarized zone.");

		labels[14] = new JLabel("ToD Filter");
		labels[14]
				.setToolTipText("Whether or not the software supports ToDFilter, Time of day filter.");

		labels[15] = new JLabel("Forwarding");
		labels[15]
				.setToolTipText("Whether or not the software supports forwarding.");

		labels[16] = new JLabel("Port Forwarding");
		labels[16]
				.setToolTipText("Whether or not the software supports port forwarding.");

		labels[17] = new JLabel("QoS");
		labels[17]
				.setToolTipText("Whether or not the software supports QoS, quality of service.");

		labels[18] = new JLabel("Tarpit");
		labels[18]
				.setToolTipText("Whether or not the software supports TP, tarpit.");


		Dimension tfSize = new Dimension(90, 20);


		// The supported operating systems by the Email software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(160, 60));
		listPane.setPreferredSize(new Dimension(160, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainFW.getSupportedOperatingSystems() != null )
		{
			if ( mainFW.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainFW
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);


		// Whether or not the software supports network firewall
		labels[1].setLabelFor(hasNetworkFirewall);
		hasNetworkFirewall = new JCheckBox();
		hasNetworkFirewall.setMaximumSize(tfSize);
		hasNetworkFirewall.setPreferredSize(tfSize);
		hasNetworkFirewall.setToolTipText(labels[1].getToolTipText());
		hasNetworkFirewall.setActionCommand("NetworkFW");
		hasNetworkFirewall.addActionListener(this);

		hasNetworkFirewall.setSelected(mainFW.HasNetworkFirewall());

		panel.add(labels[1]);
		panel.add(hasNetworkFirewall);


		// Whether or not the software supports stateful firewall
		labels[2].setLabelFor(hasStatefulFirewall);
		hasStatefulFirewall = new JCheckBox();
		hasStatefulFirewall.setMaximumSize(tfSize);
		hasStatefulFirewall.setPreferredSize(tfSize);
		hasStatefulFirewall.setToolTipText(labels[2].getToolTipText());
		hasStatefulFirewall.setActionCommand("StatefulFW");
		hasStatefulFirewall.addActionListener(this);

		hasStatefulFirewall.setSelected(mainFW.HasStatefulFirewall());

		panel.add(labels[2]);
		panel.add(hasStatefulFirewall);



		// Whether or not the software supports DPI, Deep package inspection.
		labels[3].setLabelFor(hasDPI);
		hasDPI = new JCheckBox();
		hasDPI.setMaximumSize(tfSize);
		hasDPI.setPreferredSize(tfSize);
		hasDPI.setToolTipText(labels[3].getToolTipText());
		hasDPI.setActionCommand("HasDPI");
		hasDPI.addActionListener(this);

		hasDPI.setSelected(mainFW.HasDPI());

		panel.add(labels[3]);
		panel.add(hasDPI);



		// Whether or not the software has proxy feature
		labels[4].setLabelFor(hasProxy);
		hasProxy = new JCheckBox();
		hasProxy.setMaximumSize(tfSize);
		hasProxy.setPreferredSize(tfSize);
		hasProxy.setToolTipText(labels[4].getToolTipText());
		hasProxy.setActionCommand("HasProxy");
		hasProxy.addActionListener(this);

		hasProxy.setSelected(mainFW.HasProxy());

		panel.add(labels[4]);
		panel.add(hasProxy);


		// Whether or not the software has NAT feature
		labels[5].setLabelFor(hasNAT);
		hasNAT = new JCheckBox();
		hasNAT.setMaximumSize(tfSize);
		hasNAT.setPreferredSize(tfSize);
		hasNAT.setToolTipText(labels[5].getToolTipText());
		hasNAT.setActionCommand("HasNAT");
		hasNAT.addActionListener(this);

		hasNAT.setSelected(mainFW.HasNAT());

		panel.add(labels[5]);
		panel.add(hasNAT);


		// Whether or not the software has VPN feature
		labels[6].setLabelFor(hasVPN);
		hasVPN = new JCheckBox();
		hasVPN.setMaximumSize(tfSize);
		hasVPN.setPreferredSize(tfSize);
		hasVPN.setToolTipText(labels[6].getToolTipText());
		hasVPN.setActionCommand("HasVPN");
		hasVPN.addActionListener(this);

		hasVPN.setSelected(mainFW.HasVPN());

		panel.add(labels[6]);
		panel.add(hasVPN);


		// Whether or not the software has antivirus feature
		labels[7].setLabelFor(hasAntivirus);
		hasAntivirus = new JCheckBox();
		hasAntivirus.setMaximumSize(tfSize);
		hasAntivirus.setPreferredSize(tfSize);
		hasAntivirus.setToolTipText(labels[7].getToolTipText());
		hasAntivirus.setActionCommand("HasAV");
		hasAntivirus.addActionListener(this);

		hasAntivirus.setSelected(mainFW.HasAntivirus());

		panel.add(labels[7]);
		panel.add(hasAntivirus);


		// Whether or not the software has IDS, Intrusion Detection System,
		// feature
		labels[8].setLabelFor(hasIDS);
		hasIDS = new JCheckBox();
		hasIDS.setMaximumSize(tfSize);
		hasIDS.setPreferredSize(tfSize);
		hasIDS.setToolTipText(labels[8].getToolTipText());
		hasIDS.setActionCommand("HasIDS");
		hasIDS.addActionListener(this);

		hasIDS.setSelected(mainFW.HasIDS());

		panel.add(labels[8]);
		panel.add(hasIDS);



		// DIFFERENT SUPPORT FEATURES

		// Whether or not the software supports Modularity, third-party modules
		// to extend functionality
		labels[9].setLabelFor(supportsModularity);
		supportsModularity = new JCheckBox();
		supportsModularity.setMaximumSize(tfSize);
		supportsModularity.setPreferredSize(tfSize);
		supportsModularity.setToolTipText(labels[9].getToolTipText());
		supportsModularity.setActionCommand("SupportsModularity");
		supportsModularity.addActionListener(this);

		supportsModularity.setSelected(mainFW.SupportsModularity());

		panel.add(labels[9]);
		panel.add(supportsModularity);


		// Whether or not the software supports IP version 6
		labels[10].setLabelFor(supportsIPv6);
		supportsIPv6 = new JCheckBox();
		supportsIPv6.setMaximumSize(tfSize);
		supportsIPv6.setPreferredSize(tfSize);
		supportsIPv6.setToolTipText(labels[10].getToolTipText());
		supportsIPv6.setActionCommand("SupporsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainFW.SupportsIPv6());

		panel.add(labels[10]);
		panel.add(supportsIPv6);


		// Whether or not the software supports TTL, Transparent to traceroute
		labels[11].setLabelFor(supportsTTL);
		supportsTTL = new JCheckBox();
		supportsTTL.setMaximumSize(tfSize);
		supportsTTL.setPreferredSize(tfSize);
		supportsTTL.setToolTipText(labels[11].getToolTipText());
		supportsTTL.setActionCommand("SupportsTTL");
		supportsTTL.addActionListener(this);

		supportsTTL.setSelected(mainFW.SupportsTTL());

		panel.add(labels[11]);
		panel.add(supportsTTL);


		// Whether or not the software supports RWA, Reject-with-answer
		labels[12].setLabelFor(supportsRWA);
		supportsRWA = new JCheckBox();
		supportsRWA.setMaximumSize(tfSize);
		supportsRWA.setPreferredSize(tfSize);
		supportsRWA.setToolTipText(labels[12].getToolTipText());
		supportsRWA.setActionCommand("SupportsRWA");
		supportsRWA.addActionListener(this);

		supportsRWA.setSelected(mainFW.SupportsRWA());

		panel.add(labels[12]);
		panel.add(supportsRWA);


		// Whether or not the software supports a DMZ, de-militarized zone
		labels[13].setLabelFor(supportsDMZ);
		supportsDMZ = new JCheckBox();
		supportsDMZ.setMaximumSize(tfSize);
		supportsDMZ.setPreferredSize(tfSize);
		supportsDMZ.setToolTipText(labels[13].getToolTipText());
		supportsDMZ.setActionCommand("SupportsDMZ");
		supportsDMZ.addActionListener(this);

		supportsDMZ.setSelected(mainFW.SupportsDMZ());

		panel.add(labels[13]);
		panel.add(supportsDMZ);


		// Whether or not the software supports ToDFilter, Time of day filter
		labels[14].setLabelFor(supportsToD);
		supportsToD = new JCheckBox();
		supportsToD.setMaximumSize(tfSize);
		supportsToD.setPreferredSize(tfSize);
		supportsToD.setToolTipText(labels[14].getToolTipText());
		supportsToD.setActionCommand("SupportsToD");
		supportsToD.addActionListener(this);

		supportsToD.setSelected(mainFW.SupportsToD());

		panel.add(labels[14]);
		panel.add(supportsToD);


		// Whether or not the software supports forwarding
		labels[15].setLabelFor(supportsForwarding);
		supportsForwarding = new JCheckBox();
		supportsForwarding.setMaximumSize(tfSize);
		supportsForwarding.setPreferredSize(tfSize);
		supportsForwarding.setToolTipText(labels[15].getToolTipText());
		supportsForwarding.setActionCommand("SupportsForwarding");
		supportsForwarding.addActionListener(this);

		supportsForwarding.setSelected(mainFW.SupportsForwarding());

		panel.add(labels[15]);
		panel.add(supportsForwarding);


		// Whether or not the software supports port forwarding
		labels[16].setLabelFor(supportsPortForwarding);
		supportsPortForwarding = new JCheckBox();
		supportsPortForwarding.setMaximumSize(tfSize);
		supportsPortForwarding.setPreferredSize(tfSize);
		supportsPortForwarding.setToolTipText(labels[16].getToolTipText());
		supportsPortForwarding.setActionCommand("SupportsPortForwarding");
		supportsPortForwarding.addActionListener(this);

		supportsPortForwarding.setSelected(mainFW.SupportsPortForwarding());

		panel.add(labels[16]);
		panel.add(supportsPortForwarding);


		// Whether or not the software supports QoS, quality of service
		labels[17].setLabelFor(supportsQos);
		supportsQos = new JCheckBox();
		supportsQos.setMaximumSize(tfSize);
		supportsQos.setPreferredSize(tfSize);
		supportsQos.setToolTipText(labels[17].getToolTipText());
		supportsQos.setActionCommand("SupportsQoS");
		supportsQos.addActionListener(this);

		supportsQos.setSelected(mainFW.SupportsQos());

		panel.add(labels[17]);
		panel.add(supportsQos);


		// Whether or not the software supports TP, tarpit
		labels[18].setLabelFor(supportsTarpit);
		supportsTarpit = new JCheckBox();
		supportsTarpit.setMaximumSize(tfSize);
		supportsTarpit.setPreferredSize(tfSize);
		supportsTarpit.setToolTipText(labels[18].getToolTipText());
		supportsTarpit.setActionCommand("SupportsTP");
		supportsTarpit.addActionListener(this);

		supportsTarpit.setSelected(mainFW.SupportsTarpit());

		panel.add(labels[18]);
		panel.add(supportsTarpit);



		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad



		return panel;
	}



	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainFW.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainFW.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainFW.setSupportedOperatingSystems(OSs);
		}

		mainFW.setHasNetworkFirewall(hasNetworkFirewall.isSelected());

		mainFW.setHasStatefulFirewall(hasStatefulFirewall.isSelected());

		mainFW.setHasApplicationFirewall(hasApplicationFirewall.isSelected());

		mainFW.setHasDPI(hasDPI.isSelected());


		mainFW.setHasProxy(hasProxy.isSelected());

		mainFW.setHasNAT(hasNAT.isSelected());

		mainFW.setHasVPN(hasVPN.isSelected());

		mainFW.setHasAntivirus(hasAntivirus.isSelected());

		mainFW.setHasIDS(hasIDS.isSelected());


		mainFW.setSupportsModularity(supportsModularity.isSelected());

		mainFW.setSupportsIPv6(supportsIPv6.isSelected());

		mainFW.setSupportsTTL(supportsTTL.isSelected());

		mainFW.setSupportsRWA(supportsRWA.isSelected());

		mainFW.setSupportsDMZ(supportsDMZ.isSelected());

		mainFW.setSupportsToD(supportsToD.isSelected());

		mainFW.setSupportsForwarding(supportsForwarding.isSelected());

		mainFW.setSupportsPortForwarding(supportsPortForwarding.isSelected());

		mainFW.setSupportsQos(supportsQos.isSelected());

		mainFW.setSupportsTarpit(supportsTarpit.isSelected());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JCheckBox )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("NetworkFW") )
			{

			}
			else if ( command.equals("StatefulFW") )
			{

			}
			else if ( command.equals("HasDPI") )
			{

			}
			else if ( command.equals("HasProxy") )
			{

			}
			else if ( command.equals("HasNAT") )
			{

			}
			else if ( command.equals("HasVPN") )
			{

			}
			else if ( command.equals("HasAV") )
			{

			}
			else if ( command.equals("HasIDS") )
			{

			}
			else if ( command.equals("SupportsModularity") )
			{

			}
			else if ( command.equals("SupporsIPv6") )
			{

			}
			else if ( command.equals("SupportsTTL") )
			{

			}
			else if ( command.equals("SupportsTTL") )
			{

			}
			else if ( command.equals("SupportsRWA") )
			{

			}
			else if ( command.equals("SupportsDMZ") )
			{

			}
			else if ( command.equals("SupportsToD") )
			{

			}
			else if ( command.equals("SupportsForwarding") )
			{

			}
			else if ( command.equals("SupportsPortForwarding") )
			{

			}
			else if ( command.equals("SupportsQoS") )
			{

			}
			else if ( command.equals("SupportsTP") )
			{

			}
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems" JList.
	 *  
	 */
	private class SharedListSelectionHandler implements ListSelectionListener
	{
		/*
		 * (non-Javadoc)
		 * @see
		 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.
		 * event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e)
		{
			int[] indeces = supportedOS.getSelectedIndices();

			if ( indeces.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indeces.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indeces.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}

}
