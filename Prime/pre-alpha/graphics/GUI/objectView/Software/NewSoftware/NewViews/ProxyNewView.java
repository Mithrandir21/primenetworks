/**
 * 
 */
package graphics.GUI.objectView.Software.NewSoftware.NewViews;

import graphics.GraphicalFunctions;
import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.objectView.Software.SoftwareView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareEditor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import managment.SoftwareManagment;

import objects.Object;
import objects.Software;
import software.Proxy;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ProxyNewView extends JFrame implements SoftwareView,
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

	// Has caching feature
	private JCheckBox caching;

	// Has Web proxy feature
	private JCheckBox webProxy;

	// Has Anonymizing proxy feature
	private JCheckBox anonymizingProxy;

	// Has transparent proxy feature
	private JCheckBox transparentProxy;

	// Has reverse proxy feature
	private JCheckBox reverseProxy;


	// DIFFERENT SUPPORTED FEATURES
	// Supports IP version 6
	private JCheckBox supportsIPv6;

	// Supports SSL
	private JCheckBox supportsSSL;

	// Supports TLS
	private JCheckBox supportsTSL;

	// Supports HTTPS
	private JCheckBox supportsHTTPS;



	private Object mainObj;

	private Proxy mainProxy;

	/**
	 * Constructor for the software view.
	 * 
	 * @param obj
	 *            The main {@link Object object}.
	 * @param proxy
	 *            The {@link Proxy Proxy} software.
	 */
	public ProxyNewView(Object obj, Proxy proxy)
	{
		super("New Backup");
		
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));

		mainObj = obj;
		mainProxy = proxy;
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
		JPanel p1 = SoftwareEditor.GeneralInfo(mainProxy, icon, name, desc);
		p1.setBorder(BorderFactory.createEtchedBorder());


		this.add(p1, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 0, 10);

		JPanel p2 = createSpesificInfo(mainProxy);
		p2.setBorder(BorderFactory.createEtchedBorder());

		this.add(p2, c);


		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.01;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel buttons = createButtons();
		buttons.setBorder(BorderFactory.createEtchedBorder());

		this.add(buttons, c);
		


		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}

	
	/**
	 * This method creates and returns a JPanel that contains all the
	 * different settings of the given Software object. It uses the
	 * {@link graphics.GraphicalFunctions.make6xGrid make6xGrid} to order
	 * all the different components in the JPanel in grids.
	 * 
	 * @param proxy The Software that will be examined and will fill inn the fields.
	 * @return A JPanel that contains fields to set the given objects settings.
	 */	
	private JPanel createSpesificInfo(Proxy proxy)
	{
		JPanel panel = new JPanel(new SpringLayout());
		JLabel[] labels = new JLabel[10];


		labels[0] = new JLabel("Supported OS");
		labels[0]
				.setToolTipText("The supported Operating Systems by the software.");

		labels[1] = new JLabel("Chaching");
		labels[1]
				.setToolTipText("Whether or not the software has caching feature.");

		labels[2] = new JLabel("Proxy");
		labels[2]
				.setToolTipText("Whether or not the software has Web proxy feature.");

		labels[3] = new JLabel("Anonymizing");
		labels[3]
				.setToolTipText("Whether or not the software has Anonymizing proxy feature.");

		labels[4] = new JLabel("Transparent Proxy");
		labels[4]
				.setToolTipText("Whether or not the software has transparent proxy feature.");

		labels[5] = new JLabel("Reverse Proxy");
		labels[5]
				.setToolTipText("Whether or not the software has reverse proxy feature.");

		labels[6] = new JLabel("Supports IPv6");
		labels[6]
				.setToolTipText("Whether or not the software supports IP version 6.");

		labels[7] = new JLabel("Supports SSL");
		labels[7].setToolTipText("Whether or not the software supports SSL.");

		labels[8] = new JLabel("Supports TSL");
		labels[8].setToolTipText("Whether or not the software supports TSL.");

		labels[9] = new JLabel("Supports HTTPS");
		labels[9].setToolTipText("Whether or not the software supports HTTPS.");


		Dimension tfSize = new Dimension(90, 20);



		// The supported operating systems by the Proxy software.
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

		if ( mainProxy.getSupportedOperatingSystems() != null )
		{
			if ( mainProxy.getSupportedOperatingSystems().length > 0 )
			{
				listPane.setViewportView(GraphicalFunctions.getIndexInJList(
						supportedOS, listData, mainProxy
								.getSupportedOperatingSystems()));
			}
		}

		panel.add(labels[0]);
		panel.add(listPane);



		// Whether or not the software has caching feature
		labels[1].setLabelFor(caching);
		caching = new JCheckBox();
		caching.setMaximumSize(tfSize);
		caching.setPreferredSize(tfSize);
		caching.setToolTipText(labels[1].getToolTipText());
		caching.setActionCommand("Caching");
		caching.addActionListener(this);

		caching.setSelected(mainProxy.hasCaching());

		panel.add(labels[1]);
		panel.add(caching);


		// Whether or not the software has Web proxy feature
		labels[2].setLabelFor(webProxy);
		webProxy = new JCheckBox();
		webProxy.setMaximumSize(tfSize);
		webProxy.setPreferredSize(tfSize);
		webProxy.setToolTipText(labels[2].getToolTipText());
		webProxy.setActionCommand("WebProxy");
		webProxy.addActionListener(this);

		webProxy.setSelected(mainProxy.hasWebProxy());

		panel.add(labels[2]);
		panel.add(webProxy);



		// Whether or not the software has Anonymizing proxy feature
		labels[3].setLabelFor(anonymizingProxy);
		anonymizingProxy = new JCheckBox();
		anonymizingProxy.setMaximumSize(tfSize);
		anonymizingProxy.setPreferredSize(tfSize);
		anonymizingProxy.setToolTipText(labels[3].getToolTipText());
		anonymizingProxy.setActionCommand("Anonymizing");
		anonymizingProxy.addActionListener(this);

		anonymizingProxy.setSelected(mainProxy.hasAnonymizingProxy());

		panel.add(labels[3]);
		panel.add(anonymizingProxy);



		// Whether or not the software has transparent proxy feature
		labels[4].setLabelFor(transparentProxy);
		transparentProxy = new JCheckBox();
		transparentProxy.setMaximumSize(tfSize);
		transparentProxy.setPreferredSize(tfSize);
		transparentProxy.setToolTipText(labels[4].getToolTipText());
		transparentProxy.setActionCommand("Transparent");
		transparentProxy.addActionListener(this);

		transparentProxy.setSelected(mainProxy.hasTransparentProxy());

		panel.add(labels[4]);
		panel.add(transparentProxy);


		// Whether or not the software has reverse proxy feature
		labels[5].setLabelFor(reverseProxy);
		reverseProxy = new JCheckBox();
		reverseProxy.setMaximumSize(tfSize);
		reverseProxy.setPreferredSize(tfSize);
		reverseProxy.setToolTipText(labels[5].getToolTipText());
		reverseProxy.setActionCommand("ReverseProxy");
		reverseProxy.addActionListener(this);

		reverseProxy.setSelected(mainProxy.hasReverseProxy());

		panel.add(labels[5]);
		panel.add(reverseProxy);


		// Whether or not the software supports IP version 6
		labels[6].setLabelFor(supportsIPv6);
		supportsIPv6 = new JCheckBox();
		supportsIPv6.setMaximumSize(tfSize);
		supportsIPv6.setPreferredSize(tfSize);
		supportsIPv6.setToolTipText(labels[6].getToolTipText());
		supportsIPv6.setActionCommand("SupportsIPv6");
		supportsIPv6.addActionListener(this);

		supportsIPv6.setSelected(mainProxy.supportsIPv6());

		panel.add(labels[6]);
		panel.add(supportsIPv6);


		// Whether or not the software supports SSL
		labels[7].setLabelFor(supportsSSL);
		supportsSSL = new JCheckBox();
		supportsSSL.setMaximumSize(tfSize);
		supportsSSL.setPreferredSize(tfSize);
		supportsSSL.setToolTipText(labels[7].getToolTipText());
		supportsSSL.setActionCommand("SupportsSSL");
		supportsSSL.addActionListener(this);

		supportsSSL.setSelected(mainProxy.supportsSSL());

		panel.add(labels[7]);
		panel.add(supportsSSL);


		// Whether or not the software supports TSL
		labels[8].setLabelFor(supportsTSL);
		supportsTSL = new JCheckBox();
		supportsTSL.setMaximumSize(tfSize);
		supportsTSL.setPreferredSize(tfSize);
		supportsTSL.setToolTipText(labels[8].getToolTipText());
		supportsTSL.setActionCommand("SupportsTSL");
		supportsTSL.addActionListener(this);

		supportsTSL.setSelected(mainProxy.supportsTSL());

		panel.add(labels[8]);
		panel.add(supportsTSL);


		// Whether or not the software supports HTTPS
		labels[9].setLabelFor(supportsHTTPS);
		supportsHTTPS = new JCheckBox();
		supportsHTTPS.setMaximumSize(tfSize);
		supportsHTTPS.setPreferredSize(tfSize);
		supportsHTTPS.setToolTipText(labels[9].getToolTipText());
		supportsHTTPS.setActionCommand("SupportsHTTPS");
		supportsHTTPS.addActionListener(this);

		supportsHTTPS.setSelected(mainProxy.supportsHTTPS());

		panel.add(labels[9]);
		panel.add(supportsHTTPS);


		// Lay out the panel.
		graphics.GraphicalFunctions.make6xGrid(panel, panel.getComponentCount(), // rows, cols
				10, 10, // initX, initY
				20, 20); // xPad, yPad

		return panel;
	}
	
	
	
	/**
	 * Creates a JPanel with two buttons that are listened for by actionlisteners.
	 * 
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button cancel = new Button("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(cancel);

		return buttons;
	}
	
	

	@Override
	public void save()
	{
		if ( name.getText() != "" )
		{
			mainProxy.setObjectName(name.getText());
		}

		if ( desc.getText() != "" )
		{
			mainProxy.setDescription(desc.getText());
		}

		if ( supportedOS.getSelectedIndex() != -1 )
		{
			mainProxy.setSupportedOperatingSystems(OSs);
		}

		mainProxy.setSupportsCaching(caching.isSelected());

		mainProxy.setSupportsWebProxy(webProxy.isSelected());

		mainProxy.setSupportsAnonymizingProxy(anonymizingProxy.isSelected());

		mainProxy.setSupportsTransparentProxy(transparentProxy.isSelected());

		mainProxy.setSupportsReverseProxy(reverseProxy.isSelected());

		mainProxy.setSupportsIPv6(supportsIPv6.isSelected());

		mainProxy.setSupportsSSL(supportsSSL.isSelected());

		mainProxy.setSupportsTSL(supportsTSL.isSelected());

		mainProxy.setSupportsHTTPS(supportsHTTPS.isSelected());
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			// Saves the current values of the new motherboard.
			save();
			
			// Checks whether or not the software is compatible with the OS
			if ( SoftwareManagment.validateSoftware(mainProxy, mainObj) )
			{
				// Sets an array with the newly added software object
				mainObj.setSoftware(SoftwareManagment.addSoftware(mainProxy,
						mainObj));


				// Updates the views of the object to correctly show the
				// current info.
				ObjectView view = PrimeMain1.getObjectView(mainObj);
				if ( view != null )
				{
					view.updateViewInfo();
				}


				// Closes the JFrame.
				this.dispose();
			}
			else
			{
				JOptionPane
						.showMessageDialog(this,
								"The supported Operating System chosen is not " +
								"compatible with the objects Operating System");
			}

		}
		else if ( e.getActionCommand().equals("cancel") )
		{
			this.dispose();
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
