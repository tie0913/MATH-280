package a2q3;

/**
 * <p>This program creates several A2Q3Integral objects using
 * different example functions (Q3A, Q3B, Q3C, Q3D) and integration
 * ranges. For each function, it executes the integration using
 * multiple numerical methods and prints the convergence status,
 * the number of refinement loops used, and the final estimated result.</p>
 *
 */
public class A2Q3TestIntegral
{
    public static void main(String[] args)
    {

        A2Q3Integral q3a = new A2Q3Integral(new Q3A(), 6, 8, 0.000001, 25, "Rectangdle");
        A2Q3Integral q3b = new A2Q3Integral(new Q3B(), 0, 8, 0.000001, 25, "Rectangdle");
        A2Q3Integral q3c = new A2Q3Integral(new Q3C(), 0, 2, 0.000001, 25, "Rectangdle");

        System.out.println("Here is Q3 a");
        execute(q3a);
        System.out.println();
        System.out.println("Here is Q3 b");
        execute(q3b);
        System.out.println();
        System.out.println("Here is Q3 c");
        execute(q3c);

        System.out.println();
        System.out.println("Here is Q3 d function 1");
        execute(Q3D.getIntegral(Q3D.FIRST));
        System.out.println();
        System.out.println("Here is Q3 d function 2");
        execute(Q3D.getIntegral(Q3D.SECOND));
        System.out.println();
        System.out.println("Here is Q3 d function 3");
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
