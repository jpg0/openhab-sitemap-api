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

package org.openhab.api.sitemap.builder;

import org.openhab.api.sitemap.model.*;
import org.openhab.api.sitemap.model.impl.*;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class WidgetFactory {

    public static WidgetFactory create(){
        return new WidgetFactory();
    }

    public Text newText(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
                List<VisibilityRule> visibility, List<Widget<?>> children) {
            return new TextImpl(item, label, icon, labelColor, valueColor, visibility, children);
        }

    public Frame newFrame(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, List<Widget<?>> children) {
        return new FrameImpl(item, label, icon, labelColor, valueColor, visibility, children);
    }

    public Switch newSwitch(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, List<Mapping> mappings) {
        return new SwitchImpl(item, label, icon, labelColor, valueColor, visibility, mappings);
    }

    public Selection newSelection(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, List<Mapping> mappings) {
        return new SelectionImpl(item, label, icon, labelColor, valueColor, visibility, mappings);
    }

    public Webview newWebview(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, int height, String url) {
        return new WebviewImpl(item, label, icon, labelColor, valueColor, visibility, height, url);
    }

    public Setpoint newSetpoint(String item, String label, String icon, List<ColorArray> labelColor,
            List<ColorArray> valueColor, List<VisibilityRule> visibility, BigDecimal minValue, BigDecimal maxValue,
            BigDecimal step) {
        return new SetpointImpl(item, label, icon, labelColor, valueColor, visibility, minValue, maxValue, step);
    }

    public Default newDefault(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, int height) {
        return new DefaultImpl(item, label, icon, labelColor, valueColor, visibility, height);
    }

    public Slider newSlider(String item, String label, String icon, List<ColorArray> labelColor, List<ColorArray> valueColor,
            List<VisibilityRule> visibility, int frequency, boolean switchEnabled, BigDecimal minValue,
            BigDecimal maxValue, BigDecimal step) {
        return new SliderImpl(item, label, icon, labelColor, valueColor, visibility, frequency, switchEnabled, minValue,
                maxValue, step);
    }
}
