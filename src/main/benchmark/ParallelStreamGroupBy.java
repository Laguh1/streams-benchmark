package main.benchmark;

import static main.benchmark.Configuration.MEASURE_ITERATIONS;
import static main.benchmark.Configuration.NUMBER_OF_FORKS;
import static main.benchmark.Configuration.WARM_UP_ITERATIONS;

import java.util.List;
import java.util.Map;
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
public class ParallelStreamGroupBy {

    /*
    @Benchmark
    public Map<String, List<BuildState.GivenState.Employee>> sequentialStreamGroupBy(BuildState.GivenState state) {
        return state.employees.stream().collect(Collectors.groupingBy(e -> e.getGenre()));
    }

    @Benchmark
    public ConcurrentMap<String, List<BuildState.GivenState.Employee>> sequentialStreamConcurrentGroupBy(BuildState.GivenState state) {
        return state.employees.stream().collect(Collectors.groupingByConcurrent(e -> e.getGenre()));
    }

    @Benchmark
    public Map<String, List<BuildState.GivenState.Employee>> parallelStreamGroupBy(BuildState.GivenState state) {
        return state.employees.parallelStream().collect(Collectors.groupingBy(e -> e.getGenre()));
    }

    @Benchmark
    public ConcurrentMap<String, List<BuildState.GivenState.Employee>> parallelStreamConcurrentGroupBy(BuildState.GivenState state) {
        return state.employees.parallelStream().collect(Collectors.groupingByConcurrent(e -> e.getGenre()));
    }
*/
    @Benchmark
    public Map<String, List<BuildState.GivenState.Employee>> parallelStreamDisticntGroupBy(BuildState.GivenState state) {
        return state.employees.parallelStream().unordered().collect(Collectors.groupingBy(e -> e.getGenre()));
    }

}
