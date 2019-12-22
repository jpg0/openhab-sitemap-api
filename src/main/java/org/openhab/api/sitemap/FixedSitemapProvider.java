package org.openhab.api.sitemap;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.model.core.ModelRepositoryChangeListener;
import org.eclipse.smarthome.model.sitemap.SitemapProvider;
import org.eclipse.smarthome.model.sitemap.sitemap.Sitemap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FixedSitemapProvider implements SitemapProvider {
    private Map<String, Sitemap> sitemaps;

    public FixedSitemapProvider(List<Sitemap> sitemaps) {
        this.sitemaps = new HashMap<>(sitemaps.size());
        for(Sitemap sitemap : sitemaps) {
            this.sitemaps.put(sitemap.getName(), sitemap);
        }
    }

    @Override
    public @Nullable Sitemap getSitemap(String s) {
        return sitemaps.get(s);
    }

    @Override
    public Set<String> getSitemapNames() {
        return sitemaps.keySet();
    }

    @Override
    public void addModelChangeListener(ModelRepositoryChangeListener modelRepositoryChangeListener) {
        //ignore
    }

    @Override
    public void removeModelChangeListener(ModelRepositoryChangeListener modelRepositoryChangeListener) {
        //ignore
    }
}
