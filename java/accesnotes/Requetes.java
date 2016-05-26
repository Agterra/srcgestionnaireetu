package accesnotes;

import Model.DaoBDA;
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
        etu = new Etudiant(nom,prenom,0.0); // en entrée
        Requetes.matiere = matiere;
        erreur_matiere = false;
        listeEtudiants1 = new ArrayList<>();
        listeEtudiants2 = new ArrayList<>();
        if( matiere.equalsIgnoreCase(MATIERE1)){
       /*  listeEtudiants1.add(new Etudiant("DUPOND", "Jean", 11.50));
         listeEtudiants1.add(new Etudiant("DURAND", "Jacques", 12.50));
         listeEtudiants1.add(new Etudiant("GABRIELE", "Patricia", 13.50));*/
            try{
            DaoBDA daoBD=new DaoBDA(SourceOracleDAO.getSource().getConnection());
           daoBD.lireLesEtu(listeEtudiants1);
              
            }catch(SQLException e ){
               
            }
        }else {
          if( matiere.equalsIgnoreCase(MATIERE2)){
           listeEtudiants2.add(new Etudiant("MARTIN", "Jacques", 11.00));
           listeEtudiants2.add(new Etudiant("MATHIEU", "Henri", 13.50));
           listeEtudiants2.add(new Etudiant("TRIOLET", "Elsa", 16.50));
          }
          else {
            erreur_matiere = true;  
          }
        }
    }

    public Etudiant getEtu() {
        return etu;
    }

    public static int rechEtu() {
        
        if ( erreur_matiere==true ) {
            return ERREUR_MATIERE;
        }
        /* Recherche étudiant */
     //   Etudiant etudiant = null;
       
        boolean trouve = false; //= false;
       if(matiere.equalsIgnoreCase(MATIERE1)){
            for (Etudiant testEtu : listeEtudiants1){
                if(testEtu.getNom().toUpperCase().contains(etu.getNom().toUpperCase())&& testEtu.getPrenom().toUpperCase().contains(etu.getPrenom().toUpperCase())){
                    trouve=true;
                    etu=testEtu;
                }
            }
        }else{
            for (Etudiant testEtu : listeEtudiants2){
                if(testEtu.getNom().toUpperCase().contains(etu.getNom().toUpperCase())&& testEtu.getPrenom().toUpperCase().contains(etu.getPrenom().toUpperCase())){
                    trouve=true;
                    etu=testEtu;
                }
            }
            
        }
        if (trouve==false) {
            return NOT_FOUND;
        } else {
           return OK;
        }
     }
   public static double getMoyenne() {
        /* calcul et renvoi de la moyenne */ 
     double moyenne;  
     double tot=0;
     int nombre=0;
     if(matiere.equalsIgnoreCase(MATIERE1)){
         for (Etudiant testEtu : listeEtudiants1){
            tot=tot+ testEtu.getNote();
            nombre++;
            }
     }else{
        for (Etudiant testEtu : listeEtudiants2){
               tot=tot+ testEtu.getNote();
               nombre++;
               }
     }
     moyenne=(int)((tot/nombre)*100+0.5)/100.0;
     
     return moyenne;
     
   } 
   
   public static String getMatiere() {
       return Requetes.matiere.toUpperCase();
   }
} // fin classe Requetes
