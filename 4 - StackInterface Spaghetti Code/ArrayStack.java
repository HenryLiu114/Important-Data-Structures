import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack;
    private final int PRODUCT_SIZE = 5;
    private int currentSize;

    public ArrayStack(){
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Object[PRODUCT_SIZE];
        stack = temp;
        currentSize = 0;
    }

    @Override
    public void push(T newEntry){
        if(currentSize >= stack.length){
            System.out.println("Increased!!");
            resize();
        }
        stack[currentSize] = newEntry;
        currentSize++;
    }

    @Override
    public T pop(){
        T temp;
        if(isEmpty()){
            throw new NoSuchElementException("No Objects currently in the Stack!!! :(((");
        }
        else{
            temp = stack[currentSize-1];
            stack[currentSize-1] = null;
            currentSize--;
        }
        return temp;
    }

    @Override
    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException("No Objects currently in the Stack!!! :(((");
        }
        else{
            T temp = stack[currentSize-1];
            return temp;
        }
    }

    @Override
    public boolean isEmpty(){
        return currentSize == 0;
    }

    @Override
    public void clear(){
        while(!(isEmpty())){
            pop();
        }
    }

    private void resize(){
        stack = Arrays.copyOf(stack, currentSize + PRODUCT_SIZE);
    }

}
