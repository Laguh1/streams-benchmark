package main.benchmark;

import static main.benchmark.Configuration.SMALL_COLLECTION;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

public class BuildState {

    @State(Scope.Benchmark)
    public static class GivenState {

        public List<String> someStrings;
        public Set<String> setOfStrings;
        public List<String> linkedStrings;
        public List<Employee> employees;
        public List<String> limitedStrings;

        // @Param({VERY_LARGE_COLLECTION ,LARGE_COLLECTION, AVERAGE_COLLECTION, SMALL_COLLECTION})
        @Param({SMALL_COLLECTION})
        private int numberOfElements;

        //Creates a new List of random Strings every time the benchmark method is invoked
        @Setup(Level.Invocation)
        public void setUp() {
            someStrings = createListOfStrings();
            setOfStrings = createSetOfStrings();
            linkedStrings = createLinkedStrings();
           // employees = createListOfEmployees();
           // limitedStrings = createListStringsCheck();
        }

        private List<String> createListOfStrings() {
            List<String> list = new ArrayList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return list;
        }

        private Set<String> createSetOfStrings() {
            Set<String> set = new HashSet<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                set.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return set;
        }

        private List<String> createLinkedStrings() {
            List<String> list = new LinkedList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return list;
        }

        private List<Employee> createListOfEmployees() {
            List<Employee> list = new ArrayList<>();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(new Employee(String.format("%02d", index), random(1, 'm', 'f')));
            }
            return list;
        }

        private List<String> createListStringsCheck() {
            return Arrays.asList("Foo", "Bar", "New York", "Rio", "Tokio", "Madri", "London", "Paris");
        }

        class Employee {
            private String id;
            private String genre;

            public Employee(String id, String genre) {
                this.id = id;
                this.genre = genre;
            }

            public String getGenre() {
                return genre;
            }

            public void setGenre(String genre) {
                this.genre = genre;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
