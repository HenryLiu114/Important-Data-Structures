import java.util.Arrays;

public class LinkedBag<T> implements BagInterface<T>{
    private Node firstNode;
    private int numberOfEntries;
    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add (T newEntry){
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        System.out.println(Arrays.toString(toArray()));
        return true;
    }
    
    @Override
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public T remove(){
        T output = null;
        if(firstNode != null){
            output = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            System.out.println(Arrays.toString(toArray()));
        }
        else{
            System.out.println("No items to remove :(");
        }
        return output;
    }

    @Override
    public boolean remove(T targetEntry){
        boolean isFound = false;
        Node currentNode = firstNode;
        while (!isFound && (currentNode != null)){
            if (targetEntry.equals(currentNode.data))
                isFound = true;
            else{
                currentNode = currentNode.next;
            } 
        }
        if (currentNode != null){
            T temp = currentNode.data;
            currentNode.data = firstNode.data; 
            firstNode.data = temp;
            System.out.println(Arrays.toString(toArray()));
            firstNode = firstNode.next;
            numberOfEntries--;
        }
        System.out.println(Arrays.toString(toArray()));
        return isFound;
    }

    @Override
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    @Override
    public int getCurrentSize(){
        return numberOfEntries;
    }

    @Override
    public int getFrequencyOf(T anEntry){
        int i = 0;
        int count = 0;
        Node current = firstNode;
        while(i < numberOfEntries && !(current.equals(null))){
            if(current.data.equals(anEntry)){
                count++;
            }
            i++;
            current = current.next; 
        }
        return count;
    }

    @Override
    public void clear(){
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean contains(T anEntry){
        boolean isFound = false;
        int i = 0;
        Node current = firstNode;
        while(i < numberOfEntries && !isFound){
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
    public boolean addEnd(T addEntry){
        Node current = firstNode;
        if(isEmpty()){
            firstNode = new Node(addEntry);
        }
        else{
            while(current.next != null){
                current = current.next;
            }
            current.next = new Node(addEntry);
        }
        numberOfEntries++;
        System.out.println(Arrays.toString(toArray()));
        return true;
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

