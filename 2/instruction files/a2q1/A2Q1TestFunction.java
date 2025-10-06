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
        // TODO:  delete the following code and replace with your answers

        
        // Test our example function for exploring integration
        A2Q1IFunction fun1 = new FunctionExample();
        System.out.println( "Table of example function for integration," 
                + "f(x) = 1/3 x^2 - 1/300 x^4" );
        fun1.printTable( 0.0, 10.0, 1.0 );

        System.out.println( "Integral of example function from 3 to 9: "
                + fun1.integrateLeftSimple( 3.0, 9.0, 0.000001, 25 ) );
        System.out.println( "Integral of example function from 0 to 10: "
                + fun1.integrateLeftSimple( 0.0, 10.0, 0.000001, 25 ) );
        
        // Test our speed function
        A2Q1IFunction fun2 = new FunctionSpeed();
        System.out.println( "\n\nTable of speed function," 
                + "f(x) = 1/4 x" );
        fun2.printTable( 0.0, 8.0, 1.0 );
        
        System.out.println( "Integral of speed function from 0 to 8: "
                + fun2.integrateLeftSimple( 0.0, 8.0, 0.000001, 25 ) );
        System.out.println( "Integral of speed function from 6 to 8: "
                + fun2.integrateLeftSimple( 6.0, 8.0, 0.000001, 25 ) );
    }
}
