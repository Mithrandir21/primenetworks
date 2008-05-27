/**
 * 
 */
package graphics;

import graphics.GUI.workareaCanvas.WorkareaTabbed;
import graphics.GUI.menues.GenericPrimeMenuBar;
import graphics.GUI.menues.GenericPrimeToolbar;
import graphics.GUI.selectArea.TabbedSelection;
import graphics.GUI.statusArea.PrimeStatusBar;

import java.io.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;

import javax.swing.*;
import javax.swing.border.Border;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.MultiSplitLayout.*;
import org.netbeans.api.visual.widget.Scene;


/**
 * Description NEEDED!
 *
 * @author Bahram Malaekeh
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PrimeMain1 extends JFrame implements ActionListener
{
	
	
	
	//Main window panel setup
	private JPanel toolbarPanel,selectionPanel,workareaPanel,propertiesPanel
				  ,messagesPanel;
	
	static JXMultiSplitPane multiSplitPane = new JXMultiSplitPane();
	
	
	// A simple border that is gray 
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);
	
	
	public static Scene scene = new Scene();
	
	
	
	// Constructor
	public PrimeMain1()
	{
		super("Prime");
		
		
		// SETUP FOR THE OEVERALL GUI
		// Get the content pane for this object
		Container c = this.getContentPane();
		
				
		/* 
		 * SETUP FOR THE toolbarPanel PANEL. This panel will contain all the toolbar buttons and
		 * icons.
		 */
		// Creates a new panel with a GridBagLayout.
		toolbarPanel = new JPanel(new BorderLayout());
		
		//toolbarPanel.setSize(11, 6);
		
		// Constraints for the window
		GridBagConstraints conToolbarPanel = new GridBagConstraints();
		
		// Sets the constraints to fill both vertical and horizontal
		conToolbarPanel.fill = GridBagConstraints.BOTH;
		
		// Sets the border around the panel
		toolbarPanel.setBorder(grayline);
		
		toolbarPanel.add(new GenericPrimeToolbar());		
		
		
		//toolbarPanel.add(new JLabel("Toolbar"));
		
		/* 
		 * SETUP FOR THE selectionPanel PANEL. This panel will contain all the selectable icons that
		 * can be places within the workarea, like client, server or printer icons.
		 * This will also contain the Project selection tab, where a user can pick
		 * different projects to work on in the workarea.
		 */
		// Creates a new panel with a GridBagLayout.
		selectionPanel = new JPanel(new GridLayout());
		
		// Constraints for the window
		GridBagConstraints conSelectionPanel = new GridBagConstraints();
		
		// Sets the constraints to fill both vertical and horizontal
		conSelectionPanel.fill = GridBagConstraints.BOTH;
		
		// Sets the border around the panel
		//selectionPanel.setBorder(grayline);
		
		//selectionPanel.add(new JLabel("Selection"));
		
		selectionPanel.add(new TabbedSelection());
		
		
		
		/* 
		 * SETUP FOR THE propertiesPanel PANEL. This panel will contain some, if not all, of
		 * the properties of the selected object.
		 */
		// Creates a new panel with a GridBagLayout.
		propertiesPanel = new JPanel(new GridBagLayout());
		
		// Constraints for the window
		GridBagConstraints conPropertiesPanel = new GridBagConstraints();
		
		// Sets the constraints to fill both vertical and horizontal
		conPropertiesPanel.fill = GridBagConstraints.BOTH;
		
		// Sets the border around the panel
		propertiesPanel.setBorder(grayline);
		
		propertiesPanel.add(new JLabel("Properties"));
		
		/* 
		 * SETUP FOR THE messagesPanel PANEL. This panel will contain the errors, warnings, and 
		 * suggestions tabs that will give information to the user about different aspects
		 * of their network setup. 
		 */
		// Creates a new panel with a GridBagLayout.
		messagesPanel = new JPanel(new GridBagLayout());
		
		// Constraints for the window
		GridBagConstraints conMessagesPanel = new GridBagConstraints();
		
		// Sets the constraints to fill both vertical and horizontal
		conMessagesPanel.fill = GridBagConstraints.BOTH;
		
		// Sets the border around the panel
		messagesPanel.setBorder(grayline);
		
		messagesPanel.add(new JLabel("Messages"));
		
		
		/* 
		 * SETUP FOR THE workareaPanel PANEL. This panel will contain the actual work area where
		 * all of the icons and connection will be shown. This is where the "map" of the network will
		 * be.
		 */
		// Creates a new panel with a GridBagLayout.
		workareaPanel = new JPanel(new BorderLayout());
		
		// Constraints for the window
		GridBagConstraints conWorkareaPanel = new GridBagConstraints();
		
		// Sets the constraints to fill both vertical and horizontal
		//conWorkareaPanel.fill = GridBagConstraints.BOTH;
		
		// Sets the border around the panel
		workareaPanel.setBorder(grayline);
		
		//workareaPanel.add(new JLabel("Workarea"));
		
	    //workareaPanel.add(new WorkareaCanvas());
        workareaPanel.add(new WorkareaTabbed());
        
	
		
		
		this.setJMenuBar(new GenericPrimeMenuBar());
	
		this.add(new PrimeStatusBar(), BorderLayout.SOUTH);
		
		makeLayoutModel();
		//LoadLayoutModel();
		
		
		multiSplitPane.setDividerSize(5);
			
		multiSplitPane.setContinuousLayout(true);
		
		
		multiSplitPane.add(toolbarPanel, "toolbarLeaf");
		multiSplitPane.add(selectionPanel, "selectionLeaf");
	    multiSplitPane.add(workareaPanel, "workareaLeaf");
	    multiSplitPane.add(propertiesPanel, "propertiesLeaf");
	    multiSplitPane.add(messagesPanel, "messagesLeaf");
	    
	    
		
		// Adding everything to the contentpane of the JFrame
		c.add(multiSplitPane, BorderLayout.CENTER);
		
		
		
	    this.setSize(1600,1000);
	    this.setVisible(true);
	    
	}
	

	/**
	 * Description
	 * 
	 */
	public static void main(String[] args)
	{
		PrimeMain1 temp = new PrimeMain1();
		
		temp.addWindowListener(new WindowAdapter() 
			{
            	public void windowClosing(WindowEvent ev) 
            	{
            		//SaveLayoutModel();
            		System.exit(0);
            	}
			}
		);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	
	
	private static void SaveLayoutModel()
	{
		try 
		{
			XMLEncoder output = 
			    new XMLEncoder(new BufferedOutputStream(
			            new FileOutputStream("PrimeLayoutModel.xml")));
			
			output.writeObject(multiSplitPane);
			output.close();
			//JOptionPane.showMessageDialog(null,"Saved model.");
		} 
		catch (FileNotFoundException output) 
		{
			//JOptionPane.showMessageDialog(null,"Error saving layout model.");
		}
	}
	
	private void makeLayoutModel()
	{
		String layoutDef =
		    "(COLUMN weight=1.0" +
		    	"(LEAF name=toolbarLeaf) " +
		    		"(ROW " +
		    			"(LEAF name=selectionLeaf) " +
		    			"(COLUMN " +
		    				"(ROW " +
		    					"(LEAF name=workareaLeaf weight=0.7)" +
		    					"(LEAF name=propertiesLeaf) " +
		    				")" +
		    				"(LEAF name=messagesLeaf weight=0.2) " +
		    			")" +
		    		")" +
		    ")";
		
		//JOptionPane.showMessageDialog(null,"Error Loading layout Model.");
		Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
	    multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
	    multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
	}
	
	private void LoadLayoutModel()
	{
		String layoutDef =
		    "(COLUMN weight=1.0" +
		    	"(LEAF name=toolbarLeaf) " +
		    		"(ROW " +
		    			"(LEAF name=selectionLeaf) " +
		    			"(COLUMN " +
		    				"(ROW " +
		    					"(LEAF name=workareaLeaf weight=0.7)" +
		    					"(LEAF name=propertiesLeaf) " +
		    				")" +
		    				"(LEAF name=messagesLeaf weight=0.2) " +
		    			")" +
		    		")" +
		    ")";
		
		
		try 
		{
		    XMLDecoder d = 
		        new XMLDecoder(new BufferedInputStream(
		            new FileInputStream("PrimeLayoutModel.xml")));
		    

		    multiSplitPane = (JXMultiSplitPane)(d.readObject());
		    d.close();
		    //JOptionPane.showMessageDialog(null,"Loaded model.");
		}
		catch (Exception exc) 
		{ 
			//JOptionPane.showMessageDialog(null,"Error Loading layout Model.");
			Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		    multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		    multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
		}		
	}

}
