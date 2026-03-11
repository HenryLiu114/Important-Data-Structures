public class LinkedDeque<T> implements DequeInterface<T> {
    private DLNode firstNode;
    private DLNode lastNode;

    public LinkedDeque(){
        firstNode = null;
        lastNode = null;
    }

    /** 
     * Adds a new entry to the front/back of this deque.
     * @param newEntry An object to be added. 
     */
    @Override
    public void addToFront(T newEntry){
        DLNode newNode = new DLNode(null, newEntry, firstNode);
        if (isEmpty())
            lastNode = newNode;
        else
            firstNode.prev = newNode;
        firstNode = newNode;
    }

    @Override
    public void addToBack(T newEntry){
        DLNode newNode = new DLNode(lastNode, newEntry, null);
        if (isEmpty()){
            firstNode = newNode;
        } 
        else{
            lastNode.next = newNode;
        }
        lastNode = newNode;
    }

    /**
     * Removes and returns the front/back entry of this deque.
     * @return The object at the front/back of the deque.
     * @throws EmptyQueueException if the deque is empty before the operation.
     */
    @Override
    public T removeFront(){
        T front = getFront(); // Might throw EmptyQueueException
        // Assertion: firstNode != null
        firstNode = firstNode.next;
        if (firstNode == null)
            lastNode = null;
        else
            firstNode.prev = null;
        return front;
    }

    @Override
    public T removeBack(){
        T back = getBack(); // Might throw EmptyQueueException
        // Assertion: lastNode != null
        lastNode = lastNode.prev;
        if (lastNode == null)
            firstNode = null;
        else
            lastNode.next = null;
        return back;
    }

    /**
     * Retrieves the front/back entry of this deque.
     * @return The object at the front/back of the deque.
     * @throws EmptyQueueException if the deque is empty.
     */
    @Override
    public T getFront(){
        return firstNode.data;
    }

    public T getBack(){
        return lastNode.data;
    }

    /**
     * Detects whether this deque is empty.
     * @return True if the deque is empty, or false otherwise.
     */
    @Override
    public boolean isEmpty(){
        return firstNode == null && lastNode == null;
    }

    /**
     * Removes all entries from this deque.
     */
    @Override
    public void clear(){
        firstNode = null;
        lastNode = null;
    }

    private class DLNode {
        private T data;
        private DLNode next;
        private DLNode prev;
        private DLNode(T data){
            this(null, data, null);
        }

        private DLNode(DLNode prev, T data){
            this(prev, data, null);
        }

        private DLNode(T data, DLNode next){
            this(null, data, next);
        }

        private DLNode(DLNode prevE, T dataE, DLNode nextE){
            prev = prevE;
            data = dataE;
            next = nextE;
        }
    }
}
