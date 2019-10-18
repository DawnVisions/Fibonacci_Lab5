import java.io.FileWriter;
import java.io.PrintWriter;

public class X_BasedTests {

    private static final int MAXIMUM = 93;
    /* define constants */
    static int numberOfTrials = 1;
    static String ResultsFolderPath = "/home/elizabeth/IdeaProjects/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    public static void main(String[] args) {

        //FibRecurDP method = new FibRecurDP();
        //boolean correct = method.checkSortCorrectness();
        boolean correct = Verification.checkSortCorrectness(FibRecur::fibonacci);
        System.out.println("Verification Pass?: " + correct);

        // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
        runFullExperiment("FibLoop-XBasedExp1-ThrowAway.txt");
        runFullExperiment("FibLoop-XBasedExp2.txt");
        runFullExperiment("FibLoop-XBasedExp3.txt");
    }

    private static void runFullExperiment(String resultsFileName) {

        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("*****!!!!!  Had a problem opening the results file " + ResultsFolderPath + resultsFileName);
            return;
        }

        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials
        ThreadCpuStopWatch TrialStopwatch = new ThreadCpuStopWatch(); // for timing an individual trial

        resultsWriter.println("#Fib number x  AverageTime"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        for (int i = 1; i<= MAXIMUM; i++) {
            System.out.print("    Running trial batch...");
            System.gc();
            long batchElapsedTime = 0;
            for (long trial = 0; trial < numberOfTrials; trial++) {
                System.out.print("    Generating test data...");
                System.gc();
                TrialStopwatch.start();
                long result = FibLoop.fibonacci(i);
                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime();
                System.out.print(i + " fib number is " + result);
            }
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch

            /* print data for this size of input */
            resultsWriter.printf("%12d  %15.2f\n", i, averageTimePerTrialInBatch);
            resultsWriter.flush();
            System.out.println(" ....done.");
        }
    }

    //Returns a random long of the specified bit size
    public static long randomIntegerOfBitSize(int size){
        double minvalue = Math.pow(2,size-1);
        double maxvalue = Math.pow(2, size);
        return (long) (minvalue + Math.random()*(maxvalue-minvalue));
    }

    public static int sizeInBits(long x)
    {
        return (int) Math.floor(Math.log(x)/Math.log(2)+1);
    }
}

