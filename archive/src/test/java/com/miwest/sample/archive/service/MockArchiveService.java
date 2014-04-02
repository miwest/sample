/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.archive.service;

public interface MockArchiveService<E extends Object> extends ArchiveService<E> 
{
	public boolean contains(E e);
	public void clear();
}