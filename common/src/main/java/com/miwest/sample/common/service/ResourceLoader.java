/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.common.service;

import java.io.BufferedReader;
import java.io.IOException;

import org.osgi.framework.Bundle;

/**
 * A service for loading resources into OSGi 
 */
public interface ResourceLoader 
{
	/**
	 * Loads a file contained in the bundle into the OSGi
	 * container. Files are located in src/main/resources
	 *
	 * @param  bundle	the bundle where the file is located
	 * @param  filename	the name of the file	
	 * @return      	a buffered reader with the contents of the file
	 */
	public BufferedReader loadResource(Bundle bundle, String filename) throws IOException;
}
