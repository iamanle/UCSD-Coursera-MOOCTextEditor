/**
 * 
 */
package textgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// Not work with sentinal node
//		assertEquals("Remove: check if head pointer is updated ", list1.head.data, list1.get(0));
		
		try{
			list1.remove(-1);
			fail("Remove element lower out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			list1.remove(3);
			fail("Remove element upper out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        int beforeSize = list1.size();
        // Clear cases
        assertTrue("AddEnd: check if 10 is added to list1 ", list1.add(10));
        assertEquals("AddEnd: check if size is reduced by 1 ", beforeSize+1, list1.size());
        
		// When list empty 
        assertTrue("AddEnd: check if 10 is added to emptylist ", emptyList.add(10));
        assertEquals("AddEnd: check if size of emptylist is now 1 ", 1, emptyList.size());
        
        // Not work with sentinal node
//        assertEquals("AddEnd: check if head is updated ", emptyList.head.data, emptyList.get(0));
        try{
			list1.add(null);
			fail("Add null element");
		} catch(NullPointerException e){
			
		}
        
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("testSize: check size shortList is 2", 2, shortList.size());
		assertEquals("testSize: check size longerList is 10", LONG_LIST_LENGTH, longerList.size());
		assertEquals("testSize: check size emptyList is 0", 0, emptyList.size());
		assertEquals("testSize: check size list1 is 3", 3, list1.size());
	}

	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// 65,21,42,10
        list1.add(3,10);
        assertEquals("AddAtIndex: check if last element is 10 after adding to tail position 3", 10, (int)(list1.get(list1.size() - 1)));
        assertEquals("AddAtIndex: check size list1 is 4", 4, list1.size());
        
        // 11,65,21,42,10
        list1.add(0, 11);
        assertEquals("AddAtIndex: check if last element is 11 after adding to head position 0", 11, (int)(list1.get(0)));
        assertEquals("AddAtIndex: check size list1 is 5", 5, list1.size());
        
        // 11,12,65,21,42,10
        list1.add(1, 12);
        assertEquals("AddAtIndex: check if last element is 12 after adding to position 1", 12, (int)(list1.get(1)));
        assertEquals("AddAtIndex: check size list1 is 6", 6, list1.size());
        
        try{
			list1.add(-1,1);
			fail("Add element lower out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			list1.add(7,2);
			fail("Add element upper out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
        
		try{
			list1.add(1,null);
			fail("Add null element");
		} catch(NullPointerException e){
			
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// 65,21,42
		assertEquals("Set: check if 65 is return after setting it to 10", 65, (int)(list1.set(0,10)));
		assertEquals("Set: check if list1(0) is 10 ", 10, (int)(list1.get(0)));
		try{
			list1.set(-1, 1);
			fail("Set element lower out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			list1.set(3, 1);
			fail("Set element upper out of bound");
		} catch(IndexOutOfBoundsException e){
			
		}
		
		try{
			list1.set(0,null);
			fail("Set null element");
		} catch(NullPointerException e){
			
		}
	}
}
