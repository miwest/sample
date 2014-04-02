/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.service;

import com.miwest.sample.server.service.ServerService;
import com.miwest.sample.thesaurus.dto.Term;

public interface MockServerService extends ServerService 
{	
	public MockArchiveService<Term> getMockArchiveService();
}