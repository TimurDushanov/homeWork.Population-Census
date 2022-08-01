package CORE.Citizens;


import java.util.*;
import java.util.stream.Collectors;

import static CORE.Citizens.Education.HIGHER;
import static CORE.Citizens.Sex.MAN;
import static CORE.Citizens.Sex.WOMAN;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]
            ));
        }
        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        final List<String> conscript = persons.stream()
                .filter(age -> age.getAge() > 18)
                .filter(sex -> sex.getSex() == MAN)
                .map(Person::getSurname)
                .collect(Collectors.toList());
        conscript.forEach(System.out::println);


        persons.stream()
                .filter(Education -> Education.getEducation() == HIGHER)
                .filter(age -> age.getAge() >= 18)
                .filter(p -> p.getSex().equals(WOMAN) && p.getAge() < 60 || p.getSex().equals(MAN) && p.getAge() < 65)
                .sorted(Comparator.comparing(person -> person.getSurname()))
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

}
