package benchmark.streams;

import benchmark.BuildState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static benchmark.Configuration.MEASURE_ITERATIONS;
import static benchmark.Configuration.NUMBER_OF_FORKS;
import static benchmark.Configuration.WARM_UP_ITERATIONS;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = NUMBER_OF_FORKS, jvmArgs = {"-Xms8G", "-Xmx8G"})
@Warmup(iterations = WARM_UP_ITERATIONS)
@Measurement(iterations = MEASURE_ITERATIONS)
public class CapturingBenchmark {

    private static final BigDecimal ONEHUNDRED = new BigDecimal(100);
    private BigDecimal oneHundred = new BigDecimal(100);


    @Benchmark
    public List<BigDecimal> capturingStaticFinalvariable(BuildState.GivenState state) {
        Function<BigDecimal, BigDecimal> func = (a) -> a.multiply(ONEHUNDRED);

        return state.bigDecimals.stream()
                .map(bigDecimal -> func.apply(bigDecimal))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<BigDecimal> capturingClassvariable(BuildState.GivenState state) {
        Function<BigDecimal, BigDecimal> func = (a) -> a.multiply(oneHundred);

        return state.bigDecimals.stream()
                .map(bigDecimal -> func.apply(bigDecimal))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<BigDecimal> capturingLocalvariable(BuildState.GivenState state) {
        BigDecimal oneHundred = new BigDecimal(100);
        Function<BigDecimal, BigDecimal> func = (a) -> a.multiply(oneHundred);

        return state.bigDecimals.stream()
                .map(bigDecimal -> func.apply(bigDecimal))
                .collect(Collectors.toList());
    }

    @Benchmark
    public List<BigDecimal> nonCapturingvariable(BuildState.GivenState state) {
        Function<BigDecimal, BigDecimal> func = (a) -> a.multiply(new BigDecimal(100));

        return state.bigDecimals.stream()
                .map(bigDecimal -> func.apply(bigDecimal))
                .collect(Collectors.toList());
    }
}
