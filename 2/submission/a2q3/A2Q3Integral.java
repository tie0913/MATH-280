package a2q3;

/**
 * The {@code A2Q3TestIntegral} class is a test driver for the
 * {@link A2Q3Integral} class. It creates several example functions
 * and uses different integration methods to compare the results.
 *
 * <p>This program runs Rectangle, Trapezoid, and Simpson’s Rule
 * on the same functions and prints the result table for each one.
 * The output shows if the method converged, how many loops were used,
 * and what the final estimate was.</p>
 *
 * <p>It helps to check if the integration code works correctly
 * and how fast each method reaches the expected precision.</p>
 *
 * <p><b>Example Output:</b></p>
 * <pre>
 * Results for integral of example function from 0.0 to 8.0:
 * Method       Converged?  Loops   Result
 * ======       ==========  =====   ======
 * Rectangle    true        10      12.3456
 * Trapezoid    true         9      12.3457
 * Simpsons     true         5      12.3458
 * </pre>
 *
 * @author      Tie Wang
 * @version     1.0
 * @since       2025-10-14
 */
public class A2Q3Integral
{
    private A2Q3IFunction fun;
    private double left;
    private double right;
    private double precision;
    private int maxLoops;
    private String method;
    
    private int loopsUsed;
    private boolean converged;
    private double estimate;
    
    public A2Q3Integral( A2Q3IFunction funToUse, double leftEdge,
            double rightEdge, double desiredPrecision,
            int maxLoopsAllowed, String integrationMethod )
    {
        this.fun = funToUse;
        this.left = leftEdge;
        this.right = rightEdge;
        this.precision = desiredPrecision;
        this.maxLoops = maxLoopsAllowed;
        this.recalculate( integrationMethod );
    }

    public double getLeft(){
        return this.left;
    }

    public double getRight(){
        return this.right;
    }
    
    public void recalculate( String integrationMethod )
    {
        this.method = integrationMethod;
        
        this.converged = false;
        this.loopsUsed = 0;
        if ( integrationMethod.equalsIgnoreCase("simpsons") )
        {
            integrateSimpsons();
        }
        else if ( integrationMethod.equalsIgnoreCase("trapezoid") )
        {
            integrateTrapezoid();
        }
        else // assume rectangle rule
        {
            this.method = "Rectangle";
            integrateLeft();
        }
    }

    
    public void integrateSimpsons()
    {
        // TODO:  implement Simpson's rule

        long numBlocks = 1;

        double leftHeight = this.fun.calculate(this.left);
        double rightHeight = this.fun.calculate(this.right);
        double totalWidth = this.right - this.left;
        double twiceTotalMidHeight = 2 * this.fun.calculate(this.left + totalWidth / 2);

        this.estimate = totalWidth / 6  * (leftHeight + 2 * twiceTotalMidHeight + rightHeight);


        boolean keepGoing = true;

        while(keepGoing){
            this.loopsUsed++;

            double oldEstimate = this.estimate;

            double tempTwiceTotalMidHeight = twiceTotalMidHeight;

            double fourTimeTotalMidHeight = 0;

            numBlocks *= 2;
            double currentWidth = totalWidth/numBlocks;

            for(long i = 1; i < numBlocks; i += 2){
                double currentMid = this.left + i * currentWidth;
                double currentMidHeight = this.fun.calculate(currentMid);
                tempTwiceTotalMidHeight += 2 * currentMidHeight;
                fourTimeTotalMidHeight += 4 * currentMidHeight;
            }

            double sum = leftHeight + rightHeight + twiceTotalMidHeight + fourTimeTotalMidHeight;
            this.estimate = sum * currentWidth / 3;

            twiceTotalMidHeight = tempTwiceTotalMidHeight;

            double error = Math.abs( this.estimate - oldEstimate );
            double relError = Math.abs( error / this.estimate );
            if ( relError <= this.precision )
            {
                this.converged = true;
                keepGoing = false;
            }
            else if ( this.loopsUsed >= this.maxLoops )
            {
                this.converged = false;
                keepGoing = false;
            }
        }

    }
    
    public void integrateTrapezoid()
    {
        // TODO:  implement trapezoid rule
        long numTrapezoid = 1;
        double leftHeight = this.fun.calculate(this.left);
        double rightHeight = this.fun.calculate(this.right);
        double totalWidth = this.right - this.left;
        double totalHeight = (leftHeight + rightHeight)/2;

        this.estimate = totalHeight * totalWidth;

        boolean keepGoing = true;

        while(keepGoing){
            this.loopsUsed++;
            numTrapezoid *= 2;
            double oldEstimate = this.estimate;
            double currentWidth = totalWidth/numTrapezoid;
            for(long i = 1; i < numTrapezoid; i += 2){
                double current = this.left + i * currentWidth;
                totalHeight += this.fun.calculate(current);
            }

            this.estimate = totalHeight * currentWidth;

             // are we "close enough"?
            double error = Math.abs( this.estimate - oldEstimate );
            double relError = Math.abs( error / this.estimate );
            if ( relError <= this.precision )
            {
                this.converged = true;
                keepGoing = false;
            }
            else if ( this.loopsUsed >= this.maxLoops )
            {
                this.converged = false;
                keepGoing = false;
            }
        }

    }
    
    public void integrateLeft()
    {
        // initial estimate - one rectangle
        long numRectangles = 1;
        double totalWidth = this.right - this.left;
        double height = this.fun.calculate( this.left );
        this.estimate = height * totalWidth;
        
        this.loopsUsed = 0;
        boolean keepGoing = true;

        double totalHieght = height;
        
        while ( keepGoing )
        {
            this.loopsUsed++;
            double oldEstimate = this.estimate;
            
            // find a better approximation - twice as many rectangles
            numRectangles *= 2;
            double currentWidth = totalWidth / numRectangles;

            // @Tie Wang We do not need to assign this varable to 0 
            // because we are going to assign the result of calculation directly
            //estimate = 0;

            /*
             * This is the efficient version.
             * The result of this version will be a bit different with the original one
             * Because we have less calculations, so we have less rounding operations
             */
            for(long i = 1; i < numRectangles; i += 2){
                double currentLeft = this.left + i * currentWidth;
                totalHieght += this.fun.calculate(currentLeft);
            }

            this.estimate = totalHieght * currentWidth;
            
            /*
             * This is the original version
            for ( long i = 0; i < numRectangles; i++ )
            {
                double currentLeft = this.left + i * currentWidth;
                double currentHeight = this.fun.calculate( currentLeft );
                double currentArea = currentHeight * currentWidth;
                estimate += currentArea;
            }*/

          
            
            // are we "close enough"?
            double error = Math.abs( this.estimate - oldEstimate );
            double relError = Math.abs( error / this.estimate );
            if ( relError <= this.precision )
            {
                this.converged = true;
                keepGoing = false;
            }
            else if ( this.loopsUsed >= this.maxLoops )
            {
                this.converged = false;
                keepGoing = false;
            }
        }
    }
    
    
    public double getEstimate()
    {
        return this.estimate;
    }
    
    public boolean getConvergeStatus()
    {
        return this.converged;
    }
    
    public int getNumLoops()
    {
        return this.loopsUsed;
    }
    
    
    public String getResults()
    {
        return this.method + "\t" 
                + this.getConvergeStatus() + "\t\t"
                + this.getNumLoops() + "\t"
                + this.getEstimate();
    }
}
