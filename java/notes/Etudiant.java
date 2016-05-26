package notes;

public class Etudiant {

    private String nom;
    private String prenom;
    private double note;

    public Etudiant() {
        this("", "", 0.0);
    }

    public Etudiant(String nom, String prenom, double note) {
        this.nom = nom;
        this.prenom = prenom;
        this.note = note;
    }

    public String getNom() {
        return nom;
    }

    // .. A COMPLETER(définir les getters et les setters pour nom, prénom et note)

    public String getPrenom() {
        return prenom;
    }

    public double getNote() {
        return note;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNote(double note) {
        this.note = note;
    }
    
}// fin classe Etudiant
