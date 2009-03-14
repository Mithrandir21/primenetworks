/**
 * 
 */
package graphics;


import graphics.GUI.menues.GenericPrimeMenuBar;
import graphics.GUI.menues.GenericPrimeToolbar;
import graphics.GUI.messageArea.MessageTabbed;
import graphics.GUI.objectView.ObjectView;
import graphics.GUI.properties.PropertiesArea;
import graphics.GUI.selectArea.TabbedSelection;
import graphics.GUI.statusArea.PrimeStatusBar;
import graphics.GUI.workareaCanvas.WorkareaCanvas;
import graphics.GUI.workareaCanvas.WorkareaTabbed;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import objects.Object;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.MultiSplitLayout;
import org.jdesktop.swingx.MultiSplitLayout.Node;


/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */
@SuppressWarnings("serial")
public class PrimeMain1 extends JFrame
{
	// Variables to place the height and width of the main screen.
	public static int width, height;

	// Main window panel setup
	private JPanel toolbarPanel, selectionPanel;

	// The tab selection area where one can open projects and create new objects
	private static TabbedSelection tabSelection;
	
	// The JPanel where the properties of any selected object or network
	// overview is shown.
	public static JPanel propertiesPanel;
	
	// The JPanel that will show information about the objects and the network.
	private static JPanel messagesPanel;
	
	// The JPanel that will hold all the canvas.
	private static JPanel workareaPanel;
	
	public static WorkareaTabbed workTab;

	static JXMultiSplitPane multiSplitPane = new JXMultiSplitPane();

	// A simple border that is gray
	Border grayline = BorderFactory.createLineBorder(Color.GRAY);

	// An array that points to all the currently open canvases in the workarea.
	public static WorkareaCanvas[] canvases = new WorkareaCanvas[3];


	// A pointer to the currently open canvas that is displayed in the workarea.
	public static WorkareaCanvas currentCanvas = null;

	// An ImageIcon array that contains all the icons used in the system.
	public static ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(50);

	// A pointer to where all standard internal components are kept.
	public static MakeStandardInternalComponents standard_internal_components = new MakeStandardInternalComponents();
	
	// A pointer to where all standard softwares are kept.
	public static MakeStandardSoftware standard_software = new MakeStandardSoftware();

	// The variable for the object that is in view.
	private static ArrayList<ObjectView> objView = new ArrayList<ObjectView>(1);
	


