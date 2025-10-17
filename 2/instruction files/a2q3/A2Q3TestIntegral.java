/**
 * Driver program to test numerical integration methods (Rectangle, Trapezoid, and Simpson’s rules).
 * <p>
 * This class demonstrates how the {@link A2Q3Integral} class can be used to compute definite integrals
 * for a given function over a specified interval, with a desired relative precision and a maximum
 * number of refinement loops.
 * <p>
 */
public class A2Q3TestIntegral
{
    static final class F1 extends  FunctionExample
    {
        @Override public double calculate(double x) { return 0.25 * x; }
    }

    static final class F2 extends  FunctionExample
    {
        @Override public double calculate(double x) { return -0.2 * (x + 2.0) * (x - 8.0); }
    }

    static final class F3 extends  FunctionExample
    {
        @Override public double calculate(double x) { 
            double v = 4 - x*x;
            return v <= 0.0 ? 0.0 : Math.sqrt(v); }
    }

    public static void main(String[] args)
    {
        final double PREC = 1e-6;
        final int MAX_LOOPS = 25;

        System.out.println("\nTesting integration of f(x) = 0.25 * x over [6,8]");
        runAllRules( new F1(), 6.0, 8.0, PREC, MAX_LOOPS );
        System.out.println("\nTesting integration of f(x) = -0.2 * (x + 2) * (x - 8) over [0,8]");
        runAllRules( new F2(), 0.0, 8.0, PREC, MAX_LOOPS );
        System.out.println("\nTesting integration of f(x) = sqrt(4 - x^2) over [0,2]");
        runAllRules( new F3(), 0.0, 2.0, PREC, MAX_LOOPS );
    }


    /**
     *  Run all three integration rules (Rectangle, Trapezoid, Simpson's) on the given function
     *  over the interval [a,b] with specified precision and maximum loops.
     *
     * @param  f           Function to integrate (implements A2Q3IFunction)
     * @param  a           Lower limit of integration
     * @param  b           Upper limit of integration
     * @param  precision   Desired relative precision for convergence
     * @param  maxLoops    Maximum number of refinement loops to perform
     */
    private static void runAllRules(A2Q3IFunction f, double a, double b, double precision, int maxLoops)
    {
        A2Q3Integral integral;

        String[] methods = { "Rectangle", "Trapezoid", "Simpsons" };

        System.out.println( "Results for integral of example function " 
                + "from " + a + " to " + b + ": " );
        System.out.println( "Method\t\tConverged?\tLoops\tResult" );
        System.out.println( "======\t\t==========\t=====\t======" );

        for ( String method : methods )
        {
            integral = new A2Q3Integral( f, a, b, precision, maxLoops, method );
            System.out.println( integral.getResults() );
        }
    }
}
