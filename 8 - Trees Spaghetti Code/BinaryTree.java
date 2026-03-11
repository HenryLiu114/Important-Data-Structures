import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTree<T> implements BinaryTreeInterface<T>{

    private BinaryNode<T> root;
    public BinaryTree(){
        root = null;
    }
    public BinaryTree(T rootData){
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        root = new BinaryNode<>(rootData);
        if(leftTree != null){
            root.leftChild = leftTree.getRootNode();
        }
        if(rightTree != null){
            root.rightChild = rightTree.getRootNode();
        }
    }

    @Override
    public T getRootData() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        else
            return root.getData();
    }

    @Override
    public int getHeight() {
        int height = 0;
        if (root != null)
            height = root.getHeight();
        return height;
    }

    @Override
    public int getNumberOfNodes() {
        int numberOfNodes = 0;
        if (root != null)
            numberOfNodes = root.getNumberOfNodes();
        return numberOfNodes;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator<T>();
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostorderIterator'");
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator<T>();
    }

    @Override
    public Iterator<T> getLevelOrderIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLevelOrderIterator'");
    }

    @Override
    public void setRootData(T rootData) {
        root.data = rootData;
    }

    private void setRootNode(BinaryNode<T> rootNode){
        root = rootNode;
    }

    private BinaryNode<T> getRootNode(){
        return root;
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        root.data = rootData; 
        root.leftChild = (((BinaryTree<T>)leftTree).getRootNode());
        root.setRightChild(((BinaryTree<T>)rightTree).getRootNode());
    }

    private void initializeTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        root = new BinaryNode<>(rootData);
        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);
        if ((rightTree != null) && !rightTree.isEmpty()){
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        } // end if
        if ((leftTree != null) && (leftTree != this))
            leftTree.clear();
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
    }

    //Binray Node Sub-Class
    private class BinaryNode<T>{
        private T data;
        private BinaryNode<T> leftChild; 
        private BinaryNode<T> rightChild; 
        
        //Constructors
        public BinaryNode(){
            this(null);
        }
        
        public BinaryNode(T dataPortion){
            this(dataPortion, null, null);
        }

        public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild){
            data = dataPortion;
            leftChild = newLeftChild;
            rightChild = newRightChild;
        }

        //Methods
        public T getData() {
            return data;
        } 

        public void setData(T newData) {
            data = newData;
        } 

        public BinaryNode<T> getLeftChild(){
            return leftChild;
        }
    
        public void setLeftChild(BinaryNode<T> newLeftChild){
            leftChild = newLeftChild;
        }

        public BinaryNode<T> getRightChild(){
            return rightChild;
        }
    
        public void setRightChild(BinaryNode<T> newRightChild){
            leftChild = newRightChild;
        }

        public boolean hasLeftChild(){
            return leftChild != null;
        }

        public boolean isLeaf(){
            return (leftChild == null) && (rightChild == null);
        } 

        public int getNumberOfNodes(){
            throw new UnsupportedOperationException("Unimplemented method 'getRootData'");
        } // end getNumberOfNodes
       
        public int getHeight(){
            return getHeight(this); 
        }

        public BinaryNode<T> copy(){
            BinaryNode<T> newRoot = new BinaryNode<>(data);
            if (leftChild != null)
                newRoot.setLeftChild(leftChild.copy());
            if (root.rightChild != null)
                newRoot.setRightChild(rightChild.copy());
            return newRoot;
        }
        private int getHeight(BinaryNode<T> node){
            int height = 0;
            if (node != null)
                height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
            return height;
        }
    }

    private class InorderIterator<T> implements Iterator<T>{
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        @SuppressWarnings("unchecked")
        private InorderIterator(){
            nodeStack = new LinkedStack<>();
            currentNode = (BinaryNode<T>) root;
        }
        
        public boolean hasNext(){
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            while (currentNode != null){
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            if (!nodeStack.isEmpty()){
                nextNode = nodeStack.pop();
                currentNode = nextNode.getRightChild();
            }
            else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }

    private class PreorderIterator<T> implements Iterator<T>{
        private StackInterface<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        @SuppressWarnings("unchecked")
        private PreorderIterator(){
            nodeStack = new LinkedStack<>();
            currentNode = (BinaryNode<T>) root;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || (currentNode != null);
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode = null;
            if(currentNode != null){
                nextNode = currentNode;
                if(currentNode.hasLeftChild()){
                    nodeStack.push(nextNode);
                    currentNode = currentNode.leftChild;
                }
                else{
                    nextNode = currentNode;
                    currentNode = nodeStack.pop();
                    currentNode = currentNode.rightChild;
                    nodeStack.push(currentNode);
                }
                return nextNode.data;
            }
            else{
                nodeStack.clear();
            }
            return null;
        }
        
    }
}
