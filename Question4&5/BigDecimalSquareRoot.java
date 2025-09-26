import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


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

    public static class Result{
        public final BigDecimal value;
        public final int loops;
        public Result(BigDecimal value, int loops){
            this.value = value;
            this.loops = loops;
        }
    }
    
    
    /**
     * The main program for the BigDecimalSquareRoot class<br>
     * 
     * @param args  The command line arguments
     */
    public static void main(String[] args)
    {
        // Scanner consoleInput = new Scanner(System.in);
        
        // // note - could do input validation on value
        // System.out.print("Enter the value to find the square root of (>=0): ");
        // BigDecimal bdValue = new BigDecimal( consoleInput.next() );
        
        // // note - could do input validation on precision
        // System.out.print("Enter the desired precision for the square root: ");
        // BigDecimal bdPrecision = new BigDecimal( consoleInput.next() );
        
        // BigDecimal bdResult = sqrtBisection(bdValue, bdPrecision);
        // System.out.print("\nSquare root from bisection is: ");
        // System.out.println(bdResult);

    BigDecimal p = new BigDecimal("0.001");     // desired precision
    BigDecimal p2 = new BigDecimal("0.000001"); // finer precision
    BigDecimal[] values = {
            new BigDecimal("0.25"),
            new BigDecimal("5000000000000"), // 5,000,000,000,000
            new BigDecimal("5000000"),       // 5,000,000
            new BigDecimal("5"),
            new BigDecimal("0.005"),
            new BigDecimal("0.000005")
    };

    // ---------- Table for precision 0.001 ----------
    System.out.println("\n=== Precision 0.001 ===");
    System.out.printf("%-15s | %-25s | %-6s | %-25s | %-6s%n",
            "Value", "RelError Result", "Loops", "AbsError Result", "Loops");
    System.out.println("-----------------------------------------------------------------------------------------------");

    for (BigDecimal v : values) {
        Result rel = sqrtBisection(v, p, true);   // relative error
        Result abs = sqrtBisection(v, p, false);  // absolute error
        System.out.printf("%-15s | %-25s | %-6d | %-25s | %-6d%n",
                v.toPlainString(),
                rel.value.toPlainString(), rel.loops,
                abs.value.toPlainString(), abs.loops);
    }

    // ---------- Table for precision 0.000001 ----------
    System.out.println("\n=== Precision 0.000001 ===");
    System.out.printf("%-15s | %-25s | %-6s | %-25s | %-6s%n",
            "Value", "RelError Result", "Loops", "AbsError Result", "Loops");
    System.out.println("-----------------------------------------------------------------------------------------------");

    for (BigDecimal v : values) {
        Result rel = sqrtBisection(v, p2, true);   // relative error
        Result abs = sqrtBisection(v, p2, false);  // absolute error
        System.out.printf("%-15s | %-25s | %-6d | %-25s | %-6d%n",
                v.toPlainString(),
                rel.value.toPlainString(), rel.loops,
                abs.value.toPlainString(), abs.loops);
    }
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
        
        if (bdValue.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new ArithmeticException( "Value cannot be negative" );
        }
        if (bdValue.compareTo(BigDecimal.ZERO) > 0)
        {
            // TODO Change algorithm & method so it works in all cases
            
            // Start with initial guess - square root between 1 and x
            BigDecimal bdLower = BigDecimal.ONE;
            BigDecimal bdUpper = bdValue;
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
                bdError = bdError.divide(bdGuess, mcPrecisionDigits); //relative
                bdError = bdError.abs(); // absolute relative error
                if (bdError.compareTo(bdPrecision) <= 0)
                {
                    bKeepGoing = false;
                }
            }
        }
        
        return bdGuess.round(mcPrecisionDigits);
    }
    /**
     * Bisection sqrt for x >= 0.
     * @param x value to root
     * @param precision stopping threshold (e.g., 0.001). Interpreted as relative-error threshold if useRelativeError=true,
     *                  otherwise as absolute interval width threshold.
     * @param useRelativeError true => stop when (upper-lower)/guess <= precision; false => stop when (upper-lower) <= precision
     */
    public static Result sqrtBisection(BigDecimal x, BigDecimal precision, boolean useRelativeError){
        if(x.compareTo(BigDecimal.ZERO) < 0){
            throw new ArithmeticException("Value cannot be negative");
        }
        if (x.compareTo(BigDecimal.ZERO) == 0){
            return new Result(BigDecimal.ZERO.setScale(precision.scale(), RoundingMode.HALF_UP), 0);
        }

        MathContext mc = new MathContext(x.precision() + precision.scale() + 8);

        if (x.signum() == 0) {
            return new Result(BigDecimal.ZERO.setScale(precision.scale(), RoundingMode.HALF_UP), 0);
        }
        BigDecimal lower, upper;
        if (x.compareTo(BigDecimal.ONE) >= 0){
            lower = BigDecimal.ONE;
            upper = x;
        }else{
            lower = BigDecimal.ZERO;
            upper = BigDecimal.ONE;   
        }

        BigDecimal guess = BigDecimal.ZERO;
        int loops = 0;
        while (true) { 
            loops++;
            guess = lower.add(upper,mc).divide(BD_TWO,mc);
            if(guess.compareTo(BigDecimal.ZERO) == 0){
                guess = new BigDecimal("1e-30");
            }   
            BigDecimal test = guess.multiply(guess, mc);
            int cmp = test.compareTo(x);
            if(cmp == 0) break;
            else if (cmp > 0) upper = guess;
            else lower = guess;

            BigDecimal interval = upper.subtract(lower, mc).abs();
            BigDecimal error = useRelativeError ? interval.divide(guess.abs(), mc) : interval;
            if (error.compareTo(precision) <= 0) break;
        }

        BigDecimal rounded = guess.setScale(precision.scale(), RoundingMode.HALF_UP);
        return new Result(rounded, loops);
    }
}
