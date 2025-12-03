/*
 *  Date submitted: Fall 2022
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */
package matrix;

/**
 * Abstract class for Matrix class<br>
 * for creating matrices and performing operations on them<br>
 * for Learning Outcomes 3 and 5<br>
 *
 * @author MATH 282
 * @version Fall 2022
 */
public abstract class ACMatrix implements IMatrix {

    /**
     * Number of rows in the matrix
     */
    private int iRows;

    /**
     * Number of columns in the matrix
     */
    private int iCols;


    /* (non-Javadoc)
     * @see matrix.IMatrix#getRows()
     */
    /**
     * Returns the number of rows in the invoking matrix
     *
     * @return Number of rows in the matrix
     */
    @Override
    public int getRows() {
        return this.iRows;
    }

    /* (non-Javadoc)
     * @see matrix.IMatrix#getCols()
     */
    /**
     * Returns the number of columns in the invoking matrix
     *
     * @return Number of columns in the matrix
     */
    @Override
    public int getCols() {
        return this.iCols;
    }

    /**
     * Sets the number of rows in the matrix
     *
     * @param iRows Number of rows in the matrix
     * @throws IllegalArgumentException Number of rows is less than 1
     */
    protected void setRows(int iRows)
            throws IllegalArgumentException {
        if (iRows < 1) {
            throw new IllegalArgumentException("Matrix must have at least 1 row");
        }
        this.iRows = iRows;
    }

    /**
     * Sets the number of rows in the matrix
     *
     * @param iRows Number of rows in the matrix
     * @throws IllegalArgumentException Number of rows is less than 1
     */
    protected void setCols(int iCols)
            throws IllegalArgumentException {
        if (iCols < 1) {
            throw new IllegalArgumentException("Matrix must have at least 1 row");
        }
        this.iCols = iCols;
    }

    /* (non-Javadoc)
     * @see matrix.IMatrix#getElement(int, int)
     */
    /**
     * Gets the element at the specified row and column in the invoking matrix
     *
     * @param iRow The row to get the element from
     * @param iCol The column to get the element from
     * @return Element in the matrix at the specified row and column
     */
    @Override
    public abstract double getElement(int iRow, int iCol);

    /**
     * Sets the element in the matrix in the specified row and column to the
     * specified value.
     *
     * @param iRow The row to set the element in
     * @param iCol The column to set the element in
     * @param dValue The value to set the element to
     */
    protected abstract void setElement(int iRow, int iCol, double dValue);

    /**
     * Creates a new matrix of the specified size with all entries set to 0.0
     *
     * @param iRows The number of rows in the new matrix
     * @param iCols The number of columns in the new matrix
     * @return A new matrix of the specified size with all entries set to 0.0
     */
    protected abstract ACMatrix createMatrix(int iRows, int iCols);

    /* (non-Javadoc)
     * @see matrix.IMatrix#add(matrix.IMatrix)
     */
    /**
     * Adds together the invoking matrix and the passed-in matrix and returns
     * the sum matrix. Throws exception if matrices are not of equal size.
     *
     * @param mRight Matrix to be added to the invoking matrix
     * @return Sum of invoking matrix and matrix passed in
     * @throws IllegalArgumentException Matrices are not the same size and
     * cannot be added
     */
    @Override
    public IMatrix add(IMatrix mRight)
            throws IllegalArgumentException {
        ACMatrix mLeftOp = this;
        ACMatrix mRightOp = (ACMatrix) mRight;
        ACMatrix mSum = null;

        // if the matrices are not the same size, throw an exception
        if (mLeftOp.iRows != mRightOp.iRows || mLeftOp.iCols != mRightOp.iCols) {
            throw new IllegalArgumentException("Matrices must be the same size for addition");
        }

        // sum matrix is the same size as the operands
        mSum = createMatrix(mLeftOp.iRows, mLeftOp.iCols);

        // for every row in the sum matrix
        for (int r = 1; r <= mSum.iRows; r++) {
            // for every column in the current row of the sum matrix
            for (int c = 1; c <= mSum.iCols; c++) {
                // add corresponding elements in the operand matrices and put the result
                // in the same position in the sum matrix
                mSum.setElement(r, c,
                        mLeftOp.getElement(r, c) + mRightOp.getElement(r, c));
            }
        }

        return mSum;
    }

