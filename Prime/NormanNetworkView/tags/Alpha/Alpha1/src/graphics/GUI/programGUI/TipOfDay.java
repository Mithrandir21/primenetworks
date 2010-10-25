package graphics.GUI.programGUI;


import graphics.PrimeMain1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import managment.Settings;


/**
 * TODO - Description NEEDED! (Example provided by 'www.java2s.com') Extended by
 * Bahram Malaekeh
 */
public class TipOfDay extends JFrame implements ActionListener
{
	/**
	 * TODO - Description NEEDED!
	 */
	public TipOfDay()
	{
		super(PrimeMain1.texts.getString("totdTitle"));

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(450, 350);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		JPanel basic = new JPanel();
		basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
		this.add(basic);

		JPanel topPanel = new JPanel(new BorderLayout(0, 0));
		topPanel.setMaximumSize(new Dimension(450, 0));
		JLabel hint = new JLabel("Tip Headline");
		hint.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));
		topPanel.add(hint);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.gray);

		topPanel.add(separator, BorderLayout.SOUTH);

		basic.add(topPanel);

		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
		JTextPane pane = new JTextPane();

		pane.setContentType("text/html");
		String text = "<p><b>Working on the Tip of the day...</b></p>";
		pane.setText(text);
		pane.setEditable(false);
		textPanel.add(new JScrollPane(pane));

		basic.add(textPanel);

		JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

		JCheckBox box = new JCheckBox(PrimeMain1.texts
				.getString("totdShowTipOnStartup"));
		box.addActionListener(this);
		box.setActionCommand("showTips");
		box.setMnemonic(KeyEvent.VK_S);

		boxPanel.add(box);
		basic.add(boxPanel);

		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		JButton ntip = new JButton(PrimeMain1.texts
				.getString("totdNextTipButton"));
		ntip.addActionListener(this);
		ntip.setActionCommand("nextTip");
		ntip.setMnemonic(KeyEvent.VK_N);

		JButton close = new JButton(PrimeMain1.texts.getString("closeButton"));
		close.addActionListener(this);
		close.setActionCommand("close");
		close.setMnemonic(KeyEvent.VK_C);

		bottom.add(ntip);
		bottom.add(close);
		basic.add(bottom);

		bottom.setMaximumSize(new Dimension(450, 0));

		this.setLocation(initXLocation, initYLocation);
		this.setSize(new Dimension(450, 350));
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(Settings.showTOFD);
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

		}
		else if ( e.getActionCommand().equals("nextTip") )
		{

		}
		else if ( e.getActionCommand().equals("close") )
		{
			this.dispose();
		}
	}
}