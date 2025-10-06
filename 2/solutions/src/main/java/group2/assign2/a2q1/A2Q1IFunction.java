package group2.assign2.a2q1;

/**
 *  Interface for function class to be used for integration<br>
 *  for Assignment #2, Question #1<br>
 *
 * @author     MATH 282
 * @created    Fall 2025
 */

public interface A2Q1IFunction
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

    
    /**
     * Calculates the integral of the function over the specified range<br>
     * Returns the answer to within the given precision, or<br>
     * the last answer when the maximum number of loops is done.<br>
     * Uses a straightforward implementation of the left rectangle rule.<br>
     * 
     * @param dLeft      Value of x at left edge of range to be integrated
     * @param dRight     Value of x and right edge of range to be integrated
     * @param dPrecision Precision to which the integral must be calculated
     * @param iMaxLoops  Maximum number of loops for calculating the integral
     * @return           Value of integral of function over specified range
     */
    public double integrateLeftSimple( double dLeft, double dRight,
            double dPrecision, int iMaxLoops );
}
