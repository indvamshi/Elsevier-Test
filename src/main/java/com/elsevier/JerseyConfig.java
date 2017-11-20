package com.elsevier;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.context.annotation.Configuration;

/**
 * The Jersey configuration is responsible for configuring resources during startup.
 * @author vchitti
 *
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(RequestContextFilter.class);
		packages("com.elsevier");
	}
}
