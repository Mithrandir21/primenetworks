package graphics.GUI.workareaCanvas.providers;


import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;


/**
 * Javadoc-TODO - Description NEEDED!
 * 
 * @author Bahram Malaekeh
 * 
 */
public class LabelTextFieldEditor implements TextFieldInplaceEditor
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#getText(org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public String getText(Widget widget)
	{
		return ((WidgetObject) widget).getLabel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#isEnabled(org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public boolean isEnabled(Widget widget)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#setText(org.netbeans.api.visual.widget.Widget,
	 * java.lang.String)
	 */
	@Override
	public void setText(Widget widget, String text)
	{
		((WidgetObject) widget).setLabel(text);
	}
}
