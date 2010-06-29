/**
 * 
 */
package graphics.GUI.messageArea;


import graphics.ImageLocator;
import graphics.PrimeMain;
import graphics.GUI.messageArea.ConnectionTab.ConnectionMessages;
import graphics.GUI.messageArea.ConnectionTab.ConnectionProcessing;
import graphics.GUI.messageArea.HardwareTab.HardwareMessages;
import graphics.GUI.messageArea.HardwareTab.HardwareProcessing;
import graphics.GUI.messageArea.NetworkTab.NetworkMessages;
import graphics.GUI.messageArea.NetworkTab.NetworkProcessing;
import graphics.GUI.messageArea.SoftwareTab.SoftwareMessages;
import graphics.GUI.messageArea.SoftwareTab.SoftwareProcessing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import logistical.cleanup;
import managment.ArrayManagment;
import managment.Settings;
import objects.Clients;
import objects.ExternalHardware;
import objects.Infrastructure;
import objects.Object;
import objects.Servers;
import widgets.WidgetObject;
import widgets.WorkareaCanvas;
import connections.Connection;


/**
 * A class that will contain the different Message Panel that will show the user
 * messages regarding the {@link WorkareaCanvas} that is currently being
 * displayed. All tabs will be disabled in creation, and Tab titles will be
 * shown in bold when content is added to the JTable inside each tab.
 * 
 * @author Bahram Malaekeh
 */
public class MessageTabbed extends JTabbedPane implements ActionListener
{
	private JPanel netMsgPanel;


	private JPanel conMsgPanel;


	private JPanel softMsgPanel;


	private JPanel hardMsgPanel;


	/**
	 * A constructor for this class that initiates the the different JPanels in
	 * this class. It also sets the preferred size of this JTabbedPane.
	 */
	public MessageTabbed()
	{
		netMsgPanel = new JPanel(new GridLayout());
		netMsgPanel.setEnabled(false);


		conMsgPanel = new JPanel(new GridLayout());
		conMsgPanel.setEnabled(false);


		softMsgPanel = new JPanel(new GridLayout());
		softMsgPanel.setEnabled(false);


		hardMsgPanel = new JPanel(new GridLayout());
		hardMsgPanel.setEnabled(false);


		int width = (int) (PrimeMain.width * 0.70);

		int height = (int) (PrimeMain.height * 0.11);


		this.setMaximumSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
	}


	/**
	 * Processes all the objects given in the Objects array. The method
	 * separates the different object classes and then passes the different
	 * arrays with object to methods that will create multidimensional String
	 * arrays with messages to the user about the given objects.
	 * 
	 * @param objects
	 */
	public void processAllObjects(Object[] objects)
	{
		Object[] conObj = new Object[objects.length];

		Object[] computerObj = new Object[objects.length];

		Object[] peripheralObj = new Object[objects.length];

		Object[] infraObj = new Object[objects.length];


		for ( int i = 0; i < conObj.length; i++ )
		{
			if ( objects[i] instanceof Connection )
			{
				conObj[i] = objects[i];
			}
		}



		for ( int i = 0; i < computerObj.length; i++ )
		{
			if ( objects[i] instanceof Clients )
			{
				computerObj[i] = objects[i];
			}
			else if ( objects[i] instanceof Servers )
			{
				computerObj[i] = objects[i];
			}
		}


		for ( int i = 0; i < peripheralObj.length; i++ )
		{
			if ( objects[i] instanceof ExternalHardware )
			{
				peripheralObj[i] = objects[i];
			}
		}



		for ( int i = 0; i < infraObj.length; i++ )
		{
			if ( objects[i] instanceof Infrastructure )
			{
				infraObj[i] = objects[i];
			}
		}



		// Removes all the null pointers in the arrays.
		conObj = cleanup.cleanObjectArray(conObj);

		computerObj = cleanup.cleanObjectArray(computerObj);

		peripheralObj = cleanup.cleanObjectArray(peripheralObj);

		infraObj = cleanup.cleanObjectArray(infraObj);


		// Processes all the objects and gets the messages
		processHardwareMessage(computerObj);

		processConnectionMessage(conObj);

		processSoftwareMessage(computerObj);

		processNetworkMessage(computerObj);


		this.revalidate();
		this.repaint();
	}



