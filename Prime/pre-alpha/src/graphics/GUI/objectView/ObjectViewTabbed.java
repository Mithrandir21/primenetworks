package graphics.GUI.objectView;


import graphics.GUI.objectView.Connections.NetworkConnectionsView;
import graphics.GUI.objectView.General.GeneralObjectView;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Network.NetworkView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareObjectView;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import widgetManipulation.WidgetObject;


/**
 * The JTabbedPane class that will contain all the different tabs showing all object information.
 * 
 * @author Bahram Malaekeh
 */
public class ObjectViewTabbed extends JTabbedPane
{
	public GeneralObjectView genObjView;

	public HardwareObjectView hardObjView;

	public SoftwareObjectView softObjView;

	public NetworkConnectionsView conObjView;

	public NetworkView netObjView;


	/**
	 * Constructor that creates all the different tabs that show different information about the given object. This
	 * includes general, hardware, software and network information.
	 * 
	 * @param obj
	 *            The object that will be examined for information
	 */
	public ObjectViewTabbed(WidgetObject obj)
	{
		genObjView = new GeneralObjectView(obj.getObject());
		String genDesc = "General information and option";
		this.addTab("General", null, genObjView, genDesc);


		hardObjView = new HardwareObjectView(obj.getObject());
		String hardwareDesc = "General information and option";
		JScrollPane scrollPaneHW = new JScrollPane(hardObjView);
		scrollPaneHW.setViewportView(hardObjView);
		scrollPaneHW.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.addTab("Hardware", null, scrollPaneHW, hardwareDesc);


		softObjView = new SoftwareObjectView(obj.getObject());
		String softDesc = "General information and option";
		JScrollPane scrollPaneSW = new JScrollPane(softObjView);
		scrollPaneSW.setViewportView(softObjView);
		scrollPaneSW.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.addTab("Software", null, scrollPaneSW, softDesc);


		conObjView = new NetworkConnectionsView(obj.getObject());
		String netDesc = "General information and option";
		this.addTab("Connections", null, conObjView, netDesc);


		netObjView = new NetworkView(obj);
		String visDesc = "General information and option";
		this.addTab("Network", null, netObjView, visDesc);
	}


	/**
	 * Gets the hardware editor that where hardware can be edited.
	 */
	public HardwareObjectView getHardwareEditor()
	{
		return hardObjView;
	}



	/**
	 * Calls the update functions in the classes views to update the information it contains about the classes given
	 * object. This includes both hardware and software views.
	 * 
	 */
	public void updateTabInfo()
	{
		hardObjView.updateTabInfo();
		softObjView.updateTabInfo();
	}
}
