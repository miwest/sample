/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.service;

import java.io.IOException;

import com.miwest.sample.thesaurus.dto.Term;

/**
 * A service for loading a thesaurus from a file and 
 * finding and retrieving terms within that thesaurus
 * 
 * @see	Set
 * @see Collections.synchronizedSet
 */
public interface ThesaurusService 
{
	/**
	 * Loads a thesaurus from a flat file contained within the bundle
	 *
	 * @param  thesaurusFilename	the filename of the thesaurus to load
	 */
	public void loadThesaurus(String thesaurusFilename) throws IOException;
	
	/**
	 * Finds a word in the thesaurus and creates a term for it
	 *
	 * @param  word		the word to find in the thesaurus
	 * @return 			the term found
	 */
	public Term findTerm(String word);
}