/*
 *  Date submitted: Fall 2022
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */
package note;

/**
 * Abstract class for Matrix class<br>
 * for creating matrices and performing operations on them<br>
 * for Learning Outcomes 3 and 5<br>
 * 
 * @author MATH 282
 * @version Fall 2022
 */
public abstract class ACMatrix implements IMatrix
{
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
     * @return          Number of rows in the matrix
     */
    @Override
    public int getRows()
    {
        return this.iRows;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#getCols()
     */
    /**
     * Returns the number of columns in the invoking matrix
     * 
     * @return          Number of columns in the matrix
     */
    @Override
    public int getCols()
    {
        return this.iCols;
    }

    
    /**
     * Sets the number of rows in the matrix
     * 
     * @param iRows     Number of rows in the matrix
     * @throws IllegalArgumentException     Number of rows is less than 1
     */
    protected void setRows(int iRows)
            throws IllegalArgumentException
    {
        if (iRows < 1)
        {
            throw new IllegalArgumentException("Matrix must have at least 1 row");
        }
        this.iRows = iRows;
    }
    
    
    /**
     * Sets the number of rows in the matrix
     * 
     * @param iRows     Number of rows in the matrix
     * @throws IllegalArgumentException     Number of rows is less than 1
     */
    protected void setCols(int iCols)
            throws IllegalArgumentException
    {
        if (iCols < 1)
        {
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
     * @param iRow      The row to get the element from
     * @param iCol      The column to get the element from
     * @return          Element in the matrix at the specified row and column
     */
    @Override
    public abstract double getElement(int iRow, int iCol);

    
    /**
     * Sets the element in the matrix in the specified row and column
     * to the specified value.
     * 
     * @param iRow      The row to set the element in
     * @param iCol      The column to set the element in
     * @param dValue    The value to set the element to
     */
    protected abstract void setElement(int iRow, int iCol, double dValue);

    
    /**
     * Creates a new matrix of the specified size with all entries set to 0.0
     * 
     * @param iRows     The number of rows in the new matrix
     * @param iCols     The number of columns in the new matrix
     * @return          A new matrix of the specified size with all entries set to 0.0
     */
    protected abstract ACMatrix createMatrix(int iRows, int iCols);

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#add(matrix.IMatrix)
     */
    /**
     * Adds together the invoking matrix and the passed-in matrix and returns
     * the sum matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be added to the invoking matrix
     * @return          Sum of invoking matrix and matrix passed in
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be added
     */
    @Override
    public IMatrix add(IMatrix mRight)
            throws IllegalArgumentException
    {
        ACMatrix mLeftOp = this;
        ACMatrix mRightOp = (ACMatrix)mRight;
        ACMatrix mSum = null;
        
        // if the matrices are not the same size, throw an exception
        if (mLeftOp.iRows != mRightOp.iRows || mLeftOp.iCols != mRightOp.iCols)
        {
            throw new IllegalArgumentException("Matrices must be the same size for addition");
        }
        
        // sum matrix is the same size as the operands
        mSum = createMatrix(mLeftOp.iRows, mLeftOp.iCols);
        
        // for every row in the sum matrix
        for (int r = 1; r <= mSum.iRows; r++)
        {
            // for every column in the current row of the sum matrix
            for (int c = 1; c <= mSum.iCols; c++)
            {
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
     * Multiplies all entries in the invoking matrix by the scalar value passed in.
     * 
     * @param dScalar   Value to multiply the matrix entries by
     * @return          Matrix with all entries multiplied by the scalar value passed in
     */
    @Override
    public IMatrix scalarMultiply(double dScalar)
    {
        ACMatrix result = this.createMatrix(this.iRows, this.iCols);
        for(int r = 1; r <= this.iRows; r++){
            for(int c = 1; c <= this.iCols; c++){
                result.setElement(r, c, this.getElement(r, c) * dScalar);
            }
        }
        return result;
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#subtract(matrix.IMatrix)
     */
    /**
     * Subtracts the passed-in matrix from the invoking matrix and returns
     * the resultant matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be subtracted the invoking matrix
     * @return          Matrix that results from subtracting the passed-in matrix from the invoking matrix
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be subtracted
     */
    @Override
    public IMatrix subtract(IMatrix mRight)
            throws IllegalArgumentException
    {
        return this.add(mRight.scalarMultiply(-1));
    }

    
    /* (non-Javadoc)
     * @see matrix.IMatrix#multiply(matrix.IMatrix)
     */
    /**
     * Multiplies the invoking matrix by the passed-in matrix and returns
     * the product matrix. Throws exception if matrices cannot be multiplied.
     * 
     * @param mRight    Matrix to be multiplied against the invoking matrix
     * @return          Matrix that results from multiplying the invoking matrix by the passed-in matrix
     * @throws IllegalArgumentException     Matrices cannot be multiplied because they are the wrong sizes
     */
    @Override
    public IMatrix multiply(IMatrix mRight)
            throws IllegalArgumentException
    {
        
        if(this.iCols != mRight.getRows()){
            throw new IllegalArgumentException("Illegal Matrix to Multiply");
        }

        ACMatrix result = this.createMatrix(this.iRows, mRight.getCols());

        for(int r = 1; r <= this.iRows; r++){
            for(int c = 1; c <= this.iCols; c++){
                for(int rc = 1; rc <= mRight.getCols(); rc++){
                    double val = result.getElement(r, rc);
                    result.setElement(r, rc, val + this.getElement(r, c) * mRight.getElement(c, rc));
                }
            }
        }

        return result;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    /**
     * Returns the matrix as a string with each row on one line
     * and each column separated by tabs.
     * 
     * @return  String representation of the invoking matrix
     */
    @Override
    public String toString()
    {
        String s = "";
        
        // go through every row
        for (int r = 1; r <= this.iRows; r++)
        {
            s += "[\t";
            // go through every column in this row
            for (int c = 1; c <= this.iCols; c++)
            {
                s += this.getElement(r, c) + "\t";
            }
            s += "]\n";
        }
        
        return s;
    }
}
