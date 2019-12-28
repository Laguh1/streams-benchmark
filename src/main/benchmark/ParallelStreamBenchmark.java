package benchmark;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@Warmup(iterations = 5)
@Measurement(iterations = 10)
public class ParallelStreamBenchmark {

    @Param({"100", "1500", "500000"})
    private int numberOfElements =10;

    private List<String> someStrings;

    @Setup(Level.Invocation)
    public void setup() {
        someStrings = createListOfStrings();
    }

    @Benchmark
    public void loopFor() {
        System.out.println(someStrings.get(0));

    }

    private List<String> createListOfStrings() {
        List<String> list = new ArrayList<>;
        for (int index = 0; index <= numberOfElements; index++) {
            list.add(randomAlphabetic(64));
        }
        return list;
    }
}
