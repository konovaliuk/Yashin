package util;

import java.util.ResourceBundle;

public class Config {
    private static Config INSTANCE;
    private static ResourceBundle bundle;
    private static final String BUNDLE_NAME = "config";

    public static final String DRIVER_CLASS_NAME = "config.driverClassName";
    public static final String URL = "config.url";
    public static final String USERNAME = "config.username";
    public static final String PASSWORD = "config.password";
    public static final String DATABASE = "config.database";
    public static final String REGISTER = "config.register";

    public static final String LOGIN = "config.login";
    public static final String DATE = "config.date";
    public static final String ADMIN = "config.admin";
    public static final String ORDER = "config.order";

    private Config(){
        bundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public static Config getInstance(){
        if(INSTANCE == null){
            synchronized (Config.class){
                if (INSTANCE == null){
                    INSTANCE = new Config();
                }
            }
        }

        return INSTANCE;
    }

    public String getConfig(String parameter){
        return bundle.getString(parameter);
    }
}
