package benchmark;

import static benchmark.Configuration.LARGE_COLLECTION;
import static benchmark.Configuration.SMALL_COLLECTION;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static benchmark.Configuration.VERY_LARGE_COLLECTION;
import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class BuildState {

    @State(Scope.Benchmark)
    public static class GivenState {

        public List<String> someStrings;
        public Set<String> setOfStrings;
        public List<String> linkedStrings;
        public List<Employee> employees;
        public List<String> limitedStrings;
        public List<BigDecimal> bigDecimals;

        // @Param({VERY_LARGE_COLLECTION ,LARGE_COLLECTION, AVERAGE_COLLECTION, SMALL_COLLECTION})
        @Param({VERY_LARGE_COLLECTION})
        public int numberOfElements;

        //Creates a new List of random Strings every time the benchmark method is invoked
        @Setup(Level.Invocation)
        public void setUp() {
            someStrings = createListOfStrings();
            setOfStrings = createSetOfStrings();
            linkedStrings = createLinkedStrings();
            bigDecimals = createListOfBigDecimals();
           // employees = createListOfEmployees();
           // limitedStrings = createListStringsCheck();
        }

        public List<String> createListOfStrings() {
            List<String> list = new ArrayList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return list;
        }

        public List<BigDecimal> createListOfBigDecimals() {
            List<BigDecimal> list = new ArrayList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(new BigDecimal(r.nextInt(10) + 1));
            }
            return list;
        }

        public Set<String> createSetOfStrings() {
            Set<String> set = new HashSet<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                set.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return set;
        }

        public List<String> createLinkedStrings() {
            List<String> list = new LinkedList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return list;
        }

        public List<Employee> createListOfEmployees() {
            List<Employee> list = new ArrayList<>();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(new Employee(String.format("%02d", index), random(1, 'm', 'f')));
            }
            return list;
        }

        public List<String> createListStringsCheck() {
            return Arrays.asList("Foo", "Bar", "New York", "Rio", "Tokio", "Madri", "London", "Paris");
        }

        public class Employee {
            String id;
            String genre;

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
