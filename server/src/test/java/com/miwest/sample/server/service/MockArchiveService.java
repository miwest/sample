/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.service;

import com.miwest.sample.archive.service.ArchiveService;

public interface MockArchiveService<E extends Object> extends ArchiveService<E> 
{
	public E get();
}
