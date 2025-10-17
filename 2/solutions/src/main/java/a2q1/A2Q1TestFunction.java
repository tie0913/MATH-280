package a2q1;

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
       
        A2Q1IFunction xto2 = new Xto2();
        System.out.println("for question a = " + xto2.integrateLeftSimple(0, 10, 0.000001, 500));

        A2Q1IFunction xtox = new XtoX();
        System.out.println("for question b = " + xtox.integrateLeftSimple(1.5, 2.5, 0.000001, 500));

        A2Q1IFunction log2 = new Log2();
        System.out.println("for question c = " + log2.integrateLeftSimple(1, 9, 0.000001, 500));

        System.out.println("for question d = " + log2.integrateLeftSimple(2, 256, 0.000001, 500));


        System.out.println(log2.calculate(2));
        System.out.println(log2.calculate(4));
        System.out.println(log2.calculate(6));
        System.out.println(log2.calculate(8));
        
    }
}
