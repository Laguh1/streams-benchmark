package main.benchmark;

import static main.benchmark.Configuration.AVERAGE_COLLECTION;
import static main.benchmark.Configuration.LARGE_COLLECTION;
import static main.benchmark.Configuration.SMALL_COLLECTION;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;


public class BuildState {

    @State(Scope.Benchmark)
    public static class GivenState {

        public List<String> someStrings;
        @Param({LARGE_COLLECTION, AVERAGE_COLLECTION, SMALL_COLLECTION})
        private int numberOfElements;

        //Creates a new List of random Strings every time the benchmark method is invoked
        @Setup(Level.Invocation)
        public void setUp() {
            someStrings = createListOfStrings();
            System.out.print(someStrings.size()+" ");
        }

        private List<String> createListOfStrings() {
            List<String> list = new ArrayList<>();
            Random r = new Random();
            for (int index = 0; index < numberOfElements; index++) {
                list.add(randomAlphabetic(r.nextInt(10) + 1));
            }
            return list;
        }
    }
}
