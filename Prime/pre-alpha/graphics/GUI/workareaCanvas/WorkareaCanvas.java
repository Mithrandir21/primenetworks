/**
 * 
 */
package graphics.GUI.workareaCanvas;


import graphics.ImageLocator;
import graphics.PrimeMain1;
import graphics.WidgetIcon;
import graphics.GUI.selectArea.CreateObjectDragged;
import graphics.GUI.workareaCanvas.providers.AdapterExtended;
import graphics.GUI.workareaCanvas.providers.CanvasMenu;
import graphics.GUI.workareaCanvas.providers.CreateProvider;
import graphics.GUI.workareaCanvas.providers.JMenuProvider;
import graphics.GUI.workareaCanvas.providers.SceneConnectProvider;
import infrastructure.Hub;
import infrastructure.Router;
import infrastructure.Switch;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.border.BorderFactory;
import org.netbeans.api.visual.model.ObjectScene;
import org.netbeans.api.visual.widget.LabelWidget;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Scene;

import peripheral.Scanner;
import servers.BackupServer;
import servers.FirewallServer;
import servers.HTTPServer;
import servers.MailServer;
import servers.ProxyServer;
import widgetManipulation.WidgetObject;
import clients.Laptop;
import connections.Connection;




/**
 * A visual canvas that will hold all the object of any given network. The
 * object can be moved around, deleted or connected to other object. The canvas
 * has a come special feature like zooming, scrolling and panning.
 * 
 * The canvas has D'n'D(Drag and Drop) features. Any Widget can be dragged and
 * dropped onto the the canvas. The widget will then be converted into a
 * {@link WidgetObject WidgetObject} and a standard object will be created. The
 * object class depends on the class of the dragged widget. The object will be
 * created and place within the WidgetObject.
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas extends JPanel implements DropTargetListener, ActionListener
{
	private String CanvasName;

	private TransferHandler TransHandler = new WidgetTransferHandler();

	private DropTarget dt;

	private JPopupMenu popup = CanvasMenu.createPopupMenu(this);


	private ObjectScene scene = new ObjectScene();

	private JComponent myView = scene.createView();

	private LayerWidget mainLayer;

	private LayerWidget interactionLayer;

	private LayerWidget connectionLayer;

	private Connection[] connections = new Connection[5];


	// TODO - Create array of amount of different object types on the scene.
	private int numberOfWidgetsOnTheScene = 0;
	
	private int numberOfNICs = 0;


	private WidgetObject currentWidgetObject = null;




	/**
	 * A constructor for the canvas class. This constructor will set the drop
	 * handling and all the special features the canvas has.
	 */
	public WorkareaCanvas()
	{
		// Creating the actual view
		myView.setTransferHandler(TransHandler);

		dt = new DropTarget(myView, this);


		// scene.getActions().addAction(ActionFactory.createSelectAction(new
		// CreateProvider()));


		// Adds the zoom feature to the scene.
		scene.getActions().addAction(ActionFactory.createZoomAction());
		scene.getActions().addAction(ActionFactory.createPanAction());

		// This is the main layer of the scene where the widgets, objects, are
		// placed.
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);

		// This is the interaction layer
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);

		// This is the connection layer where all the connections between the
		// objects are shown.
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);
	}


	/**
	 * Gets the name of the canvas.
	 * 
	 * @return the canvasName
	 */
	public String getCanvasName()
	{
		return CanvasName;
	}


	/**
	 * Gets the scene.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene Scene
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}


	/**
	 * Gets the view of the scene. Scene can only be viewed through a view like
	 * this.
	 * 
	 * @see org.netbeans.api.visual.widget.Scene#getView() View
	 * @return the myView
	 */
	public JComponent getMyView()
	{
		return myView;
	}


	/**
	 * Gets the main layer of the scene. This is where the {@link WidgetObject
	 * WidgetObjects} are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the mainLayer
	 */
	public LayerWidget getMainLayer()
	{
		return mainLayer;
	}


	/**
	 * Gets the interaction layer of the scene. This is where the actions a user
	 * sees take place like clicks and menues.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the interactionLayer
	 */
	public LayerWidget getInteractionLayer()
	{
		return interactionLayer;
	}


