public class LinkedQueueWithNoLastNode<T> implements QueueInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedQueueWithNoLastNode(){
        firstNode = null;
    }

    /**
     * Adds a new entry to the back of the queue. 
     * @param newEntry the desired entry to be added
     */
    public void enqueue(T newEntry){
        if(isEmpty()){
            firstNode = new Node(newEntry);
        }
        else{
            Node current = firstNode;
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(newEntry);
        }
        numberOfEntries++;
    }

    /**
     * Removes and returns the entry at the front of the queue
     * @return Returns the queue’s front entry. Throws an exception if the queue is 
     *         empty before the operation.
     */
    public T dequeue(){
        T data = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return data;
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
        return firstNode == null;
    }

    /**
     * Removes all entries from the queue.
     */
    public void clear(){
        firstNode = null;
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
