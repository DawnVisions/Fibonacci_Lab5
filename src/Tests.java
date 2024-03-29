import java.io.FileWriter;
import java.io.PrintWriter;

public class Tests {

    /* define constants */
    static int numberOfTrials = 50;
    private static final int MAXIBITSIZE = 30;
    static String ResultsFolderPath = "/home/elizabeth/IdeaProjects/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;

    public static void main(String[] args) {

        //FibRecurDP method = new FibRecurDP();
        //boolean correct = method.checkSortCorrectness();
        boolean correct = Verification.checkSortCorrectness(FibMatrix::fibonacci);
        System.out.println("Verification Pass?: " + correct);

        // run the whole experiment at least twice, and expect to throw away the data from the earlier runs, before java has fully optimized
        runFullExperiment("FibMatrix-Exp1-ThrowAway.txt");
        runFullExperiment("FibMatrix-Exp2.txt");
        runFullExperiment("FibMatrix-Exp3.txt");
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

        resultsWriter.println("#Bit size of x(n)  AverageTime"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        for (int inputSize = 1; inputSize <= MAXIBITSIZE; inputSize++) {
            System.out.println("Running test for bit size " + inputSize + " ... ");
            System.out.print("    Running trial batch...");
            System.gc();
            long batchElapsedTime = 0;
            for (long trial = 0; trial < numberOfTrials; trial++) {
                System.out.print("    Generating test data...");
                long x = randomIntegerOfBitSize(inputSize);
                System.gc();
                System.out.println("...done.");
                TrialStopwatch.start();
                long result = FibMatrix.fibonacci(x);
                batchElapsedTime = batchElapsedTime + TrialStopwatch.elapsedTime();
            }
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials; // calculate the average time per trial in this batch

            /* print data for this size of input */
            resultsWriter.printf("%12d  %15.2f\n", inputSize, averageTimePerTrialInBatch);
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

