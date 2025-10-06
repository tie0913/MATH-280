package sask.polytech.cst.Function;
/**
 *  Abstract class for functions to be used for finding zeros, etc.<br>
 *
 * @author     MATH 282
 * @created    August 30, 2017
 */

public abstract class ACFunction implements IFunction
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


    public double findZero(double pos, double neg, double prec){


        /**
         * Part c starts from here.
         */
        double posVal = this.calculate(pos);
        double negVal = this.calculate(neg);
        if(posVal < 0){
            throw new IllegalArgumentException("the result of pos value should greater than 0");
        }

        if(negVal > 0){
            throw new IllegalArgumentException("the result of neg value should less than 0");
        }

        /**
         * Partc ends.
         */

        double mid = 0;
        boolean go = true;
        while(go){
            mid = (pos + neg) /2;
            double valueAtMid = this.calculate(mid);
            if(valueAtMid == 0 ){
                go = false;
            }else if(valueAtMid > 0){
                pos = mid;
            }else {
                neg = mid;
            }
            if(Math.abs(pos - neg) < prec) go = false;
        }
        return mid;

    }
}
