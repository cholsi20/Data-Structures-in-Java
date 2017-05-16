public class ArraySort {
	public static void main( String[] args ) {
		
		int[] A = { 9, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5 };
		
		countingSort( A, 14 );
		
	} //end main 

	    public static <T extends Comparable<? super T>>
				     void mergeSort(T[] a, int n) {
			mergeSort(a, 0, n - 1);
	    } //end mergeSort

	    public static <T extends Comparable<? super T>>
				     void mergeSort(T[] a, int first, int last) {
			@SuppressWarnings("unchecked")
		    T[] tempArray = (T[])new Comparable<?>[a.length];
		    mergeSort(a, tempArray, first, last);
	    }  //mergeSort
		
	    private static <T extends Comparable<? super T>>
				      void mergeSort(T[] a, T[] tempArray, int first, int last)
	    {
			if (first < last) {  // sort each half
				int mid = (first + last) / 2;// index of midpoint
				mergeSort(a, first, mid);  // sort left half array[first..mid] ---> you can use this just this method 
				//to optimize, just add tempArray.  mergeSort(a, tempArray, first, mid);
				mergeSort(a, mid + 1, last); // sort right half array[mid+1..last] ---> you can use this just this method
				//to optimize, just add tempArray.  mergeSort(a, tempArray, mid + 1, last);
		    
				if (a[mid].compareTo(a[mid + 1]) > 0)     
					merge(a, tempArray, first, mid, last); // merge the two halves
				//	else skip merge step
			} //end if first less than last
	    } //end mergeSort
	    
	    private static <T extends Comparable<? super T>> 
				      void merge(T[] a, T[] tempArray, int first, int mid, int last)
	    {
			// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
			int beginHalf1 = first;
			int endHalf1 = mid;
			int beginHalf2 = mid + 1;
			int endHalf2 = last;
		
			// while both subarrays are not empty, copy the
			// smaller item into the temporary array
			int index = beginHalf1; // next available location in
			// tempArray
			for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++) {  
		    
				if (a[beginHalf1].compareTo(a[beginHalf2]) < 0) {  
					tempArray[index] = a[beginHalf1];
					beginHalf1++;
				} //end if 
				else {  
					tempArray[index] = a[beginHalf2];
					beginHalf2++;
				}  //end else 
			}  //end for 
		
			// finish off the nonempty subarray
		
			// finish off the first subarray, if necessary
			for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
				tempArray[index] = a[beginHalf1];
		
			// finish off the second subarray, if necessary
			for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
				tempArray[index] = a[beginHalf2];
		
			// copy the result back into the original array
			for (index = first; index <= last; index++)
				a[index] = tempArray[index];
		}  //end merge 
	    
	    // Quick Sort
	    
	    // Median-of-three privot selection
	    // Sort by comparison
	    private static <T extends Comparable<? super T>>
				      void sortFirstMiddleLast(T[] a, int first, int mid, int last)
	    {
			// Note similarity to bubble sort
			order(a, first, mid); // make a[first] <= a[mid]
			order(a, mid, last);  // make a[mid] <= a[last]
			order(a, first, mid); // make a[first] <= a[mid]
	    } //end sortFirstMiddleLast

	    private static <T extends Comparable<? super T>>
				      void order(T[] a, int i, int j)
	    {
			if (a[i].compareTo(a[j]) > 0)
				swap(a, i, j);
	    } //end order 
	    
	    private static void swap(Object[] array, int i, int j)
	    {
			Object temp = array[i];
			array[i] = array[j];
			array[j] = temp; 
	    } //end swap
	    
	    // Partitioning
	    
	    private static <T extends Comparable<? super T>>
				      int partition(T[] a, int first, int last)
	    {
			int mid = (first + last)/2;
			sortFirstMiddleLast(a, first, mid, last);

			// move pivot to next-to-last position in array
			swap(a, mid, last - 1);
			int pivotIndex = last - 1;
			T pivot = a[pivotIndex];

			// determine subarrays Smaller = a[first..endSmaller]
			// and                 Larger  = a[endSmaller+1..last-1]
			// such that entries in Smaller are <= pivot and 
			// entries in Larger are >= pivot; initially, these subarrays are empty
		
			int indexFromLeft = first + 1; 
			int indexFromRight = last - 2;
		
			boolean done = false;
			while (!done) {
				// starting at beginning of array, leave entries that are < pivot;
				// locate first entry that is >= pivot; you will find one,
				// since last entry is >= pivot
				while (a[indexFromLeft].compareTo(pivot) < 0)
					indexFromLeft++;
				
				// starting at end of array, leave entries that are > pivot; 
				// locate first entry that is <= pivot; you will find one, 
				// since first entry is <= pivot
				
				while (a[indexFromRight].compareTo(pivot) > 0)
					indexFromRight--;
						   
				if (indexFromLeft < indexFromRight)
				{
					swap(a, indexFromLeft, indexFromRight);
					indexFromLeft++;
					indexFromRight--;
				} //end if 
				else 
					done = true;
			} // end while
		
			// place pivot between Smaller and Larger subarrays
			swap(a, pivotIndex, indexFromLeft);
			pivotIndex = indexFromLeft;
			
			return pivotIndex; 
	    } //end partition 
	    
	    public static <T extends Comparable<? super T>>
				     void quickSort(T[] a, int n) {
			quickSort(a, 0, n - 1);
	    } //end quickSort
	    
	    public static <T extends Comparable<? super T>>
				     void quickSort(T[] a, int first, int last) {
			// perform recursive step if 2 or more elements
			if(first < last) {
				// create the partition: Smaller | Pivot | Larger
				int pivotIndex = partition(a, first, last);
				
				// sort subarrays Smaller and Larger
				quickSort(a, first, pivotIndex - 1);
				quickSort(a, pivotIndex + 1, last);
			} //end if first less than last
	    } //end quickSort
	    
	    /*public static void radixSort( int a[], int first, int last, int maxDigits ) {
	    	
	    	for ( int i = 0; i <= maxDigits - 1; i++ ) {
	    		
	    		
	    		
	    	} //end for 
	    	
	    } //end radixSort
	    */
	    
	    //--------------------------------------------------------Begin Methods for Class
	    public static int findMissing(int [] a) {
	    	
	    	int[] array = new int[a.length];
	    	
	    	//use radix sort to sort array 
	    	//array = sort( a );
	    	
	    	//radix returns sorted array, assign last element to n 
	    	//int n = array[ array.length - 1 ];
	    	
	    	//find the missing number by applying the given formula
	    	//int missingNumber = n( n + 1 ) / 2;
	    	
	    	//return the missing number
	    	//return missingNumber;
	    	
	    	return 0;
	    	
	    } //end findMissing
	    
	    
	    public static void countingSort(int[] a, int n ) {
	    	
	    	int[] temp = new int[a.length]; 
	    	int[] count = new int[a.length];
	    	//int numberToFind = n;
	    	int counter = 0;
	    	int index = 0;
	    	int timesOccurred = 0;
	    	
	    	//collect all the numbers of a similar value into separate arrays 
	    	for ( int increment = 1; increment <= n; increment++ ) {
	    		
	    		//look through entire array to find all the instances of 1-n
	    		while ( counter != a.length ) {
	    			
	    			//if it is found
	    			if ( a[counter] == increment ) {
	    				//add them to the temp array, in order, so you can sort it 
	    				temp[index] += a[counter];
	    				index++;
	    				//count the number of times each number occurred
	    				++timesOccurred;
	    			} //end if 
	    			counter++;
	    		} //end while 
	    		
	    		//add how many times a number occurred to the array which tracks this
	    		count[ increment - 1 ] += timesOccurred;
	    		//reset these values for the next pass
	    		timesOccurred = 0;
	    		counter = 0;
	    		
	    	} //end for loop 
	    	
	    	//reassign sorted array to a
	    	a = temp;
	    	
	    	//print that stuff out!
	    	for ( int i = 0; i < count.length; i++ ){
	    		System.out.printf( "%d", count[i]);
	    	}
	    	
	    	System.out.println();
	    	
	    	for ( int j = 0; j < a.length; j++ ){
	    		System.out.printf( "%d", a[j]);
	    	}
	    	
	    	System.out.println();
	    	
	    }//end countingSort
}
