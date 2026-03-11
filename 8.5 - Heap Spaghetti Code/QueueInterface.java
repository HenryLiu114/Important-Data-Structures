public interface QueueInterface<T>{
    /**
     * Adds a new entry to the back of the queue. 
     * @param newEntry the desired entry to be added
     */
    public void enqueue(T newEntry);

    /**
     * Removes and returns the entry at the front of the queue
     * @return Returns the queue’s front entry. Throws an exception if the queue is 
     *         empty before the operation.
     */
    public T dequeue();

    /**
     * Retrieves the queue’s front entry without changing 
     * the queue in any way.
     * @return Returns the queue’s front entry. Throws an exception 
     *         if the queue is empty
     */
    public T getFront();

    /**
     * Detects whether the queue is empty
     * @return Returns true if the queue is empty
     */
    public boolean isEmpty();

    /**
     * Removes all entries from the queue.
     */
    public void clear();
}