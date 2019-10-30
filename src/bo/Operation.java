package bo;

public class Operation {

    private int id;
    private int score;
    private int idUser;

    public Operation() {
    }

    public Operation(int id, int score, int id_user) {
        this.id = id;
        this.score = score;
        this.idUser = id_user;
    }

    public Operation(int score, int idUser) {
        this.score = score;
        this.idUser = idUser;
    }

    // getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
