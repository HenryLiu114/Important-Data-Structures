import java.util.Iterator;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        DictionaryInterface<Integer, String> henry = new LinkedDictionary<>();
        System.out.println(henry.add(1, "Henry"));
        System.out.println(henry.add(1, "Benry"));
        System.out.println(henry.add(2, "Lenry"));
        System.out.println(henry.add(3, "Denry"));
        System.out.println(henry.add(4, "Renry"));
        System.out.println(henry.add(5, "Senry"));
        System.out.println("Removing " + henry.remove(3));
        System.out.println(henry.contains(3));
        System.out.println(henry.getValue(3));
        System.out.println(henry.getSize());
        
        Iterator<String> iter = henry.getValueIterator();
        ArrayList<String> out = new ArrayList<>();
        while(iter.hasNext()){
            out.add(iter.next());
        }
        System.out.println(out.toString());

        Iterator<Integer> iterK = henry.getKeyIterator();
        ArrayList<Integer> keys = new ArrayList<>();
        while(iterK.hasNext()){
            keys.add(iterK.next());
        }
        System.out.println(keys.toString());
    }
}
