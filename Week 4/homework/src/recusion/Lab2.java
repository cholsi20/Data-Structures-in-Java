package recusion;

public class Lab2 {
	
	public static void main(String[] arg) {
		
		System.out.println( "Testing method min to return minumum value.");
		int[] a = { 2, 3, 5, 7, 11, 13, 17, 19, 47, 53, 59, 61, 67, 71, 73, 
				79, 83, 89, 97, 23, 29, 31, 37, 41, 43 };
		System.out.printf( "The minimum value in int[] a is: %d\n\n", a[min( a, 0, 24)] );
		
		System.out.println( "Testing computePay method." );
		System.out.printf( "Pay received on day 39: %d cents\n\n", 
				computePay(39) );
		
		System.out.println( "Testing computeSavings method." );
		System.out.printf( "The total pay you would recieve in cents after 39 days is: %d cents\n\n", 
				computeSavings(39) );
		
		String s1 = "Java is a programming language originally developed by James "
				+ "Gosling at Sun Microsystems  and released in 1995 as a core component "
				+ "of Sun Microsystems Java platform. The language derives much of its syntax "
				+ "from C and C++ but has a simpler object model and fewer low-level facilities. "
				+ "Java applications are typically compiled to bytecode (class file) that can run "
				+ "on any Java Virtual Machine (JVM) regardless of computer architecture. Java is a "
				+ "general-purpose, concurrent, class-based, object-oriented language that "
				+ "is specifically designed to have as few implementation dependencies as possible. "
				+ "Java is currently one of the most popular programming languages in use, "
				+ "particularly for client-server web applications, with a reported 10 million users.";
		String s2 = " Java";
		System.out.println( "Testing countSubstring method." );
		System.out.printf( "The total number of times the word Java appears in string s1 is: "
				+ "%d\n\n", countSubstring( s1, s2) );
		//String[] s3 = s2.split( "(?!^)"); 
		//System.out.printf( "%s", s3[1]);
	
	}
	// Problem 1
	public static int min(int [] a, int begin, int end) {
	
		if ( begin == end ) { //if the beginning is the same as the end, the array being checked only
			return begin;  	  //contains one element
		} //end if the array is of size one
		
		int middle = (begin + end) / 2;
		int min1 = min( a, begin, middle );
		int min2 = min( a, middle + 1, end );
		
		return (min1 < min2) ? min1 : min2;

	} //end find minimum value method
	

	
	// Problem 2
	public static long computePay(int day) {
		
		long total = 0;
		
		if ( day == 1 ) { //establishes base case 1
			total = day;
		} //end if 
		
		else if ( day == 2 ) { //establishes base case 2
			total = day - 1;
		}
		
		else { //tallies the total amount of pay that day based on pay of previous days.
			total = day + computePay( day - 1 );
		}
		
		return total;
	
	} //end public static 

	
	public static long computeSavings(int day) {
		
		long total = 0;
		
		if ( day == 2 ) {
			total = day + 1;
		}
			
		else {
			total += (computePay( day ) + computeSavings( day - 1 ));
		} //end else 
		
		return total;
		
	} //end computeSavings method
	
	// Problem 3
	public static int countSubstring(String s1, String s2) {
		
		//if the length of the main string is less than the length of the substring
		if ( s1.length() < s2.length() ) { 
			return 0; //there aren't going to be any matches
		} //end if 
		
		//else if a substring of mainstring at the length of the s2 substring equals s2
		else if ( s1.substring(0, s2.length()).equals(s2) ) {
			//return a match, create a substring minus the match, and add one to the count
			return countSubstring( s1.substring( s2.length(), s1.length() ), s2) + 1;
			//return s1.length();
		} //end else if
		
		//else length of main string is greater than substring and there is no match
		else {
			//return a substring of main string minus one to check next set of characters
			return countSubstring( s1.substring(1, s1.length() ), s2 );
			//return 2;
		} //end else 

	} //end countSubstring method 
	
} //end class Lab2
