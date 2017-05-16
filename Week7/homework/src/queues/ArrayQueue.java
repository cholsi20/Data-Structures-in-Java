package queues;

public class ArrayQueue<T> implements QueueInterface<T>  {
    private T[] queue; // circular array of queue entries and one unused location
    private int frontIndex;
    private int backIndex;
    private static final int DEFAULT_INITIAL_CAPACITY = 50;
    
    public ArrayQueue() {
	this(DEFAULT_INITIAL_CAPACITY);
    } // end default constructor
    
    public ArrayQueue(int initialCapacity) {
	// the cast is safe because the new array contains null entries
	@SuppressWarnings("unchecked")
	    T[] tempQueue = (T[]) new Object[initialCapacity + 1];
	queue = tempQueue;
	frontIndex = 0;
	backIndex = initialCapacity;
    } // end constructor
    
    public void enqueue(T newEntry) {
	ensureCapacity();   
	backIndex = (backIndex + 1) % queue.length;
	queue[backIndex] = newEntry;
    } // end enqueue
    
    public T getFront(){
	T front = null;
	if (!isEmpty())
	    front = queue[frontIndex];
	
	return front;
    } // end getFront
    
    public T dequeue() {
	T front = null;
	if (!isEmpty())  {
	    front = queue[frontIndex];
	    queue[frontIndex] = null;
	    frontIndex = (frontIndex + 1) % queue.length;
	} // end if
	
	return front;
    } // end dequeue
    
    private void ensureCapacity() {
	if (frontIndex == ((backIndex + 2) % queue.length))  { // if array is full,
	    T[] oldQueue = queue;
	    int oldSize = oldQueue.length;
	    @SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[2 * oldSize];
	    queue = tempQueue;
	    for (int index = 0; index < oldSize - 1; index++) {
		queue[index] = oldQueue[frontIndex];
		frontIndex = (frontIndex + 1) % oldSize;
	    } // end for
	    
	    frontIndex = 0;
	    backIndex = oldSize - 2;
	} // end if
    } // end ensureCapacity

    public boolean isEmpty() {
	return frontIndex == ((backIndex + 1) % queue.length);
    } // end isEmpty
    
    public void clear() {
	if(!isEmpty()) {
	    for (int index = frontIndex; index != backIndex; index = (index+1)%queue.length)
		queue[index] = null;
	    queue[backIndex] = null;
	}
	frontIndex = 0;
	backIndex = queue.length - 1;
    }
    
    //------------------------------------------------------------Begin Lab 5 Methods
    
