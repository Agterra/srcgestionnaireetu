/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import notes.Etudiant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Claire
 */
public class DaoBDA {
      private final Connection connexion;
      private Etudiant temp;
    public DaoBDA(Connection connexion) throws SQLException {
        this.connexion = connexion;     
    }

    public Etudiant GetEtu(Etudiant etu) throws SQLException {    
    Double note=1.1;
    Etudiant tmp= new Etudiant();
         String requete = "select nom,prenom,note from BDA_G2S3 where upper(nom) like ? and upper(prenom) like ? ";
         PreparedStatement pstmt = connexion.prepareStatement(requete);
         pstmt.setString(1, "%"+etu.getNom().toUpperCase()+"&");
        pstmt.setString(2, "%"+etu.getPrenom().toUpperCase()+"&");
        
        ResultSet rset = pstmt.executeQuery();
       
        while (rset.next()) {       // traitement du résulat
            
            String nom = rset.getString(1);
            String prenom = rset.getString(2);
             note = rset.getDouble(3);
           tmp.setNom(nom); 
            tmp.setPrenom(prenom); 
             tmp.setNote(note); 
           System.out.println("aaaaaa "+note);
        }
         
        rset.close();
        pstmt.close();
        return tmp;
    }
    public double getMoy() throws SQLException {  
        Double note=0d;
        String requete = "select avg(note) from BDA_G2S3 ";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        ResultSet rset = pstmt.executeQuery(requete);
        while (rset.next()) {       // traitement du résulat
            
            
            note = rset.getDouble(1);
                       
        }
        
        rset.close();
        pstmt.close();
        return note;
    }
    public  List<Etudiant> lireLesEtu()throws SQLException{
       List<Etudiant> laListe = new ArrayList<>();
       Etudiant temp = new Etudiant();
       try{
         String requete = "select * from BDA_G2S3"  ;
        PreparedStatement pstmt = connexion.prepareStatement(requete);
          ResultSet rset = pstmt.executeQuery();
           while(rset.next()) { // traitement du résulat
                String nomEtu = rset.getString(1);
                String PrenomEtu = rset.getString(2);
                double noteEtu = rset.getDouble(3);
                temp.setNom(nomEtu);
                temp.setPrenom(PrenomEtu);
                temp.setNote(noteEtu);
                laListe.add(temp);
            }
            rset.close();
            pstmt.close();
       
         
               }catch(Exception e){
                   System.out.println("Erreur: "+e.getMessage());
               }
       return laListe;
   }
     public int getCount()throws SQLException{
          int nb=0;
         String requete = "select count(nom) from BDA_G2S3"  ;
        PreparedStatement pstmt = connexion.prepareStatement(requete);
          ResultSet rset = pstmt.executeQuery();
           while(rset.next()) { // traitement du résulat
                 nb = rset.getInt(1);
           }
           return nb;
     }
     public void GetAlea(int i) throws SQLException {
          Etudiant temp = new Etudiant();
          int cont=0;
        String requete = "select * from BDA_G2S3  ";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
      

        ResultSet rset = pstmt.executeQuery();

        while (rset.next()&& cont<=i) {       // traitement du résulat

            String nom = rset.getString(1);
            String prenom = rset.getString(2);
            double note = rset.getDouble(3);
            temp.setNom(nom);
            temp.setPrenom(prenom);
            temp.setNote(note);
            //System.out.println("aa " + nom);
        }

        rset.close();
        pstmt.close();

    }
}
