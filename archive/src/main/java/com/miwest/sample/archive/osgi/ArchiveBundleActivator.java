/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.archive.osgi;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.archive.service.ArchiveService;
import com.miwest.sample.archive.service.impl.ArchiveServiceImpl;
import com.miwest.sample.thesaurus.dto.Term;

/**
 * Implementation of BundleActivator used to 
 * start and stop the archive bundle in OSGi. 
 */
public class ArchiveBundleActivator implements BundleActivator
{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception 
	{
		// Register ArchiveService in OSGi to archive Terms
		context.registerService(ArchiveService.class.getName(), new ArchiveServiceImpl<Term>(), new Hashtable<String, String>());
		logger.info("Started Archive");
	}

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		logger.info("Stopped Archive");
	}
}