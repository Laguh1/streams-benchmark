package benchmark;

import static benchmark.Configuration.MEASURE_ITERATIONS;
import static benchmark.Configuration.NUMBER_OF_FORKS;
import static benchmark.Configuration.WARM_UP_ITERATIONS;

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
@Fork(value = NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = WARM_UP_ITERATIONS)
@Measurement(iterations = MEASURE_ITERATIONS)
public class ParallelStreamSort {


    /*@Benchmark
    public List<BuildState.GivenState.Employee> parallelStreamSort(BuildState.GivenState state) {
        return  state.employees.parallelStream().sorted(Comparator.comparing(BuildState.GivenState.Employee::getId)).collect(Collectors.toList());
    }

    @Benchmark
    public List<BuildState.GivenState.Employee> sequentialStreamSort(BuildState.GivenState state) {
        return state.employees.stream().sorted(Comparator.comparing(BuildState.GivenState.Employee::getId)).collect(Collectors.toList());
    }*/


    @Benchmark
    public List<String> parallelStreamSort(BuildState.GivenState state) {
        return  state.someStrings.parallelStream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelStreamSortUnordered(BuildState.GivenState state) {
        return  state.someStrings.parallelStream().unordered().sorted().collect(Collectors.toList());
    }

    @Benchmark
    public List<String> sequentialStreamSort(BuildState.GivenState state) {
        return state.someStrings.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
