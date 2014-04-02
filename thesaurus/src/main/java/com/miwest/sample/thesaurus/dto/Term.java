/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * An object representing a thesaurus term with a word and a set of synonyms. 
 * A date is included to use for when the term was created, but is not used 
 * to determine equality.
 */
public class Term implements Comparable<Term> 
{
	private final static String NO_SYNONYMS_MESSAGE = "[No synonyms were found]";
	
	private String word;
	private Set<String> synonyms;
	private Date date;
	
	public String getWord() 
	{
		return word;
	}
	
	public void setWord(String word) 
	{
		this.word = word;
	}
	
	public Set<String> getSynonyms() 
	{
		return synonyms;
	}
	
	public void setSynonyms(Set<String> synonyms) 
	{
		this.synonyms = synonyms;
	}
	
	public Date getDate() 
	{
		return date;
	}
	
	public void setDate(Date date) 
	{
		this.date = date;
	}
	
	/**
	 * Returns a string representation of a term in HTML. The term shows the word,
	 * when it was created, and a list of synonyms.
	 * 
	 * @Return	the string representation of a term in HTML
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy h:mm:ss");
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("<b>").append(this.word).append(":</b>&nbsp;");
		stringBuilder.append("<i>Added on&nbsp;").append(formatter.format(this.date)).append("</i>").append("&nbsp");
		
		if (synonyms != null)
		{
			stringBuilder.append(synonyms);
		}
		else
		{
			stringBuilder.append(NO_SYNONYMS_MESSAGE);
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Equals method for Term
	 * 
	 * Terms are considered equal if the word and synonyms are equal.
	 * The dates can differ, but if the word and synonyms are equal, the
	 * terms will be considered equal.
	 * 
	 * Note: This class has a natural ordering this is inconsistent with equals.
	 * 
	 * @param	o	the term object passed in to determine its equality with this object
	 * @return		true if this term is equal to the term passed in
	 */
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Term) || o == null)
		{
			return false;
		}
		
		Term term = (Term)o;
		if (this.word.equals(term.getWord()) &&
			this.synonyms.equals(term.getSynonyms()))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Hashcode method for Term
	 * 
	 * The hashcode representation for term. The word and synonym are used
	 * to determine the hashcode value.
	 * 
	 * @return		the hashcode value
	 */
	@Override
	public int hashCode()
	{
		int hashCode = 31;
		
		hashCode += this.word == null ? 0 : this.word.hashCode();
		hashCode += this.synonyms == null ? 0 : this.synonyms.hashCode();
		
		return hashCode;
	}

	/**
	 * CompareTo method for Term
	 * 
	 * Terms are ordered in alphabetical order using the word.
	 * 
	 * Note: This class has a natural ordering this is inconsistent with equals.
	 * 
	 * @param  term		the term to compare with this object
	 * @return			a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
	 */
	@Override
	public int compareTo(Term term)
	{
		if (term == null)
		{
			return -1;
		}
		
		return this.word.compareTo(term.getWord());
	}
}