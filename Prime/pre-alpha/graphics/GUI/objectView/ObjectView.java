package graphics.GUI.objectView;

import graphics.PrimeMain1;
import graphics.GUI.properties.PropertiesArea;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.Object;

import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;

/**
 * TODO - Description NEEDED!
 *
 * @author Bahram Malaekeh
 * 
 */
public class ObjectView extends JFrame implements ActionListener
{
	private ObjectViewTabbed view;
	
	private WidgetObject widgetObj;
	
	private Object currentObject;
	
	
	/**
	 * TODO - Description NEEDED!
	 *
	 */
	public ObjectView(WidgetObject obj)
	{
		super("Object View");
		
		widgetObj = obj;
		
		currentObject = obj.getObject();
		
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
		
		
		view = new ObjectViewTabbed(currentObject);
		
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

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("save"))
		{
			String viewNameText = view.genObjView.nametext.getText();
			
			if(!currentObject.getObjectName().equals(viewNameText))
			{
				currentObject.setObjectName(viewNameText);
				
				List<Widget> children = widgetObj.getChildren();
				
				LabelWidget label = null;
				
				
				for (Iterator<Widget> iter = children.iterator(); iter.hasNext();) 
				{
					Widget temp = (Widget) iter.next();
					if ( temp instanceof LabelWidget )
					{
						label = (LabelWidget) temp;
					}
				}
				
				if(label!=null)
				{
					label.setLabel(viewNameText);
				}
			}
			
			
			if(!currentObject.getDescription().equals(view.genObjView.textarea.getText()))
			{
				currentObject.setDescription(view.genObjView.textarea.getText());
			}
			
			PrimeMain1.updatePropertiesObjectArea(widgetObj.getObject());
			
			this.dispose();
		}
		else if(e.getActionCommand().equals("apply"))
		{
			if(!currentObject.getObjectName().equals(view.genObjView.nametext.getText()))
			{
				currentObject.setObjectName(view.genObjView.nametext.getText());
			}
			
			
			if(!currentObject.getDescription().equals(view.genObjView.textarea.getText()))
			{
				currentObject.setDescription(view.genObjView.textarea.getText());
			}
			
			
			PrimeMain1.updatePropertiesObjectArea(widgetObj.getObject());
		}
		else 
		{
			assert e.getActionCommand().equals("cancel");
			
			this.dispose();
		}
		
	}
}
