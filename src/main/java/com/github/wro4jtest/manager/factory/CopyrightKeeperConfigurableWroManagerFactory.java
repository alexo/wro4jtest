package com.github.wro4jtest.manager.factory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssMinProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.JawrCssMinifierProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;

public class CopyrightKeeperConfigurableWroManagerFactory extends ConfigurableWroManagerFactory {
	private static final Logger LOG = LoggerFactory.getLogger(CopyrightKeeperConfigurableWroManagerFactory.class);

	private static final String[] PROCESSORS = {
		CssImportPreProcessor.ALIAS,
		JawrCssMinifierProcessor.ALIAS,
		CssMinProcessor.ALIAS,
		JSMinProcessor.ALIAS
	};

	@Override
	protected void contributePreProcessors(final Map<String, ResourcePreProcessor> map) {
		for (String processor : PROCESSORS) {
			if (map.containsKey(processor)) {
				LOG.debug("Apply CopyrightKeeperProcessorDecorator on " + processor);
				map.put(processor, CopyrightKeeperProcessorDecorator.decorate(map.get(processor)));
			}
		}
	}
}