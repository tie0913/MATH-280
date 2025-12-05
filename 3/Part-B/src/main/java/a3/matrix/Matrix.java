package a3.matrix;

import note.IMatrix;

/**
 * Represents a polynomial constructed from an augmented matrix, where each row
 * provides one coefficient taken from the last column of the matrix. This class
 * offers functionality for converting the matrix into a human-readable polynomial
 * expression and for evaluating the polynomial at a specified input value.
 * <p>
 * The augmented matrix is assumed to contain polynomial coefficients in order:
 * the first row corresponds to the constant term, the second to the linear term,
 * and subsequent rows to higher-degree terms following the pattern
 * <code>a₀, a₁, a₂, ...</code>.
 * </p>
 *
 * <p>
 * Supported operations include:
 * <ul>
 *   <li>Generating a formatted polynomial string</li>
 *   <li>Evaluating the polynomial using least-squares coefficient values</li>
 * </ul>
 * </p>
 *
 * Instances of this class rely on the {@link matrix.IMatrix} interface for
 * accessing matrix elements and dimensions.
 */
public class Matrix {

    private IMatrix augmentedMatrix;

    public Matrix(IMatrix augmentedMatrix) {
        this.augmentedMatrix = augmentedMatrix;
    }

    /*
     * Converts the augmented matrix into a polynomial expression of the form
     * <code>y = a₀ + a₁x + a₂x² + ...</code>. Each row of the matrix contributes
     * one coefficient, taken from the last column of the augmented matrix.
     * <p>
     * Zero coefficients are skipped. Positive coefficients (except the leading
     * term)
     * are prefixed with a plus sign to ensure proper polynomial formatting.
     * If all coefficients are zero, the method returns <code>y = 0</code>.
     * </p>
     *
     * @return A string representing the polynomial constructed from the matrix
     */
    public String toPolynomialString() {

        boolean empty = true;
        StringBuilder builder = new StringBuilder("y = ");
        for (int i = 1; i <= augmentedMatrix.getRows(); i++) {

            double v = augmentedMatrix.getElement(i, augmentedMatrix.getCols());

            if (v == 0) {
                continue;
            }

            if (v > 0 && i != 1) {
                builder.append("+");
            }

            builder.append(v).append(i == 1 ? "" : (i == 2 ? "x" : "x^" + (i - 1)));
            empty = false;

        }

        if (empty) {
            builder.append("0");
        }
        return builder.toString();
    }

    /*
     * Evaluates the polynomial produced by a least-squares approximation at the
     * specified x-value. The coefficients of the polynomial are taken from the last
     * column of the augmented matrix, where each row contributes one coefficient.
     * <p>
     * Zero coefficients are skipped for efficiency. Each term is computed as
     * <code>v * x^(i-1)</code>, where <code>v</code> is the coefficient in row
     * <code>i</code>.
     * </p>
     *
     * @param x The x-value at which to evaluate the least-squares polynomial
     * @return The computed y-value of the polynomial at the given input x
     */
    public double applyLeastSquares(double x) {

        double y = 0;

        for (int i = 1; i <= augmentedMatrix.getRows(); i++) {
            double v = augmentedMatrix.getElement(i, augmentedMatrix.getCols());
            if (v == 0) {
                continue;
            }

            y += v * Math.pow(x, (i - 1));
        }

        return y;
    }
}
