package stacks;


import java.util.Arrays;
public class ArrayStack2<T> implements StackInterface<T>
{
   private T[] stack;    // array of stack entries
   private int numberOfEntries; //maintains count of number of entries
   private static final int DEFAULT_INITIAL_CAPACITY = 50;
  
   public ArrayStack2()
   {
      this(DEFAULT_INITIAL_CAPACITY);
   } // end default constructor
  
   public ArrayStack2(int initialCapacity)
   {
      // the cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
    
  } // end constructor
   
   
  
   public void push(T newEntry)   
   {  
	   
	   if ( stack == null ) {
		   stack[0] = newEntry;
		   ++numberOfEntries;
	   } //end if
	   
	   else {
		   
		   //ensure there is enough space in the array for a new entry
		   ensureCapacity();
		   
		   //size of array for count variable
		   makeRoom();
		 
		   //stack first entry is the new entry - placed on top of the stack
		   stack[ 0 ] = newEntry;
		  
		  //increase the number of entries 
		   ++numberOfEntries;
		   
	   } //end else 
	   
	   
          
   } // end push   
   
   
   public T peek()   
   {  
	   
	   T top = null;
	   if( stack[0] != null ) {
		   top = stack[0];
	   } // end if 
	   
	   return top;
   
   } // end peek
   
   public T pop()
   {
	   if ( stack == null ) {
		   return null;
	   } // end if 
	   
	   else {
		   
		   //variable to hold number of entries
		   int entries = numberOfEntries;
		   
		   for ( int count = 0; count < entries; count++ ) {
			   
			   T shiftingElement = stack[ count + 1 ];
			   stack[ count ] = shiftingElement;
			   
		   } //end for loop
		   
		   numberOfEntries--;
		   
		   //if the new number of objects in the array is half of the array length
		   if ( isTooBig() == true ) {
			   reduce(); //reduce the size of the list 
		   }// end if statement 
		   
		   return peek();
		   
	   } //end else 
 
   } // end pop
   
   public boolean isEmpty()
   {  
	   
	   return stack[ 0 ] == null;
 
   } // end isEmpty

   public void clear()
   {
	   stack = null;
   }
   
   //-----------------------------------------------------Begin Private Methods
   
   // Doubles the size of the array list if it is full.
   private void ensureCapacity() {
		if (numberOfEntries == stack.length)
		    stack = Arrays.copyOf(stack, 2 * stack.length);
   } // end ensureCapacity
   
   private void makeRoom() {
	   
	   for ( int count = numberOfEntries; count > -1; count-- ) {
		   
		   T elementToMove = stack[ count ];
		   stack[ count + 1 ] = elementToMove;
		   
	   } //end for loop
	   
   } //end private method makeRoom
   
   private void reduce() {
	   
	   int newCapacity = (int) (.75 * stack.length);
	   
	   @SuppressWarnings("unchecked")
	   T[] tempList = (T[]) new Object[ newCapacity ];
	   
	   for ( int index = 0; index < newCapacity; index++ ) { 
		   
		   tempList[index] = stack[index]; //copies entries from list into the tempList
		   
	   } //end for loop 
	   
	   stack = tempList; //copies tempList with new capacity and entries to list array
	   
   } //end private method reduce
   
   private boolean isTooBig() {
	   
	   boolean tooBig = false;
	   
	   if ( numberOfEntries <= (stack.length / 2 ) ) {
		   tooBig = true;
	   } //end if 
	   
	   return tooBig;
   
   } //end boolean isTooBig
   
  
   
} // end ArrayStack2

