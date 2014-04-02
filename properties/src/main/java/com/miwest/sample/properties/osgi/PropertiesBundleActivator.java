/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.properties.osgi;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ManagedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.common.service.ServiceFactory;
import com.miwest.sample.properties.service.PropertiesService;
import com.miwest.sample.properties.service.impl.PropertiesServiceImpl;

/**
 * Implementation of BundleActivator and ManagedService used to 
 * start and stop the properties bundle in OSGi.
 * 
 * Persists properties found in a configuration file identified 
 * by the PID (persistent identifier) in the OSGi bundle
 */
public class PropertiesBundleActivator implements BundleActivator, ManagedService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void start(BundleContext context) throws Exception 
	{
		// Register ArchiveService in OSGi
		context.registerService(PropertiesService.class.getName(), new PropertiesServiceImpl(), new Hashtable<String, String>());
		
		// Set the configuration properties in OSGi
		setProperties(context);
		logger.info("Started Properties");
	}

	@Override
	public void stop(BundleContext context) throws Exception 
	{
		logger.info("Stopped Properties");
	}

    @Override
    public void updated(Dictionary<String, ?> properties) 
    {   
    	if (properties == null)
    	{
    		return;
    	}
    	
    	PropertiesService propertiesService = ServiceFactory.getService(PropertiesService.class);
    	
        // Update the properties
        // These must be set in the <PID>.cfg config file or an exception will be thrown during bundle startup
    	propertiesService.setPort((String) properties.get(PropertiesService.PORT));
    	propertiesService.setThesaurusFilename((String) properties.get(PropertiesService.THESAURUS_FILENAME));
    }   
    
    private void setProperties(BundleContext context) throws IOException
    {   
        // Retrieve and set the Properties
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put(Constants.SERVICE_PID, PropertiesService.CONFIG_PID);

        // Register the PID (persistent identifier) with the Bundle Context
        context.registerService(ManagedService.class.getName(), new PropertiesBundleActivator() , properties);

        // Retrieve the properties using the PID (persistent identifier)
        ServiceReference<?> configurationAdminReference = context.getServiceReference(ConfigurationAdmin.class.getName());
        ConfigurationAdmin configurationAdmin = (ConfigurationAdmin) context.getService(configurationAdminReference);
        Configuration config = configurationAdmin.getConfiguration(PropertiesService.CONFIG_PID);
        updated(config.getProperties());
    }   
}