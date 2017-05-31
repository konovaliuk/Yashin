package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import exception.InvalidDataBaseOperation;
import model.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.util.LogMessageServiceUtil;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private static final Log LOG = LogFactory.getLog(AdminService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static AdminService INSTANCE;

    private static final String USER_DAO = "UserDAO";

    private static final String GET_ALL_USERS = "getAllUsers()";
    private static final String GET_USERS = "getUsers()";
    private static final String GET_ADMINS = "getAdmins()";
    private static final String ADD_USER = "addUser()";
    private static final String UPDATE_USER = "updateUser()";
    private static final String DELETE_USER = "deleteUser()";

    private DAOFactory factory;

    private AdminService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static AdminService getInstance(){
        if(INSTANCE == null){
            synchronized (AdminService.class){
                if (INSTANCE == null){
                    INSTANCE = new AdminService();
                }
            }
        }

        return INSTANCE;
    }

    public List<User> getAllUsers(){
        List<User> result = factory.createUserDAO().findAll();
        if(result == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, GET_ALL_USERS));
            //throw new InvalidDataBaseOperation();
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, GET_ALL_USERS));
        return result;
    }

    public List<User> getUsers(){
        List<User> result = factory.createUserDAO().findAll();
        if(result == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, GET_USERS));
            //throw new InvalidDataBaseOperation();
        } 
        
        List<User> users = new ArrayList<>();
        for(User user: result){
            if(!user.getAdmin())
                users.add(user);
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, GET_USERS));
        return users;
    }

    public List<User> getAdmins(){
        List<User> result = factory.createUserDAO().findAll();
        if(result == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, GET_ADMINS));
            //throw new InvalidDataBaseOperation();
        }

        List<User> users = new ArrayList<>();
        for(User user: result){
            if(user.getAdmin())
                users.add(user);
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, GET_ADMINS));
        return users;
    }
    
    public User addUser(User user){
        User createdUser = factory.createUserDAO().create(user);
        if (createdUser == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, ADD_USER));
            //throw new InvalidDataBaseOperation();
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, ADD_USER));
        return createdUser;
    }
    
    public User updateUser(User user){
        User updatedUser = factory.createUserDAO().update(user);
        if (updatedUser == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, UPDATE_USER));
            //throw new InvalidDataBaseOperation();
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, UPDATE_USER));
        return updatedUser;
    }
    
    public void deleteUser(User user){
        factory.createUserDAO().delete(user);
        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, DELETE_USER));
    }

}
