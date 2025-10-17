
/**
 * Numerical integration driver that supports the Left Rectangle, Trapezoid, and Simpson's rules.
 * <p>
 * <li> Construct with a function, interval, desired precision, max refinements
 * loop, and method. </li>
 * <li> Call {@link #recalculate(String)} to (re)run a specific
 * method("rectangle", "trapezoid", or "simpsons"). </li>
 * <li> Read results via
 * {@link #getEstimate()}, {@link #getConvergeStatus()}, {@link #getNumLoops()},
 * or {@link #getResults()}. </li>
 * </p>
 * Convergence is tested by halving the step size each loop (doubling
 * subintervals) until the relative change between successive estimates is less
 * than {@code precision}, or until {@code maxLoops} is reached.
 */
public class A2Q3Integral {

    /**
     * Function to integrate (must implement {@code calculate(x)})
     */
    private A2Q3IFunction fun;

    /**
     * Left (lower) endpoint of the integration interval
     */
    private double left;

    /**
     * Right (upper) endpoint of the integration interval
     */
    private double right;

    /**
     * Desired relative precision (e.g. 0.01 for 1%)
     */
    private double precision;

    /**
     * Maximum number of refinement loops to perform
     */
    private int maxLoops;

    /**
     * Integration method used for last calculation
     */
    private String method;

    /**
     * Number of refinement loops used in last calculation
     */
    private int loopsUsed;

    /**
     * Whether last calculation converged within desired precision
     */
    private boolean converged;

    /**
     * Estimate of integral from last calculation
     */
    private double estimate;

    /**
     * Construct an integral calculator with the given parameters, and
     * immediately perform the calculation using the specified method.
     *
     * @param funToUse Function to integrate (must implement
     * {@code calculate(x)})
     * @param leftEdge Left (lower) endpoint of the integration interval
     * @param rightEdge Right (upper) endpoint of the integration interval
     * @param desiredPrecision Desired relative precision (e.g. 0.01 for 1%)
     * @param maxLoopsAllowed Maximum number of refinement loops to perform
     * @param integrationMethod Integration method to use ("rectangle",
     * "trapezoid", or "simpsons")
     */
    public A2Q3Integral(A2Q3IFunction funToUse, double leftEdge,
            double rightEdge, double desiredPrecision,
            int maxLoopsAllowed, String integrationMethod) {
        this.fun = funToUse;
        this.left = leftEdge;
        this.right = rightEdge;
        this.precision = desiredPrecision;
        this.maxLoops = maxLoopsAllowed;
        this.recalculate(integrationMethod);
    }

    /**
     * (Re)calculate the integral using the specified method.
     *
     * @param integrationMethod Integration method to use ("rectangle",
     * "trapezoid", or "simpsons")
     */
    public void recalculate(String integrationMethod) {
        this.method = integrationMethod;

        this.converged = false;
        this.loopsUsed = 0;
        if (integrationMethod.equalsIgnoreCase("simpsons")) {
            integrateSimpsons();
        } else if (integrationMethod.equalsIgnoreCase("trapezoid")) {
            integrateTrapezoid();
        } else // assume rectangle rule
        {
            this.method = "Rectangle";
            integrateLeft();
        }
    }

    /**
     * Compute the integral using Simpson's rule. use formula: ∫[a to b] f(x) dx
     * ≈ (h/3) * [f(a) + 4 * Σ f(x_odd) + 2 * Σ f(x_even) + f(b)] successive
     * estimates is less than {@code precision}, or until {@code maxLoops} is
     * reached.
     */
    public void integrateSimpsons() {
        // Total interval width (b - a)
        final double totalWidth = this.right - this.left;

        // Start with one application of Simpson's rule (two subintervals)
        long n = 1;
        double width = totalWidth;

        // T_1 = (b - a) * (f(a) + f(b)) / 2
        final double fa = this.fun.calculate(this.left);
        final double fb = this.fun.calculate(this.right);
        double T_old = 0.5 * (fa + fb) * width;

        this.loopsUsed = 0;
        this.converged = false;

        boolean firstSimpson = true;
        double S_old = 0D;

        while (true) {
            this.loopsUsed++;

            n *= 2; // double the number of subintervals
            width = totalWidth / n;

            double sumNewPoints = 0D;
            for (long i = 1; i <= n - 1; i += 2) {
                double x = this.left + i * width;
                sumNewPoints += this.fun.calculate(x);
            }

            double T_new = 0.5 * T_old + sumNewPoints * width;

            double S_new = (4.0 * T_new - T_old) / 3.0;

            if (!firstSimpson) {
                // Relative error using successive Simpson estimates
                double relError = Math.abs((S_new - S_old) / S_new);

                if (relError <= this.precision) {
                    this.converged = true;
                    this.estimate = S_new;
                    break;
                }

                if (this.loopsUsed >= this.maxLoops) {
                    this.estimate = S_new;
                    break;
                }
            } else {
                firstSimpson = false;
            }

            T_old = T_new;
            S_old = S_new;

        }
    }