    public static boolean check( String s ) {
    	
    	//edit the string to remove extra characters
    	String tempString = checkString( s );
    	
    	//split string in two halves
    	String tempString1 = tempString.substring(0, (tempString.length() / 2) );
    	String tempString2 = tempString.substring( (tempString.length() / 2), tempString.length() );
    	
    	//enqueue the two halves to compare the queues 
    	QueueInterface<String> tempQueue1 = new ArrayQueue<String>();
    	QueueInterface<String> tempQueue2 = new ArrayQueue<String>();
    	
    	tempQueue1.enqueue(tempString1);
    	tempQueue2.enqueue(tempString2);
    	
    	//return tempQueue2.getFront();
    	
    	//compare the two halves for equality
    	if ( tempQueue1.getFront().equals( tempQueue2.getFront() ) ) {
	    			
    		//return true;
    		return true;
    		//return 1;
	    			
	    	} //end if 
    	
    	//return false;
    	return false;

    } //end check method
    
    
   public static String checkString( String stringToCheck ) {
	   
	   //split string into string array of individual characters
	   String[] tempString = stringToCheck.split("");
	   //create a temp queue for storing desired characters 
	   QueueInterface<String> tempQueue = new ArrayQueue<String>();
	   //counter for number of items to ignore
	   int ignore = 0;
	   int characters = 0;
	   
	   //for each character, see if that character is a character to ignore
	   for ( int count = 0; count < tempString.length; count++ ) {
		   
		   //if the character is one to be ignored, do not enqueue
		   if ( tempString[count].equals(" ") || tempString[count].equals(",") || 
   				tempString[count].equals(".") || tempString[count].equals("-") || 
   				tempString[count].equals("_") ) {
			   ignore++;
			   continue;
		   } //end if 
		   
		   //else enqueue it
		   else {
			   ++characters;
			   tempQueue.enqueue( tempString[count] );
		   } //end else 
	   } //end for loop
	   
	   //create a string array from your new queue
	   //create new tempArray 
	   String[] tempArray = new String[ tempString.length - ignore ];
	   int counter = 0; //for walking along the array index
	   
	   //while the queue is not empty, add items to the array
	   while ( !tempQueue.isEmpty() ) {
		   String item = tempQueue.dequeue(); //assign data to temp holder
		   tempArray[counter] = item; //place that data in the array
		   counter++; //increase the counter for the array
	   } //end while 
	   
	   //return the string array as a string
	   	StringBuilder builder = new StringBuilder(characters + 1);
	   	for(String s : tempArray) {
	   	    builder.append(s);
	   	}
	   	return builder.toString();
	   	//return characters;
   } //end checkString method 
    
    
    public void splice( QueueInterface<T> anotherQueue ) {
    	
    	//T[] anotherQueueCopy = (T[]) anotherQueue;
    	QueueInterface<T> tempQueue = anotherQueue;
    	
    	while ( !anotherQueue.isEmpty() ) {
    		
    		enqueue( anotherQueue.dequeue() );
    		
    	} //end while statement 
    	
    } //end splice method
    
    public boolean enqueueNoDuplicate(T item) {

    	//Create a copy of the queue
    	QueueInterface<T> tempQueue = new ArrayQueue<T>(); //a new temporary queue to store items in
    	QueueInterface<T> thisQueue = this; //for purposes of referencing during implementation
    	//QueueInterface<T> tempQueue = this;
    
    	//while there are still items in the queue being referenced
    	while ( !thisQueue.isEmpty() ){
    		
    		//dequeue the front item in the tempQueue
    		T tempItem = thisQueue.dequeue(); //duplicate the data in the front of the queue
    		tempQueue.enqueue( tempItem ); //copy this data to the temp queue in order

    		//check to see if the item dequeued matches the item to add
    		if ( tempItem.equals(item) ) { //if there is a match in the data
    			
    			while ( !thisQueue.isEmpty() ) { //the rest of the items in the original queue
    											 //should be copied to the temp queue
    				T tempItem1= thisQueue.dequeue(); 
    				tempQueue.enqueue( tempItem1 );
    			} //end while
    			
    			reassign( tempQueue ); //reassign the items in the tempQueue back to the original
    								   //queue
    			
    			
    			return false; //and return false 
    			
    		} //end if

    	}//end while 
    	
    	reassign( tempQueue ); //if there is no match, reassign all the items in the temp queue back
    						   //back to the original queue

    	enqueue( item ); //then enqueue the new item
    	
    	//then return true to indicate the item was added
    	return true;
    	
    } //end enqueueNoDuplicate
    
    //Deep copying of objects is challenging.  I didn't like clone, so I did this. Clone's definition 
    //according to documentation seemed ambiguous to me, and its correct implementation challenging and
    //faulty, so overall highly problematic.  There's a better way to do this,  I know there has to be, 
    //but I'm having issues figuring out how to implement it. I saw other options as well, but none
    //that seemed to hold up to scrutiny.  My implementation, on the other hand, seems messy.
    private void reassign( QueueInterface<T> aQueue ) {
    	
    	//while your holding queue is not empty
    	while ( !aQueue.isEmpty() ) {
			T tepItem2 = aQueue.dequeue(); //copy the data in order from that queue
			enqueue( tepItem2 ); //and enqueue it into the current queue
		} //end while statement 
    	
    } //end private method reassign
    
} //end ArrayQueue method
