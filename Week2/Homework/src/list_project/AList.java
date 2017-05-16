package list_project;

/**
A class that implements the ADT list by using an array.
The list is never full.
@author Frank M. Carrano
*/

import java.util.Arrays;

public class AList<T> implements ListInterface<T> 
{
    private T[] list;   // array of list entries
    private int numberOfEntries;
    private static final int DEFAULT_INITIAL_CAPACITY = 25;  
    
    public AList() {
		this(DEFAULT_INITIAL_CAPACITY); // call next constructor
    } // end default constructor
    
    public AList(int initialCapacity) {
		numberOfEntries = 0;
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		    T[] tempList = (T[])new Object[initialCapacity];
		list = tempList; 
    } // end constructor
    
    public void add(T newEntry) {  
		ensureCapacity();
		list[numberOfEntries] = newEntry;
		numberOfEntries++;
    } // end add
    
    
    public boolean add(int newPosition, T newEntry) {  
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {	
		    ensureCapacity();
		    makeRoom(newPosition);				
		    list[newPosition - 1] = newEntry;
		    numberOfEntries++;
		}
		else
		    isSuccessful = false;
		return isSuccessful;
	
    } // end add
    
    public T remove(int givenPosition) { 
	
		T result = null; // return value
	
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {	
		    assert !isEmpty();
		    result = list[givenPosition - 1]; // get entry to be removed
		    
		    // move subsequent entries towards entry to be removed, 
		    // unless it is last in list
		    if (givenPosition < numberOfEntries) {
				removeGap(givenPosition);
		    } //end if 
			    
			numberOfEntries--;
			
			if ( isTooBig() == true ) { //if the array is less than half size of capacity
				
				//System.out.printf ( "%d is list length.\n", list.length );
				reduce(); //reduces array to 3/4 size capacity
				//System.out.printf ( "%d is the new list length.\n", list.length );
				
			} //end if too big
	} // end if
	
		return result; // return reference to removed entry, or
		// null if either list is empty or givenPosition
		// is invalid
	
    } // end remove

    public void clear() { 
    	numberOfEntries = 0;
    } // end clear

    public boolean replace(int givenPosition, T newEntry)  { 
		boolean isSuccessful = true;
		
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) { // test catches empty list 
		    assert !isEmpty();
		    list[givenPosition - 1] = newEntry;
		}
		else
		    isSuccessful = false;
		
		return isSuccessful;
	
    } // end replace
    
    public T getEntry(int givenPosition)  { 
		T result = null; // result to return
		
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
		    assert !isEmpty();
		    result = list[givenPosition - 1];
		} // end if
		
		return result;
	
    } // end getEntry
    
    public boolean contains(T anEntry) { 
		boolean found = false;
		for (int index = 0; !found && (index < numberOfEntries); index++) {
		    if (anEntry.equals(list[index]))
			found = true;
	} // end for
	
	return found;
    } // end contains
    
    public int getLength() {
    	return numberOfEntries;
    } // end getLength
    
    public boolean isEmpty() {
    	return numberOfEntries == 0; // or getLength() == 0
    } // end isEmpty
    
    public T[] toArray() {
		// the cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		    T[] result = (T[])new Object[numberOfEntries];
		
		for (int index = 0; index < numberOfEntries; index++) {
		    result[index] = list[index];
		} // end for
		
	return result;
    } // end toArray
    
    // Doubles the size of the array list if it is full.
    private void ensureCapacity() {
		if (numberOfEntries == list.length)
		    list = Arrays.copyOf(list, 2 * list.length);
    } // end ensureCapacity
    
    private void makeRoom(int newPosition) {
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		
		int newIndex = newPosition - 1;
		int lastIndex = numberOfEntries - 1;
		
		// move each entry to next higher index, starting at end of
		// list and continuing until the entry at newIndex is moved
		for (int index = lastIndex; index >= newIndex; index--)
		    list[index + 1] = list[index];
    }  // end makeRoom
    
    private void removeGap(int givenPosition) {
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);
		
		int removedIndex = givenPosition - 1;
		int lastIndex = numberOfEntries - 1;
		
		for (int index = removedIndex; index < lastIndex; index++)
		    list[index] = list[index + 1];
    } // end removeGap
   
   // Lab 1
   public int getIndex(T item) {
   
	   
	   int itemFound = -1;
	   //normally would check if list has entries, but this takes item not list
	   for ( int index = numberOfEntries - 1; index > -1; index-- ){
		   if ( item.equals(list[index] ) ){
			   itemFound = index + 1;
		   } //end if statement 
	   } //end check list for matching entry 
	   
	   return itemFound;
   } //end get index
   
   
   
   public int removeEvery(T item) {
  
	   int numberRemoved = 0; 
	   int itemToBeRemovedIndex = 0;
	   
	   for (int index = 0; index < numberOfEntries; index++) {
		   if ( item.equals( list[index] ) ) { //necessary to get initial count of the item in the 
			   								  // case that item occurs consecutively in the list
			   ++numberRemoved;
		   } //end if
	   } //end for 
	   
	   //int loopCount = numberOfEntries;
	   
	   //loop through list to find matches to item
	   for (int index = 0; index < numberOfEntries; index++ ) {
		   
		   if ( item.equals( list[index] ) ) {
			   
			   itemToBeRemovedIndex = index + 1;
			   
		   } //end if statement 
		   
		   remove( itemToBeRemovedIndex ); //removes item and resizes array and revises number of entries
		   
	   } //end for loop 
   
	   return numberRemoved;
	   
   } // end removeEvery method 
   
   
   public int getLastIndex(T item) {
	   
	   int lastItemFound = -1;
	   
	   for ( int index = 0; index < numberOfEntries; index++ ) {
		   
		   if ( item.equals(list[index]) ) {
			   
			   lastItemFound = index + 1;
			   
		   } //end if
		   
	   } //end for loop
   
	   return lastItemFound;
	   
   } //end getLastIndex
   
   @Override
   public boolean equals(Object other) {
	    
   
	   if ( other.equals(list) ) {
		   return true; //returns true if other object is equal in value to list object
	   } //end if other object is equal in value to list object
	   
	   
	   else {
		   return false;
	   } //end else statement 
   
   } //end equals method
   
   private boolean isTooBig() {
	   
	   boolean tooBig = false;
	   
	   if ( numberOfEntries <= (list.length / 2 ) ) {
		   tooBig = true;
	   } //end if 
	   
	   return tooBig;
   
   } //end boolean isTooBig
   
   private void reduce() {
   
	   int newCapacity = (int) (.75 * list.length);
	   
	   @SuppressWarnings("unchecked")
	   T[] tempList = (T[]) new Object[ newCapacity ];
	   
	   for ( int index = 0; index < newCapacity; index++ ) { 
		   
		   tempList[index] = list[index]; //copies entries from list into the tempList
		   
	   } //end for loop 
	   
	   list = tempList; //copies tempList with new capacity and entries to list array
	   
   } //end private method reduce
     
} // end AList
