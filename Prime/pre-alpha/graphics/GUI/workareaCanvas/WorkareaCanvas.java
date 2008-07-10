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
import graphics.GUI.workareaCanvas.providers.PopupListener;
import graphics.GUI.workareaCanvas.providers.SceneConnectProvider;
import graphics.GUI.workareaCanvas.providers.WidgetHoverProvider;
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
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import objects.Object;

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
import clients.Desktop;
import clients.Laptop;
import connections.Connection;




/**
 * Description NEEDED!
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


	private WidgetObject currentWidgetObject = null;




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
	 * TODO - Description NEEDED!
	 * 
	 * @return the canvasName
	 */
	public String getCanvasName()
	{
		return CanvasName;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the scene
	 */
	public Scene getScene()
	{
		return scene;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the myView
	 */
	public JComponent getMyView()
	{
		return myView;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the mainLayer
	 */
	public LayerWidget getMainLayer()
	{
		return mainLayer;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the interactionLayer
	 */
	public LayerWidget getInteractionLayer()
	{
		return interactionLayer;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the connectionLayer
	 */
	public LayerWidget getConnectionLayer()
	{
		return connectionLayer;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @return the numberOfWidgetsOnTheScene
	 */
	public int getNumberOfWidgetsOnTheScene()
	{
		return numberOfWidgetsOnTheScene;
	}


	/**
	 * TODO - Description
	 */
	public Connection[] getConnections()
	{
		return connections;
	}


	/**
	 * TODO - Description
	 * 
	 */
	public WidgetObject getCurrentWidgetObject()
	{
		return currentWidgetObject;
	}


	/**
	 * TODO - Description NEEDED!
	 * 
	 * @param canvasName
	 *            the canvasName to set
	 */
	public void setCanvasName(String canvasName)
	{
		CanvasName = canvasName;
	}


	public void setConnections(Connection[] connections)
	{
		this.connections = connections;
	}



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

	// ------------------ TRANSFER METHODES -----------------------





	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		// System.out.println(getCanvasName());
	}



	@Override
	public void dragExit(DropTargetEvent dte)
	{
		// System.out.println("Drag Exit");
	}



	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
		// System.out.println("Drag Over");
	}



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



	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
	}



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


			newObject.addChild(new LabelWidget(scene, newObject.getObject().getObjectName()));


			newObject.setToolTipText(newObject.getObject().getDescription());


			// Adds hovering action to the widget.
			// newObject.getActions().addAction(
			// ActionFactory.createHoverAction(new WidgetHoverProvider()));


			newObject.getActions().addAction(
					ActionFactory.createPopupMenuAction(new JMenuProvider()));



			// newObject.getActions().addAction(ActionFactory.createRectangularSelectAction(
			// ActionFactory.createDefaultRectangularSelectDecorator(scene),interactionLayer,
			// ActionFactory.createObjectSceneRectangularSelectProvider(scene)));


			// ----------DIFFERENT BORDERS------------
			newObject.setBorder(BorderFactory.createEmptyBorder());

			// newObject.setBorder(BorderFactory.createDashedBorder(Color.black,
			// 5, 5));

			// newObject.setBorder(BorderFactory.createBevelBorder(false));

			// newObject.setBorder(BorderFactory.createResizeBorder(5));

			// newObject.setBorder(BorderFactory.createRoundedBorder(10, 10,
			// Color.white, Color.black));

			// newObject.getActions().addAction(ActionFactory.createAddRemoveControlPointAction(1.0,3.0));
			// ---------------------------------------

			mainLayer.addChild(newObject);
			numberOfWidgetsOnTheScene++;

			cleanUp();
		}
	}


	private void cleanUp()
	{
		doRepaint();

		PrimeMain1.updatePropertiesArea();

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

			if ( actionName.equals("DeleteThisObject") )
			{
				WorkareaCanvasActions.deleteCurrentObject(this);
				System.out.println("khdsag");
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