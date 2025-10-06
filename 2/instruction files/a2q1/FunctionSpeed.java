/**
 *  Sample function for speed of a car at minute x<br>
 *  Implements function f(x) = 1/4 x<br>
 *
 * @author     MATH 282
 * @created    Fall 2025
 */

public class FunctionSpeed extends A2Q1ACFunction
{
    /**
     *  Default constructor for the FunctionSpeed object<br>
     */
    public FunctionSpeed()
    {
    }


    /**
     *  Returns the value of the function f(x) = 1/4 x<br>
     *  for a given x, implementing the abstract method from ACFunction<br>
     *
     * @param  x  Value to evaluate function at
     * @return    Value of function f(x) = 1/4 x at argument
     */
    public double calculate( double x )
    {
        return 1.0 / 4.0 * x;
    }
}
