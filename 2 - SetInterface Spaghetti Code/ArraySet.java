
public class ArraySet<T> implements SetInterface<T> {
    private T[] argSet;
    private int currentSize;
    private int MAX_SIZE = 2;

    public ArraySet(){
        @SuppressWarnings("unchecked")
        T[] inv = (T[]) new Object[MAX_SIZE];
        argSet = inv;
        currentSize = 0;
    }

    @Override
    public void add(T newEntry){
        if(currentSize >= MAX_SIZE){
            if(this.contains(newEntry)){
                System.out.println("Item already in");
            }
            else{
                this.resize();
                argSet[currentSize] = newEntry;
                currentSize++;   
            }
        }
        else{
            if(this.contains(newEntry)){
                System.out.println("Item already in");
            }
            else{
            argSet[currentSize] = newEntry;
            currentSize++;
            }
        } 
    }
    @Override
    public T remove(){
        T temp = argSet[currentSize-1];
        argSet[currentSize - 1] = null;
        currentSize--;
        return temp;
    }

    @Override
    public boolean remove(T anEntry){
        boolean isFound = false;
        int index = 0;
        while(!isFound && index < currentSize){
            if(argSet[index].equals(anEntry)){
                isFound = true;
            }
            else{
                index++;
            }
        }
        if(isFound){
            for(int i = index; i < argSet.length-1; i++){
                argSet[i] = argSet[i+1];
                argSet[i+1] = null;
            }
            currentSize--;
        }
        return isFound;
    }

    private void resize(){
        MAX_SIZE += 2;
        @SuppressWarnings("unchecked")
        T[] newArgSet = (T[]) new Object[MAX_SIZE];
        for(int i= 0; i < argSet.length; i++){
            newArgSet[i] = argSet[i];
        }
        argSet = newArgSet;        
    }

    private boolean contains(T anEntry){
        int i = 0;
        boolean isFound = false;
        while(!isFound && i < argSet.length){
            if(anEntry.equals(argSet[i])){
                isFound = true;
            }
            else{
                i++;
            }
        }
        return isFound;
    }

    @Override
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[currentSize];
        for(int i = 0; i < currentSize; i++){
            result[i] = argSet[i];
        }
        return result;
    }

    public SetInterface<T> union(SetInterface<T> rhs){
        SetInterface<T> result = new ArraySet<>();
        for(T item : argSet){
            result.add(item);
        }
        for(T item : rhs.toArray()){
            result.add(item); 
        }
        return result;
    }

    public SetInterface<T> intersection(SetInterface<T> rhs){
        SetInterface<T> result = new ArraySet<>();
        T[] rhsA = rhs.toArray();
        for(int i = 0; i < currentSize; i++){
            for(int j = 0; j < rhsA.length; j++){
                if(argSet[i].equals(rhsA[j])){
                    result.add(argSet[i]);
                }
            }
        }
        return result;
    }
}
