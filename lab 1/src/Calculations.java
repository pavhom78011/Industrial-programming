public class Calculations {

    public static float task(float x, int k)
    {

        float result = 0;
        float num = x;
        int pow = 1;
        float accuracy = 0.1F;

        for(int i = 0;i < k;++i)
        {
            accuracy *= 0.1;
        }

        while (num > accuracy || -num > accuracy)
        {
            result += num;
            num *= x;
            num *= x;
            ++ pow;
            num /= pow;
            ++ pow;
            num /= pow;
        }

        return  result;

    }
    public static float taskWithMath(float x, int k)
    {
        return (float) ((Math.exp(x) - Math.exp(-x))/2);
    }
}
