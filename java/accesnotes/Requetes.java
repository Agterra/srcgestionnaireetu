package accesnotes;

import Model.DaoBDA;
import Model.DaoJava;
import Model.SourceOracleDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    private static DaoJava daoJava;
    private static DaoBDA daoBD;
    public static List<Etudiant> listeEtudiants1;
    public static List<Etudiant> listeEtudiants2;

    public Requetes(String nom, String prenom, String matiere) {
        etu = new Etudiant(nom, prenom, 0.0); // en entrée
        Requetes.matiere = matiere;
        erreur_matiere = false;

        try {

            daoJava = new DaoJava(SourceOracleDAO.getSource());
            daoBD = new DaoBDA(SourceOracleDAO.getSource());
        } catch (SQLException e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public Etudiant getEtu() {
        return etu;
    }

    public static int rechEtu() {

        if (erreur_matiere == true) {
            return ERREUR_MATIERE;
        }
        /* Recherche étudiant */
        boolean trouve = false;
        if (etu.getNom()=="?" && etu.getPrenom()=="?"){
         try{
            int i;
            boolean m; 
            Random rand = new Random();
             m=rand.nextBoolean();
             if (m==true){
                 i = rand.nextInt(daoJava.getCount());
                 daoJava.GetAlea(i);
                 trouve = true;
             }else{
                 i = rand.nextInt(daoBD.getCount());
                 daoBD.GetAlea(i);
                 trouve = true;
             }
         } catch (SQLException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }

        
        if (matiere.equalsIgnoreCase(MATIERE2)) {
            try {

             daoJava.GetEtu(etu);
             System.out.println("aa " + etu.getNote());
                if(etu!=null){
                    trouve = true;
               }
            } catch (SQLException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        } else {
            try {
                   
               daoBD.GetEtu(etu);
                
               if(etu!=null){
                    trouve = true;
               }
               
            } catch (Exception e) {
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
        double moyenne=0;
        double tot = 0;
        int nombre = 0;
        try{
            if (matiere.equalsIgnoreCase(MATIERE1)) {
                moyenne=daoBD.getMoy();
            } else {
                moyenne=daoJava.getMoy();
            }
        }catch(Exception e){
            System.out.println("Erreur: " + e.getMessage());
        }
   //     moyenne = (int) ((tot / nombre) * 100 + 0.5) / 100.0;

        return moyenne;

    }

    public static String getMatiere() {
        return Requetes.matiere.toUpperCase();
    }
} // fin classe Requetes
