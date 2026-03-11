import java.util.Iterator;
import java.util.NoSuchElementException;
public class HashedDictionary<K, V> implements DictionaryInterface<K, V>{
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5; // Must be prime
    private static final int MAX_CAPACITY = 10000;

    private Entry<K, V>[] hashTable;
    private int tableSize;                        // Must be prime
    private static final int MAX_SIZE = 2 * MAX_CAPACITY;
    private static final double MAX_LOAD_FACTOR = 0.5;
    private final Entry<K, V> AVAILABLE = new Entry<>(null, null);
    
    public HashedDictionary(){
        this(DEFAULT_CAPACITY);
    }
    
    public HashedDictionary(int initialCapacity){
        numberOfEntries = 0;
        checkSize(initialCapacity);
        tableSize = nextPrime(initialCapacity);
        @SuppressWarnings("unchecked")
        Entry<K, V>[] temp = (Entry<K, V>[])new Entry[tableSize];
        hashTable = temp;
    }

    @Override
    public V add(K key, V value) {
        if ((key == null) || (value == null)){
            throw new NullPointerException("Cannot Pass an Null Key or Value");
        }
        int index = getHashIndex(key);
        V oldValue = null;

        int i = 0;
        boolean hasFoundKey = false;
        while(!hasFoundKey && i < tableSize){
            if(hashTable[i] != null && hashTable[i].key.equals(key)){
                hasFoundKey = true;
            }
            i++;
        }

        if (hasFoundKey){
            hashTable[index] = new Entry<>(key, value);
            numberOfEntries++;
        }
        else {
            oldValue = hashTable[index].getValue();
            hashTable[index].setValue(value);
        }

        if (index > tableSize){
            enlargeHashTable();
        }
        return oldValue;
    }

    private void enlargeHashTable(){
        Entry<K, V>[] oldTable = hashTable;
        int oldSize = hashTable.length;
        int newSize = nextPrime(oldSize + oldSize);
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

    private void checkSize(int num){
        if(num > MAX_CAPACITY){
            throw new IllegalArgumentException("TOO BIG");
        }
    }

    @Override
    public V remove(K key) {
        V removedValue = null;
        int index = getHashIndex(key);
        if (key == hashTable[index]){
            removedValue = hashTable[index].getValue();
            hashTable[index] = AVAILABLE;
            numberOfEntries--;
        }
        return removedValue;
    }

    @Override
    public V getValue(K key) {
        V result = null;
        int index = getHashIndex(key);
        if ((hashTable[index] != null) && (hashTable[index] != AVAILABLE)){
            result = hashTable[index].getValue();
        }
        return result;
    }

    @Override
    public boolean contains(K key) {
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    @Override
    public Iterator<K> getKeyIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'getKeyIterator'");
    }

    @Override
    public Iterator<V> getValueIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'getValueIterator'");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }

    @Override
    public int getSize() {
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public void clear() {
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

    //Stole from W3Schools lololollolololol
    private static boolean isPrime(int n) { 
        if (n <= 1){
            return false; 
        } 
        if (n <= 3){
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) return false; 
        for (int i = 5; i * i <= n; i = i + 6){ 
            if (n % i == 0 || n % (i + 2) == 0){
                return false; 
            }
        }
         
        return true; 
    } 
     
    private static int nextPrime(int N) { 
        if (N <= 1) 
            return 2; 
        int prime = N; 
        boolean found = false; 
        while (!found) 
        { 
            prime++; 
            if (isPrime(prime)) 
                found = true; 
        } 
        return prime; 
    }

  private int getHashIndex(K key){
    int hashIndex = key.hashCode() % hashTable.length;
    if (hashIndex < 0){
        hashIndex = hashIndex + hashTable.length;
    }
    return hashIndex;
    }
}

