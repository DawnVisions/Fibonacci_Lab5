public class FibRecur {
    public static long fibonacci(long x)
    {
        //Base case
        if(x<=1)
        {
            return x;
        }
        else
        {
            return fibonacci(x-1)+fibonacci(x-2);
        }
    }
}
