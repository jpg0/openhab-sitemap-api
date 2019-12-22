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

package org.openhab.api.sitemap.builder;

import org.openhab.api.sitemap.model.ColorArray;
import org.openhab.api.sitemap.model.Mapping;
import org.openhab.api.sitemap.model.VisibilityRule;
import org.openhab.api.sitemap.model.Widget;
import org.openhab.api.sitemap.model.impl.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WidgetBuilder {
    private String type;
    private String item;
    private String label;
    private String icon;
    private java.util.List<ColorArray> labelColor = Collections.emptyList();
    private java.util.List<ColorArray> valueColor = Collections.emptyList();
    private java.util.List<VisibilityRule> visibility = Collections.emptyList();

    private java.util.List<Widget<?>> children = Collections.emptyList(); //for LinkableWidgets
    private List<Mapping> mappings = Collections.emptyList(); //for Switches,
    private int height;

    private WidgetBuilder(String type) {
        this.type = type;
    }

    public static WidgetBuilder create(String type) {
        return new WidgetBuilder(type);
    }

    public WidgetBuilder withItem(String item) {
        this.item = item;
        return this;
    }

    public WidgetBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public WidgetBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public WidgetBuilder withLabelColor(java.util.List<ColorArray> labelColor) {
        this.labelColor = labelColor;
        return this;
    }

    public WidgetBuilder withValueColor(java.util.List<ColorArray> valueColor) {
        this.valueColor = valueColor;
        return this;
    }

    public WidgetBuilder withVisibility(java.util.List<VisibilityRule> visibility) {
        this.visibility = visibility;
        return this;
    }

    public WidgetBuilder withChild(Widget<?> child) {
        if(!Arrays.asList("Text", "Frame", "Group").contains(type)) {
            throw new UnsupportedOperationException(type + " widgets do not support children");
        }

        children.add(child);
        return this;
    }

    public WidgetBuilder withMapping(Mapping mapping) {
        if(!Arrays.asList("Switch").contains(type)) {
            throw new UnsupportedOperationException(type + " widgets do not support mappings");
        }

        mappings.add(mapping);
        return this;
    }

    public WidgetBuilder withHeight(int height) {
        if(!Arrays.asList("Default").contains(type)) {
            throw new UnsupportedOperationException(type + " widgets do not support height");
        }

        this.height = height;
        return this;
    }

    public Widget<?> create(){
        switch (type) {
        case "Text":
            return new TextImpl(item, label, icon, labelColor, valueColor, visibility, children);
        case "Frame":
            return new FrameImpl(item, label, icon, labelColor, valueColor, visibility, children);
        case "Group":
            return new GroupImpl(item, label, icon, labelColor, valueColor, visibility, children);
        case "Switch":
            return new SwitchImpl(item, label, icon, labelColor, valueColor, visibility, mappings);
        case "Default":
            return new DefaultImpl(item, label, icon, labelColor, valueColor, visibility, height);
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
