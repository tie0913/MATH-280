package group2.assign2.a2q3;

// TODO:  add appropriate comments
public class A2Q3TestIntegral
{
    public static void main(String[] args)
    {
        A2Q3IFunction fun1 = new FunctionExample();
        A2Q3Integral int1 = new A2Q3Integral( fun1, 3.0, 9.0, 0.000001, 25, 
                "Rectangle");
        System.out.println( "Results for integral of example function " 
                + "from 3 to 9: " );
        System.out.println( "Method\t\tConverged?\tLoops\tResult" );
        System.out.println( "======\t\t==========\t=====\t======" );
        System.out.println( int1.getResults() );
        int1.recalculate( "Trapezoid" );
        System.out.println( int1.getResults() );
        int1.recalculate( "Simpsons" );
        System.out.println( int1.getResults() );
    }
}
