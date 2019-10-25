package bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Calcul {

    public double calculer(String calcul) {
        ArrayList<String> expression = new ArrayList<>();
        Collections.addAll(expression, calcul.split(" "));
        while (expression.size() != 1) {
            while (expression.contains("rac")) {
                int index = expression.indexOf("rac");
                double rac = Math.sqrt(Double.parseDouble(expression.get(index + 1)));
                expression.set(index, String.valueOf(rac));
                expression.remove(index + 1);
            }

            while (expression.contains("inv")) {
                int index = expression.indexOf("inv");
                double inv = Double.parseDouble(expression.get(index + 1)) * -1;
                expression.set(index, String.valueOf(inv));
                expression.remove(index + 1);
            }

            int priority = 100;

            if (expression.contains("*") && expression.indexOf("*") < priority) {
                priority = expression.indexOf("*");
            }
            if (expression.contains("/") && expression.indexOf("/") < priority) {
                priority = expression.indexOf("/");
            }
            if (priority == 100) {
                priority = 1;
            }
            double res = 0;
            switch (expression.get(priority)) {
                case "+":
                    res = Double.parseDouble(expression.get(priority - 1)) + Double.parseDouble(expression.get(priority + 1));
                    break;
                case "-":
                    res = Double.parseDouble(expression.get(priority - 1)) - Double.parseDouble(expression.get(priority + 1));
                    break;
                case "*":
                    res = Double.parseDouble(expression.get(priority - 1)) * Double.parseDouble(expression.get(priority + 1));
                    break;
                case "/":
                    res = Double.parseDouble(expression.get(priority - 1)) / Double.parseDouble(expression.get(priority + 1));
                    break;
                default:
                    break;
            }
            expression.set(priority - 1, String.valueOf(res));
            expression.remove(priority + 1);
            expression.remove(priority);
        }
        return Double.parseDouble(expression.get(0));
    }

    public String genererCalculBinaire() {
        int nbOpp = 2;
        int[] nombres = new int[nbOpp + 1];
        String expression = "";

        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = new Random().nextInt(10);
        }

        for (int i = 0; i < nbOpp; i++) {
            expression += nombres[i] + " ";
            switch (OperateurBinaire.randomOpperation()) {
                case PLUS:
                    expression += "+ ";
                    break;
                case FOIS:
                    expression += "* ";
                    break;
                case MOINS:
                    expression += "- ";
                    break;
                case DIVISE:
                    expression += "/ ";
                    break;
            }
        }
        expression += nombres[nbOpp];

        return expression;

    }

    public String genererCalculUnaire() {
        int nbOpp = 2;
        int[] nombres = new int[nbOpp + 1];
        String expression = "";
        int posOppUnaire = new Random().nextInt(nbOpp + 1);

        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = new Random().nextInt(10);
        }

        for (int i = 0; i <= nbOpp; i++) {
            if (i == posOppUnaire) {
                switch (OperateurUnaire.randomOpperation()) {
                    case RACINE:
                        expression += "rac ";
                        break;
                    case INVERSE:
                        expression += "inv ";
                        break;
                }
            }

            expression += nombres[i];

            if (i != nbOpp) {
                switch (OperateurBinaire.randomOpperation()) {
                    case PLUS:
                        expression += " + ";
                        break;
                    case FOIS:
                        expression += " * ";
                        break;
                    case MOINS:
                        expression += " - ";
                        break;
                    case DIVISE:
                        expression += " / ";
                        break;
                }
            }
        }

        return expression;
    }
}
