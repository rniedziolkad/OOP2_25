import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlantUMLRunner.setjarPath("/home/student/Pobrane/plantuml-1.2025.2.jar");
        try {
            List<Person> personList = Person.fromCsv("family.csv");

            Person.toBinaryFile(personList, "family.bin");
            List<Person> family = Person.fromBinaryFile("family.bin");

            System.out.println(family.size());
            for (Person p : family) {
                System.out.println(p);
                System.out.println("Dzieci:");
                for (Person child: p.getChildren()) {
                    System.out.println("\t"+child.getFullName());
                }
            }
            String umlData = Person.umlFromList(family);
            PlantUMLRunner.generateDiagram(umlData,
                    "/home/student/Pobrane/",
                    "diagram.png");
            System.out.println(umlData);

        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        // Na 4 punkty -- kolokwium I 2024 (3 kroki -- 1 punkt)
        // czas do terminu I kolokwium
    }
}