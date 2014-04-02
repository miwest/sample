/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * A service used to add and remove terms from an archive sent in from http requests. 
 * Listens for requests on http://<HOSTNAME>:<PORT>/thesaurus/
 */
public interface ServerService 
{	
	/**
	 * Adds one word sent in via an HTTP request to the archive. This method is consumed via
	 * the CXF REST Endpont: http://<HOSTNAME>:<PORT>/thesaurus/add/{list} where {list} is a
	 * comma delimited list of words
	 *
	 * @param  list		comma delimited list of words to be parsed
	 * @param  word		the word from the list to be added to the archive 
	 */
	@GET
	@Path("/add/{list}")
	public void addTerm(@PathParam("list") String list, String word);
	
	/**
	 * Removes one word sent in via an HTTP request from the archive. This method is consumed via
	 * the CXF REST Endpont: http://<HOSTNAME>:<PORT>/thesaurus/remove/{list} where {list} is a
	 * comma delimited list of words
	 *
	 * @param  list		comma delimited list of words to be parsed
	 * @param  word		the word from the list to be removed to the archive 
	 */
	@GET
	@Path("/remove/{list}")
	public void removeTerm(@PathParam("list") String list, String word);
	
	/**
	 * Sends a response back with a String representation of the archive
	 *
	 * @return		The response containing a String representation of the archive 
	 */
	public Response display();
	
	/**
	 * Logs that this service is alive and running 
	 */
	public void alive();
}