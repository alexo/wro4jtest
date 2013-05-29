package com.github.wro4jtest.manager.factory;

import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;

public class MyWroManagerFactory extends CopyrightKeeperConfigurableWroManagerFactory {
	private static final Logger LOG = LoggerFactory.getLogger(MyWroManagerFactory.class);

	@Override
	protected WroModelFactory newModelFactory() {
		LOG.debug("Load wro.xml directly from classpath");
		return new XmlModelFactory() {
			@Override
			protected InputStream getModelResourceAsStream() throws IOException {
				final String resourceLocation = getDefaultModelFilename();
				final InputStream stream = new ClasspathUriLocator().locate("classpath:" + resourceLocation);

				if (stream == null) {
					throw new IOException("Invalid resource requested: " + resourceLocation);
				}

				return stream;
			}
		};
	}
}