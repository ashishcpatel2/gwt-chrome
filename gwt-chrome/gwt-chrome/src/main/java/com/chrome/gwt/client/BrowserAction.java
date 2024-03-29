/*
 * Copyright 2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.chrome.gwt.client;

import com.chrome.gwt.client.events.BrowserActionEvent;
import com.chrome.gwt.client.events.BrowserActionEvent.Listener;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A BrowserAction which inserts a button in the toolbar.
 * 
 * API for chrome.browserAction.
 * 
 * The API is described in JSON form in the Chrome source:
 * src/chrome/common/extensions/api/extension_api.json
 * 
 * 
 */
public abstract class BrowserAction implements Component {
	/**
	 * Annotation for defining the properties of a PageAction.
	 */
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface ManifestInfo {
		String defaultIcon();

		String name();

		String popup() default "";
	}

	protected BrowserAction() {
	}

	public final void addListener(Listener listener) {
		getBrowserActionEvent().addListener(listener);
	}

	private native BrowserActionEvent getBrowserActionEvent() /*-{
		return chrome.browserAction.onClicked;
	}-*/;

	public abstract String getName();

	// setIcon
	/**
	 * Sets the icon for the browser action.
	 * 
	 * @param tabId
	 *            Limits the change to when a particular tab is selected.
	 *            Automatically resets when the tab is closed.
	 * @param icon
	 *            wrapper what holds the path to the image
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setIcon
	 */
	public final void setIcon(int tabId, Icon icon) {
		setIconImpl(tabId, icon.getPath());
	}

	/**
	 * Sets the icon for the browser action.
	 * 
	 * @param icon
	 *            wrapper what holds the path to the image
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setIcon
	 */
	public final void setIcon(Icon icon) {
		setIconImpl(icon.getPath());
	}

	private native void setIconImpl(int tabId, String iconPath) /*-{
		chrome.browserAction.setIcon({tabId: tabId, path: iconPath});
	}-*/;

	private native void setIconImpl(String iconPath) /*-{
		chrome.browserAction.setIcon({path: iconPath});
	}-*/;

	// setBadgeBackgroundColor
	/**
	 * Sets the background color for the badge.
	 * 
	 * @param r
	 *            color r channel
	 * @param g
	 *            color g channel
	 * @param b
	 *            color b channel
	 * @param alpha
	 *            color alpha channel
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setBadgeBackgroundColor
	 */
	public final void setBadgeBackgroundColor(byte r, byte g, byte b, byte alpha) {
		setBadgeBackgroundColor(new int[] { r, g, b, alpha });
	}

	/**
	 * Sets the background color for the badge.
	 * 
	 * @param tabId
	 *            Limits the change to when a particular tab is selected.
	 *            Automatically resets when the tab is closed.
	 * @param r
	 *            color r channel
	 * @param g
	 *            color g channel
	 * @param b
	 *            color b channel
	 * @param alpha
	 *            color alpha channel
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setBadgeBackgroundColor
	 */
	public final void setBadgeBackgroundColor(int tabId, byte r, byte g,
			byte b, byte alpha) {
		setBadgeBackgroundColor(tabId, new int[] { r, g, b, alpha });
	}

	private final native void setBadgeBackgroundColor(int tabId, int[] color) /*-{
		chrome.browserAction.setBadgeBackgroundColor({tabId: tabId, color: color});
	}-*/;

	private final native void setBadgeBackgroundColor(int[] color) /*-{
		chrome.browserAction.setBadgeBackgroundColor({color: color});
	}-*/;

	// setBadgeText

	public final native void setBadgeText(int tabId, String text) /*-{
		chrome.browserAction.setBadgeText({tabId: tabId, text: text});
	}-*/;

	public final native void setBadgeText(String text) /*-{
		chrome.browserAction.setBadgeText({text: text});
	}-*/;

	// setTitle
	/**
	 * Sets the title of the browser action. This shows up in the tooltip.
	 * 
	 * @param title
	 *            The string the browser action should display when moused over.
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setTitle
	 */
	public final native void setTitle(String title)/*-{
		chrome.browserAction.setTitle({title: title});
	}-*/;

	/**
	 * Sets the title of the browser action. This shows up in the tooltip.
	 * 
	 * @param tabId
	 *            Limits the change to when a particular tab is selected.
	 *            Automatically resets when the tab is closed.
	 * @param title
	 *            The string the browser action should display when moused over.
	 * 
	 * @see http://code.google.com/chrome/extensions/browserAction.html#method-setTitle
	 */
	public final native void setTitle(int tabId, String title) /*-{
		chrome.browserAction.setTitle({tabId: tabId, title: title});
	}-*/;

}