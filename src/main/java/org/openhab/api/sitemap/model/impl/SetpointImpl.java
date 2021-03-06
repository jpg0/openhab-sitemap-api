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

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class SetpointImpl extends NonLinkableWidgetImpl<org.eclipse.smarthome.model.sitemap.sitemap.Setpoint> implements
        Setpoint {

    private BigDecimal minValue;
    private BigDecimal maxValue;
    private BigDecimal step;

    public SetpointImpl(String item, String label, String icon, List<ColorArray> labelColor,
            List<ColorArray> valueColor, List<VisibilityRule> visibility, BigDecimal minValue, BigDecimal maxValue,
            BigDecimal step) {
        super(item, label, icon, labelColor, valueColor, visibility);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;
    }

    @Override
    public BigDecimal getMinValue() {
        return minValue;
    }

    @Override
    public BigDecimal getMaxValue() {
        return maxValue;
    }

    @Override
    public BigDecimal getStep() {
        return step;
    }
}