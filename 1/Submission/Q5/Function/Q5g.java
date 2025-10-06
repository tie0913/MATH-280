package sask.polytech.cst.Function;

public class Q5g extends ACFunction{

    @Override
    public double calculate(double x) {
        return Math.exp((-90.82) * Math.PI/x) + Math.exp((-90.23) * Math.PI/x) - 1;
    }

}
