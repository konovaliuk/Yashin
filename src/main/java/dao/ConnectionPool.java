package dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import util.Config;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private static ConnectionPool instance;

    private PoolProperties properties;
    private DataSource source;

    private ConnectionPool() throws SQLException{
        properties = new PoolProperties();
        properties.setDriverClassName(Config.getInstance().getConfig(Config.DRIVER_CLASS_NAME));
        properties.setUrl(Config.getInstance().getConfig(Config.URL) + Config.getInstance().getConfig(Config.DATABASE));
        properties.setUsername(Config.getInstance().getConfig(Config.USERNAME));
        properties.setPassword(Config.getInstance().getConfig(Config.PASSWORD));

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
