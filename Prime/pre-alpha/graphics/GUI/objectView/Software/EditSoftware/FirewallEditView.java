package graphics.GUI.objectView.Software.EditSoftware;

import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.GUI.objectView.Software.SoftwareEditView;
import graphics.GUI.objectView.Software.SoftwareEditor;
import graphics.GUI.objectView.Software.EditSoftware.EmailEditView.SharedListSelectionHandler;

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
import objects.Software;
import software.Firewall;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Firewall Firewall} Software. The panel is made up
 * of 3 JPanel ordered in a column.
 * 
 * The first one contains the name and description of the object. 
 * The second panel contains the specific software options. 
 * The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class FirewallEditView extends JPanel implements SoftwareEditView, ActionListener
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
	
	
	
	private Object mainObj;
	
	private Firewall mainFW;
	
	
	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 * 			The main {@link Object object}.
	 * @param fw
	 * 			The {@link Firewall Firewall} software.
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

		JLabel label = new JLabel("Remove this component from this device");

		Button save = new Button("Remove Component");
		save.addActionListener(this);
		save.setActionCommand("removeComp");

		buttons.add(label);
		buttons.add(save);

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
	 * Creates the JPanel that will contain the {@link Software Software} 
	 * specific options. The layout of the returned panel will 
	 * be {@link SpringLayout}.
	 */
	private JPanel createSpesificInfo(Firewall fw)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[4];
		

		labels[0] = new JLabel("Supported OS");
		labels[0]
				.setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Expiration Date");
		labels[1].setToolTipText("The date that the AV will expire.");

		labels[2] = new JLabel("Activated");
		labels[2].setToolTipText("Whether or not the AV is activated.");

		labels[3] = new JLabel("License");
		labels[3].setToolTipText("The license key for the AV.");


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
		listPane.setMaximumSize(new Dimension(90, 60));
		listPane.setPreferredSize(new Dimension(90, 60));
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
		hasNetworkFirewall.setActionCommand("POP3");
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
		hasStatefulFirewall.setActionCommand("POP3");
		hasStatefulFirewall.addActionListener(this);

		hasNetworkFirewall.setSelected(mainFW.HasStatefulFirewall());

		panel.add(labels[2]);
		panel.add(hasNetworkFirewall);

		// Has application-layer firewall feature
		private JCheckBox hasApplicationFirewall;

		// Has DPI, deep package inspections, firewall feature
		private JCheckBox hasDPI;
		
		
		
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
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
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
