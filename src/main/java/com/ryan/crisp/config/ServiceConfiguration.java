/**
  * Copyright (c) 2018 by Sovos Compliance
 */
package com.ryan.crisp.config;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ServiceLocatorFactoryBean factoryBeanForServiceFactory() {

        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ServiceFactory.class);
        return factoryBean;
    }

}
