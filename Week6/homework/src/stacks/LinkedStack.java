package stacks;

/**
A class of stacks whose entries are stored in a chain of nodes.
@author Frank M. Carrano
@version 3.0
*/
public class LinkedStack<T> implements StackInterface<T>
{
	
private Node topNode; // references the first node in the chain

public LinkedStack()
{
  topNode = null;
} // end default constructor

public void push(T newEntry)
{   
   Node newNode = new Node(newEntry, topNode);   topNode = newNode;
} // end push

public T peek()
{   
   T top = null;     
   if (topNode != null)      
   top = topNode.getData();       
   return top;
} // end peek

public T pop()
{  
   T top = peek();     
   if (topNode != null)      
   topNode = topNode.getNextNode();       
   return top;
} // end pop

public boolean isEmpty() {
   return topNode == null;
} 
public void clear() {
   topNode = null;
} 

public int remove( int n ) {
	
	//count number of times items removed
	int itemsRemoved = 0;
	
	//if there is nothing in the stack left to remove
	if ( topNode == null ) {
		return itemsRemoved;
	} //end if 
	
	//else if you have removed the desired number of items 
	else if ( itemsRemoved == n ) {
		itemsRemoved = n;
	} //end else if 

	//else continue to remove items from the linked list, and increment items removed up by one
	// and number of items to remove down by one.
	else {
		
		pop();
		itemsRemoved += remove( n - 1 ) + 1;
		
	} //end else 
	
	return itemsRemoved;
	
	//return itemsRemoved;
	
} //end remove 

private class Node
{
  private T    data; // entry in stack
  private Node next; // link to next node

  private Node(T dataPortion)
  {
     this(dataPortion, null);
  } // end constructor
  
  private Node(T dataPortion, Node nextNode)
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
} // end LinkedStack

