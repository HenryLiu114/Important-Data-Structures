public interface BagInterface<T> {
    public boolean add(T newEntry);
    public T remove();
    public boolean remove(T anEntry);
    public T[] toArray();
    public boolean isEmpty();
    public int getCurrentSize();
    public int getFrequencyOf(T anEntry);
    public void clear();
    public boolean contains(T anEntry);
    public boolean addEnd(T addEntry);
    //public boolean addToLoc(T addEntry, int spaces);
   // public boolean noSwitchRemove(T anEntry);
}
