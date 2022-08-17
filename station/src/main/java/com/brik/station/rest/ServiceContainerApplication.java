package com.brik.station.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class ServiceContainerApplication extends ResourceConfig {

	public ServiceContainerApplication() {
		registerCommonResources();
	}

	private void registerCommonResources() {
		register(new StatusResource());
		register(new CharsetResponseFilter());
	}
}
