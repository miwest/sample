/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.common.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.osgi.framework.Bundle;

import com.miwest.sample.common.service.ResourceLoader;

/**
 * A service for loading resources into OSGi 
 */
public class ResourceLoaderImpl implements ResourceLoader 
{
	/**
	 * Loads a file contained in the bundle into the OSGi
	 * container. Files are located in src/main/resources
	 *
	 * @param  bundle	the bundle where the file is located
	 * @param  filename	the name of the file	
	 * @return      	a buffered reader with the contents of the file
	 */
	@Override
	public BufferedReader loadResource(Bundle bundle, String filename) throws IOException 
	{
		URL url = bundle.getResource(filename);
		InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream());
		
		return new BufferedReader(inputStream);
	}
}