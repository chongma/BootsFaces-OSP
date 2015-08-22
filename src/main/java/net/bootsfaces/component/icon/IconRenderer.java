/**
 *  Copyright 2014-15 by Riccardo Massera (TheCoder4.Eu) and Stephan Rauh (http://www.beyondjava.net).
 *  
 *  This file is part of BootsFaces.
 *  
 *  BootsFaces is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BootsFaces is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with BootsFaces. If not, see <http://www.gnu.org/licenses/>.
 */

package net.bootsfaces.component.icon;

import javax.faces.component.*;
import java.io.IOException;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import net.bootsfaces.component.iconAwesome.IconAwesome;
import net.bootsfaces.render.A;
import net.bootsfaces.render.CoreRenderer;
import net.bootsfaces.render.R;
import net.bootsfaces.render.Tooltip;

/** This class generates the HTML code of &lt;b:icon /&gt;. */
@FacesRenderer(componentFamily = "net.bootsfaces.component", rendererType = "net.bootsfaces.component.icon.Icon")
public class IconRenderer extends CoreRenderer {

	/**
	 * This methods generates the HTML code of the current b:icon.
	 * 
	 * @param context
	 *            the FacesContext.
	 * @param component
	 *            the current b:icon.
	 * @throws IOException
	 *             thrown if something goes wrong when writing the HTML code.
	 */
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if (!component.isRendered()) {
			return;
		}
		Icon icon = (Icon) component;
		ResponseWriter rw = context.getResponseWriter();
		String clientId = icon.getClientId();

		/*
		 * <span class="badge badge-important">6</span>
		 */

		String nameOfIcon = icon.getName();
		String styleClass = icon.getStyleClass();
		String style = icon.getStyle();
		String size = icon.getSize();
		String rotate = icon.getRotate();
		String flip = icon.getFlip();
		boolean spin = icon.isSpin();
		boolean addon = icon.isAddon();

		encodeIcon(context.getResponseWriter(), icon, nameOfIcon, icon instanceof IconAwesome, size, rotate, flip, spin,
				addon, styleClass, style);
		Tooltip.activateTooltips(context, icon);

	}

	/**
	 * Renders an Icon
	 * 
	 * @param rw
	 *            ResponseWriter
	 * @param c
	 *            UIComponent
	 * @param icon
	 *            Icon Name
	 * @param isFontAwesome
	 *            Icon set: false for Bootstrap Glyphicons, true for Font
	 *            Awesome
	 * @param size
	 *            Icon Size lg, 2x, 3x, 4x, 5x
	 * @param rotate
	 *            Can be L,R
	 * @param flip
	 *            Can be H,V
	 * @param spin
	 *            true or false
	 * @param addon
	 *            If true it is an addon for inputs.
	 * @throws IOException
	 */
	public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean isFontAwesome,
			String size, String rotate, String flip, boolean spin, boolean addon, String styleClass, String style)
					throws IOException {
		rw.startElement("span", c);
		if (addon) {
			rw.writeAttribute("id", c.getClientId() + "_" + "input-group-addon", null);
			rw.writeAttribute("class", "input-group-addon", "class");
		}
		rw.startElement("i", c);
		rw.writeAttribute("id", c.getClientId() + "_icon", null);
		Tooltip.generateTooltip(FacesContext.getCurrentInstance(), c.getAttributes(), rw);

		StringBuilder sb = new StringBuilder(100); // optimize int
		if (styleClass != null) {
			sb.append(styleClass).append(" ");
		}
		if (isFontAwesome) {
			if (icon.startsWith("fa-"))
				sb.append("fa " + icon);
			else
				sb.append("fa fa-" + icon);
		} else {
			if (icon.startsWith("glyphicon-"))
				sb.append("glyphicon " + icon);
			else
				sb.append("glyphicon glyphicon-" + icon);
		}
		if (size != null) {
			sb.append(" fa-" + size);
		}
		if (rotate != null) {
			if (rotate.equalsIgnoreCase("R")) {
				sb.append(" fa-rotate-90");
			}
			if (rotate.equalsIgnoreCase("L")) {
				sb.append(" fa-rotate-270");
			}
		}
		if (flip != null) {
			if (flip.equalsIgnoreCase("H")) {
				sb.append(" fa-flip-horizontal");
			}
			if (flip.equalsIgnoreCase("V")) {
				sb.append(" fa-flip-vertical");
			}
		}
		if (spin) {
			sb.append(" fa-spin");
		}
		rw.writeAttribute("class", sb.toString(), "class");
		if (style != null) 
			rw.writeAttribute("style", style, "style");
		rw.endElement("i");

		rw.endElement("span");
	}

	/**
	 * Renders an Icon - simple version without options
	 * 
	 * @param rw
	 *            ResponseWriter
	 * @param c
	 *            UIComponent
	 * @param icon
	 *            Icon Name
	 * @param isFontAwesome
	 *            Icon set: false for Bootstrap Glyphicons, true for Font
	 *            Awesome
	 * @throws IOException
	 */
	public static final void encodeIcon(ResponseWriter rw, UIComponent c, String icon, boolean isFontAwesome) throws IOException {
		encodeIcon(rw, c, icon, isFontAwesome, null, null, null, false, false, null, null);
	}

	/**
	 * Renders an addon Icon Markup:
	 * <span class="add-on"><i class="icon-envelope"></i></span>
	 * 
	 * @param rw
	 *            ResponseWriter
	 * @param c
	 *            UIComponent
	 * @param icon
	 *            Icon Name
	 * @param isFontAwesome
	 *            Icon set: false for Bootstrap Glyphicons, true for Font
	 *            Awesome
	 * @throws java.io.IOException
	 */
	public static final void addonIcon(ResponseWriter rw, UIComponent c, String icon, boolean isFontAwesome) throws IOException {
		encodeIcon(rw, c, icon, isFontAwesome, null, null, null, false, true, null, null);
	}

}