package graphics.GUI.objectView;


import graphics.GUI.objectView.General.GeneralObjectView;
import graphics.GUI.objectView.Hardware.HardwareView.Overview.HardwareObjectView;
import graphics.GUI.objectView.Network.NetworkObjectView;
import graphics.GUI.objectView.Software.SoftwareObjectView;
import graphics.GUI.objectView.Visual.VisualObjectView;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import objects.Object;


/**
 * TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class ObjectViewTabbed extends JTabbedPane
{
	public GeneralObjectView genObjView;

	public HardwareObjectView hardObjView;

	public SoftwareObjectView softObjView;

	public NetworkObjectView netObjView;

	public VisualObjectView visObjView;


	/**
	 * TODO - Description NEEDED!
	 * 
	 */
	public ObjectViewTabbed(Object obj)
	{
		genObjView = new GeneralObjectView(obj);
		String genDesc = "General information and option";
		this.addTab("General", null, genObjView, genDesc);


		hardObjView = new HardwareObjectView(obj);
		String hardwareDesc = "General information and option";
		JScrollPane scrollPane = new JScrollPane(hardObjView);
		scrollPane.setViewportView(hardObjView);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.addTab("Hardware", null, scrollPane, hardwareDesc);


		softObjView = new SoftwareObjectView(obj);
		String softDesc = "General information and option";
		this.addTab("Software", null, softObjView, softDesc);


		netObjView = new NetworkObjectView(obj);
		String netDesc = "General information and option";
		this.addTab("Network", null, netObjView, netDesc);

		visObjView = new VisualObjectView(obj);
		String visDesc = "General information and option";
		this.addTab("Visual", null, visObjView, visDesc);
	}



	public void updateTabInfo()
	{
		hardObjView.updateTabInfo();
	}
}
