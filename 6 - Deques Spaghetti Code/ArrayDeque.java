import java.util.Arrays;

public class ArrayDeque<T> implements DequeInterface<T>{
    private T[] deque;
    private int front;
    private int back;
    private final int DEFAULT_SIZE = 10;
    private final int EXTRA_RESIZE_SLOTS = 5;

    public ArrayDeque(){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[DEFAULT_SIZE];
        deque = temp;
        front = 0;
        back = 0;
    }

    public ArrayDeque(int setSize){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[setSize];
        deque = temp;
        front = 0;
        back = 0;
    }

    @Override
    public void addToFront(T newEntry) {
        if(isEmpty()){
            deque[front] = newEntry;
            back++;
        }
        else{
            if((deque.length + front) != back+1 && front != back){
                front--;
                if(front < 0){
                    deque[deque.length+front] = newEntry;
                }
                else{
                    deque[front] = newEntry;
                }
            }
            else{
                System.out.println("detected");
                @SuppressWarnings("unchecked")
                T[] temp = (T[]) new Object[deque.length + EXTRA_RESIZE_SLOTS];
                int i = 1;
                temp[0] = newEntry;
                while(front != back){
                    if(front == deque.length){
                        front = 0;
                    }

                    if(front < 0){
                        temp[i] = deque[deque.length + front];
                    }
                    else{
                        temp[i] = deque[front];
                    }
                    i++;
                    front++;
                }
                temp[i] = deque[back];
                front = 0;
                back = i-1;
                deque = temp;
                
            }
        }
        System.out.println(Arrays.toString(deque));
    }

    @Override
    public void addToBack(T newEntry) {
        if(isEmpty()){
            deque[back] = newEntry;
        }
        else{
            back++;
            if(back != (deque.length + front)){
                if(back >= deque.length){
                    deque[back-deque.length] = newEntry;
                }
                else{
                    deque[back] = newEntry;
                }
            }
            else{
                System.out.println("Detected");
                @SuppressWarnings("unchecked")
                T[] temp = (T[]) new Object[deque.length + EXTRA_RESIZE_SLOTS];
                int i = 0;
                while(front != back){
                    if(front == deque.length){
                        front = 0;
                    }

                    if(front < 0){
                        temp[i] = deque[deque.length + front];
                    }
                    else{
                        temp[i] = deque[front];
                    }
                    i++;
                    front++;
                }
                temp[i] = newEntry;
                front = 0;
                back = i;
                deque = temp;
            }
        }
        System.out.println(Arrays.toString(deque));
        System.out.println("Back: " + back);
        System.out.println("Front: " + front);
    }

    @Override
    public T removeFront() {
        T result;
        if(front < 0){
            result = deque[deque.length+front];
            deque[deque.length+front] = null;
            front++;
        }
        else{
            result = deque[front];
            deque[front] = null;
            front++;
        }
        System.out.println(Arrays.toString(deque));
        return result;
    }

    @Override
    public T removeBack() {
        T result;
        if(back >= deque.length){
            result = deque[back-deque.length];
            deque[back-deque.length] = null;
            back--;
        }
        else{
            result = deque[back];
            deque[back] = null;
            back--;
        }
        System.out.println(Arrays.toString(deque));
        return result;
    }

    @Override
    public T getFront() {
        if(front < 0){
            return deque[deque.length+front];
        }
        else{
            return deque[front];
        }
    }

    @Override
    public T getBack() {
        if(back >= deque.length){
            return deque[back-deque.length];
        }
        else{
            return deque[back];
        }
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < deque.length; i++){
            if(deque[i] != null){
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        front = 0;
        back = 0;
        for(T i : deque){
            i = null;
        }
    }
    
}
