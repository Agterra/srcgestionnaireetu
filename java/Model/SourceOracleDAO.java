package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Claire
 */
public class SourceOracleDAO {

    public static DataSource getSource() {
        OracleDataSource ods = null;
        Properties prop = new Properties();
        try {
         //  FileInputStream f = new FileInputStream("connexion.properties");
          // prop.load(f);
            ods = new OracleDataSource();
            ods.setDriverType("thin");
            ods.setPortNumber(1521);
            ods.setServiceName("orcl.univ-lyon1.fr");
            ods.setUser("p1422645");
            ods.setPassword("234452");
            ods.setServerName("iutdoua-oracle.univ-lyon1.fr");
         //   f.close();

        } catch (Exception e) {
            System.out.println("ods: " + e.getMessage());
        }
        return ods;
    }

}
