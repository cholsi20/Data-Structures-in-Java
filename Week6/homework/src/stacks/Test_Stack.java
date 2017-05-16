package stacks;

public class Test_Stack {

	public static void main( String[] args ) {
		
		//create test stacks
		LinkedStack<String> linkedStack = new LinkedStack<>();
		ArrayStack2<String> arrayStack = new ArrayStack2<>();
		
		//-------------------------------------------------------BEGIN LinkedStack TEST
		System.out.println( "Testing LinkedStack methods.");
		//push items onto LinkedStack
		linkedStack.push( "Hello" );
		linkedStack.push( "World" );
		linkedStack.push( "." );
		linkedStack.push( "New" );
		linkedStack.push( "test" );
		linkedStack.push( "." );
		
		//peek at items on list 	
		System.out.printf( "Top Node is: %s\t", linkedStack.peek() );
		System.out.printf( "Popped: %s\n", linkedStack.pop() );
		System.out.printf( "New Top Node is: %s\n\n", linkedStack.peek() );
		
		//Testing method remove( int n ) 
		System.out.println( "Testing method remove( int n )" );
		System.out.printf( "Testing to remove 3 items.\nNumber of items removed: %d\n", 
				linkedStack.remove( 3 ) );
		System.out.printf( "Testing to remove 3 items again.\nNumber of items removed: %d\n", 
				linkedStack.remove( 3 ) );
		System.out.printf( "Testing to remove 3 items from empty stack.\nNumber of items removed: %d\n\n", 
				linkedStack.remove( 3 ) );
		
		//-------------------------------------------------------BEGIN ArrayStack2 TEST
		
		System.out.println( "Initial ArrayStack2 method tests." );
		//initial test isEmpty method
		System.out.printf( "arrayStack is currently empty: %s\n", arrayStack.isEmpty() );
		
		//initial test of peek() method
		System.out.printf( "arrayStack's top element in the stack is: %s\n", arrayStack.peek() );
		
		
		//test push
		System.out.println( "Testing push method." );
		arrayStack.push( "Hello" );
		arrayStack.push( "World" );
		arrayStack.push( "." );
		arrayStack.push( "New" );
		arrayStack.push( "test" );
		arrayStack.push( "." );
		System.out.printf( "arrayStack's new top element in the stack is: %s\n", arrayStack.peek() );
		
		//testing pop
		System.out.println( "Testing pop method." );
		System.out.printf( "arrayStack's new top element in the stack "
				+ " after popping the top element is: %s\n", arrayStack.pop() );
		
		arrayStack.pop();
		arrayStack.pop();
		arrayStack.pop();
		arrayStack.pop();
		System.out.printf( "arrayStack's top element in an empty "
				+ " stack is: %s\n", arrayStack.pop() );
		
			

		
	} //end main method 
	
} //end class Test_Stack
