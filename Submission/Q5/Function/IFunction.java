package sask.polytech.cst.Function;
/**
 *  Interface for function class to be used for finding zeros, etc.<br>
 *
 * @author     MATH 282
 * @created    August 30, 2017
 */

public interface IFunction
{
    /**
     *  Returns the value of the function at a chosen point<br>
     *
     * @param  x  Value to evaluate function at
     * @return    Value of function at argument
     */
    public double calculate( double x );


    /**
     *  Prints table of function values over specified range<br>
     *  by specified step - can paste into Excel and graph<br>
     *
     * @param  dStart  Starting value of x for table of function values
     * @param  dEnd    Ending value of x for table of function values
     * @param  dStep   Amount to increment x for table of function values
     * @return         Nothing
     * @throws         Starting value of range greater than ending value
     * @throws         Increment not positive
     */
    public void printTable( double dStart, double dEnd, double dStep )
            throws IllegalArgumentException;



    public double findZero(double pos, double neg, double prec);

}
