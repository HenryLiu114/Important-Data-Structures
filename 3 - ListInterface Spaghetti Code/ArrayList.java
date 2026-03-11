import java.util.Arrays;
import java.util.Iterator;
public class ArrayList<T> implements ListInterface<T>{
    private T[] list;
    private int currentSize;
    private int defaultSize = 3;

    public ArrayList(){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[defaultSize];
        list = temp;
        currentSize = 0;
    }  

    /** Adds a new entry to the end of this list.
    * Entries currently in the list are unaffected.
    * The list's size is increased by 1.
    * @param newEntry The object to be added as a new entry. 
    */
    public void add(T newEntry){
        if(currentSize >= list.length){
            list = Arrays.copyOf(list, currentSize+defaultSize);
            System.out.println("added more slots");
        }
        
        list[currentSize] = newEntry;
        currentSize++; 
    }

    /** Adds a new entry at a specified position within this list.
    * Entries originally at and above the specified position
    * are at the next higher position within the list.
    * The list's size is increased by 1.
    * @param newPosition An integer that specifies the desired
    * position of the new entry.
    * @param newEntry The object to be added as a new entry.
    * @throws IndexOutOfBoundsException if either
    * newPosition < 1 or newPosition > getLength() + 1. 
    */
    public void add(int newPosition, T newEntry){
        if((newPosition > (currentSize+1)) && newPosition < 1){
            throw new IndexOutOfBoundsException();
        }
        else{
            currentSize++;
            T firstEntry = null;
            for(int i = newPosition-1; i < currentSize; i++){
                if(currentSize >= list.length){
                    resize();
                }
                T temp = list[i];
                if(i == newPosition-1){
                    list[i] = newEntry;
                    firstEntry = temp;
                }
                else{
                    list[i]=firstEntry;
                    firstEntry = temp;
                }
            }

        }
    }

    private void resize(){
        list = Arrays.copyOf(list, currentSize+defaultSize);
        System.out.println("added more slots");
    }
    /** Removes the entry at a given position from this list.
    * Entries originally at positions higher than the given
    * position are at the next lower position within the list,
    * and the list's size is decreased by 1.
    * @param givenPosition An integer that indicates the position of
    * the entry to be removed.
    * @return A reference to the removed entry.
    * @throws IndexOutOfBoundsException if either
    * givenPosition < 1 or givenPosition > getLength().
    */
    public T remove(int givenPosition){
        if((givenPosition > (currentSize+1)) && givenPosition < 1){
            throw new IndexOutOfBoundsException();
        }
        else{
            T temp = list[givenPosition-1];
            list[givenPosition-1] = null;
            currentSize--;
            for(int i = givenPosition-1; i < currentSize; i++){
                list[i] = list[i+1];
            }
            return temp;
        }
    }

/** Removes all entries from this list. */
public void clear(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[defaultSize];
    list = temp;
    currentSize = 0;
}

/** Replaces the entry at a given position in this list.
 * @param givenPosition An integer that indicates the position of
 * the entry to be replaced.
 * @param newEntry The object that will replace the entry at the
 * position givenPosition.
 * @return The original entry that was replaced.
 * @throws IndexOutOfBoundsException if either
 * givenPosition < 1 or givenPosition > getLength(). 
 */
public T replace(int givenPosition, T newEntry){
    if((givenPosition > (currentSize+1)) && givenPosition < 1){
        throw new IndexOutOfBoundsException();
    }
    else{
        T temp = list[givenPosition-1];
        list[givenPosition-1] = newEntry;
        return temp;
    }
}

/** Retrieves the entry at a given position in this list.
 * @param givenPosition An integer that indicates the position of
 * the desired entry.
 * @return A reference to the indicated entry.
 * @throws IndexOutOfBoundsException if either
 * givenPosition < 1 or givenPosition > getLength().
 */
public T getEntry(int givenPosition){
    if((givenPosition > (currentSize+1)) && givenPosition < 1){
        throw new IndexOutOfBoundsException();
    }
    else{
        return list[givenPosition-1];
    }
}

/** Retrieves all entries that are in this list in the order in which
 * they occur in the list.
 * @return A newly allocated array of all the entries in the list.
 * If the list is empty, the returned array is empty. 
 */
public T[] toArray(){
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[currentSize];

    for(int i = 0; i < temp.length; i++){
        temp[i] = list[i];
    }
    return temp;
}

/** Sees whether this list contains a given entry.
 * @param anEntry The object that is the desired entry.
 * @return True if the list contains anEntry, or false if not. 
 */
public boolean contains(T anEntry){
    boolean isFound = false;
    int i = 0;
    while(!isFound && i < currentSize){
        if(list[i].equals(anEntry)){
            isFound = true;
        }
    }
    return isFound;
}

/** Gets the length of this list.
 * @return The integer number of entries currently in the list.
 */
public int getLength(){
    return currentSize;
}

/** Sees whether this list is empty.
 * @return True if the list is empty, or false if not.
 */
public boolean isEmpty(){
    return currentSize==0;
}

public Iterator<T> getIterator(){
    return new ArrayListIterator(list);
}

private class ArrayListIterator implements Iterator<T>{

    private int index;
    private T[] arr;

    private ArrayListIterator(T[] ar){
        arr = ar;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < currentSize;
    }

    @Override
    public T next() {
        if(hasNext()){
           T result = arr[index];
           index++;
           return result;
        }
        else{
            return null;
        }
    }
    
}
}