	/**
	 * This method will examine a network. Creates and adds at tab to this
	 * JTabbedPane component. The tab will contain possible JScrollPane with a
	 * JTable depending on whether or not the given objects creates any messages
	 * that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processNetworkMessage(Object[] objects)
	{
		String[][] data = null;

		data = NetworkProcessing.processNetwork(data, PrimeMain.currentCanvas,
				Settings.showNetworkErrorMessages,
				Settings.showNetworkWarningMessages,
				Settings.showNetworkNoticeMessages);

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				netMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				netMsgPanel.add(new NetworkMessages(objects, data));

				boldTab(PrimeMain.texts.getString("networkTabLabel"));
			}
			else
			{
				netMsgPanel.removeAll();

				unboldTab(PrimeMain.texts.getString("networkTabLabel"));
			}
		}
		else
		{
			netMsgPanel.removeAll();

			unboldTab(PrimeMain.texts.getString("networkTabLabel"));
		}
	}


	/**
	 * This method will examine an array of connections. Creates and adds at tab
	 * to this JTabbedPane component. The tab will contain possible JScrollPane
	 * with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processConnectionMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = ConnectionProcessing.processConnections(data, objects[i],
					PrimeMain.currentCanvas,
					Settings.showConnectionErrorMessages,
					Settings.showConnectionWarningMessages,
					Settings.showConnectionNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				conMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				conMsgPanel.add(new ConnectionMessages(objects, data));

				boldTab(PrimeMain.texts.getString("connectionTabLabel"));
			}
			else
			{
				conMsgPanel.removeAll();

				unboldTab(PrimeMain.texts.getString("connectionTabLabel"));
			}
		}
		else
		{
			conMsgPanel.removeAll();

			unboldTab(PrimeMain.texts.getString("connectionTabLabel"));
		}
	}


	/**
	 * This method will examine software in objects. Creates and adds at tab to
	 * this JTabbedPane component. The tab will contain possible JScrollPane
	 * with a JTable depending on whether or not the given objects creates any
	 * messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processSoftwareMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = SoftwareProcessing.processSoftware(data, objects[i],
					Settings.showSoftwareErrorMessages,
					Settings.showSoftwareWarningMessages,
					Settings.showSoftwareNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				softMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				softMsgPanel.add(new SoftwareMessages(objects, data));

				boldTab(PrimeMain.texts.getString("softwareTabLabel"));
			}
			else
			{
				softMsgPanel.removeAll();

				unboldTab(PrimeMain.texts.getString("softwareTabLabel"));
			}
		}
		else
		{
			softMsgPanel.removeAll();

			unboldTab(PrimeMain.texts.getString("softwareTabLabel"));
		}
	}


	/**
	 * This method will examine an array of objects for hardware. Creates and
	 * adds at tab to this JTabbedPane component. The tab will contain possible
	 * JScrollPane with a JTable depending on whether or not the given objects
	 * creates any messages that they user needs to know about the objects.
	 * 
	 * @param objects
	 */
	public void processHardwareMessage(Object[] objects)
	{
		String[][] data = null;

		for ( int i = 0; i < objects.length; i++ )
		{
			data = HardwareProcessing.processHardware(data, objects[i],
					Settings.showHardwareErrorMessages,
					Settings.showHardwareWarningMessages,
					Settings.showHardwareNoticeMessages);
		}

		if ( data != null )
		{
			if ( data[0][0] != null )
			{
				hardMsgPanel.removeAll();

				data = ArrayManagment.removeEmptyIndexes(data);

				hardMsgPanel.add(new HardwareMessages(objects, data));

				boldTab(PrimeMain.texts.getString("hardwareTabLabel"));
			}
			else
			{
				hardMsgPanel.removeAll();

				unboldTab(PrimeMain.texts.getString("hardwareTabLabel"));
			}
		}
		else
		{
			hardMsgPanel.removeAll();

			unboldTab(PrimeMain.texts.getString("hardwareTabLabel"));
		}
	}



	/**
	 * This method removes the tab with the given name from this class, which is
	 * a JTabbedPane.
	 * 
	 * @param title
	 *            The title of the Tab that is to be removed.
	 */
	private void removeTab(String title)
	{
		// The total count of the tabs on the screen
		int tabCount = this.getTabCount();

		// The index of the tab we are looking for
		int tabIndex = this.getTabCount();

		// Searching for the tab with the given title
		for ( int i = 0; i < tabCount; i++ )
		{
			if ( this.getTitleAt(i).equals(title) )
			{
				tabIndex = i;
			}
		}

		// If the tabindex is anything else then what it was in the beginning
		if ( tabIndex != tabCount )
		{
			this.remove(tabIndex);
		}
	}


