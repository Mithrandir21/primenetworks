package model.devices;

import java.util.EventObject;


/**
 * @author rakudave
 */
@SuppressWarnings("serial")
public final class DeviceEvent extends EventObject{
	public static enum Type {STATUS_CHANGED, PROPERTY_CHANGED, INTERFACE_STATUS_CHANGED, INTERFACE_ADDED, INTERFACE_REMOVED};
	private Type type;
	private Object subject;

	public DeviceEvent(Device source, Type type, Object subject) {
		super(source);
		this.type = type;
		this.subject = subject;
	}

	/**
	 * @return device that caused the event
	 */
	public Device getItem() {
		return (Device)getSource();
	}
	
	/**
	 * @return type of this event
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the subject of the event, if any (null otherwise)
	 */
	public Object getSubject() {
		return subject;
	}

	@Override
	public String toString() {
		return getSource() + "/" + type + "/" + getSubject();
	}
}
