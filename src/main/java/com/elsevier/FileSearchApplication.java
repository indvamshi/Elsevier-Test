package com.elsevier;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Standalone application to run web application
 * @author vchitti
 *
 */
@SpringBootApplication
public class FileSearchApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		new FileSearchApplication().configure(
				new SpringApplicationBuilder(FileSearchApplication.class)).run(
				args);
	}
}
