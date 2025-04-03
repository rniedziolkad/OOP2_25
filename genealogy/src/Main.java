import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<Person> personList = Person.fromCsv("family.csv");
            System.out.println(personList.size());
            for (Person p : personList) {
                System.out.println(p);
                System.out.println("Dzieci:");
                for (Person child: p.getChildren()) {
                    System.out.println("\t"+child.getFullName());
                }
            }
        } catch (AmbiguousPersonException e){
            System.err.println(e.getMessage());
        }

        // Na 4 punkty -- kolokwium I 2024 (3 kroki -- 1 punkt)
        // czas do terminu I kolokwium
    }
}