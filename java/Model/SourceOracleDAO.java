/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
/**
 *
 * @author Mooneswar.Ramburrun
 */

public class SourceOracleDAO {
private static Connection connexion;
public static Connection getSource(){
    try {
       /* java.util.Properties props;
        props = new java.util.Properties();
        java.io.FileInputStream fichier;
  
        oracle.jdbc.pool.OracleDataSource ods;
        ods = new oracle.jdbc.pool.OracleDataSource();
        ods.setDriverType("thin");
        ods.setPortNumber(1521);
        ods.setDatabaseName("p1422645");
        ods.setUser("p1422645");
        ods.setPassword("234452");
        ods.setServerName("iutdoua-oracle.univ-lyon1.fr");
        ods.setServiceName("orcl.univ-lyon1.fr");*/
        String userid = "p1422645";
        String password = "234452";
        String URL = "jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl";
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        connexion = DriverManager.getConnection(URL, userid, password);
     
       return(connexion);
    }catch (java.sql.SQLException | NumberFormatException e){
        System.out.println("Erreur lors de la lecture du fichier de configuration : " + e.getMessage());
        return null;
    }
 } 
 
 
 
}// fin ConnexionOracleFactory
