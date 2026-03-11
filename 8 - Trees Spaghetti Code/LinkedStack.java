import java.util.NoSuchElementException;;
public class LinkedStack<T> implements StackInterface<T> {
    private Node firstNode;
    private int currentSize;

    public LinkedStack(){
        firstNode = null;
        currentSize = 0;
    }

    public void push(T newEntry){
        if(isEmpty()){
            firstNode = new Node(newEntry);
        }
        else{
            firstNode = new Node(newEntry, firstNode);
        }
        currentSize++;
    }

    public T pop(){
        T temp = null;
        if(isEmpty()){
            throw new NoSuchElementException("I dont think theres anything in here buddy");
        }
        else{
            temp = firstNode.data;
            firstNode = firstNode.next;
            currentSize--;
            return temp;
        }
    }

    public T peek(){
        T temp = null;
        if(isEmpty()){
            throw new NoSuchElementException("I dont think theres anything in here buddy");
        }
        else{
            temp = firstNode.data;
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
