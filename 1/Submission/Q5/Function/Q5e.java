package sask.polytech.cst.Function;

public class Q5e extends ACFunction{

    @Override
    public double calculate(double x) {
        return Math.pow(x, 5) * (-1) / Math.sqrt(300) + Math.pow(x, 4)/777 + Math.pow(x, 3) * 2 / 3 + x + 7;
    }

}
