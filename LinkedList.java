public class LinkedList<T> implements ListInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Adds a new entry to the end of this list.
     * Entries currently in the list are unaffected.
     * The list's size is increased by 1.
     * 
     * @param newEntry The object to be added as a new entry.
     */
    public void add(T newEntry) {
        Node current = firstNode;
        if (isEmpty()) {
            firstNode = new Node(newEntry);
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(newEntry);
        }
        numberOfEntries++;
    }

    /**
     * Adds a new entry at a specified position within this list.
     * Entries originally at and above the specified position
     * are at the next higher position within the list.
     * The list's size is increased by 1.
     * 
     * @param newPosition An integer that specifies the desired
     *                    position of the new entry.
     * @param newEntry    The object to be added as a new entry.
     * @throws IndexOutOfBoundsException if either
     *                                   newPosition < 0 or newPosition >
     *                                   getLength().
     */
    public void add(int newPosition, T newEntry) {
        if (newPosition > numberOfEntries || newPosition < 0) {
            throw new IndexOutOfBoundsException();
        } else if (newPosition == 0) {
            firstNode = new Node(newEntry, firstNode);
            numberOfEntries++;
        } else {
            Node current = firstNode;
            for (int i = 0; i < newPosition - 1; i++) {
                current = current.next;
            }
            Node temp = current.next;
            current.next = new Node(newEntry);
            current = current.next;
            current.next = temp;
            numberOfEntries++;
        }
    }

    /**
     * Removes the entry at a given position from this list.
     * Entries originally at positions higher than the given
     * position are at the next lower position within the list,
     * and the list's size is decreased by 1.
     * 
     * @param givenPosition An integer that indicates the position of
     *                      the entry to be removed.
     * @return A reference to the removed entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 0 or givenPosition >
     *                                   getLength().
     */
    public T remove(int givenPosition) {
        Node current = firstNode;
        T temp;
        if (givenPosition > getLength() || givenPosition < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            if (givenPosition == numberOfEntries - 1) {

                for (int i = 0; i < givenPosition - 1; i++) {
                    current = current.next;
                }
                temp = current.next.data;
                current.next = null;
            } else {
                Node end = firstNode;
                for (int i = 0; i < givenPosition; i++) {
                    current = current.next;
                    end = end.next;
                }
                end = end.next;
                temp = current.data;
                current.data = end.data;
                current.next = end.next;
            }

            numberOfEntries--;
            return temp;
        }
    }

    /** Removes all entries from this list. */
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Replaces the entry at a given position in this list.
     * 
     * @param givenPosition An integer that indicates the position of
     *                      the entry to be replaced.
     * @param newEntry      The object that will replace the entry at the
     *                      position givenPosition.
     * @return The original entry that was replaced.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 0 or givenPosition >
     *                                   getLength().
     */
    public T replace(int givenPosition, T newEntry) {
        Node current = firstNode;
        if (givenPosition < 0 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < givenPosition; i++) {
            current = current.next;
        }
        T temp = current.data;
        current.data = newEntry;
        return temp;
    }

    /**
     * Retrieves the entry at a given position in this list.
     * 
     * @param givenPosition An integer that indicates the position of
     *                      the desired entry.
     * @return A reference to the indicated entry.
     * @throws IndexOutOfBoundsException if either
     *                                   givenPosition < 0 or givenPosition >
     *                                   getLength().
     */
    public T getEntry(int givenPosition) {
        Node current = firstNode;
        if (givenPosition < 0 || givenPosition > getLength()) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < givenPosition; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Retrieves all entries that are in this list in the order in which
     * they occur in the list.
     * 
     * @return A newly allocated array of all the entries in the list.
     *         If the list is empty, the returned array is empty.
     */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }

    /**
     * Sees whether this list contains a given entry.
     * 
     * @param anEntry The object that is the desired entry.
     * @return True if the list contains anEntry, or false if not.
     */
    public boolean contains(T anEntry) {
        Node current = firstNode;
        boolean isFound = false;
        while (!isFound && current != null) {
            if (current.data.equals(anEntry)) {
                isFound = true;
            } else {
                current = current.next;
            }
        }
        return isFound;
    }

    /**
     * Gets the length of this list.
     * 
     * @return The integer number of entries currently in the list.
     */
    public int getLength() {
        return numberOfEntries;
    }

    /**
     * Sees whether this list is empty.
     * 
     * @return True if the list is empty, or false if not.
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }
}
