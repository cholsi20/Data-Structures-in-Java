public class QueueTest {
    public static void main(String[] args) {
	QueueInterface<String> myQueue = new ArrayQueue<String>();
	myQueue.enqueue("Jim");
	myQueue.enqueue("Jess");
	myQueue.enqueue("Jill");
	myQueue.enqueue("Jane");
	myQueue.enqueue("Joe");
	
	String front = myQueue.getFront(); // returns “Jim”
	System.out.println(front + " is at the front of the queue.");
	
	front = myQueue.dequeue(); // removes and returns “Jim”;
	System.out.println(front + " is removed from the queue.");
	
	myQueue.enqueue("Jerry"); 
	front = myQueue.getFront(); // returns “Jess”
	System.out.println(front + " is at the front of the queue.");
	
	front = myQueue.dequeue(); // removes and returns “Jess”;
	System.out.printf(front + " is removed from the queue.\n\n");
	
	
	ArrayQueue<String> testString = new ArrayQueue<String>(); 
	testString.enqueue( "booboo" );
	testString.enqueue("boo , boo");
	testString.enqueue("rattan");
	testString.enqueue("abcab");
	testString.enqueue("boo boo");
	
	System.out.printf( "booboo has two equal halves: %s\n", 
			testString.check( testString.getFront() ));
	testString.dequeue();
	/*System.out.printf( "%s\n\n", 
			testString.checkString( testString.getFront() ) );*/
	System.out.printf( "boo , boo has two equal halves: %s\n", 
			testString.check( testString.getFront() ) );
	testString.dequeue();
	System.out.printf( "rattan has two equal halves: %s\n", 
			testString.check( testString.getFront() ) );
	testString.dequeue();
	System.out.printf( "abcab has two equal halves: %s\n", 
			testString.check( testString.getFront() ) );
	testString.dequeue();
	System.out.printf( "boo boo has two equal halves: %s\n\n", 
			testString.check( testString.getFront() ) );
	
	
	
	testString.splice(myQueue);
	System.out.printf( "Front of queue is: %s\n", 
			 testString.dequeue());
	System.out.printf( "Front of queue is: %s\n", 
			 testString.dequeue());
	System.out.printf( "Front of queue is: %s\n", 
			 testString.dequeue());
	System.out.printf( "Front of queue is: %s\n", 
			 testString.dequeue());
	System.out.printf( "Front of queue is: %s\n", 
			 testString.dequeue());
	System.out.printf( "Front of queue is: %s\n\n", 
			 testString.dequeue());
	
	testString.enqueue("Jim");
	testString.enqueue("Jess");
	testString.enqueue("Jill");
	testString.enqueue("Jane");
	testString.enqueue("Joe");
	testString.enqueue("Jerry");
	
	
	System.out.printf( "Jerry is added: %s\n", testString.enqueueNoDuplicate( "Jerry" ));
	//System.out.printf( "Front of queue is: %s\n", testString.getFront());
	System.out.printf( "Kim is added: %s\n", testString.enqueueNoDuplicate( "Kim" ));
	//System.out.printf( "Front of queue is: %s\n", testString.getFront());
	
}
