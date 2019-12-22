/*
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

import org.openhab.api.sitemap.model.*;

import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class ImageImpl extends LinkableWidgetImpl<org.eclipse.smarthome.model.sitemap.sitemap.Image> implements Image {
    private String url;
    private int refresh;
    private List<ColorArray> iconColor;

    public ImageImpl(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, List<Widget<?>> children, String url, int refresh,
            List<ColorArray> iconColor) {
        super(item, label, icon, labelColor, valueColor, visibility, children);
        this.url = url;
        this.refresh = refresh;
        this.iconColor = iconColor;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public int getRefresh() {
        return refresh;
    }

    @Override
    public List<ColorArray> getIconColor() {
        return iconColor;
    }
}