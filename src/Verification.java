import java.util.Arrays;

public class Verification {
    public static boolean checkSortCorrectness(Fibonacci fibMethod)
    {
        //Creates a list of fiboacci numbers with the selected method up to 20
        long[] testNum = new long[21];
        for(int i = 0; i< 21; i++)
        {
            testNum[i] = fibMethod.fibonacci(i);
        }
        printArray(testNum);

        //Compares generated list against a static list of the first 20 numbers;
        //Returns tue if the lists match
        long[] compareAgainst = {0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765};
        if(Arrays.equals(testNum, compareAgainst))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    static void printArray(long arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

}
