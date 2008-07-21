/**
 * 
 */
package graphics.GUI.objectView.Hardware;

import graphics.PrimeMain1;
import graphics.GUI.objectView.ObjectViewTabbed;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

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


		int width = ((int) scrnsize.getWidth()) / 2;

		int height = ((int) scrnsize.getHeight()) / 2;
		
		
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
}
