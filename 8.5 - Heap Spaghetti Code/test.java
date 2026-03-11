public class test {
    public static void main(String[] args) {
        QueueInterface<Integer> list = new PriorityQueue<>();
        list.enqueue(1);
        list.enqueue(2);
        list.enqueue(10);
        list.enqueue(99);
        list.enqueue(4);
        list.enqueue(3);
        System.out.println(list.getFront());
        System.out.println(list.dequeue());
        System.out.println(list.dequeue());
        System.out.println(list.dequeue());
        System.out.println(list.dequeue());
    }
}
