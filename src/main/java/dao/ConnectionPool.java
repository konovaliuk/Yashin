package dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ConnectionPool {
    private static ConnectionPool instance;

    private PoolProperties properties;
    private DataSource source;

    private ConnectionPool() throws SQLException{
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        properties = new PoolProperties();
        properties.setDriverClassName(bundle.getString("jdbc.driverClassName"));
        properties.setUrl(bundle.getString("jdbc.url") + bundle.getString("jdbc.database"));
        properties.setUsername(bundle.getString("jdbc.username"));
        properties.setPassword(bundle.getString("jdbc.password"));

        source = new DataSource();
        source.setPoolProperties(properties);
    }

    public static ConnectionPool getInstance() throws SQLException{
        if(instance == null){
            synchronized (ConnectionPool.class){
                if(instance == null){
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException{
        return this.source.getConnection();
    }
}
