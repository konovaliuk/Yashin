package dao;

import dao.mysql.MySQLFactory;
import java.util.NoSuchElementException;

public class AbstractDAOFactory {
    public static DAOFactory createDAOFactory(DataBase dataBase){
        DAOFactory factory = null;
        switch (dataBase){
            case MYSQL: factory = new MySQLFactory(); break;
        }

        return factory;
    }
}
