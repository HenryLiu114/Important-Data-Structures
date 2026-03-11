//
// Name: Liu, Henry
// Homework: #2
// Due: 9/25/2024
// Course: cs-2400—0x-f24
//
// Description:
// Implements the BagInterface to create a resizeable bag that stores objects.
//

import java.util.Arrays;

public final class ArrayBag<T> implements BagInterface<T> {
    private T[] inv;
    private int currentInvAmt;
    private static final int DEFAULT_BAG_SIZE = 25;
    private boolean integrityOK = false;

    /**
     * Creates a new ArrayBag Object with a default fixed size of 25.
     */
    public ArrayBag(){
        this(DEFAULT_BAG_SIZE);
    }

    /**
     * Creates a new ArrayBag Object with a fixed size
     * @param sizeAmount the max capacity of the ArrayBag in the form of an int.
     */
    public ArrayBag(int sizeAmount){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[(int) Math.abs(sizeAmount)];
        inv = temp;
        currentInvAmt = 0;
        integrityOK = true;
    }

    /**
     * Gets the current amount of objects in the bag
     * @return the current amount of objects in the bag
     */ 
    @Override
    public int getCurrentSize(){
        return currentInvAmt;
    }

     /**
     * Adds a object to the bag.
     * @param newEntry the object added to the bag
     * @return True if the object was successfully added to the bag, false otherwise.
     */
    @Override
    public boolean add(T newEntry){
        checkIntegrity();
        boolean result = true;
        if(currentInvAmt >= inv.length){
            System.out.println("Max Length of " + inv.length + " Reached!!! Resizing ArrayBag...");
            doubleCapacity();
        }
        inv[currentInvAmt] = newEntry;
        currentInvAmt++;
        return result;
    }

    /**
     * Attempts to remove the most recent item from the bag
     * @return The most recent object added to the bag or null, if no objects are detected.
     */
    @Override
    public T remove(){
        checkIntegrity();
        if(currentInvAmt > 0){
            T result = inv[currentInvAmt-1]; 
            inv[currentInvAmt-1] = null; 
            currentInvAmt--;
            return result;
        }
        return null;
    }

    /** 
     * Attempts to remove an instance of a selected entry from the bag
     * @param selectedEntry Attempted entry to remove from the bag.
     * @return True if the removal was successful, or false if not. 
     */
    @Override
    public boolean remove(T selectedEntry){
        checkIntegrity();
        boolean result = false;
        int index = findObject(selectedEntry);
        if(index != -1){
            result = true;
            organize(index);
        }
        return result;
    }

    /**
     * Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in the bag.
     *         Note: If the bag is empty, the returned array is empty.
     */
    @Override
    public T[] toArray(){
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[currentInvAmt];
        for(int i = 0; i < result.length; i++){
            result[i] = inv[i];
        }
        return result;
    }

    /**
     * Checks if the bag is empty
     * @return True if the bag has no obects in it, false otherwise.
     */
    @Override
    public boolean isEmpty(){
        checkIntegrity();
        return currentInvAmt == 0;
    }

    /** 
     * Removes all entries from the bag
     */
    @Override
    public void clear(){
        checkIntegrity();
        currentInvAmt = 0;
        while (!isEmpty())
            remove();  
    }

    /**
     * Finds how many times a object appears in the bag.
     * @param selectedEntry The targeted object
     * @return the number of times an entry appears
     */
    @Override
    public int getFrequencyOf(T selectedEntry){
        checkIntegrity();
        int count = 0;
        for(int i = 0; i < inv.length; i++){
            if(selectedEntry.equals(inv[i])){
                count++;
            }
        }
        return count;
    }

    /**
     * Check whether or not a object exists in the bag
     * @param selectedEntry the targeted object
     * @return True if the object exists in the bag, false otherwise.
     */
    @Override
    public boolean contains(T selectedEntry){
        checkIntegrity();
        if(findObject(selectedEntry) != -1){
            return true;
        }
        return false;
    }

    /**
     * Attempts to find a object in a bag
     * @param input The target entry to search for
     * @return the first instance of the object, -1 if not found.
     */
    private int findObject(T input){
        boolean resultFound = false;
        int i = 0;
        int index = -1;

        while(currentInvAmt != 0 && !resultFound && i < inv.length){
            if(input.equals(inv[i])){
                resultFound = true;
                index = i;
            }
            else{
                i++;
            }    
        }
        return index;
    }

    /**
     * Organizes the bag when an Object is removed
     * @param objectIndex The index where the removal starts
     */
    private void organize(int objectIndex){
        for(int i = objectIndex; i < inv.length - 1; i++){
            inv[i] = inv[i+1];
        }
        remove();
    }
    
    /**
     * Doubles the capacity of the arrayBag
     */
    private void doubleCapacity(){
        int newInv = inv.length * 2;
        inv = Arrays.copyOf(inv, newInv);;
    }

    private void checkIntegrity(){
    if (!integrityOK)
        throw new SecurityException("ArrayBag object is corrupt.");
    }
}

