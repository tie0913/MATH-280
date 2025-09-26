package sask.polytech.cst.Function;
/**
 *  Sample function implementing abstract function class<br>
 *  Implements function f(x) = 2x + 1<br>
 *
 * @author     MATH 282
 * @created    August 30, 2017
 */

public class Function1 extends ACFunction
{
    /**
     *  Default constructor for the Function1 object<br>
     */
    public Function1()
    {
    }


    /**
     *  Returns the value of the function f(x) = 2x + 1<br>
     *  for a given x, implementing the abstract method from ACFunction<br>
     *
     * @param  x  Value to evaluate function at
     * @return    Value of function f(x) = 2x + 1 at argument
     */
    public double calculate( double x )
    {
        return (2.0 * x) + 1.0;
    }
}
