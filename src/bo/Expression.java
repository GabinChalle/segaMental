package bo;

public class Expression {
    private int id;
    private String libelle;
    private double resAttendu;
    private double resDonnee;
    private int idOp;

    public Expression() {
    }

    public Expression(int id, String libelle, double resAttendu, double resDonnee, int idOp) {
        this.id = id;
        this.libelle = libelle;
        this.resAttendu = resAttendu;
        this.resDonnee = resDonnee;
        this.idOp = idOp;
    }

    //getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getResAttendu() {
        return resAttendu;
    }

    public void setResAttendu(double resAttendu) {
        this.resAttendu = resAttendu;
    }

    public double getResDonnee() {
        return resDonnee;
    }

    public void setResDonnee(double resDonnee) {
        this.resDonnee = resDonnee;
    }

    public int getIdOp() {
        return idOp;
    }

    public void setIdOp(int idOp) {
        this.idOp = idOp;
    }
}
