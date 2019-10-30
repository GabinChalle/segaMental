package bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Calcul {

    /*
     * fonction qui permet d'avoir le résultat d'une expression mathématique passée en tant que String en paramètre
     * Les différénts éléments sont répartis dans une ArrayList
     * On recherche le calcul qui a la plus haute priorité, on le fait, puis on met la valeur dans le membre de gauche
     * et on supprime le membre de droite et l'oppérateur
     */

    public static double calculer(String calcul) {
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

    /*
     * Fonction qui génére une expression seulement avec des opérateurs binaire comme : 5 + 7 * 2
     */

    public static String genererCalculBinaire() {
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
                    if(nombres[i+1]==0) {
                        expression += "* ";
                    } else {
                        expression += "/ ";
                    }
                    break;
            }
        }
        expression += nombres[nbOpp];

        return expression;

    }

    /*
     * Fonction qui génére une expression avec des opérateurs binaire et un oppérateur unaire comme : 5 + 7 * rac 2
     */

    public static String genererCalculUnaire() {
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
                        if(nombres[i]==0) {
                            expression += " * ";
                        } else {
                            expression += " / ";
                        }
                        break;
                }
            }
        }
        return expression;
    }
}
