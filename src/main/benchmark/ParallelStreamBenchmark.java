package benchmark;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

public class ParallelStreamBenchmark {

    @Param({"100", "1500", "500000"})
    private int numberOfElements =10;

    private List<String> someStrings;

    @Setup(Level.Invocation)
    public void setup() {
        someStrings = createListOfStrings();
    }

    @Benchmark
    public void loopFor() {
        System.out.println(someStrings.get(0));

    }

    private List<String> createListOfStrings() {
        List<String> list = new ArrayList<>();
        Random r = new Random();
        for (int index = 0; index <= numberOfElements; index++) {
            list.add(randomAlphabetic(r.nextInt(10) + 1));
        }
        return list;
    }

}
