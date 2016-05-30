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
        String requete = "select * from BDA_G2S3 where nom like'%?' and prenom like'%?' ";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        ResultSet rset = pstmt.executeQuery(requete);
        while (rset.next()) {       // traitement du résulat
            
            String nom = rset.getString(1);
            String prenom = rset.getString(2);
            Double note = rset.getDouble(3);
           temp = new Etudiant(nom,prenom,note);
            
        }
        
        rset.close();
        pstmt.close();
        return temp;
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
}
