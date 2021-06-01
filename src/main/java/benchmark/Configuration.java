package benchmark;

public class Configuration {

    public final static int NUMBER_OF_FORKS = 2;
    public final static int WARM_UP_ITERATIONS = 5;
    public final static int MEASURE_ITERATIONS = 10;

    public final static String VERY_LARGE_COLLECTION = "500000";
    public final static String LARGE_COLLECTION = "10000";
    public final static String AVERAGE_COLLECTION = "1500";
    public final static String SMALL_COLLECTION = "100";
    public final static String EXTREMELY_SMALL_COLLECTION = "10";

    public final static int SMALL_NUMBER_OF_THREADS = 4;
    public final static int EXACT_NUMBER_OF_THREADS = 7;
    public final static int JUST_ABOVE__NUMBER_OF_THREADS = 8;
    public final static int LARGE_NUMBER_OF_THREADS = 21;
    public final static int VERY_LARGE_NUMBER_OF_THREADS = 32;


}
