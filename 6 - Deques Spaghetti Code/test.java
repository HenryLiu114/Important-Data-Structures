
public class test {
    public static void main(String[] args) {
        DequeInterface<String> list = new ArrayDeque<>(3);
        list.addToBack("Henry");
        list.addToBack("Benry");
        list.addToFront("Lenry");
        list.addToBack("Kenry");
        list.addToBack("Denry");
        System.out.println(list.getBack());
        System.out.println(list.getFront());
        list.removeBack();
        list.removeFront();
        System.out.println(list.getBack());
        System.out.println(list.getFront());
        list.removeBack();
        list.removeFront();
        list.addToFront("Lenry");
    }
}
