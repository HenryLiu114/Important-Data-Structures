import java.util.Arrays;
import java.util.Iterator;

public class HashedDictionary<K, V> implements DictionaryInterface<K, V> {

    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_CAPACITY = 10000;

    private Entry<K, V>[] hashTable;
    private int tableSize;
    private static final int MAX_SIZE = 2 * MAX_CAPACITY;
    private static final double MAX_LOAD_FACTOR = 0.5;
    private final Entry<K, V> AVAILABLE = new Entry<>(null, null);

    public HashedDictionary(){
        this(DEFAULT_CAPACITY);
    }

    public HashedDictionary(int initialCapacity){
        initialCapacity = checkCapacity(initialCapacity);
        numberOfEntries = 0;
        tableSize = getNextPrime(initialCapacity);
        checkSize(tableSize);
        @SuppressWarnings("unchecked")
        Entry<K, V>[] temp = (Entry<K, V>[])new Entry[tableSize];
        hashTable = temp;
    }

    private int checkCapacity(int value){
        if(value > MAX_CAPACITY){
            return MAX_CAPACITY;
        }
        else{
            return value;
        }
    }

    private void checkSize(int value){
        if(value > MAX_SIZE){
            throw new IllegalArgumentException();
        }
    }

    private int getNextPrime(int value){
        if(value%2 == 0){
            value++;
        }

        int result = value;
        
        switch(value){
            case 1:
                result = 2;
                break;
            case 2:
                result = 3;
                break;
            case 3:
                result = 5;
                break;
            case 5:
                result = 7;
                break;
            case 7:
                result = 11;
                break;
            default:
                boolean isPrime = false;
                while(!isPrime){
                    value = value + 2;
                    if(value%3 != 0 && value%5 != 0 && value%7 != 0){
                        result = value;
                        isPrime = true;
                    }
                }
                break;
        }
        return result;
    }

    @Override
    public V add(K key, V value) {
        V oldValue;
        if ((key == null) || (value == null))
            throw new NullPointerException();
        int index = getHashIndex(key);
        if (key != (hashTable[index])){
            hashTable[index] = new Entry<>(key, value);
            numberOfEntries++;
            oldValue = null;
        }
        else {
            oldValue = hashTable[index].getValue();
            hashTable[index].value = value;
        }

        if (numberOfEntries >= hashTable.length)
            enlargeHashTable();
        
        System.out.println(Arrays.toString(hashTable));

        return oldValue;
    }

    private void enlargeHashTable(){
        Entry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = getNextPrime(oldSize + oldSize);
        checkSize(newSize);
        @SuppressWarnings("unchecked")
        Entry<K, V>[] temp = (Entry<K, V>[])new Entry[newSize]; 
        hashTable = temp;
        numberOfEntries = 0; 
        for (int index = 0; index < oldSize; index++){
                if ( (oldTable[index] != null) && oldTable[index] != AVAILABLE )
                    add(oldTable[index].getKey(), oldTable[index].getValue());
        }
    }

    

    @Override
    public V remove(K key) {
        V removedValue = null;
        int index = getHashIndex(key);
            if (key == hashTable[index]) {
                removedValue = hashTable[index].value;
                hashTable[index] = null;
                numberOfEntries--;
            }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
        V result = null;
        int index = getHashIndex(key);
        if ((hashTable[index] != null) && (hashTable[index] != AVAILABLE)){
            result = hashTable[index].value;
        }

        return result;
    }

    private int getHashIndex(K key){
        int hashIndex = key.hashCode() % hashTable.length;
        if (hashIndex < 0)
            hashIndex = hashIndex + hashTable.length;
        return hashIndex;
    }

    @Override
    public boolean contains(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator<K> getKeyIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKeyIterator'");
    }

    @Override
    public Iterator<V> getValueIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getValueIterator'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
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

        @SuppressWarnings("unused")
        private void setValue(V dataValue){
            value = dataValue;
        } 
    }
}
