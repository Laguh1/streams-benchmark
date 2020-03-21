package main.benchmark;

public class Configuration {

    final static int NUMBER_OF_FORKS = 2;
    final static int WARM_UP_ITERATIONS = 5;
    final static int MEASURE_ITERATIONS = 30;

    final static String VERY_LARGE_COLLECTION = "500000";
    final static String LARGE_COLLECTION = "10000";
    final static String AVERAGE_COLLECTION = "1500";
    final static String SMALL_COLLECTION = "100";
    final static String EXTREMELY_SMALL_COLLECTION = "10";

    final static int SMALL_NUMBER_OF_THREADS = 4;
    final static int EXACT_NUMBER_OF_THREADS = 7;
    final static int JUST_ABOVE__NUMBER_OF_THREADS = 8;
    final static int LARGE_NUMBER_OF_THREADS = 21;
    final static int VERY_LARGE_NUMBER_OF_THREADS = 32;


}
