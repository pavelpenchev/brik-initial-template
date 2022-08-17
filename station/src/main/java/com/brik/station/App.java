package com.brik.station;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import com.brik.station.rest.ServiceContainerApplication;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

	private final URI baseURI;
	
	public App() {
		baseURI = URI.create("http://localhost:5900");
	}
	
	public void start() throws IllegalStateException {
		new Thread(() -> {
			try {
				ServiceContainerApplication application = new ServiceContainerApplication();
				
				HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseURI, application, false);
				Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
				server.start();

				logger.info("Brik Station Container started at " + baseURI.toString());

				Thread.currentThread().join();
			} catch (IOException | InterruptedException ex) {
				logger.log(Level.SEVERE, "Error in HTTP service component, process exiting", ex);
			}		
		}).start();
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.start();
	}
}
