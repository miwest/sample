/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.common.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceException;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

/**
 * A factory to obtain OSGi registered services
 */
public class ServiceFactory 
{
	/**
	 * Gets a registered service for the passed class type
	 * 
	 * @param  type	the class type
	 * @return 		the service
	 */
	public static synchronized <T> T getService(Class<T> type)
	{
		return getService(type, -1, null);
	}
	
	/**
	 * Gets a registered service for the passed class type
	 * Sets a max time to wait for the service if it's not
	 * immediately available.
	 * 
	 * @param  type		the class type
	 * @param  timeout	the amount of time in milliseconds to wait for a service to become available, 0 waits indefinitely
	 * @return 			the service
	 */
	public static synchronized <T> T getService(Class<T> type, long timeout)
	{
		return getService(type, timeout, null);
	}
	
	/**
	 * Gets a registered service for the passed class type
	 * using a filter.
	 * 
	 * @param  type		the class type
	 * @param  filter	the filter used to find a service
	 * @return 			the service
	 */
	public static synchronized <T> T getService(Class<T> type, String filter)
	{
		return getService(type, -1, filter);
	}

	/**
	 * Gets a registered service for the passed class type
	 * using a filter. Sets a max time to wait for the service
	 * if it's not immediately available.
	 * 
	 * @param type 		the class type
	 * @param filter	the filter used to find a service
	 * @param timeout 	the amount of time in milliseconds to wait on the service, 0 waits indefinitely
	 * @return 			the service
	 */
	@SuppressWarnings("unchecked")
	public static synchronized <T> T getService(Class<T> type, long timeout, String filter)
	{
		// Get the bundle context
		BundleContext context = FrameworkUtil.getBundle(type).getBundleContext();
		if (context == null)
		{
			throw new ServiceException("A BundleContext does not currently exist for " + type.getName(), ServiceException.UNREGISTERED);
		}
		
		// Create the service tracker for this class
		ServiceTracker<T, T> serviceTracker = new ServiceTracker<T, T>(context, type, null);
		serviceTracker.open();
		
		// Get the service
		T service = null;
		if (timeout >= 0)
		{
			try
			{ 
				service = serviceTracker.waitForService(timeout);
			}
			catch (InterruptedException e)
			{
				// Ignore interruptions
			}
		}
		else 
		{
			if (filter == null)
			{
				service = serviceTracker.getService();
			}
			else
			{
				try 
				{
					ServiceReference<T>[] serviceReferences = (ServiceReference<T>[])context.getServiceReferences(type.getName(), filter);
					
					if (serviceReferences != null && serviceReferences.length > 0)
					{
						service = serviceTracker.getService(serviceReferences[0]);
					}
					else
					{
						throw new ServiceException("Could not find " + type.getName() + " service with filter: " + filter, ServiceException.UNREGISTERED);
					}
					
				}
				catch (InvalidSyntaxException e) 
				{
					throw new ServiceException("The filter: " + filter + " is invalid for " + type.getName() + " service.", ServiceException.UNREGISTERED);
				}
				finally
				{
					serviceTracker.close();
				}
			}
		}
		
		// Close the service tracker
		serviceTracker.close();
		
		// If can't find the service, throw an exception
		if (service == null)
		{
			throw new ServiceException(type.getName() + " is not registered!", ServiceException.UNREGISTERED);
		}
		
		// Finally, return the service		
		return service;
	}
}