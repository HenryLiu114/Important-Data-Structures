public class test {
    public static void main(String[] args) {
        QueueInterface<String> henry = new LinkedQueue<>();
        henry.enqueue("Henry");
        henry.enqueue("Benry");
        henry.enqueue("Renry");
        henry.enqueue("Lenry");
        System.out.println(henry.getFront());
        henry.dequeue();
        System.out.println(henry.getFront());
        henry.dequeue();
        henry.dequeue();
        System.out.println(henry.getFront());
    }
}
