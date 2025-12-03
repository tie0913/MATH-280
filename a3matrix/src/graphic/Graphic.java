package graphic;

import matrix.*;

public class Graphic {

    private static Matrix points;

    public Graphic(Matrix xyData) {
        int rows = xyData.getRows();
        int cols = xyData.getCols();

        if (cols != 2) {
            throw new IllegalArgumentException("Matrix must have exactly 2 columns for x and y coordinates.");
        }

        this.points = new Matrix(rows, 3);

        for (int i = 1; i <= rows; i++) {
            double x = xyData.getElement(i, 1);
            double y = xyData.getElement(i, 2);

            points.setElement(i, 1, x);
            points.setElement(i, 2, y);
            points.setElement(i, 3, 1.0);
        }

        //System.err.println("Graphic created with " + points.getRows() + " " + points.getCols());
    }

    public void rotate(double dCx, double dCy, double dAngle) {
        double radians = Math.toRadians(dAngle);
        double cosTheta = Math.cos(radians);
        double sinTheta = Math.sin(radians);

        Matrix R = new Matrix(3, 3);

        R.setElement(1, 1, cosTheta);
        R.setElement(1, 2, -sinTheta);
        R.setElement(1, 3, 0);

        R.setElement(2, 1, sinTheta);
        R.setElement(2, 2, cosTheta);
        R.setElement(2, 3, 0);

        double m20 = dCx * (1 - cosTheta) + dCy * sinTheta;
        double m21 = dCy * (1 - cosTheta) - dCx * sinTheta;

        R.setElement(3, 1, m20);
        R.setElement(3, 2, m21);
        R.setElement(3, 3, 1);

        this.points = (Matrix) points.multiply(R);
    }

    public void scale(double dCx, double dCy, double dSx, double dSy) {
        Matrix S = new Matrix(3, 3);

        S.setElement(1, 1, dSx);
        S.setElement(1, 2, 0);
        S.setElement(1, 3, 0);

        S.setElement(2, 1, 0);
        S.setElement(2, 2, dSy);
        S.setElement(2, 3, 0);

        double m20 = dCx * (1 - dSx);
        double m21 = dCy * (1 - dSy);

        S.setElement(3, 1, m20);
        S.setElement(3, 2, m21);
        S.setElement(3, 3, 1);

        this.points = (Matrix) points.multiply(S);
    }

    public void translate(double dTx, double dTy) {
        Matrix T = new Matrix(3, 3);

        T.setElement(1, 1, 1);
        T.setElement(1, 2, 0);
        T.setElement(1, 3, 0);

        T.setElement(2, 1, 0);
        T.setElement(2, 2, 1);
        T.setElement(2, 3, 0);

        T.setElement(3, 1, dTx);
        T.setElement(3, 2, dTy);
        T.setElement(3, 3, 1);

        this.points = (Matrix) points.multiply(T);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int rows = points.getRows();
        for (int i = 1; i <= rows; i++) {
            double x = points.getElement(i, 1);
            double y = points.getElement(i, 2);
            sb.append(String.format("(%.2f, %.2f)%n", x, y));
        }
        return sb.toString();
    }
}
