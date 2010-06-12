package graphics.GUI.programGUI;


import graphics.PrimeMain1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;

import managment.Settings;

import org.jdesktop.swingx.JXTipOfTheDay;
import org.jdesktop.swingx.tips.DefaultTip;
import org.jdesktop.swingx.tips.DefaultTipOfTheDayModel;
import org.jdesktop.swingx.tips.TipOfTheDayModel;


/**
 * This extension of the {@link JDialog} class will display a 'tip of the day' to
 * the user of the program on startup.
 * (Example provided by 'www.java2s.com') Extended by Bahram Malaekeh.
 */
public class TipOfDay extends JFrame implements ActionListener
{
	private TipOfTheDayModel model;

	private JXTipOfTheDay totd;


	public TipOfDay()
	{
		super(PrimeMain1.texts.getString("totdTitle"));

		createTipOfTheDayDemo();


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) scrnsize.getWidth()) / 3;

		int height = ((int) scrnsize.getHeight()) / 3;


		Dimension size = new Dimension(width, height);

		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void createTipOfTheDayDemo()
	{
		this.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();


		d.fill = GridBagConstraints.BOTH;
		// d.ipady = 0; // reset to default
		// d.ipadx = 0; // reset to default
		d.weighty = 1.0; // request any extra vertical space
		d.weightx = 1.0; // request any extra vertical space
		d.anchor = GridBagConstraints.CENTER; // bottom of space
		// d.insets = new Insets(10, 10, 10, 10); // top padding
		d.gridwidth = 2; // 2 columns wide
		// d.gridheight = 1; // 2 columns wide
		d.gridy = 0; // third row
		d.gridx = 0; // third row



		model = createTipOfTheDayModel();

		totd = new JXTipOfTheDay(model);
		totd.setName("totd");
		totd.setBorder(BorderFactory.createEtchedBorder());
		this.add(totd, d);



		d.weighty = 0; // request any extra vertical space
		d.weightx = 0; // request any extra vertical space
		d.gridy = 1;
		this.add(getButtons(), d);
	}



	public JPanel getButtons()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		// buttonPanel.setLayout(new BoxLayout(buttonPanel,
		// BoxLayout.PAGE_AXIS));
		Dimension panelSize = new Dimension(115, 30);
		buttonPanel.setPreferredSize(panelSize);
		buttonPanel.setMinimumSize(panelSize);
		buttonPanel.setMaximumSize(panelSize);


		JCheckBox box = new JCheckBox(PrimeMain1.texts
				.getString("totdShowTipOnStartup"));
		box.addActionListener(this);
		box.setActionCommand("showTips");


		JButton next = new JButton(PrimeMain1.texts
				.getString("totdNextTipButton"));
		next.setFocusable(false);
		next.addActionListener(this);
		next.setActionCommand("nextTip");
		Dimension textSize = new Dimension(100, 20);
		next.setPreferredSize(textSize);
		next.setMinimumSize(textSize);
		next.setMaximumSize(textSize);


		JButton close = new JButton(PrimeMain1.texts.getString("closeButton"));
		close.addActionListener(this);
		close.setActionCommand("close");
		close.setPreferredSize(textSize);
		close.setMinimumSize(textSize);
		close.setMaximumSize(textSize);


		buttonPanel.add(box);
		buttonPanel.add(next);
		buttonPanel.add(close);

		return buttonPanel;
	}



	protected TipOfTheDayModel createTipOfTheDayModel()
	{
		// Create a tip model with some tips
		DefaultTipOfTheDayModel tips = new DefaultTipOfTheDayModel();

		// plain text
		tips.add(new DefaultTip("Plain Text Tip", "This is the first tip "
				+ "This is the first tip " + "This is the first tip "
				+ "This is the first tip " + "This is the first tip "
				+ "This is the first tip" + "This is the first tip "
				+ "This is the first tip"));

		// a Component
		tips.add(new DefaultTip("Component Tip", new JTree()));


		return tips;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("showTips") )
		{
			Settings.showTOFD = !((JCheckBox) e.getSource()).isSelected();
		}
		else if ( e.getActionCommand().equals("nextTip") )
		{
			totd.nextTip();
		}
		else if ( e.getActionCommand().equals("close") )
		{
			this.dispose();
		}
	}
}