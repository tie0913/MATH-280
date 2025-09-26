package sask.polytech.cst.Function;
/**
 *  Sample function implementing abstract function class<br>
 *  Implements function f(x) = 21 x^2 - 38 x - 15<br>
 *
 * @author     MATH 282
 * @created    August 30, 2017
 */

public class Function2 extends ACFunction
{
    /**
     *  Default constructor for the Function2 object<br>
     */
    public Function2()
    {
    }


    /**
     *  Returns the value of the function f(x) = 21 x^2 - 38 x - 15<br>
     *  for a given x, implementing the abstract method from ACFunction<br>
     *
     * @param  x  Value to evaluate function at
     * @return    Value of function f(x) = 21 x^2 - 38 x - 15 at argument
     */
    public double calculate( double x )
    {
        return (21.0 * x * x) - (38.0 * x) - 15.0;
        // could also use 21 * Math.pow( x, 2.0 ) - ...
    }
}
