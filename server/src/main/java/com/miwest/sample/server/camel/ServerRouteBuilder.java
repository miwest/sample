/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.common.service.ServiceFactory;
import com.miwest.sample.properties.service.PropertiesService;

public class ServerRouteBuilder extends RouteBuilder 
{
	private static final int THREAD_POOL_SIZE = 5;
	private static final int MAX_THREAD_POOL_SIZE = 10;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void configure() throws Exception 
	{
		// Grab the configuration properties needed
		PropertiesService propertiesService = ServiceFactory.getService(PropertiesService.class);
		
		/*  
         * DISPLAY Camel Route
         * 
         * Listens on the configured port on localhost for incoming requests and routes them to the appropriate methods
         * for adding and removing synonyms from the archive. Splits the incoming comma delimited word list on the URL and
         * routes each word to the respective method to either add or remove the word from the archive. The route is then
         * channeled to display the contents of the archive in HTML
         */
		from("cxfrs://http://0.0.0.0:" + propertiesService.getPort() + "?resourceClasses=com.miwest.sample.server.service.impl.ServerServiceImpl")
			.threads(THREAD_POOL_SIZE, MAX_THREAD_POOL_SIZE)
			.split(body(String.class).tokenize(","))
				.process(new LowerCaseTransformProcessor())
				.to("cxfbean:serverBean")
				.end()
			.to("bean:serverBean?method=display");
		
		/*  
         * ALIVE Camel Route
         * 
         * Route for logging that this bundle is alive
         */
		from("timer://aliveTimer?period=5000&delay=0")
			.to("bean:serverBean?method=alive");
	}
	
	/*
	 * A processor used to transform the word list to lower case
	 */
	private class LowerCaseTransformProcessor implements Processor
	{
		@Override
		public void process(Exchange exchange) throws Exception 
		{
			Message in = exchange.getIn();
			in.setBody(in.getBody(String.class).toLowerCase());
			exchange.setIn(in);
		}
	}
}