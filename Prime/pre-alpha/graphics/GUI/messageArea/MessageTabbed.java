/**
 * 
 */
package graphics.GUI.messageArea;


import graphics.ImageLocator;
import graphics.GUI.messageArea.ConnectionTab.ConnectionMessages;
import graphics.GUI.messageArea.HardwareTab.HardwareMessages;
import graphics.GUI.messageArea.NetworkTab.NetworkMessages;
import graphics.GUI.messageArea.SoftwareTab.SoftwareMessages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import objects.Object;


/**
 * @author Bahram Malaekeh
 */
public class MessageTabbed extends JTabbedPane implements ActionListener
{
	private ArrayList<JScrollPane> contents = new ArrayList<JScrollPane>();

	// No Need for constructor

	/**
	 * TODO - Description
	 */
	public void addNetworkTab(Object[] objects)
	{
		removeTab("Connections");

		addNewMessageTab("Connections", new ConnectionMessages(objects));
	}


	/**
	 * TODO - Description
	 */
	public void addConnectionTab(Object[] objects)
	{
		removeTab("Network");

		addNewMessageTab("Network", new NetworkMessages(objects));
	}


	/**
	 * TODO - Description
	 */
	public void addSoftwareTab(Object[] objects)
	{
		removeTab("Software");

		addNewMessageTab("Software", new SoftwareMessages(objects));
	}


	/**
	 * TODO - Description
	 */
	public void addHardwareTab(Object[] objects)
	{
		removeTab("Hardware");

		addNewMessageTab("Hardware", new HardwareMessages(objects));
	}



	/**
	 * TODO - Description
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
	 * TODO - Description
	 */
	public void addNewMessageTab(String labelText, JScrollPane content)
	{
		ImageIcon closeXIcon = ImageLocator.getImageIconObject("Close");
		Dimension closeButtonSize = new Dimension(
				closeXIcon.getIconWidth() + 5, closeXIcon.getIconHeight() + 5);

		JPanel tab = new JPanel();
		tab.setOpaque(false);


		JLabel tabLabel = new JLabel(labelText);


		JButton tabCloseButton = new JButton(closeXIcon);
		tabCloseButton.setName(labelText);
		tabCloseButton.setPreferredSize(closeButtonSize);
		tabCloseButton.addActionListener(this);

		tab.add(tabLabel, BorderLayout.WEST);
		tab.add(tabCloseButton, BorderLayout.EAST);

		contents.add(content);

		// Add the tab to the tabbed pane.

		this.addTab(labelText, content);

		// Instead of using a String/Icon combination for the tab,
		// use our panel instead.

		this.setTabComponentAt(this.getTabCount() - 1, tab);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();

		String contentName = button.getName();
		JScrollPane test = null;

		int arraySize = contents.size();


		for ( int i = 0; i < arraySize; i++ )
		{
			test = contents.get(i);

			if ( test.getName().equals(contentName) )
			{
				this.remove(test);
				contents.remove(i);
				return;
			}

			test = null;
		}
	}
}
