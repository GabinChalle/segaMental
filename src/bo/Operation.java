package bo;

public class Operation {

    private int id;
    private int score;
    private int idUser;

    public Operation() {
    }

    public Operation(int id, int score) {
        this.id = id;
        this.score = score;
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
