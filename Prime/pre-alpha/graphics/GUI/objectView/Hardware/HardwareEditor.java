/**
 * 
 */
package graphics.GUI.objectView.Hardware;

import graphics.ImageLocator;

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

import objects.Hardware;
import objects.Object;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class HardwareEditor extends JFrame implements ActionListener
{
	private Object givenObject = null;
	
	public HardwareEditor(Object obj)
	{
		super("Edit hardware");
		
		givenObject = obj;
		
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		int width = ((int) (scrnsize.getWidth() - (scrnsize.getWidth()/3)));

		int height = ((int) (scrnsize.getHeight() - (scrnsize.getHeight()/3)));
		
		
		// Get the content pane for this object
		Container c = this.getContentPane();

		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		
		
		HardwareEditorTabbed view = new HardwareEditorTabbed(obj.getComponents());
		
		panel.add(view);
		
		
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.TRAILING));
		
		Button save = new Button("Save");
		save.addActionListener(this);
		save.setActionCommand("save");
		
		Button apply = new Button("Apply");
		apply.addActionListener(this);
		apply.setActionCommand("apply");
		
		Button cancel = new Button("cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		
		
		buttons.add(save);
		buttons.add(apply);
		buttons.add(cancel);
		
		buttons.setMaximumSize(new Dimension((int) scrnsize.getWidth(),1));
		
		panel.add(buttons);
		
		
		
		c.add(panel);
		
		this.setMinimumSize(new Dimension((int) scrnsize.getWidth() / 3,(int) scrnsize.getHeight() / 3));
		this.setSize(width, height);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * TODO - Description
	 * 
	 */
	public static JPanel GeneralInfo(Hardware hw,ImageIcon temp)
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
		
		JLabel image = new JLabel(temp);
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
		
		JLabel nameLabel = new JLabel("Motherboard name");
		genPanel.add(nameLabel,d);

		d.gridx = 2;
		d.gridy = 0;
		d.weightx = 1;
		d.weighty = 0.5;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;
		
		JTextField name = new JTextField(25);
		name.setText(hw.getObjectName());
		JLabel t = new JLabel();
		name.setFont(t.getFont());
		//	name.setBorder(BorderFactory.createEmptyBorder());
		genPanel.add(name,d);
		
		
		d.fill = GridBagConstraints.NONE;
		d.gridx = 1;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.CENTER;
		
		JLabel descLabel = new JLabel("Description");
		genPanel.add(descLabel,d);
		
		
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridx = 2;
		d.gridy = 1;
		d.weightx = 1;
		d.weighty = 0.1;
		d.gridwidth = 1;
		d.gridheight = 1;
		d.anchor = GridBagConstraints.LINE_START;
		
		JTextArea desc = new JTextArea(3,40);
		desc.setBorder(BorderFactory.createEtchedBorder());
		desc.setText(hw.getDescription());
		desc.setFont(t.getFont());
		genPanel.add(desc,d);
		
		
		return genPanel;
	}
	
}
