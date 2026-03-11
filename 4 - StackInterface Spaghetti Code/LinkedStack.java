import java.util.NoSuchElementException;;
public class LinkedStack<T> implements StackInterface<T> {
    private Node firstNode;
    private int currentSize;

    public LinkedStack(){
        firstNode = null;
        currentSize = 0;
    }

    public void push(T newEntry){
        Node current = firstNode;
        if(isEmpty()){
            firstNode = new Node(newEntry);
        }
        else{
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(newEntry);
        }
        currentSize++;
    }

    public T pop(){
        T temp = null;
        Node current = firstNode;
        if(isEmpty()){
            throw new NoSuchElementException("I dont think theres anything in here buddy");
        }
        else{
            for(int i = 1; i < currentSize-1; i++){
                current = current.next;
            }
            temp = current.next.data;
            current.next = null;
            currentSize--;
            return temp;
        }
    }

    public T peek(){
        T temp = null;
        Node current = firstNode;
        if(isEmpty()){
            throw new NoSuchElementException("I dont think theres anything in here buddy");
        }
        else{
            while(current.next != null){
                current = current.next;
            }
            temp = current.data;
            return temp;
        }
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void clear(){
        firstNode = null;
        currentSize = 0;
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
