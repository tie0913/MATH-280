package sask.polytech.cst.Function;
/**
 *  Class with main method for testing functions implementing ACFunction<br>
 *
 * @author     MATH 282
 * @created    August 30, 2017
 */

public class TestFunction
{
    /**
     *  The main program for the TestFunction class<br>
     *
     * @param  args  the command line arguments
     */
    public static void main(String[] args)
    {

        
        System.out.println("=========== Question 5d=============");
        IFunction f = new Q5d();
        System.out.println(f.calculate(1.1));
        System.out.println(f.calculate(1.3));
        double posRoot = f.findZero(1.3, 1.1, 0.000000000001);
        double negRoot = f.findZero(-1.3, -1.1, 0.000000000001);
        System.out.println(posRoot);
        System.out.println(negRoot);

        System.out.println("=========== Question 5e=============");
        IFunction _5e = new Q5e();
        System.out.println(_5e.findZero(-3, -2.5, 0.00000001));
        System.out.println(_5e.findZero(-2, -2.5, 0.00000001));


        System.out.println("=========== Question 5f=============");
        IFunction _5f = new Q5f();
        System.out.println(_5f.findZero(4, 8, 0.00000001));

        System.out.println("=========== Question 5g=============");
        IFunction _5g = new Q5g();
        System.out.println(_5g.findZero(500, 0.0001, 0.00000001));
    }
}
