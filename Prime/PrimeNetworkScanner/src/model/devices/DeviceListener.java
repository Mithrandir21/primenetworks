package model.devices;


import java.util.EventListener;


/**
 * Defines a listener that will be notified when a device changes its properties
 * 
 * @author rakudave
 */
// TODO - @XStreamAlias("DeviceListener")
public interface DeviceListener extends EventListener
{
	/**
	 * Called when a device changes its properties
	 * 
	 * @param e
	 *            event containing details of the change
	 * @see DeviceEvent
	 */
	void deviceChanged(DeviceEvent e);
}
