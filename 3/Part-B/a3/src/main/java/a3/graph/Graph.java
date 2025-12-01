package a3.graph;

import note.IMatrix;
import note.Matrix;

public class Graph {


    private IMatrix image;

    public Graph(IMatrix image){
        if(image.getCols() != 2){
            throw new IllegalArgumentException("The image must have 2 columns");
        }
        this.image = image;
    }

    public void rotate(double x, double y, double angle){

        IMatrix base = this.homogeneous();

        base = this.translateMatrix(base, x, y);

        double radius = angle * Math.PI / 180;
        double[][] rotation = new double[3][3];
        rotation[0][0] = Math.cos(radius);
        rotation[0][1] = Math.sin(radius);
        rotation[1][0] = 0 - Math.sin(radius);
        rotation[1][1] = Math.cos(radius);
        rotation[2][2] = 1;

        base = base.multiply(new Matrix(rotation));

        this.set(this.translateMatrix(base, -x, -y));
    }

    public void scale(double tx, double ty, double sx, double sy){
        IMatrix base = this.homogeneous();

        base = this.translateMatrix(base, tx, ty);

        double[][] scale = new double[3][3];
        scale[0][0] = sx;
        scale[1][1] = sy;
        scale[2][2] = 1;

        base = base.multiply(new Matrix(scale));

        this.set(this.translateMatrix(base, -tx, -ty));
    }

    public void tanslate(double tx, double ty){
        this.set(this.translateMatrix(this.homogeneous(), tx, ty));
    }


    private void set(IMatrix matrix){

        double[][] vals = new double[this.image.getRows()][2];
        for(int r = 0; r < vals.length; r++){
            vals[r][0] = matrix.getElement(r + 1, 1);
            vals[r][1] = matrix.getElement(r + 1, 2);
        }
        this.image = new Matrix(vals);
    }

    public IMatrix translateMatrix(IMatrix matrix, double tx, double ty){
        double[][] vals = new double[3][3];
        vals[0][0] = 1;
        vals[1][1] = 1;
        vals[2][2] = 1;
        vals[2][0] = tx;
        vals[2][1] = ty;
        return matrix.multiply(new Matrix(vals));
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int r = 1; r <= this.image.getRows(); r++){
            for(int c = 1; c <= this.image.getCols(); c++){
                if(c != 1){
                    builder.append('\t');
                }
                builder.append(this.image.getElement(r, c));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private IMatrix homogeneous(){
        double[][] vals = new double[this.image.getRows()][this.image.getCols() + 1];
        for(int r = 1; r <= vals.length; r++){
            for(int c = 1; c <= vals[r].length; c++){
                vals[r][c] = c < vals[r].length ? this.image.getElement(r, c) : 1;
            }
        }
        return new Matrix(vals);
    }

}
