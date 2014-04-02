/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.common.osgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.common.service.ResourceLoader;
import com.miwest.sample.common.service.impl.ResourceLoaderImpl;

/**
 * Implementation of BundleActivator used to 
 * start and stop the common bundle in OSGi.
 * 
 *  The common bundle contains common services
 *  that can be used in typical OSGi projects
 */
public class CommonBundleActivator implements BundleActivator 
{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		// Register ResourceLoader in OSGi
		context.registerService(ResourceLoader.class.getName(), new ResourceLoaderImpl(), new Hashtable<String, String>());
		logger.info("Started Common");
	}

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		logger.info("Stopped Common");
	}
}