    /* (non-Javadoc)
     * @see matrix.IMatrix#scalarMultiply(double)
     */
    /**
     * Multiplies all entries in the invoking matrix by the scalar value passed
     * in.
     *
     * @param dScalar Value to multiply the matrix entries by
     * @return Matrix with all entries multiplied by the scalar value passed in
     */
    @Override
    public IMatrix scalarMultiply(double dScalar) {
        ACMatrix mLeftop = this;
        int rows = mLeftop.iRows;
        int cols = mLeftop.iCols;

        ACMatrix result = createMatrix(rows, cols);

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                double value = mLeftop.getElement(r, c) * dScalar;
                result.setElement(r, c, value);
            }
        }
        return result;
    }

    /* (non-Javadoc)
     * @see matrix.IMatrix#subtract(matrix.IMatrix)
     */
    /**
     * Subtracts the passed-in matrix from the invoking matrix and returns the
     * resultant matrix. Throws exception if matrices are not of equal size.
     *
     * @param mRight Matrix to be subtracted the invoking matrix
     * @return Matrix that results from subtracting the passed-in matrix from
     * the invoking matrix
     * @throws IllegalArgumentException Matrices are not the same size and
     * cannot be subtracted
     */
    @Override
    public IMatrix subtract(IMatrix mRight)
            throws IllegalArgumentException {
        ACMatrix mLeftOp = this;
        ACMatrix mRightOp = (ACMatrix) mRight;

        if (mLeftOp.iRows != mRightOp.iRows || mLeftOp.iCols != mRightOp.iCols) {
            throw new IllegalArgumentException("Matrices must be the same size to subtract.");
        }

        ACMatrix result = createMatrix(mLeftOp.iRows, mLeftOp.iCols);

        for (int r = 1; r <= result.iRows; r++) {
            for (int c = 1; c <= result.iCols; c++) {
                double left = mLeftOp.getElement(r, c);
                double right = mRightOp.getElement(r, c);
                result.setElement(r, c, left - right);
            }
        }

        return result;
    }

    /* (non-Javadoc)
     * @see matrix.IMatrix#multiply(matrix.IMatrix)
     */
    /**
     * Multiplies the invoking matrix by the passed-in matrix and returns the
     * product matrix. Throws exception if matrices cannot be multiplied.
     *
     * @param mRight Matrix to be multiplied against the invoking matrix
     * @return Matrix that results from multiplying the invoking matrix by the
     * passed-in matrix
     * @throws IllegalArgumentException Matrices cannot be multiplied because
     * they are the wrong sizes
     */
    @Override
    public IMatrix multiply(IMatrix mRight)
            throws IllegalArgumentException {
        ACMatrix mLeftOp = this;
        ACMatrix mRightOp = (ACMatrix) mRight;

        int rowsLeft = mLeftOp.iRows;
        int colsLeft = mLeftOp.iCols;
        int rowsRight = mRightOp.iRows;
        int colsRight = mRightOp.iCols;

        // dimension check: (rowsLeft x colsLeft) * (rowsRight x colsRight)
        if (colsLeft != rowsRight) {
            throw new IllegalArgumentException(
                    "Matrices cannot be multiplied: left cols (" + colsLeft
                    + ") != right rows (" + rowsRight + ").");
        }

        // result is rowsLeft x colsRight
        ACMatrix result = createMatrix(rowsLeft, colsRight);

        // standard triple loop, using 1-based indices
        for (int r = 1; r <= rowsLeft; r++) {
            for (int c = 1; c <= colsRight; c++) {
                double sum = 0.0;
                for (int k = 1; k <= colsLeft; k++) {
                    sum += mLeftOp.getElement(r, k) * mRightOp.getElement(k, c);
                }
                result.setElement(r, c, sum);
            }
        }

        return result;
    }

    public String toPolynomialString() {
        int rows = this.getRows();
        int cols = this.getCols();

        // last column holds the solution coefficients
        int coeffCol = cols;   // since indices are 1-based

        StringBuilder sb = new StringBuilder();
        sb.append("y = ");

        for (int r = 1; r <= rows; r++) {
            double coeff = this.getElement(r, coeffCol);
            int power = r - 1;   // row 1 -> x^0, row 2 -> x^1, ...

            if (r > 1) {
                sb.append(" + ");
            }

            if(power == 0)
                sb.append("(")
                    .append(coeff)
                    .append(")");
            else if (power == 1)
                sb.append("(")
                    .append(coeff)
                    .append(")x");
            else
                sb.append("(")
                    .append(coeff)
                    .append("x^")
                    .append(power)
                    .append(")");
        }

        return sb.toString();
    }

    public double applyLeastSquares(double x) {
        int rows = this.getRows();
        int cols = this.getCols();

        int coeffCol = cols;

        double y = 0.0;
        double powerOfX = 1.0;

        for (int r = 1; r <= rows; r++) {
            double coeff = this.getElement(r, coeffCol);
            y += coeff * powerOfX;
            powerOfX *= x;
        }

        return y;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /**
     * Returns the matrix as a string with each row on one line and each column
     * separated by tabs.
     *
     * @return String representation of the invoking matrix
     */
    @Override
    public String toString() {
        String s = "";

        // go through every row
        for (int r = 1; r <= this.iRows; r++) {
            s += "[\t";
            // go through every column in this row
            for (int c = 1; c <= this.iCols; c++) {
                s += this.getElement(r, c) + "\t";
            }
            s += "]\n";
        }

        return s;
    }

    /**
     * Performs Gauss-Jordan elimination on this augmented matrix in place.
     * Assumes the matrix has n rows and n+1 columns (square system with RHS).
     * After this method, the left side should be the identity matrix and the
     * last column will contain the solution coefficients.
     *
     * @throws IllegalArgumentException if the matrix is singular (no unique
     * solution)
     */
    public ACMatrix gaussJordanSolve() throws IllegalArgumentException {
        int rows = this.getRows();
        int cols = this.getCols();

        // We expect an augmented matrix: n x (n+1)
        if (cols != rows + 1) {
            throw new IllegalArgumentException(
                    "gaussJordanSolve expects an n x (n+1) augmented matrix.");
        }

        // For each pivot row/column
        for (int pivot = 1; pivot <= rows; pivot++) {

            // 1) Find a row with a non-zero pivot in this column (for stability)
            int pivotRow = pivot;
            double pivotVal = this.getElement(pivotRow, pivot);
            double eps = 1.0e-5;

            while (pivotRow <= rows && Math.abs(pivotVal) < eps) {
                pivotRow++;
                if (pivotRow <= rows) {
                    pivotVal = this.getElement(pivotRow, pivot);
                }
            }

            if (pivotRow > rows || Math.abs(pivotVal) < eps) {
                throw new IllegalArgumentException("Matrix is singular or nearly singular; cannot solve.");
            }

            // 2) Swap current row with pivotRow if needed
            if (pivotRow != pivot) {
                swapRows(pivot, pivotRow);
            }

            // Update pivotVal after potential swap
            pivotVal = this.getElement(pivot, pivot);

            // 3) Scale pivot row to make pivot = 1
            for (int c = pivot; c <= cols; c++) {
                double value = this.getElement(pivot, c);
                this.setElement(pivot, c, value / pivotVal);
            }

            // 4) Eliminate this column in all OTHER rows
            for (int r = 1; r <= rows; r++) {
                if (r == pivot) {
                    continue;
                }

                double factor = this.getElement(r, pivot);
                if (Math.abs(factor) < eps) {
                    continue;
                }

                for (int c = pivot; c <= cols; c++) {
                    double updated = this.getElement(r, c)
                            - factor * this.getElement(pivot, c);
                    this.setElement(r, c, updated);
                }
            }
        }

        // At this point, left side should be identity, last column is solution
        return this;
    }

    private void swapRows(int row1, int row2) {
        if (row1 == row2) {
            return;
        }

        int cols = this.getCols();
        for (int c = 1; c <= cols; c++) {
            double temp = this.getElement(row1, c);
            this.setElement(row1, c, this.getElement(row2, c));
            this.setElement(row2, c, temp);
        }
    }
}
