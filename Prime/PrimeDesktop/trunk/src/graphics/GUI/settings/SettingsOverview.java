/**
 * 
 */
package graphics.GUI.settings;


import graphics.ImageLocator;
import graphics.PrimeMain;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import managment.Settings;


/**
 * This JFrame extension will contain the framework for the settings JFrame.
 * 
 * @author Bahram Malaekeh
 */
public class SettingsOverview extends JFrame
{
	public static JCheckBox[] messagesCheckBox = new JCheckBox[12];

	/**
	 * A constructor for the class that sets up the settings variables and
	 * JPanels of the display.
	 */
	public SettingsOverview()
	{
		super(PrimeMain.texts.getString("settingsFrameLabel"));
		Dimension size = new Dimension(600, 530);

		setUpMessageCheckBoxes();

		SettingsListener setListener = new SettingsListener(this,
				messagesCheckBox);

		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();



		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;

		ImageIcon frameIcon = ImageLocator.getImageIconObject("Settings");
		this.setIconImage(frameIcon.getImage());




		panel.add(getSettingsTabs(setListener));



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		JButton save = new JButton(PrimeMain.texts.getString("save"));
		save.addActionListener(setListener);
		save.setActionCommand("save");

		JButton apply = new JButton(PrimeMain.texts.getString("apply"));
		apply.addActionListener(setListener);
		apply.setActionCommand("apply");

		JButton cancel = new JButton(PrimeMain.texts.getString("cancel"));
		cancel.addActionListener(setListener);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);


		c.add(panel);



		this.setPreferredSize(size);
		this.setMinimumSize(size);
		// this.setResizable(false);
		this.setLocation(initXLocation, initYLocation);
		// this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}





	/**
	 * Creates a JTabbedPane where a {@link NetworkMessagesSettings} panel is
	 * placed.
	 * 
	 * @return A JTabbedPane with a {@link NetworkMessagesSettings} panel
	 *         inside.
	 */
	private JTabbedPane getSettingsTabs(ActionListener lis)
	{
		JTabbedPane tabs = new JTabbedPane();

		ImageIcon frameIcon = ImageLocator
				.getImageIconObject("ProcessingSettings");
		tabs.addTab(PrimeMain.texts.getString("settingsShowMsgPanelLabel"),
				frameIcon, new NetworkMessagesSettings(messagesCheckBox, lis),
				PrimeMain.texts.getString("settingsShowMsgPanelTip"));
		//
		// tabs.addTab(PrimeMain.texts.getString("settingsAdvancedPanelLabel"),
		// new AdvancedSettings());

		return tabs;
	}



	/**
	 * Sets up the check boxes with the correct variables, the program settings.
	 */
	private void setUpMessageCheckBoxes()
	{
		messagesCheckBox[0] = new JCheckBox();
		messagesCheckBox[0].setSelected(Settings.showHardwareErrorMessages);

		messagesCheckBox[1] = new JCheckBox();
		messagesCheckBox[1].setSelected(Settings.showHardwareWarningMessages);

		messagesCheckBox[2] = new JCheckBox();
		messagesCheckBox[2].setSelected(Settings.showHardwareNoticeMessages);

		messagesCheckBox[3] = new JCheckBox();
		messagesCheckBox[3].setSelected(Settings.showSoftwareErrorMessages);

		messagesCheckBox[4] = new JCheckBox();
		messagesCheckBox[4].setSelected(Settings.showSoftwareWarningMessages);

		messagesCheckBox[5] = new JCheckBox();
		messagesCheckBox[5].setSelected(Settings.showSoftwareNoticeMessages);

		messagesCheckBox[6] = new JCheckBox();
		messagesCheckBox[6].setSelected(Settings.showConnectionErrorMessages);

		messagesCheckBox[7] = new JCheckBox();
		messagesCheckBox[7].setSelected(Settings.showConnectionWarningMessages);

		messagesCheckBox[8] = new JCheckBox();
		messagesCheckBox[8].setSelected(Settings.showConnectionNoticeMessages);

		messagesCheckBox[9] = new JCheckBox();
		messagesCheckBox[9].setSelected(Settings.showNetworkErrorMessages);

		messagesCheckBox[10] = new JCheckBox();
		messagesCheckBox[10].setSelected(Settings.showNetworkWarningMessages);

		messagesCheckBox[11] = new JCheckBox();
		messagesCheckBox[11].setSelected(Settings.showNetworkNoticeMessages);
	}

}
