package a3.matrix;

import note.IMatrix;

public class Matrix {

    private IMatrix augmentedMatrix;

    public Matrix(IMatrix augmentedMatrix){
        this.augmentedMatrix = augmentedMatrix;
    }


    public String toPolynomialString(){

        boolean empty = true;
        StringBuilder builder = new StringBuilder("y = ");
        for(int i = 1; i <= augmentedMatrix.getRows(); i++){

            double v = augmentedMatrix.getElement(i, augmentedMatrix.getCols());

            if(v == 0){
                continue;
            }

            if(v > 0 && i != 1){
                builder.append("+");
            }

            builder.append(v).append( i == 1? "" : (i == 2? "x" : "x^" + (i - 1)));
            empty = false;

        }

        if(empty){
            builder.append("0");
        }
        return builder.toString();
    }


    public double applyLeastSquares(double x){

        double y = 0;

        for(int i = 1; i <= augmentedMatrix.getRows(); i++){
            double v = augmentedMatrix.getElement(i, augmentedMatrix.getCols());
            if(v == 0){
                continue;
            }

            y += v * Math.pow(x, (i - 1));
        }

        return y;
    }
}
