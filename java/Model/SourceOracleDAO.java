/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
/**
 *
 * @author Mooneswar.Ramburrun
 */

public class SourceOracleDAO {

public static DataSource getSource(){
    try {
        java.util.Properties props;
        props = new java.util.Properties();
        java.io.FileInputStream fichier;
       // fichier = new java.io.FileInputStream("./src/java/connexion.properties");
      //  props.load(fichier);
        oracle.jdbc.pool.OracleDataSource ods;
        ods = new oracle.jdbc.pool.OracleDataSource();
        ods.setDriverType("thin");
        ods.setPortNumber(1521);
        ods.setDatabaseName("p1422645");
        ods.setUser("p1422645");
        ods.setPassword("234452");
        ods.setServerName("iutdoua-oracle.univ-lyon1.fr");
        ods.setServiceName("orcl.univ-lyon1.fr");
     /*   ods.setDriverType(props.getProperty("pilote"));
        ods.setPortNumber(Integer.parseInt(props.getProperty("port")));
        ods.setDatabaseName(props.getProperty("service"));
        ods.setUser(props.getProperty("user"));
        ods.setPassword(props.getProperty("pwd"));
        ods.setServerName(props.getProperty("serveur"));*/
       return(ods);
    }catch (java.sql.SQLException | NumberFormatException e){
        System.out.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
        return null;
    }
 } 
 
 private static java.sql.Connection connexion;
 
}// fin ConnexionOracleFactory
