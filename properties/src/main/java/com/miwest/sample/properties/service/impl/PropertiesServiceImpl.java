/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.properties.service.impl;

import com.miwest.sample.properties.service.PropertiesService;

/**
 * Gets and sets properties found in the configuration file
 * identified by CONFIG_PID
 */
public class PropertiesServiceImpl implements PropertiesService 
{
	private String port;
	private String thesaurusFilename;
	
	// Getters and setters
	@Override
	public String getPort() 
	{
		return port;
	}
	
	@Override
	public void setPort(String port) 
	{
		this.port = port;
	}
	
	@Override
	public String getThesaurusFilename() 
	{
		return thesaurusFilename;
	}
	
	@Override
	public void setThesaurusFilename(String thesaurusFilename) 
	{
		this.thesaurusFilename = thesaurusFilename;
	}
}