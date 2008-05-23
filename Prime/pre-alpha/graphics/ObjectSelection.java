/**
 * 
 */
package graphics;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * NOTE - Maybe set inn a vertical tab for all the different object categories.
 */

/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
public class ObjectSelection extends JPanel
{
	
	// A simple border that is gray 
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);
	
	
	
	public ObjectSelection()
	{
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(grayline);
		
		initClientButtonIcons();
		
		initServerButtonIcons();
		
		initHardwareButtonIcons();
		
		initExternalHardwareButtonIcons();
		
		initInfrastructureButtonIcons();
		
		initSoftwareButtonIcons();
		
		///////////////
		JTextField tf = new JTextField();
	    //tf.setDragEnabled(true);
	    
	    this.add(tf);
	    //////////////
	}
	
	
	private void initClientButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/client.png","Desktop"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/client.png","Laptop"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/client.png","Thin Client"));
	
		this.add(new JToolBar.Separator());
		
		
		this.add(new JSeparator());
	}
	
	
	private void initServerButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/server.png","Server"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/web-server.png","Web Server"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/data-server.png","Data Server"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/email-server.png","Email Server"));
	
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/firewall-server.png","Firewall Server"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/proxy-server.png","Proxy Server"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/print-server.png","Print Server"));
		
		this.add(new JToolBar.Separator());
		

		this.add(new JSeparator());
	}
	
	private void initHardwareButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/harddisc.png","Harddisc"));
		
		this.add(new JToolBar.Separator());
		
		
		this.add(new JSeparator());
	}
	
	private void initExternalHardwareButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/scanner.png","Scanner"));
		
		this.add(new JToolBar.Separator());
		
		
		this.add(new JSeparator());
	}

	
	private void initInfrastructureButtonIcons()
	{
		this.add(makeImageIcon("images/objectImages/hub.png","Hub"));
	
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/switch.jpg","Switch"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/router.jpg","Router"));
		
		this.add(new JToolBar.Separator());
		
		this.add(makeImageIcon("images/objectImages/WirelessRouter.gif","Wireless Router"));
		
		this.add(new JToolBar.Separator());
		

		this.add(new JSeparator());
	}
	
	
	private void initSoftwareButtonIcons()
	{
		
	}
	
	
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	
	private JLabel makeImageIcon(String imgURL,String text)
	{
		Icon imageIcon = createImageIcon(imgURL);
		JLabel iconButton = new JLabel(imageIcon);
		iconButton.setName("Bam");
		iconButton.setText(text);		

		MouseListener mouseListener = new MouseAdapter() 
		{
		      public void mousePressed(MouseEvent e) 
		      {
		        JComponent comp = (JComponent) e.getSource();
		        TransferHandler handler = comp.getTransferHandler();
		        handler.exportAsDrag(comp, e, TransferHandler.COPY);
		      }
		};
		
		iconButton.setTransferHandler(new ImageSelection());
		
		iconButton.addMouseListener(mouseListener);
		
		iconButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		iconButton.setAlignmentY(Component.TOP_ALIGNMENT);
		//iconButton.setBorder(grayline);
		
		return iconButton;
	}
	
	
	private JLabel makeCenteredJLabel(String text)
	{
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setAlignmentY(Component.TOP_ALIGNMENT);
		
		
		return label;
	}
	
	

}
