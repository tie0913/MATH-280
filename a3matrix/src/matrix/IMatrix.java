/*
 *  Date submitted: Fall 2022
 *  Assignment number: N/A
 *  Course name:  MATH 282
 *  Instructor:  Michael Grzesina
 */

package matrix;

/**
 * Interface for Matrix class<br>
 * for creating matrices and performing operations on them<br>
 * for Learning Outcomes 3 and 5<br>
 * 
 * @author MATH 282
 * @version Fall 2022
 */
public interface IMatrix
{
    /**
     * Returns the number of rows in the invoking matrix
     * 
     * @return          Number of rows in the matrix
     */
    public int getRows();
    

    /**
     * Returns the number of columns in the invoking matrix
     * 
     * @return          Number of columns in the matrix
     */
    public int getCols();

    
    /**
     * Gets the element at the specified row and column in the invoking matrix
     * 
     * @param iRow      The row to get the element from
     * @param iCol      The column to get the element from
     * @return          Element in the matrix at the specified row and column
     */
    public double getElement(int iRow, int iCol);

    
    /**
     * Adds together the invoking matrix and the passed-in matrix and returns
     * the sum matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be added to the invoking matrix
     * @return          Sum of invoking matrix and matrix passed in
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be added
     */
    public IMatrix add(IMatrix mRight)
            throws IllegalArgumentException;

    
    /**
     * Multiplies all entries in the invoking matrix by the scalar value passed in.
     * 
     * @param dScalar   Value to multiply the matrix entries by
     * @return          Matrix with all entries multiplied by the scalar value passed in
     */
    public IMatrix scalarMultiply(double dScalar);

    
    /**
     * Subtracts the passed-in matrix from the invoking matrix and returns
     * the resultant matrix. Throws exception if matrices are not of equal size.
     * 
     * @param mRight    Matrix to be subtracted the invoking matrix
     * @return          Matrix that results from subtracting the passed-in matrix from the invoking matrix
     * @throws IllegalArgumentException     Matrices are not the same size and cannot be subtracted
     */
    public IMatrix subtract(IMatrix mRight)
            throws IllegalArgumentException;

    
    /**
     * Multiplies the invoking matrix by the passed-in matrix and returns
     * the product matrix. Throws exception if matrices cannot be multiplied.
     * 
     * @param mRight    Matrix to be multiplied against the invoking matrix
     * @return          Matrix that results from multiplying the invoking matrix by the passed-in matrix
     * @throws IllegalArgumentException     Matrices cannot be multiplied because they are the wrong sizes
     */
    public IMatrix multiply(IMatrix mRight)
            throws IllegalArgumentException;
}
