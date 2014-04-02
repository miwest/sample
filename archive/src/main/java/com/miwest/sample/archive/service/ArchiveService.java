/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.archive.service;

/**
 * A thread safe service for archiving elements of Type E
 * This is a wrapper service around java.util.Set
 * 
 * @see	Set
 * @see Collections.synchronizedSet
 */
public interface ArchiveService<E extends Object>
{	
	/**
	 * Adds an element of type E to the archive.
	 *
	 * @param  e	element to be added to this archive
	 * @return      true if this set did not already contain the specified element
	 */
	public boolean add(E e);
	
	/**
	 * Removes an element of type E from the archive.
	 *
	 * @param  e	element to be removed from this archive
	 * @return      true if this set contained the specified element
	 */
	public boolean remove(E e);
	
	/**
	 * Returns the number of elements in this archive.
	 *
	 * @return      the number of elements in this set (its cardinality)
	 */
	public int size();
}