
public class BigO {
	
	public static void main( String[] args ) {
		
		int[] i = {1,2,5,6,9,100,20};
		int[] j = {1,2,5,6,9,100};
		
		System.out.printf( "Integer array i is sorted: %s\n\n", isSorted( i ) );
		System.out.printf( "Integer array i is sorted: %s\n\n", isSorted( j ) );
		
	}
	
	//begin isSorted method 
	static boolean isSorted( int[] a ) {
		
		for ( int count = 1; count < a.length; count++ ){ //occurs n times
			
				if ( a[count-1] > a[count] ) { //occurs n times
					
					return false; //occurs once
					
				} //end if the current number is greater the next number
			
		} //end for loop 
		
		return true; //occurs once 
		
	} //end method isSorted
	

} //end class BigO