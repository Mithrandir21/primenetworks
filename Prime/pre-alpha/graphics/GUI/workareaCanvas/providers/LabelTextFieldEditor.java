package graphics.GUI.workareaCanvas.providers;

import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.widget.Widget;

import widgetManipulation.WidgetObject;

public class LabelTextFieldEditor implements TextFieldInplaceEditor
{
	@Override
	public String getText(Widget widget)
	{
		return ((WidgetObject) widget).getLabel();
	}

	@Override
	public boolean isEnabled(Widget widget)
	{
		return true;
	}

	@Override
	public void setText(Widget widget, String text)
	{
		((WidgetObject) widget).setLabel(text);
	}
}
