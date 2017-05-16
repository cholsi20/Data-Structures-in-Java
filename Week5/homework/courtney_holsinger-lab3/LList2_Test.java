public class LList2_Test {

	public static void main( String[] args ) {
		
		LList2<String> myList = new LList2<>();
		
		myList.add( "Hello" );
		myList.add( "World" );
		myList.add( "." );
		myList.add( "This" );
		myList.add( "is" );
		myList.add( "a" );
		myList.add( "test" );
		myList.add( "." );
		
		//-------------------------------------------------------GET INDEX METHOD------------
		System.out.println( "Testing getIndex method." );
		System.out.printf( "The index of 'This' is: %d\n", myList.getIndex( "This" ) );
		System.out.printf( "The index of 'Who' is: %d\n\n", myList.getIndex( "Who" ) );
		
		//-------------------------------------------------------REMOVE EVERY METHOD---------
		System.out.println( "Testing removeEvery method." );
		System.out.printf( "The number of times \".\" was removed: %d\n", myList.removeEvery( "." ) );
		System.out.printf( "The number of times \"Who\" was removed: %d\n", myList.removeEvery( "Who" ) );
		
		//-------------------------------------------------------EQUALS METHOD---------------
		System.out.println( "Testing equals method." );
		LList2<String> exactCopy = myList;
		System.out.printf( "The List exactCopy is an exact copy of myList: %s\n", myList.equals( exactCopy ) );
		LList2<String> notACopy = new LList2<>();
		notACopy.add( "Hello" );
		notACopy.add( "Who" );
		System.out.printf( "The List notACopy is an exact copy of "
				+ "myList: %s\n\n", myList.equals( notACopy ) );
		
		//-------------------------------------------------------CONTAINS2 METHOD------------
		System.out.println( "Testing contains2 method." );
		System.out.printf( "myList contains \"This\": %s\n", myList.contains2( "This" ) );
		System.out.printf( "myList contains \"Who\": %s\n", myList.contains2( "Who" ) );
		
		
		
	} //end main method
	
} //end public class LList2
