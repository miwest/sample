/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.thesaurus.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;

import com.miwest.sample.common.service.ResourceLoader;
import com.miwest.sample.thesaurus.dto.Term;
import com.miwest.sample.thesaurus.service.ThesaurusService;
import com.miwest.sample.thesaurus.service.impl.ThesaurusServiceImpl;
 
/**
 * Unit test for ThesaurusService
 */
public class ThesaurusServiceTest
{	
	private static final String THESAURUS_FILENAME = "thesaurus";
	private static final String THESAURUS = "happy,cheerful,glad,merry\nsad,sorrowful,gloomy,hopeless\n";
	private static final String WORD_1 = "happy";
	private static final String WORD_2 = "sad";
	private static final String SYNONYMS_LIST_1 = "cheerful,glad,merry";
	private static final String SYNONYMS_LIST_2 = "sorrowful,gloomy,hopeless";
	private static final String SYNONYM_1 = "cheerful";
	private static final String SYNONYM_2 = "glad";
	private static final String SYNONYM_3 = "merry";
	private static final String SYNONYM_4 = "sorrowful";
	private static final String SYNONYM_5 = "gloomy";
	private static final String SYNONYM_6 = "hopeless";
	private static final Set<String> SYNONYMS_SET_1 = new TreeSet<String>();
	private static final Set<String> SYNONYMS_SET_2 = new TreeSet<String>();
	
	private static MockThesaurusService MOCK_THESAURUS_SERVICE;
	private static Term TERM_1;
	private static Term TERM_2;
	  
	@Test
    public void testLoadThesaurus() 
    {
		// The thesaurus is a map with the key=word and value=comma delimited list of synonyms
		Map<String, String> thesaurus = MOCK_THESAURUS_SERVICE.getThesaurus();
		
		// Check the values of the thesaurus
		assertTrue(thesaurus.containsKey(WORD_1));
		assertTrue(thesaurus.containsKey(WORD_2));
		assertTrue(thesaurus.containsValue(SYNONYMS_LIST_1));
		assertTrue(thesaurus.containsValue(SYNONYMS_LIST_2));
		assertEquals(SYNONYMS_LIST_1, thesaurus.get(WORD_1));
		assertEquals(SYNONYMS_LIST_2, thesaurus.get(WORD_2));
    }
	
	@Test
	public void testFindTerm()
	{
		Term actualTerm1 = MOCK_THESAURUS_SERVICE.findTerm(WORD_1);
		Term actualTerm2 = MOCK_THESAURUS_SERVICE.findTerm(WORD_2);
		assertEquals(TERM_1, actualTerm1);
		assertEquals(TERM_2, actualTerm2);
	}
	
	@Test
	public void testConvertCsvToSet()
	{
		Set<String> actualSet1 = MOCK_THESAURUS_SERVICE.convertCsvToSetWrapper(SYNONYMS_LIST_1);
		Set<String> actualSet2 = MOCK_THESAURUS_SERVICE.convertCsvToSetWrapper(SYNONYMS_LIST_2);
		
		assertEquals(actualSet1, SYNONYMS_SET_1);
		assertEquals(actualSet2, SYNONYMS_SET_2);
	}
    
    @Before
    public void setUp() throws Exception 
    {
    	MOCK_THESAURUS_SERVICE = new MockThesaurusServiceImpl();
    	
    	try 
		{
			MOCK_THESAURUS_SERVICE.loadThesaurus(THESAURUS_FILENAME);
		}
		catch (IOException e) 
		{
			fail(e.getMessage());
		}
    }
 
    @After
    public void tearDown() throws Exception 
    {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception 
    {
    	// Set up the test terms
    	TERM_1 = new Term();
    	TERM_2 = new Term();
    	
    	SYNONYMS_SET_1.add(SYNONYM_1);
    	SYNONYMS_SET_1.add(SYNONYM_2);
    	SYNONYMS_SET_1.add(SYNONYM_3);
    	TERM_1.setWord(WORD_1);
    	TERM_1.setSynonyms(SYNONYMS_SET_1);
    	
    	SYNONYMS_SET_2.add(SYNONYM_4);
    	SYNONYMS_SET_2.add(SYNONYM_5);
    	SYNONYMS_SET_2.add(SYNONYM_6);
    	TERM_2.setWord(WORD_2);
    	TERM_2.setSynonyms(SYNONYMS_SET_2);
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception 
    {
    }
    
    private static class MockThesaurusServiceImpl extends ThesaurusServiceImpl implements ThesaurusService, MockThesaurusService
    {	
    	@Override
		public Map<String, String> getThesaurus() 
		{
			return this.thesaurus;
		}
    	
    	@Override
    	protected ResourceLoader getResourceLoader()
    	{
    		return new MockResourceLoader();
    	}

		@Override
		public Set<String> convertCsvToSetWrapper(String csv) 
		{
			// Wrapper around convertCsvToSet to expose it in a public interface
			return convertCsvToSet(csv);
		}
    }
    
    private static class MockResourceLoader implements ResourceLoader
    {
		@Override
		public BufferedReader loadResource(Bundle bundle, String filename) throws IOException 
		{
			// Ensure we're getting the filename we're expecting
			assertEquals(THESAURUS_FILENAME, filename);
			
			// Convert String into BufferedReader
			return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(THESAURUS.getBytes())));
		}
    }
}