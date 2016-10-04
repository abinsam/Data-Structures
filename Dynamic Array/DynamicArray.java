import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import javax.swing.text.html.MinimalHTMLWriter;

/**
 * @author Abin Sam
 * INFS 519
 * Fall 2015
 */

/**
 * A class for implementing List API as a dynamic Array
 * @Classname:DynamicArray
 * @author:Abin Sam
 * @Mason Id:G00979582
 * @Date:09-15-2015
  */
public class DynamicArray <Type> implements List<Type>
{
    public static final int DEFAULT_INIT_CAPACITY = 2;
    public Type[] items;
    public static int capacity=0;
    public int size=0;
    
    public DynamicArray()
    {
       capacity=DEFAULT_INIT_CAPACITY;
       this.size=0;
       this.items=(Type[]) new Object[DEFAULT_INIT_CAPACITY];//allocate initial memory using constant 
    }

    /**
     * Gets the item at index i
     * @param i
     * @return item at i
     * @call function to check range IndexOutOfBoundsException if i is not in range
     */
    public Type get(int i){
    	checkRange(i);
    	return items[i];
    }
    
    /**
     * check range of index i
     * @param i
     * @return 
     * @throws IndexOutOfBoundsException if i is not in range
     */
    private void checkRange(int i) {
    	if( i < 0 || i >= items.length )
    	{
    	throw new IndexOutOfBoundsException(
    	"Index out of range: "+i);
    	}
	}

    /**
     * Overwrites the item at position i with specified item
     * @param i
     * @param item
     * @throws IndexOutOfBoundsException if i is not in range
     * @throws NullPointerException is item is null
     */
	public void set(int i, Type item)
    {
        if(item==null){
        	throw new NullPointerException("Null Value "+item);
        }
        else if( i < 0 || i >= items.length ){
    	   throw new IndexOutOfBoundsException("Index out of range: "+i);
    	}
        else
        items[i]=item;//set new item to index
    }

	 
	/**
     * Appends item to the end of the list.
     * @param item
     * @throws NullPointerException if item is null
     */
    public void add(Type item)
    {
        if(item!=null){
        	checkGrow(size+1);//call checkGrow Function
        	items[size]=(Type) item;
        	size=size+1;
    }
        else{
          throw new NullPointerException("Null Value "+item);
        }
    }

    
	/**
     * Check capacity of Array before adding new elements and grow to increase capacity.
     * @param requiredCapacity
     * @throws NullPointerException if item is null
     */
    private void checkGrow(int requiredCapacity) {
    	int oldCapacity=capacity;
    	int newCapacity=0;
		if(requiredCapacity>oldCapacity){
			newCapacity=oldCapacity*2;// increase capacity by two times
			if(requiredCapacity>newCapacity)
				newCapacity=requiredCapacity;
			    resize(newCapacity);
		}
		else{
			newCapacity=oldCapacity;
		}
		capacity=newCapacity;//updating new capacity to variable capacity
	}
    
	/**
     * Copy array elements to new array
     * @param newCapacity
     * @throws 
     */
	private void resize(int newCapacity) {
		items=Arrays.copyOf(items, newCapacity);
	
	}

	   /**
     * Removes item at index from the list.
     * @param i
     * @return item at i
     * @throws IndexOutOfBoundsException if i is not in range
     * @throws NullPointerException if items null
     */
	public Type remove(int i){
	   if(items==null){
	     	throw new NullPointerException("Null Value "+items);
	  }
	  else if( i < 0 || i >= items.length ){
    	   throw new IndexOutOfBoundsException("Index out of range: "+i);
      }
	  else{
		  Type removeElement=items[i];
		  for(int j=i; j<(items.length-1); j++)
		      items[j]=items[j+1];
		  items[items.length-1]=null;
		  size=size-1;
		  checkShrink();//call checkShrink Method
		  return removeElement;
		  }
	 }

	
	/**
     * Check capacity and size to shrink array in case of remove.
     * @param 
     * @throws 
     */
    private void checkShrink() {
		if(size<=(capacity/4))
			capacity=capacity/2;
		
	}

    /**
     * Inserts item at index into the list at index i.
     * @param i
     * @param item
     * @throws IndexOutOfBoundsException if i is not in range
     * @throws NullPointerException is item is null
     */
	public void insert(int i, Type item){
		System.out.println("INSERT");
     if(item==null){
	  	throw new NullPointerException("Null Value "+item);
	 }
	 else if( i < 0 || i >= items.length ){
	    throw new IndexOutOfBoundsException("Index out of range: "+i);
	 }
	 else{
		 if(capacity>=size+1){
			 Type oldValue=items[i];
			 items[i]=item;
			 for(int j=i;j<=items.length;j++){
				 oldValue=items[i+1];
				 items[i+1]=items[i];
			 }
		 }
		 else{
			 checkGrow(size+1);//call checkGrow Function
			 Type oldValue=items[i];
			 items[i]=item;
			 for(int j=i;j<=items.length;j++){
				 oldValue=items[i+1];
				 items[i+1]=items[i];
			 }
		 }
         size=size+1;
	 }
    }

	   /**
     * Gets the size of the list (number of items).
     * @return size
     */
    public int size(){
        return size;
    }


    //--------------------- DO NOT MODIFY BELOW THIS -----------------------//
    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        //buf.append("cap="+this.items.length+"[");
        buf.append("[");
        for( int i = 0; i < this.size(); i++ )
        {
            Type item = this.get(i);
            if( i != 0 ) buf.append( ", " );
            buf.append( item.toString() );
        }
        buf.append("]");
        return buf.toString();
    }


    public static void main(String[] args)
    {
        DynamicArray<String> list = new DynamicArray<String>();
        Stdio.open( args[0] );
        while( Stdio.hasNext() )
        {
            String method = Stdio.readString();
            if( method.equals("add") )
            {
                String param1 = Stdio.readString();
                list.add( param1 );
            }
            else if( method.equals("get") )
            {
                int index = Stdio.readInt();
            }
            else if( method.equals("size") )
            {
                Stdio.println( list.size() );
            }
            else if( method.equals("insert") )
            {
                System.out.println("Hello");
                int param1=Stdio.readInt();
                String param2 = Stdio.readString();
                list.insert(param1, param2);
            }
            else if( method.equals("remove") )
            {
                int index = Stdio.readInt();
                Stdio.println( list.remove(index) );
            }
        }
        Stdio.println( "" );
        Stdio.println( "Final list=" +list.toString() );
        Stdio.println( "Final list size=" +list.size()+" Capacity==="+capacity);

        Stdio.close();
    }

}
