/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.properties.service;

/**
 * Gets and sets properties found in the configuration file
 * identified by CONFIG_PID
 */
public interface PropertiesService 
{
	// Config Persistent Identifier
	public static final String CONFIG_PID = "com.miwest.sample.properties";
	
	// Property names
	public static final String PORT = "port";
	public static final String THESAURUS_FILENAME = "thesaurus-filename";
    
	// Getters and setters
	public String getPort();
	public void setPort(String port);
	public String getThesaurusFilename();
	public void setThesaurusFilename(String thesaurusFilename);
}