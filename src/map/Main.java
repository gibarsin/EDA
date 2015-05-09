package map;

public class Main {
    public static void main(String[] args) {
        SingleMap<Integer, String> map = new SingleMapOpenImpl<Integer, String>(50, 10);
        SingleMap<Integer, String> map2 = new SingleMapClosedImpl<Integer, String>(3);

//        map.put(1, "Hola");
//        map.put(2, "Chau");
//        map.put(3, "Konichiwa");
//
//        System.out.println(map.get(1));
//        System.out.println(map.get(3));
//
//        map.remove(3);
//        System.out.println(map.get(3));

        map2.put(1, "Hola");
        map2.put(2, "Chau");
        map2.put(3, "Konichiwa");
        map2.put(4, "Tronco");
        map2.put(3, "NyanCat");
        map2.put(3, "Papa");
        map2.put(1, "Lala");
        map2.put(10, "Emm");
        map2.print();
    }
}
