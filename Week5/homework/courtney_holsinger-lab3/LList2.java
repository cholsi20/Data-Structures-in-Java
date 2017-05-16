/**
   A class that implements the ADT list by using a chain of nodes.
   The chain has both a head reference and a tail reference.
   @author Frank M. Carrano
   @version 3.0
*/
public class LList2<T> implements ListInterface<T>
{
	private Node firstNode; // head reference to first node
	private Node lastNode;  // tail reference to last node
	private int  numberOfEntries;

	public LList2()
	{
		clear();
	} // end default constructor

	public final void clear() // NOTICE clear is not final in interface and that is OK
	{
		firstNode = null;
  		lastNode = null;
		numberOfEntries = 0;
	} // end clear
  
	public void add(T newEntry) 	  // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry); // create new node

		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		
  		lastNode = newNode;
  		numberOfEntries++;
	}  // end add

   public boolean add(int newPosition, T newEntry)  // OutOfMemoryError possible	                                                 
	{
      boolean isSuccessful = true;

      if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) 
      {
         Node newNode = new Node(newEntry);

         if (isEmpty())
         {
            firstNode = newNode;
            lastNode = newNode;
         }
         else if (newPosition == 1)
         {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
         }
         else if (newPosition == numberOfEntries + 1)
         {
            lastNode.setNextNode(newNode);
            lastNode = newNode;
         } 
         else
         {
            Node nodeBefore = getNodeAt(newPosition - 1);
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
         } // end if	    
         numberOfEntries++;
      }
      else
         isSuccessful = false;
       
	   return isSuccessful;
	} // end add

	public T remove(int givenPosition)
	{
      T result = null;                 // return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         assert !isEmpty();
         if (givenPosition == 1)        // case 1: remove first entry
         {
            result = firstNode.getData();     // save entry to be removed 
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null; // solitary entry was removed
            }
            else                           // case 2: givenPosition > 1
            {
               Node nodeBefore = getNodeAt(givenPosition - 1);
               Node nodeToRemove = nodeBefore.getNextNode();
               Node nodeAfter = nodeToRemove.getNextNode();
               nodeBefore.setNextNode(nodeAfter);  // disconnect the node to be removed
               result = nodeToRemove.getData();  // save entry to be removed

               if (givenPosition == numberOfEntries)
                  lastNode = nodeBefore; // last node was removed
         } // end if

         numberOfEntries--;
      } // end if

      return result;                   // return removed entry, or 
                                      // null if operation fails
	} // end remove

	public boolean replace(int givenPosition, T newEntry)
	{
		boolean isSuccessful = true;

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {   
      	assert !isEmpty();

			Node desiredNode = getNodeAt(givenPosition);
			desiredNode.setData(newEntry);
      }    
		else
			isSuccessful = false;
			
		return isSuccessful;
   } // end replace

   public T getEntry(int givenPosition)
   {
      T result = null;  // result to return
      
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
         result = getNodeAt(givenPosition).getData();
     	} // end if
      
      return result;
   } // end getEntry

	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;
		
		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while
		
		return found;
	} // end contains

   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   public boolean isEmpty()
   {
   		boolean result;
   		
      	if (numberOfEntries == 0) // or getLength() == 0
      	{
      		assert firstNode == null;
      		result = true;
      	}
      	else
      	{
      		assert firstNode != null;
      		result = false;
      	} // end if
      	
      	return result;
   } // end isEmpty
	
   public T[] toArray()
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries]; // warning: [unchecked] unchecked cast

	  int index = 0;
     Node currentNode = firstNode;
	  while ((index < numberOfEntries) && (currentNode != null))
	  { 
	    result[index] = currentNode.getData();
	    currentNode = currentNode.getNextNode();
       index++; 
	  } // end while
     
     return result;
   } // end toArray

	// Returns a reference to the node at a given position.
	// Precondition:  List is not empty; 1 <= givenPosition <= numberOfEntries.	
	private Node getNodeAt(int givenPosition)
	{
		assert (firstNode != null) && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
		Node currentNode = firstNode;
		
      if (givenPosition == numberOfEntries)
         currentNode = lastNode;
      else if (givenPosition > 1)      // traverse the chain to locate the desired node
		{
         for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();
		} // end if
      
		assert currentNode != null;
		return currentNode;
	} // end getNodeAt

	// Lab 3
	// Problem 1
	int getIndex(T item) {
	
		//start at the beginning 
		Node currentNode = firstNode;
		
		//create counter beginning at 1 to keep track of place along list as getPrevious not defined
		int indexCounter = 0; 
		
		while ( currentNode != null && indexCounter <= numberOfEntries ) {
			
			//increment counter to keep track of where you are along the list
			indexCounter ++;
			
			//if the item equal the data in the current node 
			if ( item.equals( currentNode.getData() ) ) {
				
				return indexCounter;
			
			} //end if 
			
			else if ( indexCounter == numberOfEntries ) {
				
				return -1;
				
			} //end else if 
			
			//else keep traversing along the nodes
			else {
				
				currentNode = currentNode.getNextNode();
				
			} //end else statement 
			
		} //end while statement 
		
		return 0;
	}
	// Problem 2
	int removeEvery(T item) {
	
		//start at the beginning 
		Node currentNode = firstNode;
		
		//create counter beginning at 1 to keep track of place along list as getPrevious not defined
		int indexCounter = 0; 
		int removalCounter = 0;
		
		while ( currentNode != null && indexCounter <= numberOfEntries ) {
			
			//increment counter to keep track of where you are along the list
			indexCounter ++;
			
			//if the item equal the data in the current node 
			if ( item.equals( currentNode.getData() ) ) {
				
				if ( remove( getIndex( item )) != null ){
					removalCounter++;
					indexCounter--;
				}
			
			} //end if 
			
			//else keep traversing along the nodes
			else {
				
				currentNode = currentNode.getNextNode();
				
			} //end else statement 
			
		} //end while statement 
		
		return removalCounter;
	}
	// Problem 3
	boolean equals(Object other) {
	
		//create variable result to keep track of the result 
		boolean result = false;
		
		//begin initial check to see if object is an instance of LList
		if ( other instanceof LList2 ) {
			
			//casting is safe once initial test is passed
			@SuppressWarnings("unchecked")
			LList2<T> otherList = (LList2<T>)other;
			
			//create a new variable to hold the length of otherList
			int lengthOtherList = otherList.getLength();
			
			//if the two lists have the same number of entries
			if ( numberOfEntries == lengthOtherList) {
			
				//create node variables for traversing List
				Node currentNode = firstNode;
				int indexCounter = 0;
				
				//process each entry to see there is exact equality in the values 
				while ( currentNode != null && indexCounter <= numberOfEntries ) {
					
					//increment counter to keep track of where you are along the list
					indexCounter ++;
					
					//if the item equal the data in the current node 
					if ( !otherList.equals( currentNode.getData() ) ) {
						
						result = false;
					
					} //end if 
					
					//else keep traversing along the nodes
					else {
						
						currentNode = currentNode.getNextNode();
						
					} //end else statement 
					
				} //end while statement
				
				//assumes lists are equal at this point
				result = true;
				
			} //end if statement 
			
		} //end if instance of statement 
		
		//return false by default
		return result;
	}
	
	//Problem 4
	boolean contains2(T anEntry) {
	
		//create "startNode to pass to recursive method "contains"
		Node currentNode = firstNode;
		
		if ( contains( anEntry, currentNode ) == true ) {
			return true;
		} //end if the LList contains the entry 
	
		return false;
	}
	private boolean contains(T anEntry, Node startNode) {
		
		//while there are still nodes to traverse
		while (startNode != null) {
		
			//if the entry is found in the nodes, then return true
			if ( anEntry.equals(startNode.getData() ) )  {
				
				return true;
				
			} //end if statement  
			
			//if it is not found, and the node is not null, then keep traversing the nodes
			else {
				
				startNode = startNode.getNextNode();
			} //end else statement 
		} //end while statement 
		
		//return false if no match was found
		return false;
	}
	
	
	private class Node 
	{
		private T data;  // data portion
		private Node next;  // next to next node

		private Node(T dataPortion)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = null;	
		} // end constructor

		private Node(T dataPortion, Node nextNode)//  PRIVATE or PUBLIC is OK
		{
			data = dataPortion;
			next = nextNode;	
		} // end constructor

		private T getData()
		{
			return data;
		} // end getData
		
		private void setData(T newData)
		{
			data = newData;
		} // end setData
		
		private Node getNextNode()
		{
			return next;
		} // end getNextNode
		
		private void setNextNode(Node nextNode)
		{
			next = nextNode;
		} // end setNextNode
	} // end Node
} // end LList2

