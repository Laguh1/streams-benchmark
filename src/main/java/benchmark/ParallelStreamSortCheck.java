package benchmark;

import java.util.concurrent.TimeUnit;

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

 /*   @Benchmark
    public List<String> parallelStreamSort(BuildState.GivenState state) {
        System.out.println("Parallel before sort"+state.someStrings.toString());
        List<String> result = state.someStrings.parallelStream().sorted().collect(Collectors.toList());
        System.out.println("Parallel after sort"+result.toString());
        return result;
    }

    @Benchmark
    public List<String> parallelStreamSortUnordered(BuildState.GivenState state) {
        System.out.println("Parallel before sort"+state.someStrings.toString());
        List<String> result = state.someStrings.parallelStream().unordered().sorted().collect(Collectors.toList());
        System.out.println("Parallel after sort"+result.toString());
        return result;
    }

    @Benchmark
    public List<String> sequentialStreamSort(BuildState.GivenState state) {
        System.out.println("Sequential before sort"+state.someStrings.toString());
        List<String> result = state.someStrings.stream().sorted().collect(Collectors.toList());
        System.out.println("Sequential after sort"+result.toString());
        return result;
    }*/

}
