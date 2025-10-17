package a2q3;

public class Q3D {

    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;


    private static double PRECISION = 0.000001;
    private static int MAX_LOOPS = 25;
    private static String RECTANGLE = "Rectangle";

    public static A2Q3Integral getIntegral(int index){
        return switch(index){
            case FIRST -> new A2Q3Integral(new A2Q3ACFunction() {
                @Override
                public double calculate(double x) {
                    return Math.sin(2 * x * Math.PI / 180 / 3);
                }
            }, 0, Math.PI, PRECISION, MAX_LOOPS, RECTANGLE);
            case SECOND -> new A2Q3Integral(new A2Q3ACFunction() {
                @Override
                public double calculate(double x) {
                    return 2 * Math.exp(-1 * x) / 3;
                }

                
            }, 0, Math.E, 0.000001, MAX_LOOPS, RECTANGLE);
            case THIRD -> new A2Q3Integral(new A2Q3ACFunction() {
                @Override
                public double calculate(double x) {
                    return 2 * Math.sqrt(x) / 7;
                }
            }, 0, Math.PI, PRECISION, MAX_LOOPS, RECTANGLE);

            default -> throw new IllegalArgumentException("We only support Q3D.FIRST, Q3D.SECOND, and Q3D.THIRD as parameters");
        };
    }



}
