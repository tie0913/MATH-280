package a2q1;

/**
 *  Abstract class for functions to be used for integration<br>
 *  for Assignment #2, Question #1<br>
 *  
 * @author     MATH 282
 * @created    Fall 2025
 */

public abstract class A2Q1ACFunction implements A2Q1IFunction
{
    /**
     *  Returns the value of the function at a chosen point - must implement<br>
     *  specific calculations in any function class extending ACFunction<br>
     *
     * @param  x  Value to evaluate function at
     * @return    Value of function at argument
     */
    public abstract double calculate( double x );


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
            throws IllegalArgumentException
    {
        if ( dStart > dEnd )
        {
            throw new IllegalArgumentException( "Start must be <= End" );
        }
        if ( dStep <= 0 )
        {
            throw new IllegalArgumentException( "Step must be > 0" );
        }
        System.out.println( " x \t f(x) " );
        System.out.println( "===\t======" );
        for ( double x = dStart; x <= dEnd; x += dStep )
        {
            System.out.println( x + "\t" + this.calculate( x ) );
        }
        System.out.println( );
    }
    
    
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
            double dPrecision, int iMaxLoops )
    {
        // initial estimate - one rectangle
        long numRectangles = 1;
        double totalWidth = dRight - dLeft;
        double height = this.calculate( dLeft );
        double estimate = height * totalWidth;
        
        int numLoops = 0;
        boolean keepGoing = true;
        
        while ( keepGoing )
        {
            numLoops++;
            double oldEstimate = estimate;
            
            // find a better approximation - twice as many rectangles
            numRectangles *= 2;
            double currentWidth = totalWidth / numRectangles;
            estimate = 0;
            
            for ( long i = 0; i < numRectangles; i++ )
            {
                double currentLeft = dLeft + i * currentWidth;
                double currentHeight = this.calculate( currentLeft );
                double currentArea = currentHeight * currentWidth;
                estimate += currentArea;
            }
            
            // are we "close enough"?
            double error = Math.abs( estimate - oldEstimate );
            double relError = Math.abs( error / estimate );
            if ( relError <= dPrecision )
            {
                keepGoing = false;
            }
            else if ( numLoops >= iMaxLoops )
            {
                // could throw an exception or indicate otherwise
                // but this is good enough for our purposes
                System.out.println( "Did not converge" );
                keepGoing = false;
            }
        }
        return estimate;
    }
}
