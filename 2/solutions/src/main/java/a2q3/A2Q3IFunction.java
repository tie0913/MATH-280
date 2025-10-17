package a2q3;

/**
 *  Interface for function class to be used for integration<br>
 *  for Assignment #2, Question #3<br>
 *
 * @author     MATH 282
 * @created    Fall 2025
 */

public interface A2Q3IFunction
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
}
