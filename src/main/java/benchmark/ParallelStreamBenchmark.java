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
public class ParallelStreamBenchmark {

    @Benchmark
    public List<String> sequentialStream(BuildState.GivenState state) {
        return state.someStrings.stream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<String> parallelStream(BuildState.GivenState state) {
        return state.someStrings.parallelStream()
                .filter(string -> string.length() > 5)
                .peek(string -> string.replace(string.charAt(5), 'z'))
                .collect(Collectors.toList());
    }

}
