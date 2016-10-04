
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author Abin Sam
 * INFS 519
 * Fall 2015
 */

/**
 * A class for implementing Queue API as linked list
 * @Classname:Queue
 * @author:Abin Sam
 * @Mason Id:G00979582
 * @Date:09-22-2015
  */
public class Queue <Type> implements QueueAPI<Type>
{
    private LinkedlistNode firstElement;
    private LinkedlistNode lastElement;
 	public int listSize=0;
 	/**
     * Node Inner Class
     * @Classname:LinkedListNode
    */
    private static class LinkedlistNode<Type>{
		public Type data;
		public LinkedlistNode nextElement;
     }
    
    
    public Queue(){
        this.listSize=0;
        firstElement=null;
        lastElement=null;
    }
    
    /**
     * Returns number of items in the queue.
     * @return size
     */
    public int size(){
        return listSize; 
    }
    
    /**
     * Determines if queue is empty.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        if(listSize==0)
        	return true;
       	else
       		return false; 
    }
    
    /**
     * Adds an item to the end of the queue.
     * @param item 
     */
    public void enqueue( Type item ){
    	   LinkedlistNode<Type> currentElement = lastElement;
           lastElement =new LinkedlistNode<Type>();
           lastElement.data = item;
           if (listSize++ == 0) 
        	   firstElement = lastElement;
           else 
        	   currentElement.nextElement = lastElement;	
    }
    
    /**
     * Returns item at front of queue without removing.
     * @return item at front
     * @throws java.util.NoSuchElementException if empty
     */
    public Type peek(){
        if (firstElement == null) {
            throw new NoSuchElementException("No more elements");
        }
        else return (Type) firstElement.data;
    }
    
    /**
     * Removes item from front of queue.
     * @return item removed from front
     * @throws java.util.NoSuchElementException if empty
     */
    public Type dequeue( ){
        if(isEmpty()==true || listSize==0)
        	throw new NoSuchElementException("No more elements");
          Object data = firstElement.data;
          firstElement = firstElement.nextElement;
          if (--listSize== 0) 
        	  lastElement = null;
          return (Type) data;
    }
    
    
    public Iterator<Type> iterator(){
    	return new IteratorClass();
     }
    
    /**
     * Inner Class to implement Iterator interface
     * @Classname:IteratorClass
    */
    private class IteratorClass implements Iterator<Type>{
       private LinkedlistNode<Type> nextNode;
       public IteratorClass(){
          nextNode = firstElement;
       }

       /**
        * Returns {@code true} if the iteration has more elements.
        * @return {@code true} if the iteration has more elements
        */
	@Override
	public boolean hasNext() {
		if(nextNode==null){
			return false;
		}
		else return true;
	}
	

	 /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
	@Override
	public Type next() {
		   if(!hasNext()) throw new NoSuchElementException("No more elements");
		   Type nextValue = nextNode.data;
		   nextNode = nextNode.nextElement;
		   return nextValue;
	}
	
	
	/**
     * throw an UnsupportedOperationException for the remove
	   method and does not need to check for concurrent modifications.
     *
     * @return 
     * @throws UnsupportedOperationException
     */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove");
		
	}
      
    }
 
    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//
    
    @Override
    public String toString()
    {
        // Uses the iterator to build String
        StringBuilder buf = new StringBuilder();
        boolean first = true;
        buf.append("[");
        for (Type item : this)
        {
            if( first ) first = false;
            else buf.append( ", " );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }
    
    
    /**
     * Unit tests the Queue data type.
     * @param args 
     */
    public static void main( String[] args )
    {
        Queue queue = new Queue();
        
        Stdio.open( args[0] );
        while( Stdio.hasNext() )
        {
            String operation = Stdio.readString();
            if( operation.equals("enqueue") )
            {
                String item = Stdio.readString();
                Stdio.println( "enqueue "+ item );
                queue.enqueue(item);
            }
            else if( operation.equals("dequeue") )
            {
                Stdio.println( "dequeue "+ queue.dequeue() );
            }
            else if( operation.equals("peek") )
            {
                Stdio.println( "peek "+ queue.peek() );
            }
            else if( operation.equals("size") )
            {
                Stdio.println( "size "+ queue.size() );
            }
       
        }
        Stdio.println( "Queue=" +queue.toString() );
        
        Stdio.close();
    }

	
}
