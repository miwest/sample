/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.archive.service.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import com.miwest.sample.archive.service.ArchiveService;

/**
 * A thread safe service for archiving elements of Type E
 * This is a wrapper service around java.util.Set
 * 
 * @see	Set
 * @see Collections.synchronizedSet
 */
public class ArchiveServiceImpl<E extends Object> implements ArchiveService<E> 
{
	// The thread safe underlying archive 
	protected volatile Set<E> archive = Collections.synchronizedSet(new TreeSet<E>());

	/**
	 * Adds an element of type E to the archive.
	 *
	 * @param  e	element to be added to this archive
	 * @return      true if this set did not already contain the specified element
	 */
	@Override
	public synchronized boolean add(E e)
	{
		return this.archive.add(e);
	}
	
	/**
	 * Removes an element of type E from the archive.
	 *
	 * @param  e	element to be removed from this archive
	 * @return      true if this set contained the specified element
	 */
	@Override
	public synchronized boolean remove(E e)
	{
		return this.archive.remove(e);
	}
	
	/**
	 * Returns the number of elements in this archive.
	 *
	 * @return      the number of elements in this set (its cardinality)
	 */
	@Override
	public synchronized int size()
	{
		return this.archive.size();
	}
	
	/**
	 * Returns an HTML view of the archive with one element per line
	 *
	 * @return      a string representation of the archive
	 */
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<E> iterator = archive.iterator();

		while (iterator.hasNext())
		{
			stringBuilder.append(iterator.next()).append("\n\n<br /><br />");
		}
		
		return stringBuilder.toString();
	}
}