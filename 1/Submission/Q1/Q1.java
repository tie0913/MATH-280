package sp.cst.math.assignment01.q1;


public class Q1 {
    public static void main(String[] args) {
        System.out.println("-------------------Here are the Binary Results-----------------------------------");
        calculate(2);
        System.out.println("-------------------Here are the Decimal Results-----------------------------------");
        calculate(10);
    }

    public static void calculate(int base){
        for (int i = 2; i <= 24 ; i++ ) {
            System.out.println("1/"+ i+ " = " +factionBase(1,i,base, 24));
        }
        System.out.println("------------------------------------------------------");
        for(int i = 1; i < 23; i++){
            System.out.println(i + "/" + (i+1) + " = " +factionBase(i,i+1 ,base,24));
        }
        System.out.println("--------------------------------------------------------");
        for(int i = 1; i < 24; i++ ){
            System.out.println(i + "/24" + "=" +factionBase(i, 24,base,24));
        }
    }


    public static String factionBase(int numerator, int denominator, int base, int maxDigits) {
        int numDigits = 0;
        String result = "0.";

        do {
            numerator = numerator * base;
            int newDigits = numerator / denominator;
            result = result + newDigits;
            numDigits++;
            numerator = numerator % denominator;
        }
        while (numerator != 0 && numDigits < maxDigits );
        if(numerator != 0){
            result = result + "...";
        }

        return result;
    }

}

