/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.framework.FrameworkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.miwest.sample.common.service.ResourceLoader;
import com.miwest.sample.common.service.ServiceFactory;
import com.miwest.sample.thesaurus.dto.Term;
import com.miwest.sample.thesaurus.service.ThesaurusService;

/**
 * A service for loading a thesaurus from a file and 
 * finding and retrieving terms within that thesaurus
 * 
 * @see	Set
 * @see Collections.synchronizedSet
 */
public class ThesaurusServiceImpl implements ThesaurusService 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	// The loaded thesaurus
	protected Map<String, String> thesaurus = new HashMap<String, String>();
	
	/**
	 * Loads a thesaurus from a flat file contained within the bundle
	 *
	 * @param  thesaurusFilename	the filename of the thesaurus to load
	 */
	@Override
	public void loadThesaurus(String thesaurusResource) throws IOException 
	{
		logger.info("Loading thesaurus from " + thesaurusResource);
		BufferedReader bufferedReader = getResourceLoader().loadResource(FrameworkUtil.getBundle(getClass()), thesaurusResource);
		
		while(bufferedReader.ready())
		{
			String line = bufferedReader.readLine();
			
			if (line.contains(","))
			{
				int index = line.indexOf(",");
				String word = line.substring(0, index);
				String synonyms = line.substring(index + 1);
				this.thesaurus.put(word, synonyms);
			}
		}
		
		logger.info("Finished loading thesaurus");
	}
	
	/**
	 * Finds a word in the thesaurus and creates a term for it
	 *
	 * @param  word		the word to find in the thesaurus
	 * @return 			the term found
	 */
	@Override
	public Term findTerm(String word) 
	{	
		String synonyms = this.thesaurus.get(word);
		
		Term thesaurusTerm = new Term();
		thesaurusTerm.setWord(word);
		thesaurusTerm.setSynonyms(synonyms == null ? null : convertCsvToSet(synonyms));
		thesaurusTerm.setDate(new Date());
		
		return thesaurusTerm;
	}
	
	// Get the resource loader from OSGi
	protected ResourceLoader getResourceLoader()
	{
		return ServiceFactory.getService(ResourceLoader.class);
	}
	
	// Convert a comma separated values string into a set
	protected Set<String> convertCsvToSet(String csv)
	{
		Set<String> set = new TreeSet<String>();
		String[] array = csv.split(",");
	
		for (int i=0;i<array.length;i++)
		{
			set.add(array[i]);
		}
	
		return set;
	}
}