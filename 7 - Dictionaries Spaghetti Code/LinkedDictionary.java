import java.util.Iterator;

public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>{
    private Node firstNode;
    private int currentSize;

    public LinkedDictionary(){
        firstNode = null;
    }

    /** 
    * Adds a new entry to this dictionary. If the given search key already
    * exists in the dictionary, replaces the corresponding value.
    * @param key An object search key of the new entry.
    * @param value An object associated with the search key.
    * @return Either null if the new entry was added to the dictionary
    * or the value that was associated with key if that value
    * was replaced. 
    */
    public V add(K key, V value){
        Node newNode = new Node(new Entry<>(key, value));
        boolean contains = false;
        Node current = firstNode;
        if(isEmpty()){
            firstNode = newNode;
            return value;
        }
        else{
            while(current != null && !contains){
                if(current.data.getKey().equals(key)){
                    current.data.setValue(value);
                    contains = true;
                }
                else{
                    current = current.next;
                }
            }
    
            if(!contains){
                newNode.next = firstNode;
                firstNode = newNode;
                currentSize++;
                return value;
            }
            return null;
        }

        
    }

    /** 
     * Removes a specific entry from this dictionary.
     * @param key An object search key of the entry to be removed.
     * @return Either the value that was associated with the search key
     * or null if no such object exists. 
     */
    public V remove(K key){
        Node current = firstNode;
        boolean isFound = false;
        V result = null;
        while(current != null && !isFound){
            if(current.data.getKey().equals(key)){
                isFound = true;
                result = current.data.getValue();
            }
            else{
                current = current.next;
            }
        }

        if(isFound){

            current.data = firstNode.data;
            firstNode = firstNode.next;
        }

        return result;
        
    }

    /**
     * Retrieves from this dictionary the value associated with a given
     * search key.
     * @param key An object search key of the entry to be retrieved. 
     * @return Either the value that is associated with the search key
     * or null if no such object exists. 
     */
    public V getValue(K key){
        Node current = firstNode;
        boolean isFound = false;
        V result = null;
        while(current.next != null && !isFound){
            if(current.data.getKey().equals(key)){
                isFound = true;
                result = current.data.getValue();
            }
            else{
                current = current.next;
            }
        }

        return result;
    }

    /** 
     * Sees whether a specific entry is in this dictionary.
     * @param key An object search key of the desired entry.
     * @return True if key is associated with an entry in the dictionary. 
     */
    public boolean contains(K key){
        Node current = firstNode;
        boolean isFound = false;
        while(current != null && !isFound){
            if(current.data.getKey().equals(key)){
                isFound = true;
            }
            else{
                current = current.next;
            }
        }
        return isFound;
    }

    /** 
     * Creates an iterator that traverses all search keys in this dictionary.
     * @return An iterator that provides sequential access to the search
     * keys in the dictionary. 
     */
    public Iterator<K> getKeyIterator(){
        return new LinkedKeyIterator(firstNode);
    }

    /** 
     * Creates an iterator that traverses all values in this dictionary.
     * @return An iterator that provides sequential access to the values
     * in this dictionary. 
     */
    public Iterator<V> getValueIterator(){
        return new LinkedValueIterator(firstNode);
    }

    /** 
     * Sees whether this dictionary is empty.
     * @return True if the dictionary is empty.
     */
    public boolean isEmpty(){
        return firstNode == null;
    }

    /** 
     * Gets the size of this dictionary.
     * @return The number of entries (key-value pairs) currently
     * in the dictionary. 
     */
    public int getSize(){
        return currentSize;
    }

    /** 
     * Removes all entries from this dictionary. 
     */
    public void clear(){
        firstNode = null;
        currentSize = 0;
    }

    @SuppressWarnings("hiding")
    private class Entry<K, V>{
        private K key;
        private V value;

        private Entry(K newKey, V newValue){
            key = newKey;
            value = newValue;
            
        }
        private K getKey(){
            return key;
        }

        private V getValue(){
            return value;
        } 

        private void setValue(V dataValue){
            value = dataValue;
        } 
    }

    private class Node{
        private Entry<K, V> data;
        private Node next;

        private Node (Entry<K, V> dataPortion) {
            this(dataPortion, null);
        } 

        private Node (Entry<K, V> dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    public class LinkedKeyIterator implements Iterator<K>{
        private Node iterDict;
        private boolean cursorFirst;

        public LinkedKeyIterator(Node dictIter){
            cursorFirst = true;
            iterDict = dictIter;
        }

        @Override
        public boolean hasNext() {
            return iterDict.next != null;
        }

        @Override
        public K next() {
            if(cursorFirst){
                cursorFirst = false;
            }
            else{
                iterDict = iterDict.next;
            }
            return iterDict.data.getKey();
        }  

    }

    public class LinkedValueIterator implements Iterator<V>{
        private Node iterDict;
        private boolean cursorFirst;

        public LinkedValueIterator(Node dictIter){
            iterDict = dictIter;
            cursorFirst = true;
        }

        @Override
        public boolean hasNext() {
            return iterDict.next != null; 
        }

        @Override
        public V next() {
            if(cursorFirst){
                cursorFirst = false;
            }
            else{
                iterDict = iterDict.next;
            }
            return iterDict.data.getValue();
        }  
    }
}
