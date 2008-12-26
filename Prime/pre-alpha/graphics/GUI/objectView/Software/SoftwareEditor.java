package graphics.GUI.objectView.Software;


import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import objects.Object;
import objects.Software;


public class SoftwareEditor extends JFrame implements ActionListener
{
	private Object givenObject = null;



	/**
	 * Javadoc-TODO - Description NEEDED!
	 * 
	 * @param obj
	 */
	public SoftwareEditor(Object obj)
	{
		super("Edit software");

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


		// view = new HardwareEditorTabbed(obj);
		//
		// panel.add(view);



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



	/**
	 * Creates a JPanel that shows an Icon representing the hardware object and
	 * two fields with the name and description of the hardware object.
	 * 
	 * @param sw
	 *            The actual hardware object.
	 * @param icon
	 *            The Icon representing the hardware component.
	 * @param name
	 *            A JTextField that will contain the name of the object.
	 * @param desc
	 *            A JTextArea that holds the description of the object.
	 * @return Returns the created JPanel with all the information about the
	 *         hardware object.
	 */
	public static JPanel GeneralInfo(Software sw, ImageIcon icon,
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
		name.setText(sw.getObjectName());
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

		desc.setName("Description");
		desc.setBorder(BorderFactory.createEtchedBorder());
		desc.setText(sw.getDescription());
		desc.setFont(t.getFont());
		genPanel.add(desc, d);


		return genPanel;
	}





	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
