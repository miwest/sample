/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.service.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.archive.service.ArchiveService;
import com.miwest.sample.common.service.ServiceFactory;
import com.miwest.sample.properties.service.PropertiesService;
import com.miwest.sample.server.service.ServerService;
import com.miwest.sample.thesaurus.dto.Term;
import com.miwest.sample.thesaurus.service.ThesaurusService;

/**
 * A service used to add and remove terms from an archive sent in from http requests. 
 * Listens for requests on http://<HOSTNAME>:<PORT>/thesaurus/
 */
@SuppressWarnings("unchecked")
@Path("/thesaurus")
public class ServerServiceImpl implements ServerService 
{
	private Logger logger = LoggerFactory.getLogger(getClass()); 

	/**
	 * Adds one word sent in via an HTTP request to the archive. This method is consumed via
	 * the CXF REST Endpont: http://<HOSTNAME>:<PORT>/thesaurus/add/{list} where {list} is a
	 * comma delimited list of words
	 *
	 * @param  list		comma delimited list of words to be parsed
	 * @param  word		the word from the list to be added to the archive 
	 */
	@Override
	@GET
	@Path("/add/{list}")
	public synchronized void addTerm(@PathParam("list") String list, String word) 
	{	
		Term term = getThesaurusService().findTerm(word);
		getArchiveService().add(term);
	}
	
	/**
	 * Removes one word sent in via an HTTP request from the archive. This method is consumed via
	 * the CXF REST Endpont: http://<HOSTNAME>:<PORT>/thesaurus/remove/{list} where {list} is a
	 * comma delimited list of words
	 *
	 * @param  list		comma delimited list of words to be parsed
	 * @param  word		the word from the list to be removed to the archive 
	 */
	@Override
	@GET
	@Path("/remove/{list}")
	public synchronized void removeTerm(@PathParam("list") String list, String word)
	{
		Term term = getThesaurusService().findTerm(word);
		getArchiveService().remove(term);
	}
	
	/**
	 * Sends a response back with a String representation of the archive
	 *
	 * @return		The response containing a String representation of the archive 
	 */
	@Override
	public synchronized Response display()
	{
		return Response.ok(getArchiveService().toString()).build();
	}
	
	/**
	 * Logs that this service is alive and running 
	 */
	@Override
	public void alive() 
	{
		logger.info("Listening on port " + getPropertiesService().getPort());
	}
	
	// Retrieve the services from OSGi
	protected ArchiveService<Term> getArchiveService()
	{
		return ServiceFactory.getService(ArchiveService.class);
	}
	
	protected PropertiesService getPropertiesService()
	{
		return ServiceFactory.getService(PropertiesService.class);
	}
	
	protected ThesaurusService getThesaurusService()
	{
		return ServiceFactory.getService(ThesaurusService.class);
	}
}