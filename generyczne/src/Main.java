import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomList<Object> list = new CustomList<>();
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(0);
        list.addFirst(-100);
        list.addLast(100);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println("Usuwam: "+list.removeFirst());
        System.out.println("Usuwam: "+list.removeLast());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println("Rozmiar: "+list.size());
        System.out.println("list[2] = "+list.get(2));
        System.out.println("list[1] = "+list.get(1));
        System.out.println("list[0] = "+list.get(0));
//        System.out.println("list[0] = "+list.get(-1));

        list.add("hello");
        list.addFirst("inny napis");
        list.add(3.1415);
        list.stream().forEach(System.out::println);
        System.out.println(list);
        List<?> strings = Util.selectClass(list, String.class);
        System.out.println(strings);
        List<?> numbers = Util.selectClass(list, Number.class);
        System.out.println(numbers);

        List<Integer> ints = List.of(1, 3, 4, 7, 14, 0, 2);
        List<Double> dubs = List.of(0.0, 1.3, 15.1, 14.6);
        System.out.println("In range: "+Util.countInRange(ints, 0, 4));
        System.out.println("In range: "+Util.countInRange(dubs, 0.0, 4.0));

        CollectionComparator<List<? extends Number>> comparator =
                new CollectionComparator<>();
        System.out.println(comparator.compare(ints, dubs));
    }
}