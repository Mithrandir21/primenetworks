/**
 * 
 */
package graphics.GUI.visualObjectCustomization;


import graphics.PrimeMain1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import objects.Object;
import widgets.WidgetButton;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class VisualCustomFrame extends JDialog implements ActionListener
{
	// A simple border that is gray
	private Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// The listener that listens for ImageIcon buttons pressed.
	private VisualCustomListener listener = new VisualCustomListener(this);

	// The HashMap that contains object ImageIcons temporary
	public HashMap<Class, ImageIcon> tempImageIcons = new HashMap<Class, ImageIcon>();

	// The JPanel all WidgetButtons will palce in.
	JPanel iconPanel;


	/**
	 * TODO - Description NEEDED!
	 */
	public VisualCustomFrame()
	{
		this.setTitle(PrimeMain1.texts.getString("visObjCustomFrameTitle"));

		tempImageIcons.putAll(PrimeMain1.objectImageIcons);

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();

		// Set size for the settings JFrame
		Dimension size = new Dimension(456, 480);

		int initYLocation = (scrnsize.height - size.height) / 3;
		int initXLocation = (scrnsize.width - size.width) / 2;


		JPanel panel = new JPanel();
		panel.setBorder(grayline);

		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 5, 10);

		iconPanel = createVisualPanel();
		JScrollPane scrollPane = new JScrollPane();
		iconPanel.setPreferredSize(new Dimension(scrollPane.getWidth(), 1000));
		scrollPane.setViewportView(iconPanel);

		// Increases how far the scroll bar scrolls on one step of a mouse wheel
		scrollPane.getVerticalScrollBar().setUnitIncrement(10);


		// scrollPane
		// .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scrollPane.setBorder(null);

		panel.add(scrollPane, c);


		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 0;
		c.insets = new Insets(0, 10, 10, 10);

		JPanel p2 = createButtons();

		panel.add(p2, c);


		this.add(panel);


		this.setSize(size);
		this.setLocation(initXLocation, initYLocation);
		this.setMinimumSize(size);
		this.setVisible(true);


		/**
		 * When the windows is closed by pressing the exit button, the pointer
		 * to this JFrame is set to null.
		 */
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				PrimeMain1.vcf = null;
			}
		});
	}


	/**
	 * TODO - Description
	 */
	private JPanel createVisualPanel()
	{
		JPanel visPanel = new JPanel(new GridLayout(0, 3, 25, 25));


		Iterator<Object> iterator = PrimeMain1.objectlist.iterator();

		while ( iterator.hasNext() )
		{
			visPanel.add(createImageButton(iterator.next()));
		}

		visPanel.setBackground(Color.WHITE);

		return visPanel;
	}


	/**
	 * TODO - Description
	 */
	private JButton createImageButton(Object obj)
	{
		WidgetButton button = new WidgetButton(obj);

		button.addActionListener(listener);
		button.setVerticalTextPosition(AbstractButton.BOTTOM);
		button.setHorizontalTextPosition(AbstractButton.CENTER);
		button.setPreferredSize(new Dimension(50, 70));

		return button;
	}


	/**
	 * Creates a JPanel with two buttons that are listened for by
	 * actionlisteners.
	 */
	private JPanel createButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));


		Button save = new Button(PrimeMain1.texts.getString("save"));
		save.addActionListener(this);
		save.setActionCommand("save");

		Button reset = new Button(PrimeMain1.texts.getString("reset"));
		reset.addActionListener(this);
		reset.setActionCommand("reset");

		Button cancel = new Button(PrimeMain1.texts.getString("cancel"));
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(reset);
		buttons.add(cancel);

		return buttons;
	}




	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			for ( int i = 0; i < iconPanel.getComponentCount(); i++ )
			{
				WidgetButton button = (WidgetButton) iconPanel.getComponent(i);

				Object obj = button.getObject();

				button.getObject().setVisualImage(
						tempImageIcons.get(obj.getClass()));
			}

			PrimeMain1.objectImageIcons.putAll(tempImageIcons);

			PrimeMain1.updateSelectionArea();

			this.dispose();
		}
		else if ( e.getActionCommand().equals("reset") )
		{

		}
		else
		{
			assert (e.getActionCommand().equals("cancel"));

			this.dispose();
		}

		// Sets the pointer to this JFrame to null.
		PrimeMain1.vcf = null;
	}



	/**
	 * Updates the icons on the buttons.
	 */
	public void updateButtonImages()
	{
		for ( int i = 0; i < iconPanel.getComponentCount(); i++ )
		{
			WidgetButton button = (WidgetButton) iconPanel.getComponent(i);

			button.setIcon(tempImageIcons.get(button.getObject().getClass()));

			button.repaint();
		}
	}
}
