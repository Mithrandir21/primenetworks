/**
 * 
 */
package graphics.GUI.visualObjectCustomization;


import graphics.ImageLocator;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class VisualCustomFrame extends JDialog implements MouseListener
{
	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// ActionListener for the class
	VisualCustomListener listener = new VisualCustomListener();

	/**
	 * TODO - Description NEEDED!
	 */
	public VisualCustomFrame()
	{
		this.setTitle(PrimeMain1.texts.getString("visObjCustomFrameTitle"));

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

		JScrollPane scrollPane = new JScrollPane(createVisualPanel("1"));
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
		this.setResizable(false);
		this.setVisible(true);
	}



	/**
	 * TODO - Description
	 */
	private JPanel createVisualPanel(String a)
	{
		JPanel visPanel = new JPanel(new GridLayout(0, 3, 25, 25));


		JLabel b2 = new JLabel(ImageLocator.getImageIconObject("CPU"));
		b2.setText("CPU");
		// b2.setSize(buttonDim);
		b2.setVerticalTextPosition(AbstractButton.BOTTOM);
		b2.setHorizontalTextPosition(AbstractButton.CENTER);
		b2.setBorder(grayline);

		JLabel b3 = new JLabel(ImageLocator.getImageIconObject("CPU"));
		b3.setText("CPU");
		// b2.setSize(buttonDim);
		b3.setVerticalTextPosition(AbstractButton.BOTTOM);
		b3.setHorizontalTextPosition(AbstractButton.CENTER);
		b3.setBorder(grayline);

		JLabel b4 = new JLabel(ImageLocator.getImageIconObject("CPU"));
		b4.setText("CPU");
		// b2.setSize(buttonDim);
		b4.setVerticalTextPosition(AbstractButton.BOTTOM);
		b4.setHorizontalTextPosition(AbstractButton.CENTER);
		b4.setBorder(grayline);

		JLabel b5 = new JLabel(ImageLocator.getImageIconObject("CPU"));
		b5.setText("CPU");
		// b2.setSize(buttonDim);
		b5.setVerticalTextPosition(AbstractButton.BOTTOM);
		b5.setHorizontalTextPosition(AbstractButton.CENTER);
		b5.setBorder(grayline);

		visPanel.add(b2);


		visPanel.add(b3);


		visPanel.add(b4);


		visPanel.add(b5);
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));
		visPanel.add(new JButton("rr"));


		visPanel.setBackground(Color.WHITE);

		return visPanel;
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
		save.addActionListener(listener);
		save.setActionCommand("save");

		Button reset = new Button(PrimeMain1.texts.getString("reset"));
		reset.addActionListener(listener);
		reset.setActionCommand("save");

		Button cancel = new Button(PrimeMain1.texts.getString("cancel"));
		cancel.addActionListener(listener);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(reset);
		buttons.add(cancel);

		return buttons;
	}







	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}



	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
}
