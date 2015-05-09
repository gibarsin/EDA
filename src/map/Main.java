package map;

public class Main {
    public static void main(String[] args) {
        SingleMap<Integer, String> map = new SingleMapOpenImpl<Integer, String>(50, 10);

        map.put(1, "Hola");
        map.put(2, "Chau");
        map.put(3, "Konichiwa");

        System.out.println(map.get(1));
        System.out.println(map.get(3));

        map.remove(3);
        System.out.println(map.get(3));
    }
}
