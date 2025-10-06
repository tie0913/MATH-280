
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class Bisection{

    public static double findzeroroot(
        DoubleUnaryOperator f, double pos, double neg, 
        double precision){
    final Function<String, Double> fail = (msg) -> {
        System.err.println("Cannot find root: " + msg);
        return Double.NaN;
    };
        
        double fpos = f.applyAsDouble(pos);
        double fneg = f.applyAsDouble(neg);

        /**
         * Error checking 
         * - Case if the values of function turn out infinite that mean it can be reached limit
         * - Case if f(a) and f(b) are on the same side
         * - Minimal inputing checking like pos and neg is the same and swapping endpoints follow format neg  < pos
         */
        if (!Double.isFinite(fpos) || !Double.isFinite(fneg))
                return fail.apply("f(a) or f(b) is not finite");
        if (pos == neg) return fail.apply("Interval has zero length.");

        if (neg > pos) {
            double t = neg; neg = pos; pos = t;
            double ft = fneg; fneg = fpos; fpos = ft;
        }

        // early return if an endpoint is already a root
        if (fneg == 0.0) return neg;
        if (fpos == 0.0) return pos;

        if (fneg * fpos > 0.0){
            return fail.apply("f(neg) and f(pos) must have opposite side");
        }

        final int maxsafe = 200; //just case infinite loop
        final double fTol = 1e-12;
        int guard = 0;

        double m  = 0.5 * (pos + neg);
        double fm = f.applyAsDouble(m);
        double term = 0.5 * Math.abs(pos - neg); 

        while(term >= precision* Math.max(1.0, Math.abs(m)) && Math.abs(fm) > fTol){
            if (fneg * fm < 0.0) {
                // root is in [neg, m]
                pos  = m;
                fpos = fm;
            } else {
                // root is in [m, pos]
                neg  = m;
                fneg = fm;
            }

            m     = 0.5 * (pos + neg);
            fm    = f.applyAsDouble(m);
            term  = 0.5 * Math.abs(pos - neg);

            if (++guard > maxsafe) {
                System.err.printf(
                    "Cannot find root: exceeded max iterations. Last m=%.16f, |f(m)|=%.3e, half=%.3e%n",
                    m, Math.abs(fm), term);
                return Double.NaN;
            }
        }
        return m;
    }

    static double p(double x){
        return 8*Math.pow(x, 4) - (89D / 9D)*x*x - 1D;
    }

    static double q(double x){
        return (-1.0/ Math.sqrt(300.0)) * Math.pow(x, 5) 
                + (1.0/777.0) * Math.pow(x, 4) 
                + (2.0/3.0)*Math.pow(x, 3)
                + x + 7.0;
    }

    static double f1(double x){
        return Math.exp(5/x) -3;
    }

    final static double A = 90.82 * Math.PI;
    final static double B = 90.23 * Math.PI;

    static double g(double x){
        return Math.exp(-A/x) + Math.exp(-B/x) -1;
    }

    static double gp(double x){
        double eA = Math.exp(-A/x);
        double eB = Math.exp(-B/x);
        double invx2 = 1.0/(x*x);
        return eA * A * invx2 + eB * B * invx2;
    }

    static double initialGuess(){
        double C = 0.5 * (A + B);
        double delta = 0.5 * (A - B);
        double ln2 = Math.log(2.0);
        double inside = C * C - 2.0 * delta * delta * ln2;
        if (inside <= 0.0) return C/ln2;
        return (C + Math.sqrt(inside)) / (2.0 * ln2);
    }

    static double newtonSolv(double xTol) {
        if (xTol <= 0) xTol = 1e-12;          // bảo vệ đầu vào
        final double fTol = 1e-12;
        final int maxIter = 50;

        double x = initialGuess();
        int iter = 0;

        while (iter++ < maxIter) {
            if (x <= 0) x = Math.max(1e-9, initialGuess()); // luôn giữ x > 0

            double fx = g(x);
            if (!Double.isFinite(fx) || Math.abs(fx) <= fTol) return x;

            double dfx = gp(x);
            if (!Double.isFinite(dfx) || dfx == 0.0) break; // tránh chia 0

            double step = fx / dfx;
            x -= step;

            if (Math.abs(step) <= xTol * Math.max(1.0, Math.abs(x))) return x;
        }
        return x; // trả về x hiện tại (gần nghiệm). Bạn có thể trả NaN nếu muốn "soft-fail".
    }   


    public static void main(String[] args) {
        DoubleUnaryOperator f = Bisection::p;
        // 8*x^4 - (89/9)*x^2 - 1
        System.out.println("Find zero root in p(x) in part(a) & part(b)");

        double rNeg = findzeroroot(f, -1.2, -1.0, 0.001);
        double rPos = findzeroroot(f, 1.0, 1.2, 0.001);

        System.out.printf("x = %f in [%f , %f]%n", rNeg,-1.2D,-1.0D);
        System.out.printf("x = %f in [%f , %f]%n", rPos, 1D,1.2D);

        DoubleUnaryOperator q1 = Bisection::q;

        // (-1 / sqrt(300)) * x^5 + (1/777) * x^4 + (2/3)* x^3 + x + 7
        System.out.println("Find zero root in q(x) in part(e)");

        rNeg = findzeroroot(q1, -3D, -2.5D, 0.001);
        rPos = findzeroroot(q1, -2.5D, -2D, 0.001);

        System.out.printf("x = %f in [%f , %f]%n", rNeg,-3D,-2.5D);
        System.out.printf("x = %f in [%f , %f]%n", rPos, -2.5D, -2D);

        DoubleUnaryOperator h = Bisection::f1;

        // (-1 / sqrt(300)) * x^5 + (1/777) * x^4 + (2/3)* x^3 + x + 7
        System.out.println("Find zero root in f(x) in part(f)");

        double zero = findzeroroot(h, 4D, 8D, 0.001);

        System.out.printf("x = %f in [%f , %f]%n", zero, 4D, 8D);

        // e^(-90.82*pi/x) + e^(-90.23*pi/x) = 1
        DoubleUnaryOperator g = Bisection::g;
        System.out.print("Solution for part(g): ");
        System.err.println(newtonSolv(0.001));

    }
}