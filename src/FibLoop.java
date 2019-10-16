public class FibLoop{

    public static long fibonacci(long x)
    {
        //Base case, return 0 or 1
        if (x<=1)
        {
            return x;
        }
        //Keeps track of x-2 and x-1 to calculate x
        else
        {
            //Initializing variables
            long secondToLast = 0;
            long last = 1;
            long current = 1;
            //Loop until we reach the desired x value
            for(int i = 1; i<x; i++)
            {
                current = secondToLast+last;
                secondToLast = last;
                last = current;
            }
            //Return the x fibonacci number
            return current;
        }
    }
}
