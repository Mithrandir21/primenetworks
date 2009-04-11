/**
 * 
 */
package graphics.GUI.settings;


import graphics.Settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class SettingsListener implements ActionListener
{
	JCheckBox[] messageCheckBox;

	SettingsOverview settingsFrame;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param checkBox
	 */
	public SettingsListener(SettingsOverview panel, JCheckBox[] checkBox)
	{
		messageCheckBox = checkBox;
		settingsFrame = panel;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() instanceof JButton)
		{
			JButton button = (JButton) e.getSource();
			String command = button.getActionCommand();
			
			if( command.equals("save") )
			{
				setMessagesSettings();
				settingsFrame.dispose();
			}
			else if( command.equals("apply") )
			{
				setMessagesSettings();
			}
			else if( command.equals("cancel") )
			{
				
			}
		}
	}
	
	
	
	
	private void setMessagesSettings()
	{
		Settings.showHardwareErrorMessages = messageCheckBox[0].isSelected();
		Settings.showHardwareWarningMessages = messageCheckBox[1].isSelected();
		Settings.showHardwareNoticeMessages = messageCheckBox[2].isSelected();
		
		Settings.showSoftwareErrorMessages = messageCheckBox[3].isSelected();
		Settings.showSoftwareWarningMessages = messageCheckBox[4].isSelected();
		Settings.showSoftwareNoticeMessages = messageCheckBox[5].isSelected();
		
		Settings.showConnectionErrorMessages = messageCheckBox[6].isSelected();
		Settings.showConnectionWarningMessages = messageCheckBox[7].isSelected();
		Settings.showConnectionNoticeMessages = messageCheckBox[8].isSelected();
		
		Settings.showNetworkErrorMessages = messageCheckBox[9].isSelected();
		Settings.showNetworkWarningMessages = messageCheckBox[10].isSelected();
		Settings.showNetworkNoticeMessages = messageCheckBox[11].isSelected();
	}
}
