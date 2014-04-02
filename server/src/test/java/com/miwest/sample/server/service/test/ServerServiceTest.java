/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.server.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.miwest.sample.archive.service.ArchiveService;
import com.miwest.sample.server.service.MockArchiveService;
import com.miwest.sample.server.service.MockServerService;
import com.miwest.sample.server.service.ServerService;
import com.miwest.sample.server.service.impl.ServerServiceImpl;
import com.miwest.sample.thesaurus.dto.Term;
import com.miwest.sample.thesaurus.service.ThesaurusService;
 
/**
 * Unit test for ServerService
 */
public class ServerServiceTest
{
	private static final String TEST_LIST = "test1,test2,test3";
	private static final String TEST_WORD = "test";
	private static final Set<String> TEST_SYNONYMS = new TreeSet<String>();
	private static final Date TEST_DATE = new Date();
	
	private static MockServerService MOCK_SERVER_SERVICE;
	private static Term TEST_TERM;
	  
	@Test
    public void testAddTerm() 
    {
		MOCK_SERVER_SERVICE.addTerm(TEST_LIST, TEST_WORD);
		ensureTermExistsInMock();
    }
	
	@Test
	public void testRemoveTerm()
	{
		// Add a term to the mock archive
		MOCK_SERVER_SERVICE.getMockArchiveService().add(TEST_TERM);
		ensureTermExistsInMock();
		
		// Remove the term and ensure it was removed from the mock archive
		MOCK_SERVER_SERVICE.removeTerm(TEST_LIST, TEST_WORD);
		ensureTermDoesntExistInMock();
	}
	
	private void ensureTermExistsInMock()
	{
		// Ensure the term got added to the mock archive
		assertEquals(TEST_TERM, MOCK_SERVER_SERVICE.getMockArchiveService().get());
		assertEquals(1, MOCK_SERVER_SERVICE.getMockArchiveService().size());
		
		// Ensure the term that is added to the archive is what we're expecting
		assertEquals(TEST_WORD, MOCK_SERVER_SERVICE.getMockArchiveService().get().getWord());
		assertEquals(TEST_SYNONYMS, MOCK_SERVER_SERVICE.getMockArchiveService().get().getSynonyms());
		assertEquals(TEST_DATE, MOCK_SERVER_SERVICE.getMockArchiveService().get().getDate());
	}
	
	private void ensureTermDoesntExistInMock()
	{
		assertNull(MOCK_SERVER_SERVICE.getMockArchiveService().get());
		assertEquals(0, MOCK_SERVER_SERVICE.getMockArchiveService().size());
	}
    
    @Before
    public void setUp() throws Exception 
    {
    	MOCK_SERVER_SERVICE = new MockServerServiceImpl();
    	
    	// Make sure the mock archive is cleared out
    	ensureTermDoesntExistInMock();
    }
 
    @After
    public void tearDown() throws Exception 
    {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception 
    {
    	// Set up the term
    	TEST_TERM = new Term();
    	
    	TEST_TERM.setWord(TEST_WORD);
    	TEST_TERM.setSynonyms(TEST_SYNONYMS);
    	TEST_TERM.setDate(TEST_DATE);
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception 
    {
    }
    
    private static class MockServerServiceImpl extends ServerServiceImpl implements ServerService, MockServerService
    {	
    	private ArchiveService<Term> archiveService = new MockArchiveServiceImpl<Term>();
    	private ThesaurusService thesaurusService = new MockThesaurusServiceImpl();
    	
    	@Override
    	protected ArchiveService<Term> getArchiveService()
    	{
    		return archiveService;
    	}
    	
    	@Override
    	protected ThesaurusService getThesaurusService()
    	{
    		return thesaurusService;
    	}

    	@Override
		public MockArchiveService<Term> getMockArchiveService()
    	{
    		return (MockArchiveService<Term>) archiveService;
    	}
    }
    
    private static class MockArchiveServiceImpl<E extends Object> implements ArchiveService<E>, MockArchiveService<E>
    {
    	E e = null;
    	
		@Override
		public boolean add(E e) 
		{
			this.e = e;
			return true;
		}

		@Override
		public boolean remove(E e) 
		{
			// Ensure that what we are removing is what is passed in
			assertEquals(this.e, e);
			this.e = null;
			
			return true;
		}
		
		@Override
		public int size() 
		{
			return e == null ? 0 : 1;
		}
		
		@Override
		public E get()
		{
			return e;
		}
    }
    
    private static class MockThesaurusServiceImpl implements ThesaurusService
    {
		@Override
		public void loadThesaurus(String thesaurus) throws IOException 
		{
			// No implementation needed for mock
		}

		@Override
		public Term findTerm(String word) 
		{	
			return TEST_TERM;
		}	
    }
}