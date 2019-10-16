import java.util.Arrays;
import java.util.List;

public class FibRecurDP {

    long[] fibResultsCache;

    public long fibonacci(long x) {
        fibResultsCache = new long[(int) x+1];
        for (int i = 0; i <= x; i++) {
            fibResultsCache[i] = -1;
        }
        return fibRecursiveWithCache(x);
    }

    public long fibRecursiveWithCache(long x) {
        if (x <= 1)
            return x;
        else if (fibResultsCache[(int) x] != -1) {
            return fibResultsCache[(int) x];
        }
        else {
            long result = fibRecursiveWithCache(x-1) + fibRecursiveWithCache(x-2);
            fibResultsCache[(int) x] = result;
            return result;
        }
    }

    public boolean checkSortCorrectness() {
        //Creates a list of fibonacci numbers with the selected method up to 20
        long[] testNum = new long[21];
        for (int i = 0; i < 21; i++) {
            testNum[i] = fibonacci(i);
        }
        Verification.printArray(testNum);
        //Compares generated list against a static list of the first 20 numbers;
        //Returns tue if the lists match
        long[] compareAgainst = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765};
        if (Arrays.equals(testNum, compareAgainst)) {
            return true;
        } else {
            return false;
        }
    }
}