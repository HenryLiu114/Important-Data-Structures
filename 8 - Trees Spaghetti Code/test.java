import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        BinaryTreeInterface<String> benry = new BinaryTree<>("Benry");
        BinaryTreeInterface<String> renry = new BinaryTree<>("Renry");
        BinaryTreeInterface<String> list = new BinaryTree<>("Henry", (BinaryTree<String>)benry, (BinaryTree<String>)renry);

        Iterator<String> iterate = list.getInorderIterator();
        while(iterate.hasNext()){
                System.out.println(iterate.next());
             
        }

    }
}
