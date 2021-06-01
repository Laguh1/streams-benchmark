package benchmark.parallel;

import benchmark.parallel.ParallelStreamBenchmark;
import benchmark.parallel.ParallelStreamCollectionType;
import benchmark.parallel.ParallelStreamDistinct;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static benchmark.Configuration.MEASURE_ITERATIONS;
import static benchmark.Configuration.NUMBER_OF_FORKS;
import static benchmark.Configuration.WARM_UP_ITERATIONS;

public class ExportParallelStreamReportsTest {


    @Test
    public void testParallelStreamBenchmark() throws RunnerException, IOException {
        String name = ParallelStreamBenchmark.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamCollectionType() throws RunnerException, IOException {
        String name = ParallelStreamCollectionType.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamDistinct() throws RunnerException, IOException {
        String name = ParallelStreamDistinct.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamGroupBy() throws RunnerException, IOException {
        String name = ParallelStreamGroupBy.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamLimit() throws RunnerException, IOException {
        String name = ParallelStreamLimit.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamSort() throws RunnerException, IOException {
        String name = ParallelStreamSort.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
    }

    @Test
    public void testParallelStreamThreads() throws RunnerException, IOException {
        String name = ParallelStreamThreads.class.getSimpleName();
        File file = checkFileAndCreate(name);
        new Runner(getOptions(name, file)).run();
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
                .warmupIterations(WARM_UP_ITERATIONS)
                .measurementIterations(MEASURE_ITERATIONS)
                .forks(NUMBER_OF_FORKS)
                .build();
    }

}
