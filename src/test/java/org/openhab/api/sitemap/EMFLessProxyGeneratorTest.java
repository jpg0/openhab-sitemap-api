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

package org.openhab.api.sitemap;

import org.eclipse.emf.common.util.EList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openhab.api.sitemap.EMFLessProxyGenerator;
import org.openhab.api.sitemap.model.Sitemap;
import org.openhab.api.sitemap.model.Widget;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Jonathan Gilbert - Initial contribution
 */
public class EMFLessProxyGeneratorTest {

    Sitemap stubSitemap;

    @Before
    public void createSitemap() {
        stubSitemap = new Sitemap() {

            String name;

            @Override
            public String getName() {
                return name;
            }

            public void setName(String var1) {
                this.name = var1;
            }

            @Override
            public String getLabel() {
                return "test-label";
            }

            @Override
            public String getIcon() {
                return null;
            }

            @Override
            public List<Widget<?>> getChildren() {
                return Collections.unmodifiableList(Collections.singletonList(null));
            }
        };
    }

    @Test
    public void canProxySimpleGetter() {

        EMFLessProxyGenerator proxyGenerator = new EMFLessProxyGenerator();

        org.eclipse.smarthome.model.sitemap.sitemap.Sitemap proxied = proxyGenerator.proxyFor(stubSitemap,
                org.eclipse.smarthome.model.sitemap.sitemap.Sitemap.class);

        assertEquals(stubSitemap.getLabel(), proxied.getLabel());
    }

    @Test
    public void canProxyEListAndContents() {

        EMFLessProxyGenerator proxyGenerator = new EMFLessProxyGenerator();

        org.eclipse.smarthome.model.sitemap.sitemap.Sitemap proxied = proxyGenerator.proxyFor(stubSitemap,
                org.eclipse.smarthome.model.sitemap.sitemap.Sitemap.class);

        EList<org.eclipse.smarthome.model.sitemap.sitemap.Widget> proxiedReturn = proxied.getChildren();

        assertEquals(1, proxiedReturn.size());
    }

    @Test
    public void willNotProxySimpleSetter() {

        EMFLessProxyGenerator proxyGenerator = new EMFLessProxyGenerator();

        org.eclipse.smarthome.model.sitemap.sitemap.Sitemap proxied = proxyGenerator.proxyFor(stubSitemap,
                org.eclipse.smarthome.model.sitemap.sitemap.Sitemap.class);

        try {
            proxied.setName("test-name");
            Assert.fail("Proxy allowed setter");
        } catch (UnsupportedOperationException e) {
            //pass
        }
    }

}