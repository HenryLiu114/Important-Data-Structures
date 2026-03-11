public interface MinHeapInterface<T> {
   public void add(T newEntry);
   public T removeMin();
   public T getMin();
   public boolean isEmpty();
   public int getSize();
   public void clear();
}
