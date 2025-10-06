package sp.cst.math.assignment01.q4;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.MathContext;


/*
 * Purpose:     Square root functions for BigDecimal objects
 * Assignment:  N/A
 * Due Date:    N/A
 * Course Name: MATH 282
 * Instructors: Michael Grzesina, Rob Miller
 * 
 * Goals:
 * - demonstrate finding square roots using the method of bisection
 * - provide an example using the BigDecimal class
 * - give an example of program documentation
 * 
 * Note that this is just one example of documentation.  You should
 *  use your own judgement when documenting your own programs.
 * 
 * Note that although this example asks for input from the user, that
 *  is NOT a requirement for programs in MATH 282 (unless otherwise specified)
 */


/**
 * Class with square root methods for BigDecimal objects<br> 
 *
 * @author	Michael Grzesina (cst)
 * @created	13-Sep-06
 */
public class BigDecimalSquareRoot
{
    // quick reference for BigDecimal value 2 for use in calculations
    private static final BigDecimal BD_TWO = new BigDecimal("2");
    
    
    /**
     *  Constructor for the BigDecimalSquareRoot object.<br>
     *  Ensures no instantiation<br>
     */
    private BigDecimalSquareRoot()
    {
    }
    
    
    /**
     * The main program for the BigDecimalSquareRoot class<br>
     * 
     * @param args  The command line arguments
     */
    public static void main(String[] args)
    {
        Scanner consoleInput = new Scanner(System.in);
        
        // note - could do input validation on value
        System.out.print("Enter the value to find the square root of (>=0): ");
        BigDecimal bdValue = new BigDecimal( consoleInput.next() );
        
        // note - could do input validation on precision
        System.out.print("Enter the desired precision for the square root: ");
        BigDecimal bdPrecision = new BigDecimal( consoleInput.next() );
        
        BigDecimal bdResult = sqrtBisection(bdValue, bdPrecision);
        System.out.print("\nSquare root from bisection is: ");
        System.out.println(bdResult);
    }
    
    
    /**
     * Returns the square root as calculated using the method of bisection<br>
     * of the given value to the given precision<br>
     * 
     * @param bdValue       Value to find square root of
     * @param bdPrecision   Precision desired for square root as relative error
     * @return              Sqaure root of value
     * @throws              ArithmeticException if value is negative
     */
    /*
     * PARTIAL Algorithm (what else is needed to work for all values of x?):
     * Given x, precision
     * guess <- 0
     * if x < 0
     *     throw exception
     * if x > 0
     *     lower <- 1
     *     upper <- x
     *     keepGoing <- true
     *     
     *     while(keepGoing)
     *         guess <- (lower + upper)/2
     *         test <- guess * guess
     *         if test = x
     *             keepGoing <- false
     *         else if (test > x)
     *             upper <- guess
     *         else
     *             lower <- guess
     *
     *         error <- |(upper - lower)/guess|
     *         if error <= precision
     *             keepGoing <- false
     *             
     * return guess
     */
    public static BigDecimal sqrtBisection( BigDecimal bdValue,
            BigDecimal bdPrecision )
            throws ArithmeticException
    {
        BigDecimal bdGuess = BigDecimal.ZERO;
        
        // note that scale + precision gives digits in bdPrecision + 1
        //  including all leading zeros;
        //  + bdValue makes sure we show some extra digits related to value
        MathContext mcPrecisionDigits =
                new MathContext(bdPrecision.scale() + bdPrecision.precision() +
                bdValue.precision());
        
        int loops = 0;

        if (bdValue.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new ArithmeticException( "Value cannot be negative" );
        }
        if (bdValue.compareTo(BigDecimal.ZERO) > 0)
        {
            // TODO Change algorithm & method so it works in all cases
            
            // Start with initial guess - square root between 1 and x

            /**
             * 
             *  Modification Introduction
             *  the original version was 
             *      BigDecimal bdLower = BigDecimal.ONE;
             *      BigDecimal bdUpper = bdValue;
             *  however this can not work correctly with bdValue less than 1
             *  because the lower and upper boundary would be quite opposite when the dbValue less than 1
             *  so we use this way below to make sure the upper and lower boundaries will be exchanged when the dbValue is less than 1
             */
            BigDecimal bdLower = bdValue.compareTo(BigDecimal.ONE) < 0 ? bdValue : BigDecimal.ONE;
            BigDecimal bdUpper = bdValue.compareTo(BigDecimal.ONE) < 0 ? BigDecimal.ONE : bdValue;
            boolean bKeepGoing = true;
            
            while (bKeepGoing)
            {
                // get a better guess - try halfway between and test results 
                bdGuess = bdLower.add(bdUpper).divide(BD_TWO);
                BigDecimal bdTest = bdGuess.multiply(bdGuess);
                if (bdTest.compareTo(bdValue) == 0)
                {
                    bKeepGoing = false;
                }
                else if (bdTest.compareTo(bdValue) > 0)
                {
                    bdUpper = bdGuess;
                }
                else
                {
                    bdLower = bdGuess;
                }
                
                // check if we are "close enough" so we can stop
                // could combine all three steps below into one calculation
                BigDecimal bdError = bdUpper.subtract(bdLower);  //error
                /**
                 * We commented this line to use absolute error
                 */
                //bdError = bdError.divide(bdGuess, mcPrecisionDigits); //relative
                bdError = bdError.abs(); // absolute relative error
                if (bdError.compareTo(bdPrecision) <= 0)
                {
                    bKeepGoing = false;
                }

                loops ++;
            }
        }
        System.out.println("loops = " + loops);
        return bdGuess.round(mcPrecisionDigits);
    }
}
