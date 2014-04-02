/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.osgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.common.service.ServiceFactory;
import com.miwest.sample.properties.service.PropertiesService;
import com.miwest.sample.thesaurus.service.ThesaurusService;
import com.miwest.sample.thesaurus.service.impl.ThesaurusServiceImpl;

/**
 * Implementation of BundleActivator used to 
 * start and stop the thesaurs bundle in OSGi. 
 */
public class ThesaurusBundleActivator implements BundleActivator 
{	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		// Register the Thesaurus Service
		context.registerService(ThesaurusService.class.getName(), new ThesaurusServiceImpl(), new Hashtable<String, String>());
		
		// Load the thesaurus
		ThesaurusService thesaurusService = ServiceFactory.getService(ThesaurusService.class);
		PropertiesService propertiesService = ServiceFactory.getService(PropertiesService.class);
		thesaurusService.loadThesaurus(propertiesService.getThesaurusFilename());
		
		logger.info("Started thesaurus");
	}

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		logger.info("Stopped thesaurus");
	}
}