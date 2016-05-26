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

    private SourceOracleDAO() throws SQLException {
    }

    public static DataSource getSource() {
        OracleDataSource ods = null;
        Properties prop = new Properties();
        try {
            FileInputStream f = new FileInputStream("src/connexion.properties");
            prop.load(f);
            ods = new OracleDataSource();
            ods.setDriverType(prop.getProperty("pilote"));
            ods.setPortNumber(Integer.parseInt(prop.getProperty("port")));
            ods.setServiceName(prop.getProperty("service"));
            ods.setUser(prop.getProperty("user"));
            ods.setPassword(prop.getProperty("pwd"));
            ods.setServerName(prop.getProperty("serveur"));
            f.close();

        } catch (Exception e) {
            System.out.println("ods" + e.getMessage());
        }
        return ods;
    }

}
