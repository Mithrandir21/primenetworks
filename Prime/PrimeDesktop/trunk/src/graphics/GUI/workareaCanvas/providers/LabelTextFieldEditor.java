/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (C) 2010  Bahram Malaekeh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphics.GUI.workareaCanvas.providers;


import org.netbeans.api.visual.action.TextFieldInplaceEditor;
import org.netbeans.api.visual.widget.Widget;

import widgets.WidgetObject;


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
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#getText(org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public String getText(Widget widget)
	{
		return ((WidgetObject) widget).getLabel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#isEnabled(org.netbeans.api.visual.widget.Widget)
	 */
	@Override
	public boolean isEnabled(Widget widget)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.netbeans.api.visual.action.TextFieldInplaceEditor#setText(org.netbeans.api.visual.widget.Widget,
	 * java.lang.String)
	 */
	@Override
	public void setText(Widget widget, String text)
	{
		((WidgetObject) widget).setLabel(text);
	}
}
