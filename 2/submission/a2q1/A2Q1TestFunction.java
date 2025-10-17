/**
 *  Class with main method for calculating integrals<br>
 *  for Assignment #2, Question #1<br>
 *
 * @author     [update with your names]
 * @created    Fall 2025
 */

public class A2Q1TestFunction
{
    /**
     *  The main program for the TestFunction class<br>
     *
     * @param  args  the command line arguments (not used)
     */
    public static void main(String[] args)
    {
    double presision = 0.000001;
    int maxLoop = 100;

    //a. 0 to 10
    FunXSquared a = new FunXSquared();
    double resultA = a.integrateLeftSimple(0, 10, presision, maxLoop);
    System.out.println("a." + resultA);
    System.out.println("-------------");

    //b. 1.5 to 2.5
    FunXToX b = new FunXToX();
    double resultB = b.integrateLeftSimple(1.5, 2.5, presision, maxLoop);
    System.out.println("b." + resultB );
    System.out.println("-------------");

    //c. 1 to 9
    FunLog2 c = new FunLog2();
    double resultC = c.integrateLeftSimple(1, 9, presision, maxLoop);
    System.out.println("c." + resultC );
    System.out.println("------------");

    // d. 2 to 256
    FunLog2 d = new FunLog2();
    double resultD = d.integrateLeftSimple(2, 256, presision, maxLoop);
    System.out.println("d." + resultD );
    }
}
// Function fo r f(x) = x2
class FunXSquared extends A2Q1ACFunction {
    public double calculate(double x)
    {
        return x * x;
    }
}
// Function for f(x) = x^x
class FunXToX extends A2Q1ACFunction {
    public double calculate(double x)
    {
        return Math.pow(x, x);
    }
}
// Function for f(x) = log2(x)
class FunLog2 extends A2Q1ACFunction {
    public double calculate (double x)
    {
        return Math.log(x)/Math.log(2);
    }
}