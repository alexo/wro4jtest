package com.github.wro4jtest;

import java.util.Properties;

import ro.isdc.wro.http.ConfigurableWroFilter;
import ro.isdc.wro.manager.factory.WroManagerFactory;

import com.github.wro4jtest.manager.factory.MyWroManagerFactory;

public class MyConfigurableWroFilter
    extends ConfigurableWroFilter {
  private Properties properties;
  /**
   * {@inheritDoc}
   */
  @Override
  protected WroManagerFactory newWroManagerFactory() {
    return new MyWroManagerFactory() {
      @Override
      protected Properties newConfigProperties() {
        return properties;
      }
    };
  }

  /**
   * Since properties field is not accessible.
   */
  @Override
  public void setProperties(final Properties properties) {
    super.setProperties(properties);
    this.properties = properties;
  }
}
