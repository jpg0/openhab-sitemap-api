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

import org.openhab.api.sitemap.model.*;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class LinkableWidgetImpl<T extends org.eclipse.smarthome.model.sitemap.sitemap.LinkableWidget> extends
        WidgetImpl<T> implements LinkableWidget<T> {

    private List<Widget<?>> children;

    public LinkableWidgetImpl(String item, String label, String icon, List<ColorArray> labelColor,
            List<ColorArray> valueColor, List<VisibilityRule> visibility, List<Widget<?>> children) {
        super(item, label, icon, labelColor, valueColor, visibility);
        this.children = (children == null ? Collections.emptyList() : children);
    }

    @Override
    public List<Widget<?>> getChildren() {
        return children;
    }
}
