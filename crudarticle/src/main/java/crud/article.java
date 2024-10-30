package crud;

public class article  {
    private int id;
    private String nom;
    private String date;

    // Constructeur sans argument
    public article() {}

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;  // Corrigé ici
    }
}
