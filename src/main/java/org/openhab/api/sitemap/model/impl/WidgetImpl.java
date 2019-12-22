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

import org.openhab.api.sitemap.model.ColorArray;
import org.openhab.api.sitemap.model.VisibilityRule;
import org.openhab.api.sitemap.model.Widget;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class WidgetImpl<T extends org.eclipse.smarthome.model.sitemap.sitemap.Widget> implements Widget<T> {

    private String item, label, icon;
    private List<ColorArray> labelColor, valueColor;
    private List<VisibilityRule> visibility;

    public WidgetImpl(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility) {
        this.item = item;
        this.label = label;
        this.icon = icon;
        this.labelColor = labelColor == null ? Collections.emptyList() : labelColor;
        this.valueColor = valueColor == null ? Collections.emptyList() : valueColor;
        this.visibility = visibility == null ? Collections.emptyList() : visibility;
    }

    @Override
    public String getItem() {
        return item;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public List<ColorArray> getLabelColor() {
        return labelColor;
    }

    @Override
    public List<ColorArray> getValueColor() {
        return valueColor;
    }

    @Override
    public List<VisibilityRule> getVisibility() {
        return visibility;
    }
}
