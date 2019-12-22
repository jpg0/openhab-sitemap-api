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
import org.openhab.api.sitemap.model.Mapping;
import org.openhab.api.sitemap.model.Switch;
import org.openhab.api.sitemap.model.VisibilityRule;

import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class ListImpl extends NonLinkableWidgetImpl<org.eclipse.smarthome.model.sitemap.sitemap.List> implements org.openhab.api.sitemap.model.List {
    private String separator;

    public ListImpl(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, String separator) {
        super(item, label, icon, labelColor, valueColor, visibility);
        this.separator = separator;
    }

    @Override
    public String getSeparator() {
        return separator;
    }
}