	// Constructor
	public PrimeMain1()
	{
		super("Prime");
		
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();


		width = ((int) scrnsize.getWidth()) - 200;

		height = ((int) scrnsize.getHeight()) - 200;


		final SplashScreen splash = SplashScreen.getSplashScreen();

		if ( splash == null )
		{
			// System.out.println("SplashScreen.getSplashScreen() returned
			// null");
		}
		else
		{
			Graphics2D g = splash.createGraphics();
			if ( g == null )
			{
				System.out.println("g is null");
			}
			else
			{
				for ( int i = 0; i < 2; i++ )
				{
					renderSplashFrame(g, i);
					splash.update();
					try
					{
						Thread.sleep(100);
					}
					catch ( InterruptedException e )
					{
					}
				}
				splash.close();
			}
		}


		// Creates the system icons and places them in the ImageIcon array.
		MakeSystemImageIcons standard_Image_Icons = new MakeSystemImageIcons();
		standard_Image_Icons.getImageIcons();


		// SETUP FOR THE OEVERALL GUI
		// Get the content pane for this object
		Container c = this.getContentPane();


		/*
		 * SETUP FOR THE toolbarPanel PANEL. This panel will contain all the
		 * toolbar buttons and icons.
		 */
		// Creates a new panel with a GridBagLayout.
		toolbarPanel = new JPanel(new BorderLayout());

		// toolbarPanel.setSize(11, 6);

		// Constraints for the window
		GridBagConstraints conToolbarPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conToolbarPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// toolbarPanel.setBorder(grayline);

		toolbarPanel.add(new GenericPrimeToolbar());


		// toolbarPanel.add(new JLabel("Toolbar"));

		/*
		 * SETUP FOR THE selectionPanel PANEL. This panel will contain all the
		 * selectable icons that can be places within the workarea, like client,
		 * server or printer icons. This will also contain the Project selection
		 * tab, where a user can pick different projects to work on in the
		 * workarea.
		 */
		// Creates a new panel with a GridBagLayout.
		selectionPanel = new JPanel(new GridLayout());

		// Constraints for the window
		GridBagConstraints conSelectionPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conSelectionPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// selectionPanel.setBorder(grayline);

		// selectionPanel.add(new JLabel("Selection"));

		tabSelection = new TabbedSelection();
		selectionPanel.add(tabSelection);



		/*
		 * SETUP FOR THE propertiesPanel PANEL. This panel will contain some, if
		 * not all, of the properties of the selected object.
		 */
		// Creates a new panel with a GridBagLayout.
		propertiesPanel = new JPanel(new BorderLayout());

		// Constraints for the window
		GridBagConstraints conPropertiesPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conPropertiesPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		// propertiesPanel.setBorder(grayline);

		propertiesPanel.add(new PropertiesArea());

		// propertiesPanel.setPreferredSize(new Dimension(200,300));

		/*
		 * SETUP FOR THE messagesPanel PANEL. This panel will contain the
		 * errors, warnings, and suggestions tabs that will give information to
		 * the user about different aspects of their network setup.
		 */
		// Creates a new panel with a GridBagLayout.
//		messagesPanel = new JPanel(new GridLayout(0,1));
		messagesPanel = new JPanel(new BorderLayout());

		// Constraints for the window
		GridBagConstraints conMessagesPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		conMessagesPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		messagesPanel.setBorder(grayline);

		MessageTabbed msgTabs = new MessageTabbed();
		messagesPanel.add(msgTabs, BorderLayout.CENTER);
		msgTabs.createInitialTabs();
//		messagesPanel.add(new JLabel("Messages"));


		/*
		 * SETUP FOR THE workareaPanel PANEL. This panel will contain the actual
		 * work area where all of the icons and connection will be shown. This
		 * is where the "map" of the network will be.
		 */
		// Creates a new panel with a GridBagLayout.
		workareaPanel = new JPanel(new BorderLayout());

		// Constraints for the window
		GridBagConstraints conWorkareaPanel = new GridBagConstraints();

		// Sets the constraints to fill both vertical and horizontal
		// conWorkareaPanel.fill = GridBagConstraints.BOTH;

		// Sets the border around the panel
		workareaPanel.setBorder(grayline);

		workTab = new WorkareaTabbed();
		
		// workareaPanel.add(new WorkareaCanvas());
		workareaPanel.add(workTab);




		this.setJMenuBar(new GenericPrimeMenuBar());

		this.add(new PrimeStatusBar(), BorderLayout.SOUTH);

		makeLayoutModel();
		// LoadLayoutModel();


		multiSplitPane.setDividerSize(5);

		multiSplitPane.setContinuousLayout(true);


		multiSplitPane.add(toolbarPanel, "toolbarLeaf");
		multiSplitPane.add(selectionPanel, "selectionLeaf");
		multiSplitPane.add(workareaPanel, "workareaLeaf");
		multiSplitPane.add(propertiesPanel, "propertiesLeaf");
		multiSplitPane.add(messagesPanel, "messagesLeaf");



		// Adding everything to the contentpane of the JFrame
		c.add(multiSplitPane, BorderLayout.CENTER);



		this.setSize(width, height);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);

	}


	/**
	 * Description
	 */
	public static void main(String[] args)
	{
		PrimeMain1 temp = new PrimeMain1();

		temp.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent ev)
			{
				// SaveLayoutModel();
				System.exit(0);
			}
		});

	}


	/**
	 */
	private static void SaveLayoutModel()
	{
		try
		{
			XMLEncoder output = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream("PrimeLayoutModel.xml")));

			output.writeObject(multiSplitPane);
			output.close();
			// JOptionPane.showMessageDialog(null,"Saved model.");
		}
		catch ( FileNotFoundException output )
		{
			// JOptionPane.showMessageDialog(null,"Error saving layout model.");
		}
	}


	/**
	 */
	private void makeLayoutModel()
	{
		String layoutDef = "(COLUMN (LEAF name=toolbarLeaf) (ROW (LEAF name=selectionLeaf) "
				+ "(COLUMN (ROW (LEAF name=workareaLeaf weight=0.6) "
				+ "(LEAF name=propertiesLeaf weight=0.2) )"
				+ "(LEAF name=messagesLeaf weight=1)  ) ) )";

		// JOptionPane.showMessageDialog(null,"Error Loading layout Model.");
		Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
		multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
		multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
	}


	/**
	 */
	private void LoadLayoutModel()
	{
		String layoutDef = "(COLUMN (LEAF name=toolbarLeaf) (ROW (LEAF name=selectionLeaf) "
				+ "(COLUMN (ROW (LEAF name=workareaLeaf weight=0.6) "
				+ "(LEAF name=propertiesLeaf weight=0.2) )"
				+ "(LEAF name=messagesLeaf weight=1)  ) ) )";


		try
		{
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(
					new FileInputStream("PrimeLayoutModel.xml")));


			multiSplitPane = (JXMultiSplitPane) (d.readObject());
			d.close();
			// JOptionPane.showMessageDialog(null,"Loaded model.");
		}
		catch ( Exception exc )
		{
			// JOptionPane.showMessageDialog(null,"Error Loading layout
			// Model.");
			Node modelRoot = MultiSplitLayout.parseModel(layoutDef);
			multiSplitPane.getMultiSplitLayout().setModel(modelRoot);
			multiSplitPane.setPreferredSize(modelRoot.getBounds().getSize());
		}
	}


	/**
	 */
	static void renderSplashFrame(Graphics2D g, int frame)
	{
		final String[] comps = { "Bam", "Pegah", "Lille-Bam", "Bitch" };
		g.setComposite(AlphaComposite.Clear);
		g.fillRect(120, 140, 200, 40);
		g.setPaintMode();
		g.setColor(Color.BLACK);
		g.drawString("Loading " + comps[(frame / 5) % 4] + "...", 120, 150);
	}

	
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	public static void updatePrimeTree()
	{
		tabSelection.getPrimeTree().createTree();
		tabSelection.validate();
		tabSelection.repaint();
	}


	/**
	 * Updates the properties panel with information from the currently selected
	 * canvas.
	 */
	public static void updatePropertiesCanvasArea()
	{
		PropertiesArea temp = (PropertiesArea) propertiesPanel.getComponent(0);

		temp.newObjectSelectedPropertiesTab(currentCanvas);
	}



	/**
	 * Updates the properties panel with information from the given object.
	 */
	public static void updatePropertiesObjectArea(Object obj)
	{
		PropertiesArea temp = (PropertiesArea) propertiesPanel.getComponent(0);

		temp.newObjectSelectedPropertiesTab(obj);
	}
	
	
	
	/**
	 * Thi method runs all update function. These include functions to
	 * update the canvas, properties panel and messages panel.
	 * 
	 */
	public static void updateCanvasAndObjectInfo()
	{
		runCanvasObjectCheck();
	}
	
	
	
	/**
	 * Runs a check on all the objects on the current canvas
	 * and passed that information over to the JTable that shows¨
	 * different errors, warnings and notices.
	 */
	public static void runCanvasObjectCheck()
	{
		Object[] children = currentCanvas.getObjectsOnTheScene();
		
		
		MessageTabbed msgTab = (MessageTabbed) messagesPanel.getComponent(0);
		
		msgTab.processAllObjects(children);
	}
	
	
	
	/**
	 * Searches the object views that exist to find the given object
	 * as one of the views main object. If found that view is returned.
	 */
	public static ObjectView getObjectView(Object obj)
	{
		Iterator<ObjectView> i = objView.iterator();
		while (i.hasNext()) 
		{
			ObjectView objViewTest = i.next();
			
			if(obj == objViewTest.getObject())
			{
				return objViewTest;
			}
            
        }
		
		return null;
	}
	
	
	
	/**
	 * Adds a view to the arraylist of views.
	 */
	public static void addObjectView(ObjectView view)
	{
		objView.add(view);
	}
	
	
	/**
	 * Removes the view, if it exist, that has as its main object
	 * the given object.
	 */
	public static void removeObjectView(Object obj)
	{
		ObjectView view = getObjectView(obj);
		
		if(view != null)
		{
			objView.remove(view);
		}
	}


	/**
	 * TODO - Description NEEDED!
	 *
	 * @param workareaPanel the workareaPanel to set
	 */
	public static void setWorkareaPanel(JPanel workareaPanel)
	{
		PrimeMain1.workareaPanel = workareaPanel;
	}
	
	
	
	/**
	 * Javadoc-TODO - Description
	 * 
	 * @return workareaPanel the workareaPanel to get
	 */
	public static JPanel getWorkareaPanel()
	{
		return workareaPanel;
	}


	/**
	 * TODO - Description NEEDED!
	 *
	 * @return the workareaPanel
	 */
	public static WorkareaTabbed getWorkarea()
	{
		return workTab;
	}
}
