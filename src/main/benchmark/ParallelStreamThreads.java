package main.benchmark;

import static main.benchmark.Configuration.EXACT_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.JUST_ABOVE__NUMBER_OF_THREADS;
import static main.benchmark.Configuration.LARGE_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.MEASURE_ITERATIONS;
import static main.benchmark.Configuration.NUMBER_OF_FORKS;
import static main.benchmark.Configuration.SMALL_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.VERY_LARGE_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.WARM_UP_ITERATIONS;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = WARM_UP_ITERATIONS)
@Measurement(iterations = MEASURE_ITERATIONS)
public class ParallelStreamThreads {


    @Threads(SMALL_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamSmallThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(EXACT_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamExactThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(JUST_ABOVE__NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamAboveThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamLargeThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(VERY_LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamVeryLargeThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(SMALL_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamSmallThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(EXACT_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamExactlThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(JUST_ABOVE__NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamAboveThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamLargeThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(VERY_LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamVeryLargeThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }
}
