

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack ; // for Stack

public class BinaryTree<T>
{
    protected BinaryNode<T> root;
    
    public BinaryTree() {
	     root = null;
    } // end default constructor
    
    public BinaryTree(T rootData) {
	     root = new BinaryNode<T>(rootData);
    } // end constructor

   public BinaryTree(T rootData, BinaryTree<T> leftTree, 
                                 BinaryTree<T> rightTree) {
       privateSetTree(rootData, leftTree, rightTree);
   } // end constructor

   public void setTree(T rootData)
   {
      root = new BinaryNode<T>(rootData);
   } // end setTree
   
   public void setTree(T rootData, BinaryTree<T> leftTree,
		       BinaryTree<T> rightTree)
   {
      privateSetTree(rootData, leftTree, rightTree);
   } // end setTree

   private void privateSetTree(T rootData, BinaryTree<T> leftTree, 
                                          BinaryTree<T> rightTree)
   {
    
      root = new BinaryNode<T>(rootData);

      if (leftTree != null)
	       root.setLeftChild(leftTree.root);
         
      if (rightTree != null)
         root.setRightChild(rightTree.root);
   } 

   public T getRootData () {
        T rootData = null;
        if (root != null)
            rootData = root.getData();
        return rootData;
   }
   public boolean isEmpty () {
       return root == null;
   }
   public void clear (){
       root = null;
   }
   // getHeight and getNumberOfNodes call same functions in BinaryNode<T>
   public int getHeight () {
       return root.getHeight ();
   } 
   public int getNumberOfNodes () {
       return root.getNumberOfNodes ();
   }
   
   public void inorderTraversal() {
       Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
       BinaryNode<T> currentNode = root;
       
       while (!nodeStack.empty() || currentNode != null) {
      	   while(currentNode != null) {
      	       nodeStack.push(currentNode);
      	       currentNode = currentNode.getLeftChild();
      	   }
      	   if(!nodeStack.empty()) {
      	       BinaryNode<T> nextNode = nodeStack.pop();
      	       System.out.println(nextNode.getData());
      	       currentNode = nextNode.getRightChild();
      	   }
       }
   }
   
   public Iterator<T> getPreorderIterator() {
       return new PreorderIterator();
   }
   
   public Iterator<T> getInorderIterator() {
       return new InorderIterator();
   }
   
   private class PreorderIterator implements Iterator<T> {
       private Stack<BinaryNode<T>> nodeStack;
       
       public PreorderIterator() {
	   nodeStack = new Stack<BinaryNode<T>>();
	   if (root != null)
	       nodeStack.push(root);
       } // end default constructor
       
       public boolean hasNext()  {
	   return !nodeStack.isEmpty();
       } // end hasNext
       
       public T next() {
	   BinaryNode<T> nextNode;
	   
	   if (hasNext()) {
	       nextNode = nodeStack.pop();
	       BinaryNode<T> leftChild = nextNode.getLeftChild();
	       BinaryNode<T> rightChild = nextNode.getRightChild();
	       
	       // push into stack in reverse order of recursive calls
	       if (rightChild != null)
		   nodeStack.push(rightChild);
	       
	       if (leftChild != null)
		   nodeStack.push(leftChild);
	   }
	   else {
	       throw new NoSuchElementException();
	   }
	   return nextNode.getData();
       } // end next
       
       public void remove() {
	   throw new UnsupportedOperationException();
       } // end remove
   } // end PreorderIterator
   
    
   private class InorderIterator implements Iterator < T >
   {
       private Stack< BinaryNode < T >> nodeStack;
       private BinaryNode< T > currentNode;
       public InorderIterator () {
	   nodeStack = new Stack < BinaryNode< T >> ();
	   currentNode = root;
       } // end default constructor


       public boolean hasNext () {
	   return !nodeStack.isEmpty () || (currentNode != null);
       } // end hasNext


       public T next ()
       {
	   BinaryNode< T > nextNode = null;
	   // find leftmost node with no left child
	   while (currentNode != null) {
	       nodeStack.push (currentNode);
	       currentNode = currentNode.getLeftChild ();
	   } // end while
	   // get leftmost node, then move to its right subtree
	   if (!nodeStack.isEmpty ()) {
	       nextNode = nodeStack.pop ();
	       currentNode = nextNode.getRightChild ();
	   }
	   else
	       throw new NoSuchElementException ();
	   return nextNode.getData ();
       } // end next
       
       
       public void remove () {
	   throw new UnsupportedOperationException ();
       } // end remove

   } // end InorderIterator
   
} // end BinaryTree


