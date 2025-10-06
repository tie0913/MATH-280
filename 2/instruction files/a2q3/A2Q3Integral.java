// TODO:  add appropriate comments
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
        this.estimate = 2.0;
    }

    
    public void integrateTrapezoid()
    {
        // TODO:  implement trapezoid rule
        this.estimate = 1.0;
    }
    
    // TODO:  make rectangle rule efficient
    public void integrateLeft()
    {
        // initial estimate - one rectangle
        long numRectangles = 1;
        double totalWidth = this.right - this.left;
        double height = this.fun.calculate( this.left );
        this.estimate = height * totalWidth;
        
        this.loopsUsed = 0;
        boolean keepGoing = true;
        
        while ( keepGoing )
        {
            this.loopsUsed++;
            double oldEstimate = this.estimate;
            
            // find a better approximation - twice as many rectangles
            numRectangles *= 2;
            double currentWidth = totalWidth / numRectangles;
            estimate = 0;
            
            for ( long i = 0; i < numRectangles; i++ )
            {
                double currentLeft = this.left + i * currentWidth;
                double currentHeight = this.fun.calculate( currentLeft );
                double currentArea = currentHeight * currentWidth;
                estimate += currentArea;
            }
            
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
