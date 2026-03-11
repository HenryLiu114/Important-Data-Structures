import java.util.Arrays;
import java.util.Iterator;

public class test{
    public static void main(String[] args) {
        ListInterface<String> list = new ArrayList<>();
        list.add("Henry");
        list.add("Benry");
        list.add("Cenry");
        list.add("Aenry");
        Iterator<String> l = list.getIterator();
        while(l.hasNext()){
            System.out.println(l.next());
        }
    }
}