package group2.assign2.a2q3;

/**
 * The {@code A2Q3TestIntegral} class is a test harness for verifying
 * the numerical integration implementations (Rectangle, Trapezoid,
 * and Simpson’s Rule) used in the A2Q3 assignment.
 *
 * <p>This program creates several {@link A2Q3Integral} objects using
 * different example functions (Q3A, Q3B, Q3C, Q3D) and integration
 * ranges. For each function, it executes the integration using
 * multiple numerical methods and prints the convergence status,
 * the number of refinement loops used, and the final estimated result.</p>
 *
 * <p>Usage:
 * <pre>
 *     java A2Q3TestIntegral
 * </pre>
 * The output will display a formatted table of results for each test case.
 * </p>
 *
 * <p><b>Example Output:</b></p>
 * <pre>
 * Results for integral of example function from 6.0 to 8.0:
 * Method      Converged?  Loops   Result
 * ======      ==========  =====   ======
 * Rectangle   true        10      12.3456
 * Trapezoid   true         9      12.3457
 * Simpsons    true         5      12.3458
 * </pre>
 *
 * @author      Tie Wang
 * @version     1.0
 * @since       2025-10-14
 */
public class A2Q3TestIntegral
{
    public static void main(String[] args)
    {

        A2Q3Integral q3a = new A2Q3Integral(new Q3A(), 6, 8, 0.000001, 25, "Rectangdle");
        A2Q3Integral q3b = new A2Q3Integral(new Q3B(), 0, 8, 0.000001, 25, "Rectangdle");
        A2Q3Integral q3c = new A2Q3Integral(new Q3C(), 0, 2, 0.000001, 25, "Rectangdle");

        execute(q3a);
        execute(q3b);
        execute(q3c);

        execute(Q3D.getIntegral(Q3D.FIRST));
        execute(Q3D.getIntegral(Q3D.SECOND));
        execute(Q3D.getIntegral(Q3D.THIRD));
       
    }

    public static void execute(A2Q3Integral integral){
        System.out.println( "Results for integral of example function " 
                + "from " + integral.getLeft() + " to " + integral.getRight() + ": " );
        System.out.println( "Method\t\tConverged?\tLoops\tResult" );
        System.out.println( "======\t\t==========\t=====\t======" );
        System.out.println( integral.getResults() );
        integral.recalculate( "Trapezoid" );
        System.out.println( integral.getResults() );
        integral.recalculate( "Simpsons" );
        System.out.println( integral.getResults() );
    }
}
