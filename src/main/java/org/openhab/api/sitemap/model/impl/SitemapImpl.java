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

package org.openhab.api.sitemap.model.impl;

import org.openhab.api.sitemap.model.Sitemap;
import org.openhab.api.sitemap.model.Widget;

import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class SitemapImpl implements Sitemap {
    private String name, icon, label;
    private List<Widget<?>> widgets;

    public SitemapImpl(String name, String icon, String label, List<Widget<?>> widgets) {
        this.name = name;
        this.icon = icon;
        this.label = label;
        this.widgets = widgets;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public List<Widget<?>> getChildren() {
        return widgets;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public List<Widget<?>> getWidgets() {
        return widgets;
    }
}
