package main.benchmark;

import static main.benchmark.Configuration.EXACT_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.JUST_ABOVE__NUMBER_OF_THREADS;
import static main.benchmark.Configuration.LARGE_NUMBER_OF_THREADS;
import static main.benchmark.Configuration.SMALL_NUMBER_OF_THREADS;

import java.util.List;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Threads;

public class ParallelStreamThreads {

    @Threads(SMALL_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamSmallNoThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(EXACT_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamExactlNoThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(JUST_ABOVE__NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamAboveNoThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> sequentialStreamLargeNoThreads(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(SMALL_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamSmallNoThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(EXACT_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamExactlNoThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(JUST_ABOVE__NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamAboveNoThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Threads(LARGE_NUMBER_OF_THREADS)
    @Benchmark
    public List<String> parallelStreamLargeNoThreads(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }
}
