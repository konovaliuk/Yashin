package dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import util.Configuration;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static ConnectionPool instance;

    private PoolProperties properties;
    private DataSource source;

    private ConnectionPool() throws SQLException{
        properties = new PoolProperties();
        properties.setDriverClassName(Configuration.getInstance().getConfig(Configuration.DRIVER_CLASS_NAME));
        properties.setUrl(Configuration.getInstance().getConfig(Configuration.URL) + Configuration.getInstance().getConfig(Configuration.DATABASE));
        properties.setUsername(Configuration.getInstance().getConfig(Configuration.USERNAME));
        properties.setPassword(Configuration.getInstance().getConfig(Configuration.PASSWORD));

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
