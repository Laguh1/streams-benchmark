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
public class ParallelStreamLimit {

   @Benchmark
    public List<String> parallelStreamLimit(BuildState.GivenState state) {
        return state.someStrings.parallelStream().filter(string -> string.length() > 5).limit(10000).collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelStreamLimitUnordered(BuildState.GivenState state) {
        return state.someStrings.parallelStream().unordered().filter(string -> string.length() > 5).limit(10000).collect(Collectors.toList());
    }

    @Benchmark
    public List<String> sequentialStreamLimit(BuildState.GivenState state) {
        return state.someStrings.stream().filter(string -> string.length() > 5).limit(10000).collect(Collectors.toList());
    }
    /*
    @Benchmark
    public List<BuildState.GivenState.Employee> sequentialStreamLimit(BuildState.GivenState state) {
        return state.employees.stream().filter(employee -> employee.getGenre()=="m").limit(10000).collect(Collectors.toList());
    }*/
}
