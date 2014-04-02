/**

* MICHAEL WEST CODE SAMPLE
* ___________________
*
* Code sample for Solutionreach
*
* @author michaelwest
*/
package com.miwest.sample.archive.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.miwest.sample.archive.service.ArchiveService;
import com.miwest.sample.archive.service.MockArchiveService;
import com.miwest.sample.archive.service.impl.ArchiveServiceImpl;
 
/**
 * Unit test for ArchiveService
 */
public class ArchiveServiceTest
{
	private static final String ELEMENT = "test";
	private static MockArchiveService<String> MOCK_ARCHIVE_SERVICE;
	  
	@Test
    public void testAdd() 
    {
		// Test unique add
    	MOCK_ARCHIVE_SERVICE.add(ELEMENT);
    	
    	assertTrue(MOCK_ARCHIVE_SERVICE.contains(ELEMENT));
    	assertEquals(1, MOCK_ARCHIVE_SERVICE.size());
    	
    	// Test duplicate add
    	MOCK_ARCHIVE_SERVICE.add(ELEMENT);
    	
    	assertTrue(MOCK_ARCHIVE_SERVICE.contains(ELEMENT));
    	assertEquals(1, MOCK_ARCHIVE_SERVICE.size());
    }
 
    @Test
    public void testRemove() 
    {
    	// Test unique element
        MOCK_ARCHIVE_SERVICE.add(ELEMENT);
        MOCK_ARCHIVE_SERVICE.remove(ELEMENT);
        
        assertFalse(MOCK_ARCHIVE_SERVICE.contains(ELEMENT));
        assertEquals(0, MOCK_ARCHIVE_SERVICE.size());
        
        // Test addition of a duplicate element
        MOCK_ARCHIVE_SERVICE.add(ELEMENT);
        MOCK_ARCHIVE_SERVICE.add(ELEMENT);
        MOCK_ARCHIVE_SERVICE.remove(ELEMENT);
        
        assertFalse(MOCK_ARCHIVE_SERVICE.contains(ELEMENT));
        assertEquals(0, MOCK_ARCHIVE_SERVICE.size());
    }
 
    @Test
    public void testSize() 
    {
    	int numberTests = 10;
    	assertEquals(0, MOCK_ARCHIVE_SERVICE.size());
    	
    	for (int i=1;i<=numberTests;i++)
    	{
    		// Ensure the size increments properly with each addition
    		MOCK_ARCHIVE_SERVICE.add(ELEMENT + i);	
    		assertEquals(i, MOCK_ARCHIVE_SERVICE.size());
    	}
    	
    	// Test an empty archive
    	MOCK_ARCHIVE_SERVICE.clear();
    	assertEquals(0, MOCK_ARCHIVE_SERVICE.size());
    }
    
    @Before
    public void setUp() throws Exception 
    {
    }
 
    @After
    public void tearDown() throws Exception 
    {
       MOCK_ARCHIVE_SERVICE.clear();
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception 
    {
    	MOCK_ARCHIVE_SERVICE = new MockArchiveServiceImpl<String>();
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception 
    {
    }
    
    private static class MockArchiveServiceImpl<E extends Object> extends ArchiveServiceImpl<E> implements ArchiveService<E>, MockArchiveService<E>
    {	
    	@Override
		public boolean contains(E e)
		{
			return archive.contains(e);
		}
    	
    	@Override
    	public void clear()
    	{
    		archive.clear();
    	}
    }
}
