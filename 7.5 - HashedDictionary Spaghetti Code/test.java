public class test {
    public static void main(String[] args) {
        DictionaryInterface<String, Integer> list = new HashedDictionary<>(10);
        list.add("100", 1);
        System.out.println(list.getValue("100"));
        list.add("101", 3);
        System.out.println(list.getValue("101"));
        list.add("101", 5);
        System.out.println(list.getValue("101"));
        list.add("21", 121);
        System.out.println(list.getValue("21"));
        list.remove("101");
        list.remove("101");
        list.remove("101");
        
        System.out.println(list.getValue("101"));
    }
}
