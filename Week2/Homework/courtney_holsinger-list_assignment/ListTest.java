/*
Courtney Holsinger
Week 2 
List Test Program
*/

public class ListTest {
	
	public static void main(String[] args) 
	{
		testList();
	}  // end main

	public static void testList() 
	{
     ListInterface<Integer> runnerList = new AList<Integer>();
//  runnerList has only methods in ListInterface

      System.out.println( "Add items to create initial list." );
      runnerList.add(16); // winner
      runnerList.add(4); // second place
      runnerList.add(33); // third place
      runnerList.add(27); // fourth place
      displayList(runnerList);
      
      //test remove
      System.out.println( "Remove item from a list." );
      runnerList.remove(2);
      displayList( runnerList );
      
      //test add to specific position
      System.out.println( "Adding an entry to a specific location." );
      runnerList.add(2, 22);
      displayList( runnerList );
      
      //test replacing an entry
      System.out.println( "Replacing an entry at a specific position in the list." );
      runnerList.replace( 3, 4 );
      displayList( runnerList );
      
      //testing getting a specified entry 
      System.out.println( "Retrieving an entry at a specific position in the list." );
      System.out.printf( "The 4th position value in this list: %d\n\n", runnerList.getEntry( 4 ) );
      
      //test finding a specific entry by name rather than position in list
      System.out.println( "Retrieving an entry by looking for its name." );
      System.out.printf( "Does this list contain the number 4: %s\n\n", runnerList.contains( 4 ) );
      
      //testing getindex
      System.out.println( "Testing getIndex to return index of first entry of an item." );
      AList<Integer> runnerList2 = (AList<Integer>) runnerList;
      runnerList2.add( 4, 4 );
      displayList( runnerList2 );
      System.out.printf( "Number 4 has an index of: %d\n\n", runnerList2.getIndex( 4 ) );
      
      //testing removeEvery
      System.out.println( "Testing removeEvery method to remove every instance of 4 in list" );
      runnerList2.add( 2, 4 );
      System.out.println( "List before removal of #4." );
      displayList ( runnerList2 );
      System.out.printf("List after number four was removed %d times.\n", runnerList2.removeEvery( 4 ) );
      displayList ( runnerList2 );
      
      //testing getLastIndex
      System.out.println( "Testing lastItemFound method to find the last of an item in a list." );
      runnerList2.add( 2, 4 );
      runnerList2.add( 4, 4 );
      displayList( runnerList2 );
      System.out.printf( "The last item of number four has an index of: %d\n\n", 
      runnerList2.getLastIndex(4) );
      
      //testing equals method
      AList<Integer> runnerList3 = new AList<Integer>();
      runnerList3.add(7);
      System.out.println( "Testing equals method which comparies lists to determine equality." );
      System.out.printf( "runnerList and runnerList2 are equal: %s\n", runnerList2.equals( runnerList) );
      System.out.println( "runnerList3 is created as a new AList object.");
      System.out.printf( "runnerList2 and runnerList3 are equal: %s\n\n", runnerList2.equals( runnerList3 ) );
      /*displayList( runnerList );
      displayList( runnerList3 );*/
      
      //testing revised remove method with isTooBig() and reduce
      
      
      
      //test clearing the list 
      System.out.println( "Clearing a list." );
      runnerList2.clear();
      displayList( runnerList2 );
      
   } // end testList
   
   public static void displayList(ListInterface<Integer> list)
   {
	   //if list has items in it		
	   if ( list.getLength() > 0 ) { 
			  
		  int numberOfEntries = list.getLength();
	      System.out.println("The list contains " + numberOfEntries +
	                         " entries, as follows:");
	      for (int position = 1; position <= numberOfEntries; position++)
	         System.out.println(list.getEntry(position) +
	                            " is entry " + position);
	      System.out.println();
	  }
	   
	   //if the list has 0 items 
	   else {
		   System.out.println( "The list contains no items." );
	   }
   } // end displayList

}
