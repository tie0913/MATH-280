package sp.cst.regina.math.assignment01.q1;

public class Solution {



    public static void printPositionalNumber(int numerator, int denominator, int base, int precision){


        int temp = numerator;
        int count = 0;
        StringBuilder result = new StringBuilder();
        while(temp != 0 && count < precision){
            int product = temp * base;
            int pos = product/denominator;
            result.append(pos);
            temp = product%denominator;
            count++;
        }


        boolean exactly = temp == 0;

        StringBuilder output = new StringBuilder("");

        output.append(numerator).append("/").append(denominator).append("=")
        .append("0.").append(result).append(exactly?" can be represent exactly" : " can not be represent exactly");

        System.out.println(output);

    }


    public static void main(String [] args){

        int precision = 30;

        for(int i = 2; i <= 24; i++){
            printPositionalNumber(1, i, 2, precision);
        }

        for(int i = 1; i< 24; i++){
            printPositionalNumber(i, i + 1, 2, precision);
        }

        for(int i = 1; i < 24; i++){
            printPositionalNumber(i, 24, 2, precision);
        }
    }

}