    /**
     * Compute the integral using the Trapezoid rule.
     *
     * Use formula: ∫[a to b] f(x) dx ≈ (h/2) * [f(a) + 2 * Σ f(x_i) + f(b)]
     * successive estimates is less than {@code precision}, or until
     * {@code maxLoops} is reached.
     */
    public void integrateTrapezoid() {
        // Total interval width (b - a)
        final double totalWidth = this.right - this.left;

        // Start with one trapezoid (the entire interval)
        long numTrapezoids = 1;
        // Width of one trapezoid when n = 1
        double width = totalWidth / numTrapezoids;

        // Single trapezoid with left and right endpoints
        double fa = this.fun.calculate(this.left);
        double fb = this.fun.calculate(this.right);
        double T_old = 0.5 * (fa + fb) * width;

        this.loopsUsed = 0;
        this.converged = false;

        while (true) {
            this.loopsUsed++;

            // increase number of trapezoids (halve width)
            numTrapezoids *= 2;
            width = totalWidth / numTrapezoids;

            // Sum of function values at *new* points only
            double sumNewPoints = 0D;
            for (long i = 1; i <= numTrapezoids - 1; i += 2) {
                double x = this.left + i * width;
                sumNewPoints += this.fun.calculate(x);
            }

            // New estimate = 0.5 * old estimate + sum of new points * width
            double T_new = 0.5 * T_old + sumNewPoints * width;

            double relError = Math.abs((T_new - T_old) / T_new);
            if (relError <= this.precision) {
                this.converged = true;
                this.estimate = T_new;
                break;
            }

            if (this.loopsUsed >= this.maxLoops) {
                this.estimate = T_new;
                break;
            }

            T_old = T_new;
        }
    }

    /**
     * Compute the integral using the Left Rectangle rule.
     *
     * This is a simple implementation that repeatedly halves the step size
     * (doubles the number of rectangles) until the relative change between
     * successive estimates is less than {@code precision}, or until
     * {@code maxLoops} is reached.
     */
    public void integrateLeft() {
        // Start with one rectangle (the entire interval)
        long numRectangles = 1;
        // Width of the entire interval (b - a)
        double totalWidth = this.right - this.left;
        // Width of one rectangle when n = 1
        double width = totalWidth / numRectangles;
        // Variables to hold previous and current estimates
        double estimateOld, estimateNew;

        // Initial estimate: f(a) * width
        // (because for the left rectangle rule, we take function value at the left endpoint)
        estimateNew = this.fun.calculate(this.left) * width;
        // Initialize counters
        this.loopsUsed = 0;
        boolean keepGoing = true;

        // Iteratively improve the estimate until we reach desired precision or loop limit
        while (keepGoing) {
            this.loopsUsed++;
            // Save previous estimate before recalculating
            estimateOld = estimateNew;

            // Each refinement doubles the number of rectangles
            numRectangles *= 2;

            // Calculate new (smaller) rectangle width
            width = totalWidth / numRectangles;
            // This variable will accumulate function values at *new points only*
            // The new points are at odd indices: (a + h), (a + 3h), (a + 5h), ...
            double sumNewPoints = 0D;

            // Loop through the *new* left edges that didn’t exist in the previous grid
            for (long i = 1; i <= numRectangles - 1; i += 2) {
                // Compute x-coordinate of the current left edge

                double x = this.left + i * width;
                // Add the function value at this x to the sum

                sumNewPoints += this.fun.calculate(x);
            }

            estimateNew = estimateOld * 0.5 + sumNewPoints * width;

            //Check convergence
            // Stop if the relative change is within desired precision
            double relError = Math.abs((estimateNew - estimateOld) / estimateNew);

            if (relError <= this.precision) {
                this.converged = true;
                keepGoing = false;
                // Stop if we reached the maximum number of allowed refinement loops

            } else if (this.loopsUsed >= this.maxLoops) {
                keepGoing = false;
            }
        }

        this.estimate = estimateNew;
    }

    /**
     * Returns the final estimate from the most recent integration run.
     *
     * @return approximate value of the integral
     */
    public double getEstimate() {
        return this.estimate;
    }

    /**
     * Returns whether the most recent integration run converged within the
     * desired precision.
     *
     * @return true if converged, false if max loops reached without convergence
     */
    public boolean getConvergeStatus() {
        return this.converged;
    }

    /**
     * Returns the number of refinement loops used in the most recent
     * integration run.
     *
     * @return number of loops used
     */
    public int getNumLoops() {
        return this.loopsUsed;
    }

    /**
     * Returns a tab-separated string of the results from the most recent
     * integration run.
     *
     * @return string of method, convergence status, number of loops, and
     * estimate
     */
    public String getResults() {
        return this.method + "\t"
                + this.getConvergeStatus() + "\t\t"
                + this.getNumLoops() + "\t"
                + this.getEstimate();
    }
}
