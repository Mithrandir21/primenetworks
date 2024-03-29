/**
 * 
 */
package graphics.GUI.standardObjectEdit.StandardViews;


import graphics.GUI.objectView.Hardware.HardwareViewInterface;
import graphics.GUI.standardObjectEdit.ObjectViewTabbed;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.ExternalHardware;
import objects.Hardware;
import objects.Infrastructure;
import objects.Object;


/**
 * CLASSDesc - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 */
public class StandardObjectEditor extends JFrame implements ActionListener
{
	private Object givenObject = null;

	private ObjectViewTabbed view;

	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public StandardObjectEditor(Object obj)
	{
		super("Edit Standard Object");

		createNewEditorTab(obj);
	}


	/**
	 * Creates a JPanel that shows an Icon representing the standard object and two fields with the
	 * name and description of the object.
	 * 
	 * @param hw
	 *            The actual hardware object.
	 * @param icon
	 *            The Icon representing the component.
	 * @param name
	 *            A JTextField that will contain the name of the object.
	 * @param desc
	 *            A JTextArea that holds the description of the object.
	 * @return Returns the created JPanel with all the information about the hardware object.
	 */
	public static JPanel GeneralInfo(Hardware hw, ImageIcon icon,
			JTextField name, JTextArea desc)
	{
		JPanel genPanel = new JPanel();
		genPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.NONE;


		// Icon image.
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridheight = 2;

		JLabel image = new JLabel(icon);
		genPanel.add(image, c);


		GridBagConstraints d = new GridBagConstraints();

		d.fill = GridBagConstraints.NONE;
		d.gridx = 1;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.5;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.CENTER;

		JLabel nameLabel = new JLabel("Hardware name");
		genPanel.add(nameLabel, d);

		d.gridx = 2;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.5;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;

		name.setName("Name");
		name.setText(hw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		// name.setBorder(BorderFactory.createEmptyBorder());
		genPanel.add(name, d);


		d.fill = GridBagConstraints.NONE;
		d.gridx = 1;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.CENTER;

		JLabel descLabel = new JLabel("Description");
		genPanel.add(descLabel, d);


		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 2;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;

		// Description
		JScrollPane descScroll = new JScrollPane();
		desc.setName("Description");
		desc.setText(hw.getDescription());
		desc.setFont(t.getFont());
		descScroll.setViewportView(desc);

		genPanel.add(descScroll, d);


		return genPanel;
	}


	public void createNewEditorTab(Object obj)
	{
		givenObject = obj;

		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth() / 3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight() / 3)));


		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


		view = new ObjectViewTabbed(obj);

		panel.add(view);



		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));

		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");

		Button apply = new Button("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");

		Button cancel = new Button("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");


		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);

		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(), 1));

		panel.add(buttons);



		c.add(panel);

		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,
				(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}


	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getActionCommand().equals("save") )
		{
			boolean verify = true;

			// If the object is an instance of infrastructure.
			if ( givenObject instanceof Infrastructure
					|| givenObject instanceof ExternalHardware )
			{
				verify = false;
			}

			// If the information is saved a true is returned and the
			// JFrame is closed.
			if ( view.save(verify) )
			{
				this.dispose();
			}
		}
		else if ( e.getActionCommand().equals("apply") )
		{
			boolean verify = true;

			// If the object is an instance of infrastructure.
			if ( givenObject instanceof Infrastructure
					|| givenObject instanceof ExternalHardware )
			{
				verify = false;
			}

			// Saves the information with the option of verification.
			view.save(verify);
		}
		else
		{
			assert (e.getActionCommand().equals("cancel"));

			this.dispose();
		}
	}



	/**
	 * Tries to set the tab which contains the given object as the focused tab.
	 * 
	 * @param obj
	 *            The hardware object which the search for tab should contain.
	 */
	public void setTabFocus(Hardware obj)
	{
		int tabCount = view.getTabCount();

		for ( int i = 0; i < tabCount; i++ )
		{
			// Gets the component at the given index tab
			Component comp = view.getComponent(i);

			// If the hardware object returned by the component, which
			// implements HardwareViewInterface, equals the
			// given object.
			if ( ((HardwareViewInterface) comp).getViewHardware().equals(obj) )
			{
				view.setSelectedIndex(i);
				return;
			}
		}

	}



	/**
	 * This function is used for when hardware information is changed or when a component is added
	 * or removed from an object. It redraws the views that show all current hardware information.
	 */
	public void HardwarePanelRevalidate()
	{
		view.removeAll();

		view.populateTabs(givenObject);
	}
}
