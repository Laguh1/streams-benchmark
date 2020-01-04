package main.benchmark;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = Configuration.NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = Configuration.WARM_UP_ITERATIONS)
@Measurement(iterations = Configuration.MEASURE_ITERATIONS)
public class ParallelStreamBenchmark {

    @Benchmark
    public List<String> parallelUnorderedDistinctStream(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .unordered()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

/*
    @Benchmark
    public List<String> sequentialStream(GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelStream(GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> sequentialDistinctStream(GivenState state) {
        return state.someStrings.stream()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }*/

    @Benchmark
    public List<String> parallelDistinctStream(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .distinct()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }



}
