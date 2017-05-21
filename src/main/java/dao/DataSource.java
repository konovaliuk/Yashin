package dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    private static DataSource instance;
    private BasicDataSource source;

    private DataSource() throws IOException, SQLException{
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        source = new BasicDataSource();
        source.setDriverClassName(bundle.getString("jdbc.driverClassName"));
        source.setUrl(bundle.getString("jdbc.url") + bundle.getString("jdbc.database"));
        source.setUsername(bundle.getString("jdbc.username"));
        source.setPassword(bundle.getString("jdbc.password"));

        source.setMinIdle(5);
        source.setMaxIdle(20);
    }

    public static DataSource getInstance() throws IOException, SQLException{
        if(instance == null){
            synchronized (DataSource.class){
                if(instance == null){
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return this.source.getConnection();
    }
}
