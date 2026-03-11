public interface SetInterface<T> {
    public void add(T newEntry);
    public T[] toArray();
    public T remove();
    public boolean remove(T anEntry);
    public SetInterface<T> union (SetInterface<T> rhs);
    public SetInterface<T> intersection(SetInterface<T> rhs);
}
