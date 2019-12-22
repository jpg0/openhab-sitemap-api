# Sitemap API

This bundle provides an API to create sitemaps without Eclipse EMF.

## Usage:

Create widgets:
```
List<Widget<?>> widgets = new ArrayList<>;
WidgetBuilder widgetBuilder = new WidgetBuilder();

widgets.add(widgetBuilder.newSwitch(...));
```
Create sitemap:
```
Sitemap mySitemap = EMFLessSitemapBuilder.create('mySitemap.)
                    .addWidget(widgets)
                    .buildProxy();
```
Create a sitemap provider and register it:
```
SitemapProvider sp = new FixedSitemapProvider(Collections.singletonList(mySitemap));
bundleContext.registerService(SitemapProvider.class, sp); //or register via annotations etc
``` 