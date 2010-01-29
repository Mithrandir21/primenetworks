package graphics.GUI.objectView;


import graphics.PrimeMain1;
import graphics.GUI.objectView.Connections.NetworkConnectionsView;
import graphics.GUI.objectView.General.GeneralObjectView;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Network.NetworkView;
import graphics.GUI.objectView.Software.EditSoftware.EditOverview.SoftwareObjectView;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

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
				.addTab(PrimeMain1.texts.getString("generalTabLabel"), null,
						genObjView, PrimeMain1.texts
								.getString("generalTabDescription"));


		hardObjView = new HardwareObjectView(obj.getObject());
		JScrollPane scrollPaneHW = new JScrollPane(hardObjView);
		scrollPaneHW.setViewportView(hardObjView);
		scrollPaneHW
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.addTab(PrimeMain1.texts.getString("hardwareTabLabel"), null,
				scrollPaneHW, PrimeMain1.texts
						.getString("hardwareTabDescription"));


		softObjView = new SoftwareObjectView(obj.getObject());
		JScrollPane scrollPaneSW = new JScrollPane(softObjView);
		scrollPaneSW.setViewportView(softObjView);
		scrollPaneSW
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.addTab(PrimeMain1.texts.getString("softwareTabLabel"), null,
				scrollPaneSW, PrimeMain1.texts
						.getString("softwareTabDescription"));


		conObjView = new NetworkConnectionsView(obj.getObject());
		this.addTab(PrimeMain1.texts.getString("connectionTabLabel"), null,
				conObjView, PrimeMain1.texts
						.getString("connectionTabDescription"));


		netObjView = new NetworkView(obj);
		this
				.addTab(PrimeMain1.texts.getString("networkTabLabel"), null,
						netObjView, PrimeMain1.texts
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
