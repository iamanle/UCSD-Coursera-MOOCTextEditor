package textgen;

import java.util.AbstractList;


class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		if(e == null)  throw new NullPointerException();
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(){
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof LLNode)){
			return false;
		}
		if(obj == this) return true;
		
		LLNode<?> newNode = (LLNode<?>)obj;
		if(data.equals(newNode.data) 
				&& next.equals(newNode.next) 
				&& prev.equals(newNode.prev))
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		return data.toString();
	}
}

/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>();
		tail = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null)  throw new NullPointerException();
		LLNode<E> newNode = new LLNode<E>(element);
		tail.next = newNode;
		newNode.prev = tail;
		newNode.next = null;
		tail = newNode;
		
		// Add to head
		if(size == 0){
			head = newNode;
		}
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode<E> cursor = find(index);
		return cursor.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		if(element == null) throw new NullPointerException();
		// Add to tail
		if(index == size || size == 0){
			add(element);
			return;
		} 
		
		// Add in the middle
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> cursor = find(index);
		newNode.prev = cursor.prev;
		newNode.next = cursor;
		cursor.prev.next = newNode;
		cursor.prev = newNode;
		
		// If head
		if(index == 0){
			head = newNode;
		}
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(size == 0 || index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		E data;
		// Remove head
		if(index == 0){
			data = head.data;
			head = head.next;
			if(size > 1){
				head.prev = null;
			}
		}
		
		// Remove tail
		else if(index == size-1){
			data = tail.data;
			tail = tail.prev;
			tail.next = null;
		}
		
		// Remove middle
		else{
			LLNode<E> cursor = find(index);
			data = cursor.data;
			cursor.next.prev = cursor.prev;
			cursor.prev.next = cursor.next;
		}
		
		size--;
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null) throw new NullPointerException();
		if(size == 0 || index < 0 || index >= size) throw new IndexOutOfBoundsException();
		LLNode<E> cursor = find(index);
		E data = cursor.data;
		cursor.data = element;
		return data;
	}   
	
	private LLNode<E> find(int index){
		if(size == 0 || index < 0 || index >= size) throw new IndexOutOfBoundsException();
		int count = 0;
		LLNode<E> cursor = head;
		while(count != index && cursor.next != null){
			count++;
			cursor = cursor.next;
		}
		return cursor;
	}
	
	@Override
	public String toString(){
		LLNode<E> cursor = head;
		StringBuilder sb = new StringBuilder();
		while(cursor.next != null){
			sb.append(cursor + ", ");
			cursor = cursor.next;
		}
		return sb.toString();
	}
}

