import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnsortedLinkedDictionary<K, V>  {
	
	private Node head;
	private Node firstNode;
	private Node lastNode;
	private int numberOfEntries;
	
   public UnsortedLinkedDictionary() {
	
	   head = null;
	   lastNode = null;
	   numberOfEntries = 0;
	   
	} // end default constructor
   
   public UnsortedLinkedDictionary( K key, V value ) {
	   this.add( key, value );
   } //end constructor with parameters 
	
   public V add(K key, V value) {
	   
	   //if the first node is equal to null & number of entries is 0 
	   if ( head == null && numberOfEntries == 0 ) {
		   
		   Node tempNode = new Node( key, value ); //create a new node with values
		   head = tempNode; //assign to the first node
		   firstNode = tempNode;
		   lastNode = tempNode; //assign the last node to the only node in dictionary 
		   
		   numberOfEntries ++; //increment the number of entries
		   return value; //return the value added
		   
	   } //end if head is equal to null
	   
	   //if the key is not currently in the linked list
	   else if ( !this.contains(key) ) {
		   
		   Node tempNode = new Node( key, value );  //create a new node with values
		   lastNode.setNextNode( tempNode ); //set the next node from the last node to equal the new node
		   lastNode = tempNode; //reassign the last node to the new value
		   
		   numberOfEntries ++; //increment the number of entries
		   return value; //return the value of the data added 
		   
	   } //end else if the key in not in the dictionary
	   
	   //else if this contains the key or some issue has occurred 
	   else if ( this.contains( key ) ) {
			   Node current = firstNode;
			   int numberIterator = numberOfEntries;
			   
			    while ( numberIterator > 0 ) {
				   if ( current.getKey().equals( key ) ) {
					   V originalValue = current.getValue();
					   current.setValue( value );
					   return originalValue;
				   } //end if current node is equal to key
				   
				   current = current.next; //make current node equal to the next node to iterate through list
				   numberIterator --;
			   } //end while to find key match 
			    
			    firstNode = head;
		   } //end if the dictionary contains the key
		   
		else {
			   throw new NoSuchElementException();
		   } //end else throw a no such element exception
	   
	   throw new NoSuchElementException();
	   
   } // end add

   public V remove(K key) {
	   
	   Node current = firstNode;
	   
	   if ( current.getKey().equals(key) ) {
		   Node tempNode = head.next;
		   head = tempNode;
		   numberOfEntries--;
		   return head.getValue();
	   } //end if the key matches the head key
	   
	   else {
		   
		   while ( current != null ) {
			   
			   //if you find the key while looking through the nodes
			   if ( current.next.getKey().equals(key) ) {
				   Node nodeToRemove = current.next;
				   
				   if ( nodeToRemove.next == null ) {
					   
					   lastNode = current; 
					   Node tempNode = nodeToRemove;
					   nodeToRemove = null;
					   numberOfEntries--;
					   return tempNode.getValue();
					   
				   } //end if the node after the node to remove is null
				   
				   current.next = nodeToRemove.next;
				   numberOfEntries--;
				   return nodeToRemove.getValue();
			   } //end if node is found
			   current = current.next;
		   } //end while loop
		   
		   throw new NoSuchElementException();
		   
	   } //end else 
	   
   } // end remove

   public V getValue(K key) {
	   
	   Node current = firstNode;
	   int numberIterator = numberOfEntries;
	   
	    while ( current != null ) {
		   if ( current.getKey().equals( key ) ) {
			   return current.getValue();
		   } //end if current node is equal to key
		   current = current.next; //make current node equal to the next node to iterate through list
		   numberIterator --;
	   } //end while to find key match
	    
	   return null;
    
   } // end getValue

	public boolean contains(K key) {
		
	   Node current = firstNode;
	   int numberIterator = numberOfEntries;
	   
	    while ( numberIterator > 0 ) {
		   if ( current.getKey().equals( key ) ) {
			   return true;
		   } //end if current node is equal to key
		   current = current.next; //make current node equal to the next node to iterate through list
		   numberIterator --;
	   } //end while to find key match 
	    
	    return false;
   
   } // end contains

   public boolean isEmpty() {
	   
	   if ( numberOfEntries != 0 )  {
		   return false;
	   } //if the first node is not equal to null
	   
	   return true;
      
   } // end isEmpty
	
   public int getSize() {
	   
	   return numberOfEntries;
      
   } // end getSize

	public final void clear() { 
		
		Node current = firstNode;
		
		while ( !current.next.equals(null) && numberOfEntries != 0 ) {
			
			Node tempNode = current.next;
			current = null; 
			current = tempNode;
			numberOfEntries--;
			
		} //end while statement 
	
	} // end clear

	public Iterator<K> getKeyIterator() {
		
		return new KeyIterator();
	
	} // end getKeyIterator
	
	private class KeyIterator implements Iterator<K> {
      private Node current;
      
      private KeyIterator() {
	  
    	  if ( head.equals(null) ) {
    		  current = null;
    	  } //end if the head is not initialized
    	  
    	  else {
    		  current = firstNode;
    	  } //end else the current node equals the first node 
    	  // is this redundant? 
    	  
      } // end default constructor
      
      public boolean hasNext()  {
	  
    	  return current != null;
    	  
      } // end hasNext
      
      public K next() {
    	  K result = null;
	  
		  if (hasNext()) {
		      result = current.getKey();
		      current = current.next;
		  }
		  else
		      throw new NoSuchElementException();
		  
		  return result;
      } // end next
      
      public void remove(){
    	  throw new UnsupportedOperationException();
      } // end remove
	} // end KeyIterator
	
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	} // end getValueIterator
	
	private class ValueIterator implements Iterator<V> {
      private Node current;
      
      private ValueIterator() {
	  
    	current = firstNode;
    	  
      } // end default constructor
      
      public boolean hasNext() {
    	  
    	  if ( current.next.equals(null) ) {
    		  return false;
    	  } //end if the next node does not exist
    	  
    	  return true;
    	  
      } // end hasNext
      
      public V next() {
		  V result = null;
		  
		  if (hasNext()) {
			  result = current.getValue();
		      current = current.next;
		  }
		  else {
		    throw new NoSuchElementException();
		  }
		  
		  return result;
      } // end next
      
      public void remove() {
    	  throw new UnsupportedOperationException();
      } // end remove
	} // end getValueIterator
	

	private class Node {
		private K key;
		private V value;
		private Node next;

		private Node(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
			next = null;	
		} // end constructor
		
		private Node(K searchKey, V dataValue, Node nextNode) {
			key = searchKey;
			value = dataValue;
			next = nextNode;	
		} // end constructor
		
		private K getKey() {
			return key;
		} // end getKey
		
		private V getValue() {
			return value;
		} // end getValue

		private void setValue(V newValue) {
			value = newValue;
		} // end setValue

		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end UnsortedLinkedDictionary
