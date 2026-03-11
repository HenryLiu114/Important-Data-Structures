import java.util.Arrays;
public class LinkedSet<T> implements SetInterface<T> {
    private Node firstNode;
    private int currentSize;

    public LinkedSet(){
        firstNode = null;
        currentSize = 0;
    }

    @Override
    public void add (T newEntry){
        if(contains(newEntry)){
            System.out.println("Item in set");
        }
        else{
            Node newNode = new Node(newEntry);
            newNode.next = firstNode;
            firstNode = newNode;
            currentSize++;
        }
        
    }
    private boolean contains(T anEntry){
        boolean isFound = false;
        int i = 0;
        Node current = firstNode;
        while(i < currentSize && !isFound){
            if(current.data.equals(anEntry)){
                isFound = true;
            }
            else{
                current = current.next;
            }
            i++;
        }
        return isFound;
    }

    @Override
    public T remove(){
        T item = null;
        if(firstNode != null){
            item = firstNode.data;
            firstNode = firstNode.next;
            currentSize--;
        }
        return item;
    }

    @Override
    public boolean remove(T anEntry){
        Node current = firstNode;
        boolean isFound = false;
        while(!isFound && (current != null)){
            if(current.data.equals(anEntry)){
                isFound = true;
            }
            else{
                current = current.next;
            }
        }
        if(isFound){
            current.data = firstNode.data;
            firstNode = firstNode.next;
            currentSize--;
        }
        return isFound;
    }

    @Override
    public T[] toArray(){
        Node current = firstNode;
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[currentSize];
        for(int i = 0; i < result.length; i++){
            result[i] = current.data;
            current = current.next;
        }
        return result;
    }

    public SetInterface<T> union (SetInterface<T> rhs){
        SetInterface<T> result = new LinkedSet<>();

        for(T item : this.toArray()){
            result.add(item);
        }

        for(T item : rhs.toArray()){
            result.add(item);
        }
        return result;
    }

    public SetInterface<T> intersection(SetInterface<T> rhs){
        SetInterface<T> result = new LinkedSet<>();
        T[] lhs = this.toArray();
        T[] rhsA = rhs.toArray();
        for(int i = 0; i < lhs.length; i++){
            for(int j = 0; j < rhsA.length; j++){
                if(lhs[i] == rhsA[j]){
                    result.add(lhs[i]);
                }
            }
        }
        return result;
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
