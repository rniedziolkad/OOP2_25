//import java.util.LinkedList;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        List<Person> personList = new LinkedList<>();
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(
                "Andrzej", "Kowalski",
                LocalDate.of(1980, 1, 1)
        ));
        System.out.println(personList.get(0));
        personList.add(new Person(
                "Bartek", "Kowalski",
                LocalDate.of(2010, 5, 12)
        ));
        personList.add(new Person(
                "Ola", "Kowalska",
                LocalDate.of(2012, 8, 7)
        ));
        personList.add(new Person(
                "Adam", "Kowalski",
                LocalDate.of(2011, 4, 14)
        ));
        System.out.println(personList.get(0).adopt(personList.get(1)));
        System.out.println(personList.get(0).adopt(personList.get(1)));
        System.out.println(personList.get(0).adopt(personList.get(0)));
        System.out.println(personList.get(0).adopt(personList.get(2)));
        personList.get(0).adopt(personList.get(3));

        System.out.println(personList.size());
//        personList[1];    -- nie działa
        for (Person p: personList) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println(personList.get(0).getYoungestChild());
        System.out.println(personList.get(1).getYoungestChild());

        System.out.println();
        System.out.println(personList.get(0).getChildren());

        System.out.println("Family");
        Family family = new Family();
        family.add(personList.get(0));
        family.add(personList.get(1), personList.get(2), personList.get(3));
        family.add(new Person(
                "Bartek", "Kowalski",
                LocalDate.of(2009, 1, 1)
        ));


        System.out.println(family.get("Bartek Kowalski"));
        System.out.println(family.get("Bartek Kowalski"));
        System.out.println(family.get("Not exist"));

        // Na 4 punkty -- kolokwium I 2024 (3 kroki -- 1 punkt)
        // czas do terminu I kolokwium
    }
}