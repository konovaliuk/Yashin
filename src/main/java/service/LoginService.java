package service;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import exception.InvalidDataBaseOperation;
import model.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import service.util.LogMessageServiceUtil;

public class LoginService {
    private static final Log LOG = LogFactory.getLog(LoginService.class);
    private static final DataBase DB = DataBase.MYSQL;
    private static LoginService INSTANCE;

    private static final String USER_DAO = "UserDAO";

    private static final String ADD_USER = "addUser()";
    private static final String IS_PRESENT_LOGIN = "addUser()";

    private DAOFactory factory;

    private LoginService(){
        factory = AbstractDAOFactory.createDAOFactory(DB);
    }

    public static LoginService getInstance(){
        if(INSTANCE == null){
            synchronized (LoginService.class){
                if (INSTANCE == null){
                    INSTANCE = new LoginService();
                }
            }
        }

        return INSTANCE;
    }

    public User isPresentLogin(String login){
        User user = factory.createUserDAO().findByEmail(login);
        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, IS_PRESENT_LOGIN));
        return user;
    }

    public User addUser(User user) throws InvalidDataBaseOperation{
        User createdUser = factory.createUserDAO().create(user);
        if (createdUser == null){
            LOG.error(LogMessageServiceUtil.createMethodError(USER_DAO, ADD_USER));
            throw new InvalidDataBaseOperation();
        }

        LOG.info(LogMessageServiceUtil.createMethodInfo(USER_DAO, ADD_USER));
        return createdUser;
    }
}
