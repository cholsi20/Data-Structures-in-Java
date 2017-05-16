package dictionaries;

public class TestUnsortedLinkedDictionary {

	public static void main( String[] args ) {
		
		UnsortedLinkedDictionary dictionary = new UnsortedLinkedDictionary();
		
		//TEST ADD METHOD
		System.out.printf( "Mom has been added to the dictionary as: %s\n", 
				dictionary.add("mom", "Sylvia"));
		System.out.printf("Dad has been added to the dictionary as: %s\n", 
				dictionary.add("dad", "Shawn"));
		System.out.printf("Sister has been added to the dictionary as: %s\n", 
				dictionary.add("sister", "Christie"));
		System.out.printf("Worst Enemy has been added to the dictionary as: %s\n\n", 
				dictionary.add("enemy", "N/A"));
		
		//TEST REMOVE METHOD
		System.out.printf( "The value removed was: %s\n", dictionary.remove("enemy") );
		//System.out.printf( "The value removed was: %s\n\n", dictionary.remove("bff"));
		
		//TEST GET VALUE METHOD
		System.out.printf( "The value of sister is: %s\n\n", dictionary.getValue("sister"));
		
		//TEST CONTAINS METHOD
		System.out.printf( "The dictionary contains a reference to dad: %s\n\n", 
				dictionary.contains("dad") );
		/*System.out.printf( "The dictionary contains a reference to enemy: %s\n\n", 
				dictionary.contains("enemy") );*/
		
		//TEST isEmpty METHOD
		UnsortedLinkedDictionary dictionary2 = new UnsortedLinkedDictionary();
		System.out.printf( "dictionary is empty: %s\n", dictionary.isEmpty() ); 
		System.out.printf( "dictionary2 is empty: %s\n\n", dictionary2.isEmpty() ); 
		
		//TEST GET SIZE METHOD
		System.out.printf("The number of entries in the dictionary is: %d\n\n", dictionary.getSize() );
		
		
		
		
	} // end main method 
	
} //end public class TestUnsortedLinkedDictionary
