package model;

import bo.Calcul;
import java.io.Serializable;

public class GameBean implements Serializable {

    private static final String RESULTS = "result";


    public GameBean() {
    }

    public String getBinaryExp() {return Calcul.genererCalculBinaire();}

    public String getUnaryExp() {return Calcul.genererCalculUnaire();}

}
