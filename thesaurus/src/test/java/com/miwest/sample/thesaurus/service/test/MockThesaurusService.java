/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.service.test;

import java.util.Map;
import java.util.Set;

import com.miwest.sample.thesaurus.service.ThesaurusService;

public interface MockThesaurusService extends ThesaurusService 
{
	public Map<String, String> getThesaurus();
	public Set<String> convertCsvToSetWrapper(String csv);
}