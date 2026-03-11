import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vertex<T> implements VertexInterface<T>{
    private T label;
    private ListInterface<Edge> edgeArray;
    private boolean visited; // True if visited
    private VertexInterface<T> previousVertex; // On path to this vertex
    private double cost; // Of path to this vertex
    
    public Vertex(T vertexLabel){
        label = vertexLabel;
        edgeArray = new ArrayList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    @Override
    public T getLabel() {
        return label;
    }



    @Override
    public void visit() {
        visited = true;
    }



    @Override
    public void unvisit() {
        visited = false;
    }



    @Override
    public boolean isVisited() {
        return visited;
    }



    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        if (!this.equals(endVertex)){
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()){
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            }
            if (!duplicateEdge){
                edgeArray.add(new Edge(endVertex, edgeWeight));
                result = true;
            }
        }
        return result;
    }



    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }



    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }



    @Override
    public Iterator<Double> getWeightIterator() {
        throw new UnsupportedOperationException("Unimplemented method 'getWeightIterator'");
    }



    @Override
    public boolean hasNeighbor() {
        return !edgeArray.isEmpty();
    }



    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) ){
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } 
        return result;
    }



    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        previousVertex = predecessor;
    }



    @Override
    public VertexInterface<T> getPredecessor() {
        return previousVertex;
    }



    @Override
    public boolean hasPredecessor() {
        return previousVertex != null;
    }



    @Override
    public void setCost(double newCost) {
        cost = newCost;
    }



    @Override
    public double getCost() {
        return cost;
    }

    protected class Edge{
        private VertexInterface<T> vertex; 
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight){
            vertex = endVertex;
            weight = edgeWeight;
        }

        protected Edge(VertexInterface<T> endVertex){
            vertex = endVertex;
            weight = 0;
        }

        protected VertexInterface<T> getEndVertex(){
            return vertex;
        }

        protected double getWeight(){
            return weight;
        }
    }

    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        private Iterator<Edge> edges;
        private NeighborIterator(){
            edges = edgeArray.ArrayListIterator();
        } 
        
        public boolean hasNext(){
            return edges.hasNext();
        } 
        
        public VertexInterface<T> next(){
            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()){
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();
            return nextNeighbor;
        }
    }

    private interface ListInterface<T>{
        /** Adds a new entry to the end of this list.
        * Entries currently in the list are unaffected.
        * The list's size is increased by 1.
        * @param newEntry The object to be added as a new entry. 
        */
        public void add(T newEntry);

        /** Adds a new entry at a specified position within this list.
        * Entries originally at and above the specified position
        * are at the next higher position within the list.
        * The list's size is increased by 1.
        * @param newPosition An integer that specifies the desired
        * position of the new entry.
        * @param newEntry The object to be added as a new entry.
        * @throws IndexOutOfBoundsException if either
        * newPosition < 1 or newPosition > getLength() + 1. 
        */
        public void add(int newPosition, T newEntry);

        /** Removes the entry at a given position from this list.
        * Entries originally at positions higher than the given
        * position are at the next lower position within the list,
        * and the list's size is decreased by 1.
        * @param givenPosition An integer that indicates the position of
        * the entry to be removed.
        * @return A reference to the removed entry.
        * @throws IndexOutOfBoundsException if either
        * givenPosition < 1 or givenPosition > getLength().
        */
        public T remove(int givenPosition);

        /** Removes all entries from this list. */
        public void clear();

        /** Replaces the entry at a given position in this list.
        * @param givenPosition An integer that indicates the position of
        * the entry to be replaced.
        * @param newEntry The object that will replace the entry at the
        * position givenPosition.
        * @return The original entry that was replaced.
        * @throws IndexOutOfBoundsException if either
        * givenPosition < 1 or givenPosition > getLength(). 
        */
        public T replace(int givenPosition, T newEntry);

        /** Retrieves the entry at a given position in this list.
        * @param givenPosition An integer that indicates the position of
        * the desired entry.
        * @return A reference to the indicated entry.
        * @throws IndexOutOfBoundsException if either
        * givenPosition < 1 or givenPosition > getLength().
        */
        public T getEntry(int givenPosition);

        /** Retrieves all entries that are in this list in the order in which
        * they occur in the list.
        * @return A newly allocated array of all the entries in the list.
        * If the list is empty, the returned array is empty. 
        */
        public T[] toArray();

        /** Sees whether this list contains a given entry.
         * @param anEntry The object that is the desired entry.
         * @return True if the list contains anEntry, or false if not. 
         */
        public boolean contains(T anEntry);

        /** Gets the length of this list.
         * @return The integer number of entries currently in the list.
         */
        public int getLength();

        /** Sees whether this list is empty.
         * @return True if the list is empty, or false if not.
         */
        public boolean isEmpty();

        public Iterator<T> ArrayListIterator();
    }

    private class ArrayList<T> implements ListInterface<T>{
        private T[] list;
        private int currentSize;
        private int defaultSize = 3;

        public ArrayList(){
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[defaultSize];
            list = temp;
            currentSize = 0;
        }  

        /** Adds a new entry to the end of this list.
        * Entries currently in the list are unaffected.
        * The list's size is increased by 1.
        * @param newEntry The object to be added as a new entry. 
        */
        public void add(T newEntry){
            if(currentSize >= list.length){
                list = Arrays.copyOf(list, currentSize+defaultSize);
                System.out.println("added more slots");
            }
        
            list[currentSize] = newEntry;
            currentSize++; 
        }

        /** Adds a new entry at a specified position within this list.
        * Entries originally at and above the specified position
        * are at the next higher position within the list.
        * The list's size is increased by 1.
        * @param newPosition An integer that specifies the desired
        * position of the new entry.
        * @param newEntry The object to be added as a new entry.
        * @throws IndexOutOfBoundsException if either
        * newPosition < 1 or newPosition > getLength() + 1. 
        */
        public void add(int newPosition, T newEntry){
            if((newPosition > (currentSize+1)) && newPosition < 1){
                throw new IndexOutOfBoundsException();
            }
            else{
                currentSize++;
                T firstEntry = null;
                for(int i = newPosition-1; i < currentSize; i++){
                    if(currentSize >= list.length){
                        resize();
                    }
                    T temp = list[i];
                    if(i == newPosition-1){
                        list[i] = newEntry;
                        firstEntry = temp;
                    }
                    else{
                        list[i]=firstEntry;
                        firstEntry = temp;
                    }
                }
            }
        }

        private void resize(){
            list = Arrays.copyOf(list, currentSize+defaultSize);
            System.out.println("added more slots");
        }
        /** Removes the entry at a given position from this list.
        * Entries originally at positions higher than the given
        * position are at the next lower position within the list,
        * and the list's size is decreased by 1.
        * @param givenPosition An integer that indicates the position of
        * the entry to be removed.
        * @return A reference to the removed entry.
        * @throws IndexOutOfBoundsException if either
        * givenPosition < 1 or givenPosition > getLength().
        */
        public T remove(int givenPosition){
            if((givenPosition > (currentSize+1)) && givenPosition < 1){
                throw new IndexOutOfBoundsException();
            }
            else{
                T temp = list[givenPosition-1];
                list[givenPosition-1] = null;
                currentSize--;
                for(int i = givenPosition-1; i < currentSize; i++){
                    list[i] = list[i+1];
                }
                return temp;
            }
        }

        /** Removes all entries from this list. */
        public void clear(){
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[defaultSize];
            list = temp;
            currentSize = 0;
        }

        /** Replaces the entry at a given position in this list.
         * @param givenPosition An integer that indicates the position of
         * the entry to be replaced.
         * @param newEntry The object that will replace the entry at the
         * position givenPosition.
         * @return The original entry that was replaced.
         * @throws IndexOutOfBoundsException if either
         * givenPosition < 1 or givenPosition > getLength(). 
         */
        public T replace(int givenPosition, T newEntry){
            if((givenPosition > (currentSize+1)) && givenPosition < 1){
                throw new IndexOutOfBoundsException();
            }
            else{
                T temp = list[givenPosition-1];
                list[givenPosition-1] = newEntry;
                return temp;
            }
        }

        /** Retrieves the entry at a given position in this list.
         * @param givenPosition An integer that indicates the position of
         * the desired entry.
         * @return A reference to the indicated entry.
         * @throws IndexOutOfBoundsException if either
         * givenPosition < 1 or givenPosition > getLength().
         */
        public T getEntry(int givenPosition){
            if((givenPosition > (currentSize+1)) && givenPosition < 1){
                throw new IndexOutOfBoundsException();
            }
            else{
                return list[givenPosition-1];
            }
            }

        /** Retrieves all entries that are in this list in the order in which
         * they occur in the list.
         * @return A newly allocated array of all the entries in the list.
         * If the list is empty, the returned array is empty. 
         */
        public T[] toArray(){
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[currentSize];

            for(int i = 0; i < temp.length; i++){
                temp[i] = list[i];
            }
            return temp;
        }

        /** Sees whether this list contains a given entry.
         * @param anEntry The object that is the desired entry.
         * @return True if the list contains anEntry, or false if not. 
         */
        public boolean contains(T anEntry){
            boolean isFound = false;
            int i = 0;
            while(!isFound && i < currentSize){
                if(list[i].equals(anEntry)){
                    isFound = true;
                }
            }
            return isFound;
        }

        /** Gets the length of this list.
         * @return The integer number of entries currently in the list.
         */
        public int getLength(){
            return currentSize;
        }

        /** Sees whether this list is empty.
         * @return True if the list is empty, or false if not.
         */
        public boolean isEmpty(){
            return currentSize==0;
        }

        @Override
        public Iterator<T> ArrayListIterator() {
            return new ListIterator<>(list);
        }

        private class ListIterator<T> implements Iterator<T>{
            private T[] list;
            private int index;

            private ListIterator(T[] array){
                list = array;
                index = -1;
            }

            @Override
            public boolean hasNext() {
                try{
                    list[index+1] = list[index+1];
                }
                catch(ArrayIndexOutOfBoundsException e){
                    return false;
                }
                return true;
            }

            @Override
            public T next() {
                index++;
                return list[index];
            }
            
        }
    }
}