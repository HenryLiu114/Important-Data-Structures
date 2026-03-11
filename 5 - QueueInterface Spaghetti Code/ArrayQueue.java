import java.util.Arrays;
import java.util.NoSuchElementException;
public class ArrayQueue<T> implements QueueInterface<T> {
    private T[] queue;
    private int indexFirst;
    private int indexLast;
    private final int SIZE_SUM = 10;
    private final int DEFAULT_SIZE = 10;

    public ArrayQueue(){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[DEFAULT_SIZE];
        queue = temp;
        indexFirst = 0;
        indexLast = 0;
    }

    public ArrayQueue(int size){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[size];
        queue = temp;
        indexFirst = 0;
        indexLast = 0;
    }

    /**
     * Adds a new entry to the back of the queue. 
     * @param newEntry the desired entry to be added
     */
    public void enqueue(T newEntry){
        if(indexLast >= queue.length){
            resize();
        }
        queue[indexLast] = newEntry;
        indexLast++;
    }

    /**
     * Removes and returns the entry at the front of the queue
     * @return Returns the queue’s front entry. Throws an exception if the queue is 
     *         empty before the operation.
     */
    public T dequeue(){
        if(!(isEmpty())){
            T temp = queue[indexFirst];
            indexFirst++;
            return temp;
        }
        else{
            throw new NoSuchElementException("No Objects in queue!!");
        }
    }

    /**
     * Retrieves the queue’s front entry without changing 
     * the queue in any way.
     * @return Returns the queue’s front entry. Throws an exception 
     *         if the queue is empty
     */
    public T getFront(){
        return queue[indexFirst];
    }

    /**
     * Detects whether the queue is empty
     * @return Returns true if the queue is empty
     */
    public boolean isEmpty(){
        return indexFirst == 0 && indexLast == 0;
    }

    /**
     * Removes all entries from the queue.
     */
    public void clear(){
        while(!(isEmpty())){
            dequeue();
        }
    }

    private void resize(){
        queue = Arrays.copyOf(queue, queue.length + SIZE_SUM);
    }
}
