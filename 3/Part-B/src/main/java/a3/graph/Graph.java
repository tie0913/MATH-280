package a3.graph;

import note.IMatrix;
import note.Matrix;

/**
 * Represents a 2D geometric graph consisting of coordinate pairs, providing 
 * transformation operations such as rotation, scaling, and translation.
 * <p>
 * The graph is internally stored as a matrix with two columns representing the 
 * x and y coordinates of each point. Transformation operations are applied 
 * using homogeneous coordinates, enabling consistent and flexible matrix-based 
 * manipulation.
 * </p>
 * 
 * <p>
 * Supported operations include:
 * <ul>
 *   <li>Rotation about an arbitrary pivot point</li>
 *   <li>Scaling relative to a specified pivot point</li>
 *   <li>Translation along the x and y axes</li>
 * </ul>
 * </p>
 *
 * Instances of this class use underlying {@link matrix.IMatrix} objects to perform 
 * matrix multiplications required for geometric transformations.
 */
public class Graph {

    private IMatrix image;

    public Graph(IMatrix image) {
        if (image.getCols() != 2) {
            throw new IllegalArgumentException("The image must have 2 columns");
        }
        this.image = image;
    }

    /*
     * Rotates the invoking matrix around a specified pivot point by the given
     * angle.
     * The rotation is performed by converting the matrix to homogeneous
     * coordinates,
     * translating it relative to the pivot, applying the rotation transformation,
     * and then translating it back.
     *
     * @param x     The x-coordinate of the pivot point
     * @param y     The y-coordinate of the pivot point
     * @param angle The rotation angle in degrees (counter-clockwise)
     * @return void
     */
    public void rotate(double x, double y, double angle) {

        IMatrix base = this.homogeneous();

        base = this.translateMatrix(base, -x, -y);

        double radius = angle * Math.PI / 180;
        double[][] rotation = new double[3][3];
        rotation[0][0] = Math.cos(radius);
        rotation[0][1] = Math.sin(radius);
        rotation[1][0] = 0 - Math.sin(radius);
        rotation[1][1] = Math.cos(radius);
        rotation[2][2] = 1;

        base = base.multiply(new Matrix(rotation));

        this.set(this.translateMatrix(base, x, y));
    }


    /**
     * Scales the invoking matrix relative to a specified pivot point. The method
     * first
     * converts the matrix to homogeneous coordinates, translates it so that the
     * pivot
     * becomes the origin, applies the scaling transformation, and then translates
     * the
     * matrix back to its original position.
     *
     * @param tx The x-coordinate of the pivot point
     * @param ty The y-coordinate of the pivot point
     * @param sx The scaling factor in the x direction
     * @param sy The scaling factor in the y direction
     * @return void
     */
    public void scale(double tx, double ty, double sx, double sy) {
        IMatrix base = this.homogeneous();

        base = this.translateMatrix(base, -tx, -ty);

        double[][] scale = new double[3][3];
        scale[0][0] = sx;
        scale[1][1] = sy;
        scale[2][2] = 1;

        base = base.multiply(new Matrix(scale));

        this.set(this.translateMatrix(base, tx, ty));
    }

    /**
     * Translates the invoking matrix by the specified x and y offsets. The method
     * converts the matrix into its homogeneous form, applies a translation
     * transformation using the provided offsets, and updates the matrix with
     * the resulting coordinates.
     *
     * @param tx The translation offset in the x direction
     * @param ty The translation offset in the y direction
     * @return void
     */
    public void translate(double tx, double ty) {
        this.set(this.translateMatrix(this.homogeneous(), tx, ty));
    }

    private void set(IMatrix matrix) {

        double[][] vals = new double[this.image.getRows()][2];
        for (int r = 0; r < vals.length; r++) {
            vals[r][0] = matrix.getElement(r + 1, 1);
            vals[r][1] = matrix.getElement(r + 1, 2);
        }
        this.image = new Matrix(vals);
    }

    public IMatrix translateMatrix(IMatrix matrix, double tx, double ty) {
        double[][] vals = new double[3][3];
        vals[0][0] = 1;
        vals[1][1] = 1;
        vals[2][2] = 1;
        vals[2][0] = tx;
        vals[2][1] = ty;
        return matrix.multiply(new Matrix(vals));
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 1; r <= this.image.getRows(); r++) {
            for (int c = 1; c <= this.image.getCols(); c++) {
                if (c != 1) {
                    builder.append('\t');
                }
                builder.append(this.image.getElement(r, c));
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private IMatrix homogeneous() {
        double[][] vals = new double[this.image.getRows()][this.image.getCols() + 1];
        for (int r = 1; r <= vals.length; r++) {
            for (int c = 1; c <= vals[r - 1].length; c++) {
                vals[r - 1][c - 1] = c < vals[r - 1].length ? this.image.getElement(r, c) : 1;
            }
        }
        return new Matrix(vals);
    }

}
