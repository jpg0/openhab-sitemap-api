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

import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class ChartImpl extends NonLinkableWidgetImpl<org.eclipse.smarthome.model.sitemap.sitemap.Chart> implements
        Chart {
    private String service;
    private int refresh;
    private String period;
    private Boolean legend;

    public ChartImpl(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, String service, int refresh, String period, Boolean legend) {
        super(item, label, icon, labelColor, valueColor, visibility);
        this.service = service;
        this.refresh = refresh;
        this.period = period;
        this.legend = legend;
    }

    @Override
    public String getService() {
        return service;
    }

    @Override
    public int getRefresh() {
        return refresh;
    }

    @Override
    public String getPeriod() {
        return period;
    }

    @Override
    public Boolean getLegend() {
        return legend;
    }
}