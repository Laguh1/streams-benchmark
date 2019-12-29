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
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public class ParallelStreamBenchmark {

    @Param({"100", "1500", "500000"})
    private int numberOfElements;

    private List<String> someStrings;

    //Creates a new List of random Strings every time the benchmark method is invoked
    @Setup(Level.Invocation)
    public void setup() {
        someStrings = createListOfStrings();
    }

    @Benchmark
    public void parallelStream() {
         someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());

    }

    @Benchmark
    public void sequentialStream() {
        someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());

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