	/**
	 * This method creates a custom Tab with an close button.
	 * 
	 * @param labelText
	 *            The text that will be shown inside the tab.
	 * @param content
	 *            The content that will be shown within the tab.
	 */
	public void addNewMessageTab(String labelText, JPanel content)
	{
		// The ImageIcon that will be shown and will remove a tab if pressed
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");

		// The dimensions of the new button.
		Dimension closeButtonSize = new Dimension(
				closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);

		// The actual panel that will be the component panel which the JLabel
		// and the button
		// will be placed inside.
		JPanel tab = new JPanel();

		// The Panel is not opaque
		tab.setOpaque(false);

		tab.setName(labelText);


		// The JLabel that will show the name of the tab
		JLabel tabLabel = new JLabel(labelText);

		Font f = tabLabel.getFont();

		// Unbold
		tabLabel.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));

		// The button that will be represented by the ImageIcon
		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setName(labelText);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(this);

		// Adds the label and then the button to the panel.
		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		// Sets the name of the JPanel parameter
		content.setName(labelText);

		content.setEnabled(true);

		// Add the tab to the JTabbed pane.
		this.addTab(labelText, content);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.
		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}





	/**
	 * Initiates all the different tabs and panels. This function will just call
	 * all the other creation function for the different panels.
	 */
	public void createInitialTabs()
	{
		createNetworkMessagePanel();

		createConnectionMessagePanel();

		createSoftwareMessagePanel();

		createHardwareMessagePanel();
	}



	/**
	 * Creates, if not already existing, a message panel for message about the
	 * {@link WorkareaCanvas} network. If the panel already exists, it will set
	 * the focus of the JTabbedPane to the tab containing that panel.
	 */
	public void createNetworkMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !netMsgPanel.isEnabled() )
		{
			addNewMessageTab(PrimeMain.texts.getString("networkTabLabel"),
					netMsgPanel);
		}
		else
		{
			focusOnNetworkTab();
		}
	}


	/**
	 * Creates, if not already existing, a message panel for message about the
	 * connection between {@link WidgetObject WidgetObjects} in
	 * {@link WorkareaCanvas}. If the panel already exists, it will set the
	 * focus of the JTabbedPane to the tab containing that panel.
	 */
	public void createConnectionMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !conMsgPanel.isEnabled() )
		{
			addNewMessageTab(PrimeMain.texts.getString("connectionTabLabel"),
					conMsgPanel);
		}
		else
		{
			focusOnConnectionTab();
		}
	}


	/**
	 * Creates, if not already existing, a message panel for message about the
	 * software on each {@link WidgetObject} in the {@link WorkareaCanvas}. If
	 * the panel already exists, it will set the focus of the JTabbedPane to the
	 * tab containing that panel.
	 */
	public void createSoftwareMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !softMsgPanel.isEnabled() )
		{
			addNewMessageTab(PrimeMain.texts.getString("softwareTabLabel"),
					softMsgPanel);
		}
		else
		{
			focusOnSoftwareTab();
		}
	}


	/**
	 * Creates, if not already existing, a message panel for message about the
	 * hardware on each {@link WidgetObject} in the {@link WorkareaCanvas}. If
	 * the panel already exists, it will set the focus of the JTabbedPane to the
	 * tab containing that panel.
	 */
	public void createHardwareMessagePanel()
	{
		// If the JPanel is not enabled
		if ( !hardMsgPanel.isEnabled() )
		{
			addNewMessageTab(PrimeMain.texts.getString("hardwareTabLabel"),
					hardMsgPanel);
		}
		else
		{
			focusOnHardwareTab();
		}
	}


	/**
	 * Focuses on the Network tab if the tab exists.
	 */
	public void focusOnNetworkTab()
	{
		// Gets the index of the network tab
		int index = getIndexOfTab(PrimeMain.texts.getString("networkTabLabel"));

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}

	/**
	 * Focuses on the Connection tab if the tab exists.
	 */
	public void focusOnConnectionTab()
	{
		// Gets the index of the connection tab
		int index = getIndexOfTab(PrimeMain.texts
				.getString("connectionTabLabel"));

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}

	/**
	 * Focuses on the Software tab if the tab exists.
	 */
	public void focusOnSoftwareTab()
	{
		// Gets the index of the software tab
		int index = getIndexOfTab(PrimeMain.texts
				.getString("softwareTabLabel"));

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}

	/**
	 * Focuses on the Hardware tab if the tab exists.
	 */
	public void focusOnHardwareTab()
	{
		// Gets the index of the hardware tab
		int index = getIndexOfTab(PrimeMain.texts
				.getString("hardwareTabLabel"));

		if ( index != -1 )
		{
			this.setSelectedIndex(index);
		}
	}




	/**
	 * This function finds the tab, if it exists, where the given string is
	 * equal the tab title. If the tab is found, the font of the title in the
	 * tab will be set to <b>bold</b>.
	 * 
	 * @param name
	 *            The title of the tab where the font is to be set to bold.
	 */
	private void boldTab(String name)
	{
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches
		// the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			JPanel test = (JPanel) this.getTabComponentAt(i);

			String tabName = test.getName();

			// If the name of the button and the name of the content match, the
			// button to close that
			// tab with the given content has been pressed and the tab is
			// removed.
			if ( tabName != null && tabName.equals(name) )
			{
				JLabel label = (JLabel) test.getComponent(0);

				Font f = label.getFont();

				// bold
				if ( !f.isBold() )
				{
					label.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
				}
			}
		}
	}


	/**
	 * This function finds the tab, if it exists, where the given string is
	 * equal the tab title. If the tab is found, the font of the title in the
	 * tab will be set to not bold.
	 * 
	 * @param name
	 *            The title of the tab where the font is to be set to unbold.
	 */
	private void unboldTab(String name)
	{
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches
		// the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			JPanel test = (JPanel) this.getTabComponentAt(i);

			String tabName = test.getName();

			// If the name of the button and the name of the content match, the
			// button to close that
			// tab with the given content has been pressed and the tab is
			// removed.
			if ( tabName != null && tabName.equals(name) )
			{
				JLabel label = (JLabel) test.getComponent(0);

				Font f = label.getFont();

				// Unbold
				if ( f.isBold() )
				{
					label.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
				}
			}
		}
	}



	/**
	 * Searches for a Tab where the {@link JPanel} within has a name equal to
	 * the given string. If such a tab exists, the index of that tab in the
	 * JTabbedPane is returned. If no tab is found -1 is returned.
	 * 
	 * @param name
	 *            The name of the {@link JPanel} searched for inside this
	 *            classes tabs.
	 * @return The index where the tab with the {@link JPanel} that has the name
	 *         equal to the given string is located. If it is not found, -1 will
	 *         be returned.
	 */
	private int getIndexOfTab(String name)
	{
		// This is the current number of tabs
		int arraySize = this.getTabCount();

		// Goes through the list of tab contents until it finds one that matches
		// the given button name.
		for ( int i = 0; i < arraySize; i++ )
		{
			JPanel test = (JPanel) this.getTabComponentAt(i);

			String tabName = test.getName();

			// If the name of the button and the name of the content match, the
			// button to close that
			// tab with the given content has been pressed and the tab is
			// removed.
			if ( tabName != null && tabName.equals(name) )
			{
				return i;
			}
		}

		// If no tab was found with the given name
		return -1;
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{

		if ( e.getSource() instanceof JButton )
		{
			// Since there is no other components in this class that can call
			// the ActionPerformed method
			// then the created JButton, we cast the source of the event as a
			// JButton.
			JButton button = (JButton) e.getSource();

			String contentName = button.getName();
			JPanel test = null;

			// This is the current number of tabs
			int arraySize = this.getTabCount();

			// Goes through the list of tab contents until it finds one that
			// matches the given button name.
			for ( int i = 0; i < arraySize; i++ )
			{
				test = (JPanel) this.getTabComponentAt(i);

				String tabName = test.getName();

				// If the name of the button and the name of the content match,
				// the button to close that
				// tab with the given content has been pressed and the tab is
				// removed.
				if ( tabName != null && tabName.equals(contentName) )
				{
					if ( tabName.equals(PrimeMain.texts
							.getString("networkTabLabel")) )
					{
						netMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals(PrimeMain.texts
							.getString("connectionTabLabel")) )
					{
						conMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals(PrimeMain.texts
							.getString("softwareTabLabel")) )
					{
						softMsgPanel.setEnabled(false);
					}
					else if ( tabName.equals(PrimeMain.texts
							.getString("hardwareTabLabel")) )
					{
						hardMsgPanel.setEnabled(false);
					}

					this.remove(i);
					return;
				}

				test = null;
			}
		}
	}
}
