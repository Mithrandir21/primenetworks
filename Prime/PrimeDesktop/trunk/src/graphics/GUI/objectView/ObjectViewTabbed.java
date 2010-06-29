package graphics.GUI.objectView;


import graphics.PrimeMain;
import graphics.GUI.objectView.Connections.NetworkConnectionsView;
import graphics.GUI.objectView.General.GeneralObjectView;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Network.NetworkView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareObjectView;

import javax.swing.JTabbedPane;

import widgets.WidgetObject;


/**
 * The JTabbedPane class that will contain all the different tabs showing all
 * object information.
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
	 * Constructor that creates all the different tabs that show different
	 * information about the given object. This includes general, hardware,
	 * software and network information.
	 * 
	 * @param obj
	 *            The object that will be examined for information
	 */
	public ObjectViewTabbed(WidgetObject obj)
	{
		genObjView = new GeneralObjectView(obj.getObject());
		this
				.addTab(PrimeMain.texts.getString("generalTabLabel"), null,
						genObjView, PrimeMain.texts
								.getString("generalTabDescription"));


		hardObjView = new HardwareObjectView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("hardwareTabLabel"), null,
				hardObjView, PrimeMain.texts
						.getString("hardwareTabDescription"));


		softObjView = new SoftwareObjectView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("softwareTabLabel"), null,
				softObjView, PrimeMain.texts
						.getString("softwareTabDescription"));


		conObjView = new NetworkConnectionsView(obj.getObject());
		this.addTab(PrimeMain.texts.getString("connectionTabLabel"), null,
				conObjView, PrimeMain.texts
						.getString("connectionTabDescription"));


		netObjView = new NetworkView(obj);
		this
				.addTab(PrimeMain.texts.getString("networkTabLabel"), null,
						netObjView, PrimeMain.texts
								.getString("networkTabDescription"));
	}


	/**
	 * Gets the hardware editor that where hardware can be edited.
	 */
	public HardwareObjectView getHardwareEditor()
	{
		return hardObjView;
	}


	/**
	 * Gets the software editor that where software can be edited.
	 */
	public SoftwareObjectView getSoftwareEditor()
	{
		return softObjView;
	}







	/**
	 * Calls the update functions in the classes views to update the information
	 * it contains about the classes given object. This includes both hardware
	 * and software views.
	 */
	public void updateTabInfo()
	{
		hardObjView.updateTabInfo();
		softObjView.updateTabInfo();
	}
}
