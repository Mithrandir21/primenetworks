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
import software.Webserver;


/**
 * A JPanel that will contain fields and options for a presentation and
 * modification of an {@link Webserver Webserver} Software. The panel is made up
 * of 3 JPanel ordered in a column. The first one contains the name and
 * description of the object. The second panel contains the specific software
 * options. The third panel contains the button that can remove the software
 * from the computer.
 * 
 * @author Bahram Malaekeh
 */
public class WebserverEditView extends JPanel implements SoftwareView,
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

	// Supports Virtual hosting feature
	private JCheckBox hasVirtualHosting;

	// Supports HTTP compression
	private JCheckBox hasCompression;



	// DIFFERENT SECURITY FEATURES
	// Supports basic access authentication
	private JCheckBox supportsBasic;

	// Supports digest access authentication
	private JCheckBox supportsDigest;


	// SUPPORT FOR HTTPS
	// Supports SSL, Secure Sockets Layers
	private JCheckBox supportsSSL;

	// Supports TSL, Transport Layer Security
	private JCheckBox supportsTSL;


	// DIFFERENT SUPPORTED FEATURES
	// Supports IPv6
	private JCheckBox supportsIPv6;

	// Supports SSI, Server Side Includes
	private JCheckBox supportsSSI;

	// Supports CGI
	private JCheckBox supportsCGI;

	// Supports SCGI, Simple Common Gateway Interface
	private JCheckBox supportsSCGI;

	// Supports FastCGI
	private JCheckBox supportsFastCGI;

	// Supports JSP
	private JCheckBox supportsJSP;

	// Supports PHP
	private JCheckBox supportsPHP;

	// Supports ASP
	private JCheckBox supportsASP;

	// Supports ASP .net
	private JCheckBox supportsASPnet;



	private Object mainObj;

	private Webserver mainWebSer;

	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param webserver
	 *            The {@link Webserver Webserver} software.
	 */
	public WebserverEditView(Object obj, Webserver webserver)
	{
		mainObj = obj;
		mainWebSer = webserver;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainWebSer, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainWebSer);
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
	 * This method creates and returns a JPanel that contains all the different
	 * settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order all
	 * the different components in the JPanel in grids.
	 * 
	 * @param webserver
	 *            The Software that will be examined and will fill inn the
	 *            fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */
	private JPanel createSpesificInfo(Webserver webserver)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[16];


		labels[0] = new JLabel("Supported OS");
		labels[0]
				.setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Supports Virtual Hosting");
		labels[1]
				.setToolTipText("Whether or not the software supports Virtual hosting feature.");

		labels[2] = new JLabel("Supports HTTP compression");
		labels[2]
				.setToolTipText("Whether or not the software supports HTTP compression.");

		labels[3] = new JLabel("Supports Basic Access");
		labels[3]
				.setToolTipText("Whether or not the software supports basic access authentication.");

		labels[4] = new JLabel("Supports Digest Access");
		labels[4]
				.setToolTipText("Whether or not the software supports digest access authentication.");

		labels[5] = new JLabel("Supports SSL");
		labels[5]
				.setToolTipText("Whether or not the software supports SSL, Secure Sockets Layer.");

		labels[6] = new JLabel("Supports TSL");
		labels[6]
				.setToolTipText("Whether or not the software supports TSL, Transport Layer Security.");

		labels[7] = new JLabel("Supports IPv6");
		labels[7].setToolTipText("Whether or not the software supports IPv6.");

		labels[8] = new JLabel("Supports SSI");
		labels[8]
				.setToolTipText("Whether or not the software supports SSI, Server Side Includes.");

		labels[9] = new JLabel("Supports CGI");
		labels[9]
				.setToolTipText("Whether or not the software supports CGI, Common Gateway Interface.");

		labels[10] = new JLabel("Supports SCGI");
		labels[10]
				.setToolTipText("Whether or not the software supports SCGI, Simple Common Gateway Interface.");

		labels[11] = new JLabel("Supports FastCGI");
		labels[11]
				.setToolTipText("Whether or not the software supports FastCGI.");

		labels[12] = new JLabel("Supports JSP");
		labels[12].setToolTipText("Whether or not the software supports JSP.");

		labels[13] = new JLabel("Supports PHP");
		labels[13].setToolTipText("Whether or not the software supports PHP.");

		labels[14] = new JLabel("Supports ASP");
		labels[14].setToolTipText("Whether or not the software supports ASP.");

		labels[15] = new JLabel("Supports ASP .Net");
		labels[15]
				.setToolTipText("Whether or not the software supports ASP .Net.");


		Dimension tfSize = new Dimension(90, 20);

		// --------------------------------------------------------------

		// The supported operating systems by the Webserver software.
		labels[0].setLabelFor(supportedOS);
		String[] listData = { "Windows 98", "Windows 2000", "Windows XP",
				"Windows Vista", "Linux", "Novell" };
		supportedOS = new JList(listData);
		ListSelectionModel listSelectionModel = supportedOS.getSelectionModel();
		listSelectionModel
				.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(supportedOS);
		listPane.setMaximumSize(new Dimension(130, 60));
		listPane.setPreferredSize(new Dimension(130, 60));
		listSelectionModel
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		if ( mainWebSer.getSupportedOperatingSystems() != null )
		{
			if ( mainWebSer.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainWebSer
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);

		// --------------------------------------------------------------

		// Whether or not the software supports Virtual hosting feature
		labels[1].setLabelFor(hasVirtualHosting);
		hasVirtualHosting = new JCheckBox();
		hasVirtualHosting.setMaximumSize(tfSize);
		hasVirtualHosting.setPreferredSize(tfSize);
		hasVirtualHosting.setToolTipText(labels[1].getToolTipText());
		hasVirtualHosting.setActionCommand("HasVirtualHosting");
		hasVirtualHosting.addActionListener(this);

		hasVirtualHosting.setSelected(mainWebSer.HasVirtualHosting());

		panel.add(labels[1]);
		panel.add(hasVirtualHosting);

		// --------------------------------------------------------------

		// Whether or not the software supports HTTP compression
		labels[2].setLabelFor(hasCompression);
		hasCompression = new JCheckBox();
		hasCompression.setMaximumSize(tfSize);
		hasCompression.setPreferredSize(tfSize);
		hasCompression.setToolTipText(labels[2].getToolTipText());
		hasCompression.setActionCommand("HasCompression");
		hasCompression.addActionListener(this);

		hasCompression.setSelected(mainWebSer.HasCompression());

		panel.add(labels[2]);
		panel.add(hasCompression);

		// --------------------------------------------------------------

		// Whether or not the software supports basic access authentication
		labels[3].setLabelFor(supportsBasic);
		supportsBasic = new JCheckBox();
		supportsBasic.setMaximumSize(tfSize);
		supportsBasic.setPreferredSize(tfSize);
		supportsBasic.setToolTipText(labels[3].getToolTipText());
		supportsBasic.setActionCommand("SupportsBasic");
		supportsBasic.addActionListener(this);

		supportsBasic.setSelected(mainWebSer.supportsBasic());

		panel.add(labels[3]);
		panel.add(supportsBasic);

		// --------------------------------------------------------------

		// Whether or not the software supports basic access authentication
		labels[4].setLabelFor(supportsDigest);
		supportsDigest = new JCheckBox();
		supportsDigest.setMaximumSize(tfSize);
		supportsDigest.setPreferredSize(tfSize);
		supportsDigest.setToolTipText(labels[4].getToolTipText());
		supportsDigest.setActionCommand("SupportsDigest");
		supportsDigest.addActionListener(this);

		supportsDigest.setSelected(mainWebSer.supportsDigest());

		panel.add(labels[4]);
		panel.add(supportsDigest);

		// --------------------------------------------------------------

		// Whether or not the software supports SSL
		labels[5].setLabelFor(supportsSSL);
		supportsSSL = new JCheckBox();
		supportsSSL.setMaximumSize(tfSize);
		supportsSSL.setPreferredSize(tfSize);
		supportsSSL.setToolTipText(labels[5].getToolTipText());
		supportsSSL.setActionCommand("SupportsSSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainWebSer.supportsSSL());

		panel.add(labels[5]);
		panel.add(supportsSSL);

		// --------------------------------------------------------------

		// Whether or not the software supports TSL
		labels[6].setLabelFor(supportsTSL);
		supportsTSL = new JCheckBox();
		supportsTSL.setMaximumSize(tfSize);
		supportsTSL.setPreferredSize(tfSize);
		supportsTSL.setToolTipText(labels[6].getToolTipText());
		supportsTSL.setActionCommand("SupportsTSL");
		supportsTSL.addActionListener(this);

		supportsTSL.setSelected(mainWebSer.supportsTSL());

		panel.add(labels[6]);
		panel.add(supportsTSL);

		// --------------------------------------------------------------

		// Whether or not the software supports IPv6
		labels[7].setLabelFor(supportsIPv6);
		supportsIPv6 = new JCheckBox();
		supportsIPv6.setMaximumSize(tfSize);
		supportsIPv6.setPreferredSize(tfSize);
		supportsIPv6.setToolTipText(labels[7].getToolTipText());
		supportsIPv6.setActionCommand("SupportsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainWebSer.supportsIPv6());

		panel.add(labels[7]);
		panel.add(supportsIPv6);

		// --------------------------------------------------------------

		// Whether or not the software supports SSI, Server Side Includes
		labels[8].setLabelFor(supportsIPv6);
		supportsSSI = new JCheckBox();
		supportsSSI.setMaximumSize(tfSize);
		supportsSSI.setPreferredSize(tfSize);
		supportsSSI.setToolTipText(labels[8].getToolTipText());
		supportsSSI.setActionCommand("SupportsSSI");
		supportsSSI.addActionListener(this);

		supportsSSI.setSelected(mainWebSer.supportsSSI());

		panel.add(labels[8]);
		panel.add(supportsSSI);

		// --------------------------------------------------------------

		// Whether or not the software supports SSI, Server Side Includes
		labels[9].setLabelFor(supportsCGI);
		supportsCGI = new JCheckBox();
		supportsCGI.setMaximumSize(tfSize);
		supportsCGI.setPreferredSize(tfSize);
		supportsCGI.setToolTipText(labels[9].getToolTipText());
		supportsCGI.setActionCommand("SupportsCGI");
		supportsCGI.addActionListener(this);

		supportsCGI.setSelected(mainWebSer.supportsCGI());

		panel.add(labels[9]);
		panel.add(supportsCGI);

		// --------------------------------------------------------------

		// Whether or not the software supports SCGI, Simple Common Gateway
		// Interface
		labels[10].setLabelFor(supportsSCGI);
		supportsSCGI = new JCheckBox();
		supportsSCGI.setMaximumSize(tfSize);
		supportsSCGI.setPreferredSize(tfSize);
		supportsSCGI.setToolTipText(labels[10].getToolTipText());
		supportsSCGI.setActionCommand("SupportsSCGI");
		supportsSCGI.addActionListener(this);

		supportsSCGI.setSelected(mainWebSer.supportsSCGI());

		panel.add(labels[10]);
		panel.add(supportsSCGI);

		// --------------------------------------------------------------

		// Whether or not the software supports FastCGI
		labels[11].setLabelFor(supportsFastCGI);
		supportsFastCGI = new JCheckBox();
		supportsFastCGI.setMaximumSize(tfSize);
		supportsFastCGI.setPreferredSize(tfSize);
		supportsFastCGI.setToolTipText(labels[11].getToolTipText());
		supportsFastCGI.setActionCommand("SupportsFastCGI");
		supportsFastCGI.addActionListener(this);

		supportsFastCGI.setSelected(mainWebSer.supportsFastCGI());

		panel.add(labels[11]);
		panel.add(supportsFastCGI);

		// --------------------------------------------------------------

		// Whether or not the software supports JSP
		labels[12].setLabelFor(supportsJSP);
		supportsJSP = new JCheckBox();
		supportsJSP.setMaximumSize(tfSize);
		supportsJSP.setPreferredSize(tfSize);
		supportsJSP.setToolTipText(labels[12].getToolTipText());
		supportsJSP.setActionCommand("SupportsJSP");
		supportsJSP.addActionListener(this);

		supportsJSP.setSelected(mainWebSer.supportsJSP());

		panel.add(labels[12]);
		panel.add(supportsJSP);

		// --------------------------------------------------------------

		// Whether or not the software supports JSP
		labels[13].setLabelFor(supportsPHP);
		supportsPHP = new JCheckBox();
		supportsPHP.setMaximumSize(tfSize);
		supportsPHP.setPreferredSize(tfSize);
		supportsPHP.setToolTipText(labels[13].getToolTipText());
		supportsPHP.setActionCommand("SupportsPHP");
		supportsPHP.addActionListener(this);

		supportsPHP.setSelected(mainWebSer.supportsPHP());

		panel.add(labels[13]);
		panel.add(supportsPHP);

		// --------------------------------------------------------------

		// Whether or not the software supports ASP
		labels[14].setLabelFor(supportsASP);
		supportsASP = new JCheckBox();
		supportsASP.setMaximumSize(tfSize);
		supportsASP.setPreferredSize(tfSize);
		supportsASP.setToolTipText(labels[14].getToolTipText());
		supportsASP.setActionCommand("SupportsASP");
		supportsASP.addActionListener(this);

		supportsASP.setSelected(mainWebSer.supportsASP());

		panel.add(labels[14]);
		panel.add(supportsASP);

		// --------------------------------------------------------------

		// Whether or not the software supports ASP .net
		labels[14].setLabelFor(supportsASPnet);
		supportsASPnet = new JCheckBox();
		supportsASPnet.setMaximumSize(tfSize);
		supportsASPnet.setPreferredSize(tfSize);
		supportsASPnet.setToolTipText(labels[14].getToolTipText());
		supportsASPnet.setActionCommand("SupportsASPnet");
		supportsASPnet.addActionListener(this);

		supportsASPnet.setSelected(mainWebSer.supportsASPnet());

		panel.add(labels[15]);
		panel.add(supportsASPnet);

		// --------------------------------------------------------------


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel,
				panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad


		return panel;
	}



	/*
	 * (non-Javadoc)
	 * @see graphics.GUI.objectView.Software.SoftwareEditView#save()
	 */
	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainWebSer.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainWebSer.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainWebSer.setSupportedOperatingSystems(OSs);
		}


		mainWebSer.setHasVirtualHosting(hasVirtualHosting.isSelected());

		mainWebSer.setHasCompression(hasCompression.isSelected());

		mainWebSer.setSupportsBasic(supportsBasic.isSelected());

		mainWebSer.setSupportsDigest(supportsDigest.isSelected());

		mainWebSer.setSupportsSSL(supportsSSL.isSelected());

		mainWebSer.setSupportsTSL(supportsTSL.isSelected());

		mainWebSer.setSupportsIPv6(supportsIPv6.isSelected());

		mainWebSer.setSupportsSSI(supportsSSI.isSelected());

		mainWebSer.setSupportsCGI(supportsCGI.isSelected());

		mainWebSer.setSupportsSCGI(supportsSCGI.isSelected());

		mainWebSer.setSupportsFastCGI(supportsFastCGI.isSelected());

		mainWebSer.setSupportsJSP(supportsJSP.isSelected());

		mainWebSer.setSupportsPHP(supportsPHP.isSelected());

		mainWebSer.setSupportsASP(supportsASP.isSelected());

		mainWebSer.setSupportsASPnet(supportsASPnet.isSelected());
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() instanceof JCheckBox )
		{
			JCheckBox box = (JCheckBox) e.getSource();

			String command = box.getActionCommand();

			if ( command.equals("HasVirtualHosting") )
			{

			}
			else if ( command.equals("HasCompression") )
			{

			}
			else if ( command.equals("SupportsBasic") )
			{

			}
			else if ( command.equals("SupportsDigest") )
			{

			}
			else if ( command.equals("SupportsSSL") )
			{

			}
			else if ( command.equals("SupportsTSL") )
			{

			}
			else if ( command.equals("SupportsIPv6") )
			{

			}
			else if ( command.equals("SupportsSSI") )
			{

			}
			else if ( command.equals("SupportsCGI") )
			{

			}
			else if ( command.equals("SupportsSCGI") )
			{

			}
			else if ( command.equals("SupportsFastCGI") )
			{

			}
			else if ( command.equals("SupportsJSP") )
			{

			}
			else if ( command.equals("supportsPHP") )
			{

			}
			else if ( command.equals("SupportsASP") )
			{

			}
			else if ( command.equals("SupportsASPnet") )
			{

			}
		}
	}


	/**
	 * Handles the selections that are made in the "Supported Operating Systems"
	 * JList.
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
			int[] indexes = supportedOS.getSelectedIndices();

			if ( indexes.length == 0 )
			{
				OSs = null;
			}
			else
			{
				// Creates an array of strings with the length of the array with
				// the selected indices.
				OSs = new String[indexes.length];

				// Find out which indexes are selected.
				for ( int i = 0; i < indexes.length; i++ )
				{
					OSs[i] = (String) supportedOS.getSelectedValues()[i];
				}
			}
		}
	}


}
