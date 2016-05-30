package accesnotes;

import Model.DaoBDA;
import Model.DaoJava;
import Model.SourceOracleDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import notes.Etudiant;

public class Requetes {

    private static Etudiant etu; // en E/S;
    // en entrée ( nom, prénom à chercher(en maj ou min)
    // en sortie ( nom(en MAJ), prénom, note trouvés)
    private static String matiere;
    private static boolean erreur_matiere;
    public static final String MATIERE1 = "BDA_G2S3";
    public static final String MATIERE2 = "JAVA_WEB_G2S3";
    public static final int OK = 200; // valeur à renvoyer en cas de succès
    public static final int NOT_FOUND = 2;
    public static final int ERREUR_MATIERE = 3;
    public static final int ERROR = -1;

    public static List<Etudiant> listeEtudiants1;
    public static List<Etudiant> listeEtudiants2;

    public Requetes(String nom, String prenom, String matiere) {
        etu = new Etudiant(nom, prenom, 0.0); // en entrée
        Requetes.matiere = matiere;
        erreur_matiere = false;
      
        System.out.println(SourceOracleDAO.getSource());
       
        
    }

    public Etudiant getEtu() {
        return etu;
    }

    public static int rechEtu() {

        if (erreur_matiere == true) {
            return ERREUR_MATIERE;
        }
        /* Recherche étudiant */
     //   Etudiant etudiant = null;

        boolean trouve = false; 
        if (matiere.equalsIgnoreCase(MATIERE2)) {
            try {
                    DaoJava daoJava = new DaoJava(SourceOracleDAO.getSource().getConnection());
                    etu=daoJava.GetEtu(etu);

                } catch (SQLException e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
        } else {
            try {
                DaoBDA daoBD = new DaoBDA(SourceOracleDAO.getSource().getConnection());
                etu=daoBD.GetEtu(etu);

            } catch (SQLException e) {
                System.out.println("Erreur: " + e.getMessage());
            }

        }
        if (trouve == false) {
            return NOT_FOUND;
        } else {
            return OK;
        }
    }

    public static double getMoyenne() {
        /* calcul et renvoi de la moyenne */
        double moyenne;
        double tot = 0;
        int nombre = 0;
        if (matiere.equalsIgnoreCase(MATIERE1)) {
            for (Etudiant testEtu : listeEtudiants1) {
                tot = tot + testEtu.getNote();
                nombre++;
            }
        } else {
            for (Etudiant testEtu : listeEtudiants2) {
                tot = tot + testEtu.getNote();
                nombre++;
            }
        }
        moyenne = (int) ((tot / nombre) * 100 + 0.5) / 100.0;

        return moyenne;

    }

    public static String getMatiere() {
        return Requetes.matiere.toUpperCase();
    }
} // fin classe Requetes
