package binarytrees;

public class TestBinarySearchTree {

	public static void main( String[] args ) {
		
		BinaryTree<String> binaryTree = new BinaryTree<String>();
		
		binaryTree.setTree("Mike");
		binaryTree.setTree("Clara");
		binaryTree.setTree("Jim");
		
		
		
		BinarySearchTree<String> searchTree = new BinarySearchTree<String>("John");
		searchTree.add("Hello");
		searchTree.add("mission");
		
		System.out.printf( "Binary Tree 'binaryTree' is a Binary Tree: %s\n\n", searchTree.isBST( binaryTree ) );
		
		System.out.printf( "Binary Search Tree 'searchTree' is balanced: %s\n\n", searchTree.isBalanced() );
		
	} //end main method 
	
} //end Test BinarySearchTree
