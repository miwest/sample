/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of BundleActivator used to 
 * start and stop the server bundle in OSGi. 
 */
public class ServerBundleActivator implements BundleActivator 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		logger.info("Started Server");
	}

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		logger.info("Stopped Server");
	}
}