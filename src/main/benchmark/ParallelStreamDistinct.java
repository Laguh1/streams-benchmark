package main.benchmark;

import static main.benchmark.Configuration.NUMBER_OF_FORKS;
import static main.benchmark.Configuration.WARM_UP_ITERATIONS;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = WARM_UP_ITERATIONS)
public class ParallelStreamDistinct {

 /*   @Benchmark
    public List<String> sequentialDistinctStream(BuildState.GivenState state) {
        return state.someStrings.stream()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelDistinctStream(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelUnorderedDistinctStream(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .unordered()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }*/
}
