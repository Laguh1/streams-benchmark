package main.benchmark;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 3)
@Measurement(iterations = 8)
public class ParallelStreamBenchmark {

    @Param({"100", "1500", "500000"})
    private int numberOfElements = 10;

    private List<String> someStrings;

    @Setup(Level.Invocation)
    public void setup() {
        someStrings = createListOfStrings();
    }

    @Benchmark
    public void parallelStream() {
         someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());

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
