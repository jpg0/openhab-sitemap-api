/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.openhab.api.sitemap.model;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public interface Widget<T extends org.eclipse.smarthome.model.sitemap.sitemap.Widget> extends ELessObject<T> {
    String getItem();
    String getLabel();
    String getIcon();
    java.util.List<ColorArray> getLabelColor();
    java.util.List<ColorArray> getValueColor();
    java.util.List<VisibilityRule> getVisibility();
}
