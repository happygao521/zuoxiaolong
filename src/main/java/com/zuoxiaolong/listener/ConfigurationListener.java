package com.zuoxiaolong.listener;

import com.zuoxiaolong.config.Configuration;
import com.zuoxiaolong.reptile.Reptiles;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author 左潇龙
 * @since 5/7/2015 3:33 PM
 */
public class ConfigurationListener implements ServletContextListener {
	
	private static final Logger logger = Logger.getLogger(ConfigurationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	if (logger.isInfoEnabled()) {
			logger.info("will begin init configuration...");
		}
        Configuration.init(servletContextEvent.getServletContext());
        if (logger.isInfoEnabled()) {
			logger.info("init configuration success...");
		}
        if (logger.isInfoEnabled()) {
			logger.info("starting fetch and generate thread...");
		}
        Reptiles.newReptileThread().start();
        if (logger.isInfoEnabled()) {
			logger.info("fetch and generate thread has been started...");
		}
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
