public class LinkedQueue<T> implements QueueInterface<T> {
    private Node firstNode;
    private Node lastNode;

    public LinkedQueue(){
        firstNode = null;
        lastNode = null;
    }

    /**
     * Adds a new entry to the back of the queue. 
     * @param newEntry the desired entry to be added
     */
    public void enqueue(T newEntry){
        Node newNode = new Node(newEntry, null);
        if (isEmpty()){
            firstNode = newNode;
        }
        else{
            lastNode.next = newNode;
        }
        lastNode = newNode;
    }

    /**
     * Removes and returns the entry at the front of the queue
     * @return Returns the queue’s front entry. Throws an exception if the queue is 
     *         empty before the operation.
     */
    public T dequeue(){
        T result = firstNode.data; 
        firstNode.data = null;
        firstNode = firstNode.next;
        if (firstNode == null)
            lastNode = null;
        return result;
    }

    /**
     * Retrieves the queue’s front entry without changing 
     * the queue in any way.
     * @return Returns the queue’s front entry. Throws an exception 
     *         if the queue is empty
     */
    public T getFront(){
        return firstNode.data;
    }

    /**
     * Detects whether the queue is empty
     * @return Returns true if the queue is empty
     */
    public boolean isEmpty(){
        return firstNode == null && lastNode == null;
    }

    /**
     * Removes all entries from the queue.
     */
    public void clear(){
        firstNode = null;
        lastNode = null;
    }

    private class Node{
        private T data;
        private Node next;

        private Node (T dataPortion) {
            this(dataPortion, null);
        } 

        private Node (T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }
}
