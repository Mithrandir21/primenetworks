package graphics.GUI.programGUI;


import graphics.PrimeMain1;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This extension of the JFrame class will display an 'about' text about the
 * program.
 * 
 * @author Bahram Malaekeh
 */
public class AboutFrame extends JFrame
{

	public AboutFrame()
	{
		super(PrimeMain1.texts.getString("aboutTitle"));
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(300, 200);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;



		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JLabel name = new JLabel(PrimeMain1.texts.getString("aboutText"));
		name.setAlignmentX(0.5f);
		this.add(name);

		add(Box.createRigidArea(new Dimension(0, 100)));

		JButton close = new JButton(PrimeMain1.texts.getString("closeButton"));
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				dispose();
			}
		});

		close.setAlignmentX(0.5f);
		this.add(close);
		this.setLocation(initXLocation, initYLocation);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(300, 200);
		this.setResizable(false);
	}
}
