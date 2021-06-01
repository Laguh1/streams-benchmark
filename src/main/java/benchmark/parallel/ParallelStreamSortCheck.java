package benchmark.parallel;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import benchmark.BuildState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import static benchmark.Configuration.MEASURE_ITERATIONS;
import static benchmark.Configuration.NUMBER_OF_FORKS;
import static benchmark.Configuration.WARM_UP_ITERATIONS;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = WARM_UP_ITERATIONS)
@Measurement(iterations = MEASURE_ITERATIONS)
public class ParallelStreamSortCheck {

    @Benchmark
    public List<String> parallelStreamSort(BuildState.GivenState state) {
        List<String> result = state.someStrings.parallelStream().sorted().collect(Collectors.toList());
        return result;
    }

    @Benchmark
    public List<String> parallelStreamSortUnordered(BuildState.GivenState state) {
        List<String> result = state.someStrings.parallelStream().unordered().sorted().collect(Collectors.toList());
        return result;
    }

    @Benchmark
    public List<String> sequentialStreamSort(BuildState.GivenState state) {
        List<String> result = state.someStrings.stream().sorted().collect(Collectors.toList());
        return result;
    }

}
