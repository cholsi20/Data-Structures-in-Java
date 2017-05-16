package array_sort;

import java.util.Arrays;

public class ArraySory {
	
	public static void main( String[] args ) {
		
		int[] A = { 9, 2, 4, 8, 9, 4, 3, 2, 8, 1, 2, 7, 2, 5 };
		
		System.out.println( "The output of the first array counts the number of occurances for each number, and "
				+ "the second the sorted array.");
		countingSort( A, 9 );
		System.out.println();
		
		int[] A1 = { 3,6,5,1,4 };
		int[] A2 = { 4,7,5,2,6,1 };
		
		System.out.printf( "The missing number in A1 is: %d\n", findMissing( A1 ) );
		System.out.printf( "The missing number in A2 is: %d\n\n", findMissing( A2 ) );
		
		int[] testArray = { 1,4,7,9,6,10,51,23 };
		
		System.out.printf( "The kth smallest element, and median, of testArray is: %d", 
				findKSmallest( testArray, (int) Math.ceil((testArray.length) / 2)) );
		
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

	    	int n = a.length + 1;
	    	
	    	//find the vlaue of all the numbers added together in the array 
	    	int totalValue = ( n * ( n + 1 ) ) / 2;
	    	
	    	//subtract the data in each element from the total value 
	    	for ( int count = 0; count < a.length; count++ ) {
	    		
	    		totalValue -= a[count];
	    		
	    	} //end count 
	    	
	    	return totalValue;
	    	
	    } //end findMissing
	    
	    
	    public static void countingSort(int[] a, int n ) {
	    	
	    	int[] temp = new int[a.length]; 
	    	int[] count = new int[n];
	    	//int numberToFind = n;
	    	int counter = 0;
	    	int index = 0;
	    	int timesOccurred = 0;
	    	//int indexCounter = 0; 
	    	
	    	//collect all the numbers of a similar value into separate arrays 
	    	for ( int increment = 1; increment < a.length; increment++ ) {
	    		
	    		//look through entire array to find all the instances of 1 through n
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
	    		if ( increment <= n) {
	    			count[ increment - 1 ] += timesOccurred;
	    		} //end if 
	    		//reset these values for the next pass
	    		timesOccurred = 0;
	    		counter = 0;
	    		
	    	} //end for loop 
	    	
	    	//reassign sorted array to a
	    	a = temp;
	    	
	    	//print out count array
	    	System.out.println( Arrays.toString(count));
	    	
	    	//print out newly sorted original array
	    	System.out.println( Arrays.toString(a));
	    	
	    }//end countingSort
	    
	    
	 // Median-of-three pivot selection
	    // Sort by comparison
	    private static void sortFirstMiddleLast(int[] a, int first, int mid, int last)
	    {
			// Note similarity to bubble sort
			order(a, first, mid); // make a[first] <= a[mid]
			order(a, mid, last);  // make a[mid] <= a[last]
			order(a, first, mid); // make a[first] <= a[mid]
	    } //end sortFirstMiddleLast

	    private static void order(int[] a, int i, int j)
	    {
			if ( a[i] > a[j] ) {
				swap(a, i, j); 
			} //end outer if 
	    } //end order 
	    
	    private static void swap(int[] array, int i, int j)
	    {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp; 
	    } //end swap
	    
	    // Partitioning
	    
	    private static int partition(int[] a, int first, int last)
	    {
			int mid = (first + last)/2;
			sortFirstMiddleLast(a, first, mid, last);

			// move pivot to next-to-last position in array
			swap(a, mid, last - 1);
			int pivotIndex = last - 1;
			int pivot = a[pivotIndex];

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
				while ( !( a[indexFromLeft] > pivot) )  {
					indexFromLeft++;
				} //end while 
					
				
				// starting at end of array, leave entries that are > pivot; 
				// locate first entry that is <= pivot; you will find one, 
				// since first entry is <= pivot
				
				while (a[indexFromRight] > pivot)
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
	    
	    public static int findKSmallest(int[] a, int k) {
	    	
	    	int first = 0;
	    	int last = a.length - 1;
	    	int middle = (last - first) / 2;
	    	
	    	//If Smaller contains k or more entries, it must contain the kth smallest entry
	    	if ( partition( a, first, last ) >= k ) {
	    		int[] subArraySmallest = Arrays.copyOfRange(a, first, middle + 1);
	    		return findKSmallest( subArraySmallest, (int) Math.ceil((subArraySmallest.length) / 2 ));
	    		//return partition( a, first, last );
	    		//return a[partition( a, first, last)];
	    		//return a[middle];
	    	}
	    	
	    	//If Smaller contains k-1 entries, the kth smallest entry is the pivot.
	    	else if ( partition( a, first, last ) == ( k - 1 ) )  {
	    		return a[k]; 
	    		//return 0;
	    	} //end if 
	    	
	    	//If Smaller contains fewer than k-1 entries, the kth smallest entry is in Larger.
	    	else { //( a[middle -1] < k -1 )
	    		int[] subArrayLargest = Arrays.copyOfRange(a, a[middle - 1], last);
	    		return findKSmallest( subArrayLargest, (int) Math.ceil((subArrayLargest.length) / 2 ));
	    		//return subArrayLargest[3];
	    		//return last;
	    	} //end else if 
	    	
	    } //end findKSmallest method 
	    
} //end public class ArraySort
