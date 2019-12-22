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

import org.openhab.api.sitemap.EMFLessProxyGenerator;
import org.openhab.api.sitemap.model.Sitemap;
import org.openhab.api.sitemap.model.Widget;
import org.openhab.api.sitemap.model.impl.SitemapImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class EMFLessSitemapBuilder {
    private EMFLessSitemapBuilder(String name) {
        this.name = name;
    }

    private EMFLessProxyGenerator proxyGenerator = new EMFLessProxyGenerator();

    private String name;
    private String label;
    private String icon;
    private List<Widget<?>> widgets = new ArrayList<>();

    public static EMFLessSitemapBuilder create(String name) {
        return new EMFLessSitemapBuilder(name);
    }

    public EMFLessSitemapBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public EMFLessSitemapBuilder withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public EMFLessSitemapBuilder withWidgets(List<Widget<?>> widgets) {
        this.widgets.addAll(widgets);
        return this;
    }

    public EMFLessSitemapBuilder withWidget(Widget<?> widget) {
        return withWidgets(Collections.singletonList(widget));
    }

    public Sitemap build() {
        return new SitemapImpl(name, icon, label, widgets);
    }
    
    public org.eclipse.smarthome.model.sitemap.sitemap.Sitemap buildProxy() {
        return proxyGenerator.proxyFor(build(), org.eclipse.smarthome.model.sitemap.sitemap.Sitemap.class);
    }
}