/**
	 * Gets the connection layer of the scene. This is where the
	 * {@link connections.WidgetExtendedConnection connections) between objects in the scene are placed.
	 * 
	 * @see org.netbeans.api.visual.widget.LayerWidget LayerWidget
	 * @return the connectionLayer
	 */
	public LayerWidget getConnectionLayer()
	{
		return connectionLayer;
	}


	/**
	 * Gets the number of objects on the Scene.
	 * 
	 * @return the numberOfWidgetsOnTheScene
	 */
	public int getNumberOfWidgetsOnTheScene()
	{
		return numberOfWidgetsOnTheScene;
	}
	
	
	/**
	 * Gets the number of Networks cards on the Scene.
	 * 
	 * @return
	 */
	public int getNumberOfNICsOnTheScene()
	{
		return numberOfNICs;
	}


	/**
	 * Gets the array of connections between objects.
	 * 
	 * <i>Note: These connections are not the visual connections shown on the
	 * scene, but the actual connections between the objects within the
	 * different widgetObjects.</i>
	 */
	public Connection[] getConnections()
	{
		return connections;
	}


	/**
	 * Gets the currently selected widgetObject.
	 * 
	 * @return Returns the currently selected widgetObject.
	 */
	public WidgetObject getCurrentWidgetObject()
	{
		return currentWidgetObject;
	}


	/**
	 * Sets the given string as the canvas name.
	 * 
	 * @param canvasName
	 *            The name of the canvas.
	 */
	public void setCanvasName(String canvasName)
	{
		CanvasName = canvasName;
	}


	/**
	 * Sets the connections array for the canvas.
	 * 
	 * <i>Note: These connections are not the visual connections shown on the
	 * scene, but the actual connections between the objects within the
	 * different widgetObjects.</i>
	 * 
	 * @param connections
	 */
	public void setConnections(Connection[] connections)
	{
		this.connections = connections;
	}



	/**
	 * Sets the current widgetObject.
	 * 
	 * @param currentWidgetObject
	 */
	public void setCurrentWidgetObject(WidgetObject currentWidgetObject)
	{
		if ( currentWidgetObject == null )
		{
			this.currentWidgetObject = null;
		}
		else
		{
			this.currentWidgetObject = currentWidgetObject;
		}
	}
	
	
	
	/**
	 * Adds 1 to the number of nics on the scene.
	 * 
	 */
	public void addNIC()
	{
		numberOfNICs++;
	}
	

	// ------------------ TRANSFER METHODES -----------------------





	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.dnd.DropTargetListener#dragEnter(java.awt.dnd.DropTargetDragEvent
	 * )
	 */
	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		// System.out.println(getCanvasName());
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.dnd.DropTargetListener#dragExit(java.awt.dnd.DropTargetEvent)
	 */
	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// System.out.println("Drag Exit");
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.dnd.DropTargetListener#dragOver(java.awt.dnd.DropTargetDragEvent
	 * )
	 */
	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Over");
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.dnd.DropTargetListener#drop(java.awt.dnd.DropTargetDropEvent)
	 */
	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		// System.out.println("Drag Drop");
		Transferable tr = dtde.getTransferable();
		DataFlavor f[] = tr.getTransferDataFlavors();
		WidgetObject newObject = null;


		try
		{
			newObject = (WidgetObject) tr.getTransferData(new DataFlavor(WidgetObject.class,
					"Widget Object"));

			Dimension objectSize = newObject.getImageDimension();

			Point objectPoint = dtde.getLocation();

			dtde = null;


			int height = objectPoint.x - (objectSize.height / 2);

			int width = objectPoint.y - (objectSize.width / 2);

			objectPoint.setLocation(height, width);


			// objectPoint.setLocation(objectPoint);


			// LabelWidget i = new LabelWidget(scene, "This is it");


			addWidgetObject(newObject, objectPoint);


			cleanUp();
		}
		catch ( UnsupportedFlavorException e )
		{
			System.out.println("WorkareaCanvas - UnsupportedFlavorException");
		}
		catch ( IOException e )
		{
			System.out.println("WorkareaCanvas - IOException");
		}

	}



	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.awt.dnd.DropTargetListener#dropActionChanged(java.awt.dnd.
	 * DropTargetDragEvent)
	 */
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{

	}



	/**
	 * Adds the given WidgetObject at the given point on the scene. This method
	 * adds all the functionality that a widgetObject will have like being able
	 * to connect to other widgets, being clicked or dragged.
	 * 
	 * It also sets a description and places an empty border around the
	 * widgetObject.
	 * 
	 * @param newObject
	 * @param objectPoint
	 */
	private void addWidgetObject(WidgetObject newObject, Point objectPoint)
	{
		// int n = JOptionPane.showConfirmDialog(this, "Would you like to add a
		// new "
		// + newObject.getObject().getName() + "?", "An Inane Question",
		// JOptionPane.YES_NO_OPTION);

		if ( true )
		{
			Point sceneLocation = scene.convertViewToScene(objectPoint);


			newObject.setPreferredLocation(sceneLocation);


			newObject.getActions().addAction(
					ActionFactory.createExtendedConnectAction(interactionLayer,
							new SceneConnectProvider()));


			//			
			newObject.getActions()
					.addAction(ActionFactory.createSelectAction(new CreateProvider()));


			newObject.getActions().addAction(
					ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));



			newObject.getActions().addAction(new AdapterExtended());


			LabelWidget objectLabel = new LabelWidget(scene, newObject.getObject().getObjectName());

			newObject.addChild(objectLabel);


			newObject.setToolTipText(newObject.getObject().getDescription());


			// Adds hovering action to the widget.
			// newObject.getActions().addAction(
			// ActionFactory.createHoverAction(new WidgetHoverProvider()));


			newObject.getActions().addAction(
					ActionFactory.createPopupMenuAction(new JMenuProvider()));



			// newObject.getActions().addAction(ActionFactory.
			// createRectangularSelectAction(
			// ActionFactory.createDefaultRectangularSelectDecorator(scene),
			// interactionLayer,
			//ActionFactory.createObjectSceneRectangularSelectProvider(scene)));


			// ----------DIFFERENT BORDERS------------
			newObject.setBorder(BorderFactory.createEmptyBorder());

			// newObject.setBorder(BorderFactory.createDashedBorder(Color.black,
			// 5, 5));

			// newObject.setBorder(BorderFactory.createBevelBorder(false));

			// newObject.setBorder(BorderFactory.createResizeBorder(5));

			// newObject.setBorder(BorderFactory.createRoundedBorder(10, 10,
			// Color.white, Color.black));

			// newObject.getActions().addAction(ActionFactory.
			// createAddRemoveControlPointAction(1.0,3.0));
			// ---------------------------------------

			mainLayer.addChild(newObject);
			numberOfWidgetsOnTheScene++;

			cleanUp();
		}
	}


	/**
	 * This method cleans up the canvas. The scene and all the views are
	 * repainted and revalidated. The properties panel is also updated.
	 */
	public void cleanUp()
	{
		doRepaint();

		PrimeMain1.updatePropertiesCanvasArea();

		scene.revalidate();
		scene.repaint();

		myView.revalidate();
		myView.repaint();

		mainLayer.revalidate();
		mainLayer.repaint();

		interactionLayer.revalidate();
		interactionLayer.repaint();

		connectionLayer.revalidate();
		connectionLayer.repaint();

		// WorkareaTabbed.canvasScroll.repaint();
		// WorkareaSceneScroll.canvas.repaint();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem action = (JMenuItem) e.getSource();

		String actionName = "";


		if ( action.getName() != null )
		{
			actionName = action.getName();
		}


		if ( !actionName.equals("") )
		{

			boolean set = false;
			Class<?> objectType = null;
			ImageIcon objectIcon = null;
			objects.Object newObject = null;
			WidgetObject newWidgetObject = null;

			if ( actionName.equals("DeleteConnectionsObject") )
			{
				WorkareaCanvasActions.removeAllConnectionsToFromObject(this, currentWidgetObject
						.getObject());
			}
			else if ( actionName.equals("DeleteThisObject") )
			{
				WorkareaCanvasActions.deleteCurrentObject(this);
			}
			else if ( actionName.equals("CreateNewST_Laptop_Item") )
			{
				objectType = Laptop.class;
				objectIcon = ImageLocator.getImageIconObject("Desktop");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Laptop_Item") )
			{
				objectType = Laptop.class;
				objectIcon = ImageLocator.getImageIconObject("Desktop");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_HTTPServer_Item") )
			{
				objectType = HTTPServer.class;
				objectIcon = ImageLocator.getImageIconObject("Web-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_MailServer_Item") )
			{
				objectType = MailServer.class;
				objectIcon = ImageLocator.getImageIconObject("Email-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_BackupServer_Item") )
			{
				objectType = BackupServer.class;
				objectIcon = ImageLocator.getImageIconObject("Data-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_FirewallServer_Item") )
			{
				objectType = FirewallServer.class;
				objectIcon = ImageLocator.getImageIconObject("Firewall-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_ProxyServer_Item") )
			{
				objectType = ProxyServer.class;
				objectIcon = ImageLocator.getImageIconObject("Proxy-server");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Hub_Item") )
			{
				objectType = Hub.class;
				objectIcon = ImageLocator.getImageIconObject("Hub");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Switch_Item") )
			{
				objectType = Switch.class;
				objectIcon = ImageLocator.getImageIconObject("Switch");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Router_Item") )
			{
				objectType = Router.class;
				objectIcon = ImageLocator.getImageIconObject("Router");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_WirelessRouter_Item") )
			{
				objectType = Router.class;
				objectIcon = ImageLocator.getImageIconObject("WirelessRouter");

				set = true;
			}
			else if ( actionName.equals("CreateNewST_Scanner_Item") )
			{
				objectType = Scanner.class;
				objectIcon = ImageLocator.getImageIconObject("Scanner");

				set = true;
			}


			if ( set == true )
			{
				WidgetIcon newObjectIcon = new WidgetIcon(objectIcon, objectType);


				newObject = new CreateObjectDragged().CreateObject(newObjectIcon,
						numberOfWidgetsOnTheScene);


				newWidgetObject = new WidgetObject(scene, newObject, objectIcon.getImage());


				addWidgetObject(newWidgetObject, new Point(0, 0));
			}
		}
	}



	/**
	 * I HATE EVERYBODY!!!!!
	 */
	public void doRepaint()
	{
		// The Nodes API can fire events outside the AWT Thread
		if ( SwingUtilities.isEventDispatchThread() )
		{
			repaint();
			scene.getScene().validate();
			// required or repaint() doesn’t work
		}
		else
		{
			SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
					repaint();
					scene.getScene().validate();
				}
			});
		}
	}


}