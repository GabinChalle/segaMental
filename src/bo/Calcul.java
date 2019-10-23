package bo;

import java.util.ArrayList;
import java.util.Collections;

public class Calcul {

    public static double calculer(String calcul) {
        String[] expression = calcul.split(" ");
        ArrayList<String> test = new ArrayList<>();
        Collections.addAll(test, expression);
        while (test.size()!=1)
        {
            while(test.contains("rac")) {
                int index = test.indexOf("rac");
                double rac = Math.sqrt(Double.parseDouble(test.get(index+1)));
                test.set(index, String.valueOf(rac));
                test.remove(index+1);
            }

            while(test.contains("inv")) {
                int index = test.indexOf("inv");
                double inv = Double.parseDouble(test.get(index+1)) * -1;
                test.set(index, String.valueOf(inv));
                test.remove(index+1);
            }

            int priority = 100;

            if(test.contains("*") && test.indexOf("*")<priority) {
                priority = test.indexOf("*");
            }
            if(test.contains("/") && test.indexOf("/")<priority) {
                priority = test.indexOf("/");
            }
            if(priority==100) {
                priority= 1;
            }
            System.out.println(priority);
            double res = 0;
            switch (test.get(priority)) {
                case "+" :
                    res = Double.parseDouble(test.get(priority-1)) + Double.parseDouble(test.get(priority+1));
                    break;
                case "-" :
                    res = Double.parseDouble(test.get(priority-1)) - Double.parseDouble(test.get(priority+1));
                    break;
                case "*" :
                    res = Double.parseDouble(test.get(priority-1)) * Double.parseDouble(test.get(priority+1));
                    break;
                case "/" :
                    res = Double.parseDouble(test.get(priority-1)) / Double.parseDouble(test.get(priority+1));
                    break;
                default:
                    break;
            }
            System.out.println(res);
            test.set(priority-1, String.valueOf(res));
            test.remove(priority+1);
            test.remove(priority);
        }
        return Double.parseDouble(test.get(0));
    }


    public static void main(String[] args) {
        String calcul = "2.16 + inv -4 * 5";

        double test = calculer(calcul);
        System.out.println("test=" + test);
    }
}
