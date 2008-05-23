/**
 * 
 */
package graphics;

import java.awt.*;

import javax.swing.*;

import org.netbeans.api.visual.widget.*;
import org.netbeans.api.visual.action.*;
import org.netbeans.api.visual.anchor.*;
import org.netbeans.api.visual.border.BorderFactory;




/**
 * Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * @version 0.1
 */

public class WorkareaCanvas 
{
	private Scene scene;
	
	public JComponent myView;
	
	private LayerWidget mainLayer;
	
	private LayerWidget interactionLayer;
	
	private LayerWidget connectionLayer;
	
	
	public WorkareaCanvas()
	{
		scene = new Scene();
		
		myView = scene.createView();
		
		scene.getActions().addAction(ActionFactory.createSelectAction(new CreateProvider()));
		scene.getActions().addAction(ActionFactory.createZoomAction());
		
		
		mainLayer = new LayerWidget(scene);
		scene.addChild(mainLayer);
		
		interactionLayer = new LayerWidget(scene);
		scene.addChild(interactionLayer);
		
		connectionLayer = new LayerWidget(scene);
		scene.addChild(connectionLayer);
		
	}
	
	
	
	private class CreateProvider implements SelectProvider
	{

		public boolean isAimingAllowed(Widget arg0, Point arg1, boolean arg2) 
		{
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSelectionAllowed(Widget arg0, Point arg1, boolean arg2) 
		{
			// TODO Auto-generated method stub
			return true;
		}

		public void select(Widget relatedWidget, Point localLocation, boolean invertSelection) 
		{
			Widget widget = new ImageWidget(scene, createImage("images/java.jpg"));
			widget.setPreferredLocation(relatedWidget.convertLocalToScene(localLocation));
			widget.getActions().addAction(ActionFactory.createExtendedConnectAction(interactionLayer,new SceneConnectProvider()));
			//widget.setBorder(BorderFactory.createRoundedBorder(10, 10, Color.yellow, Color.black));
			widget.getActions().addAction(ActionFactory.createAlignWithMoveAction(mainLayer, interactionLayer, null));
			mainLayer.addChild(widget);
		}

	}
	
	
	private class SceneConnectProvider implements ConnectProvider
	{

		public void createConnection(Widget sourceWidget, Widget targetWidget) 
		{
			ConnectionWidget connection = new ConnectionWidget(scene);
			connection.setTargetAnchorShape(AnchorShape.TRIANGLE_FILLED);
			connection.setSourceAnchor(AnchorFactory.createRectangularAnchor(sourceWidget));
			connection.setTargetAnchor(AnchorFactory.createRectangularAnchor(targetWidget));
			connectionLayer.addChild(connection);
			
			
		}

		public boolean hasCustomTargetWidgetResolver(Scene scene) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isSourceWidget(Widget arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		public ConnectorState isTargetWidget(Widget sourceWidget, Widget targetWidget) 
		{
			if(sourceWidget != targetWidget && targetWidget instanceof LabelWidget)
			{
				return ConnectorState.ACCEPT;
			}
			else
			{
				return ConnectorState.REJECT;
			}
			
		}

		public Widget resolveTargetWidget(Scene sourceWidget, Point sceneLocation) 
		{
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	
	/** Returns an Image, or null if the path was invalid. */
	protected Image createImage(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL).getImage();
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
