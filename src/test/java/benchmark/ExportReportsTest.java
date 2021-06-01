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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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
        String name = ParallelStreamCollectionType.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
        System.out.println("Finished ");

    }

    private File checkFileAndCreate(String name) throws IOException {
        String targetFolderPath = Paths.get(
                this.getClass().getResource("/").getFile()).getParent().toString() + "/benchmark-result/";

        File targetFolder = new File(targetFolderPath);
        targetFolder.mkdirs();
        Instant date = Instant.ofEpochMilli(System.currentTimeMillis());
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String utc = dateFormatter.format(ZonedDateTime.ofInstant(date, ZoneOffset.UTC));
        File file = new File(
                targetFolderPath + name
                        + "_" + utc + ".json");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    private Options getOptions(String name, File file) {
        return new OptionsBuilder()
                .include(name)
                .resultFormat(ResultFormatType.JSON)
                .result(file.getAbsolutePath())
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build();
    }

}
