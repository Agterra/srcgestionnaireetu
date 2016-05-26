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
public class DaoJava {
      private final Connection connexion;

    public DaoJava(Connection connexion) throws SQLException {
        this.connexion = connexion;     
    }

    public void lireLesEtu(List<Etudiant> lesEtus) throws SQLException {    
        String requete = "select * from JAVA_WEB_G2S3";
        PreparedStatement pstmt = connexion.prepareStatement(requete);
        ResultSet rset = pstmt.executeQuery(requete);
        while (rset.next()) {       // traitement du résulat
            
            String nom = rset.getString(1);
            String prenom = rset.getString(2);
            Double note = rset.getDouble(3);
            Etudiant temp = new Etudiant(nom,prenom,note);
            lesEtus.add(temp);
        }
        rset.close();
        pstmt.close();     
    }
}
