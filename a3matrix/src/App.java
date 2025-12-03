import graphic.*;
import matrix.*;

public class App {
    public static void main(String[] args) throws Exception {
        Matrix square = new Matrix(4,2 );
        square.setElement(1, 1, 0);  square.setElement(1, 2, 1);
        square.setElement(2, 1, 2);  square.setElement(2, 2, 3);
        square.setElement(3, 1, 4);  square.setElement(3, 2, 5);
        square.setElement(4, 1, 6);  square.setElement(4, 2, 7);

        Graphic graphic = new Graphic(square);
        System.out.println(graphic.toString());

        graphic.translate(2,3);
        System.out.println(graphic.toString());
        System.err.println("After translation:");
        graphic.rotate(2.5,3.5,45);
        System.err.println("After rotation:");
        System.out.println(graphic.toString());
        graphic.scale(2,2,2,0.5);
        System.err.println("After scaling:");
        System.out.println(graphic.toString());

        Matrix triangle = new Matrix(3,2);
        triangle.setElement(1, 1, 2);  triangle.setElement(1, 2, 2);
        triangle.setElement(2, 1, 4);  triangle.setElement(2, 2, 2);
        triangle.setElement(3, 1, 3);  triangle.setElement(3, 2, 31);

        graphic = new Graphic(triangle);
        System.out.println(graphic.toString());

        graphic.scale(2, 2, 2, 0.5);
        System.err.println("After scaling:");
        System.out.println(graphic.toString());

        System.out.println("----- Least Squares -----");
        Matrix A = new Matrix(3,4);
        A.setElement(1, 1, 5); A.setElement(1, 2, 0); A.setElement(1, 3, 68); A.setElement(1, 4, 83);
        A.setElement(2, 1, 0); A.setElement(2, 2, 68); A.setElement(2, 3, 0); A.setElement(2, 4, 0);
        A.setElement(3, 1, 68); A.setElement(3, 2, 0); A.setElement(3, 3, 1412); A.setElement(3, 4, 1730);

        System.err.println("Matrix A:");
        System.err.println(A.toString());

        Matrix point = (Matrix) A.gaussJordanSolve();
        System.err.println("Solution Matrix:");

        System.out.println(point.toString());
        System.err.println(point.toPolynomialString());

        double yAt2 = point.applyLeastSquares(2);
        System.out.println("y at x=2: " + yAt2);

        A.setElement(1, 1, 4); A.setElement(1, 2, 8); A.setElement(1, 3, 4); A.setElement(1, 4, 80);
        A.setElement(2, 1, 2); A.setElement(2, 2, 1); A.setElement(2, 3, -4); A.setElement(2, 4, 7);
        A.setElement(3, 1, 3); A.setElement(3, 2, -1); A.setElement(3, 3, 2); A.setElement(3, 4, 22);

        System.err.println("Matrix A:");
        System.err.println(A.toString());

        point = (Matrix) A.gaussJordanSolve();
        System.err.println("Solution Matrix:");

        System.out.println(point.toString());
        System.err.println(point.toPolynomialString());

        yAt2 = point.applyLeastSquares(2);
        System.out.println("y at x=2: " + yAt2);

    }
}
