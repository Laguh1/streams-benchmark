package benchmark;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static benchmark.Configuration.MEASURE_ITERATIONS;
import static benchmark.Configuration.NUMBER_OF_FORKS;
import static benchmark.Configuration.WARM_UP_ITERATIONS;

public class ExportReportsTest {

    private static final String reportPath = "target/jmh-reports/jmh-benchmark-report.json";

    @BeforeEach
    public void createDirectoryForBenchmarkReport() throws IOException {
        Path directory = Paths.get("target/jmh-reports/");
        Files.createDirectories(directory);
    }

    @Test
    public void testBenchmarkIndex() throws RunnerException, IOException {
        File file = checkFileAndCreate(ParallelStreamBenchmark.class.getName());
        Options opt = new OptionsBuilder()
                .include(ParallelStreamBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .result(file.getAbsolutePath())
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(1)
                .build();
        new Runner(opt).run();
        System.out.println("Finished ");

    }

    @Test
    public void testBenchmarkCollectionType() throws RunnerException, IOException {
        File file = checkFileAndCreate(ParallelStreamCollectionType.class.getSimpleName());
        Options opt = new OptionsBuilder()
                .include(ParallelStreamCollectionType.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .result(file.getAbsolutePath())
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();
        new Runner(opt).run();
        System.out.println("Finished ");

    }

    private File checkFileAndCreate(String name) throws IOException {
        String targetFolderPath = Paths.get(
                this.getClass().getResource("/").getFile()).getParent().toString() + "/benchmark-result/";

        File targetFolder = new File(targetFolderPath);
        targetFolder.mkdirs();
        Instant date = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime utc = LocalDateTime.ofInstant(date, ZoneOffset.UTC);
        File file = new File(
                targetFolderPath + name
                        + "_" + utc.truncatedTo(ChronoUnit.MINUTES) + ".json");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }
}
