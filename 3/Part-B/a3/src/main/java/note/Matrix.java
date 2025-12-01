/*
 *  Date submitted: Fall 2022
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package note;

/**
 * Concrete class for implementing matrices using a 2D array of values.
 *
 * @author MATH 282
 * @version Fall 2022
 */
public class Matrix extends ACMatrix
{
    /**
     * 2D array of entries in the matrix
     */
    private double[][] dArray;
    
    
    /**
     * Constructor for the Matrix class that creates a matrix with the entries
     * given by the 2D array of doubles passed in.
     * 
     * @param dArray    2D array of doubles passed in for matrix values
     */
    public Matrix(double[][] dArray)
    {
        this.setRows(dArray.length);
        this.setCols(dArray[0].length);
        this.dArray = dArray; // shallow copy
        
        // deep copy
    }


    /**
     * Constructor for the Matrix class that creates a matrix full of 0's
     * with the specified number of rows and columns
     * 
     * @param iRows     Number of rows for the matrix to be created
     * @param iCols     Number of columns for the matrix to be created
     */
    public Matrix(int iRows, int iCols)
    {
        this.setRows(iRows);
        this.setCols(iCols);
        this.dArray = new double[iRows][iCols];
    }

    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#getElement(int, int)
     */
    /**
     * Gets the element from the matrix in the specified row and column.
     * 
     * @param iRow      The row to get the element from
     * @param iCol      The column to get the element from
     * @return          The element in the matrix in the specified row and column
     */
    @Override
    public double getElement( int iRow, int iCol )
    {
        // return element at iRow and iCol of matrix
        // remembering that iRow and iCol start at 1 for matrices
        // but dArray starts at 0
        return this.dArray[iRow - 1][iCol - 1];
    }




    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#setElement(int, int, double)
     */
    /**
     * Sets the element in the matrix in the specified row and column
     * to the specified value.
     * 
     * @param iRow      The row to set the element in
     * @param iCol      The column to set the element in
     * @param dValue    The value to set the element to
     */
    @Override
    protected void setElement( int iRow, int iCol, double dValue )
    {
        this.dArray[iRow - 1][iCol - 1] = dValue;
    }

    
    /* (non-Javadoc)
     * @see matrix.ACMatrix#createMatrix(int, int)
     */
    /**
     * Creates a new matrix of the specified size with all entries set to 0.0
     * 
     * @param iRows     The number of rows in the new matrix
     * @param iCols     The number of columns in the new matrix
     * @return          A new matrix of the specified size with all entries set to 0.0
     */
    @Override
    protected ACMatrix createMatrix( int iRows, int iCols )
    {
        return new Matrix(iRows, iCols);
    }
}
