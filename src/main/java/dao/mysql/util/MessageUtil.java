package dao.mysql.util;

//TODO: Make methods to FindAll and FindByParameter
public class MessageUtil {
    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE";

    private static final String WITH_ID = "with ID";
    private static final String CANNOT = "Cannot";
    private static final String CANNOT_GET = "Cannot get";
    private static final String CLOSE = "close()";
    private static final String LIST = "LIST";
    private static final String OF = "of";

    private static final Character SPACE = ' ';
    private static final Character EQUALLY = '=';

    /**
     * @param tableName
     * @param id
     * @return CREATE {tableName} with ID = {id}
     */
    public static String createInfoCreate(String tableName, Long id){
        return createInfo(CREATE, tableName, id);
    }

    /**
     * @param tableName
     * @param id
     * @return UPDATE {tableName} with ID = {id}
     */
    public static String createInfoUpdate(String tableName, Long id){
        return createInfo(UPDATE, tableName, id);
    }

    /**
     * @param tableName
     * @param id
     * @return DELETE {tableName} with ID = {id}
     */
    public static String createInfoDelete(String tableName, Long id){
        return createInfo(DELETE, tableName, id);
    }

    /**
     * @param tableName
     * @return Cannot get LIST of {tableName}
     */
    public static String createErrorFindAll(String tableName){
        StringBuilder builder = new StringBuilder();
        builder.append(CANNOT_GET).append(SPACE);
        builder.append(LIST).append(SPACE);
        builder.append(OF).append(SPACE);
        builder.append(tableName.toUpperCase());
        return builder.toString();
    }

    /**
     * @param tableName
     * @param id
     * @return Cannot get {tableName} with ID = {id}
     */
    public static String createErrorFindById(String tableName, Long id){
        return createInfo(CANNOT_GET, tableName, id);
    }

    /**
     * @param tableName
     * @return Cannot CREATE {tableName}
     */
    public static String createErrorCreate(String tableName){
        StringBuilder builder = new StringBuilder();
        builder.append(CANNOT).append(SPACE);
        builder.append(CREATE).append(SPACE);
        builder.append(tableName.toUpperCase());
        return builder.toString();
    }

    /**
     * @param tableName
     * @param id
     * @return Cannot UPDATE {tableName} with ID = {id}
     */
    public static String createErrorUpdate(String tableName, Long id){
        return createInfo(CANNOT + UPDATE, tableName, id);
    }

    /**
     * @param tableName
     * @param id
     * @return Cannot DELETE {tableName} with ID = {id}
     */
    public static String createErrorDelete(String tableName, Long id){
        return createInfo(CANNOT + DELETE, tableName, id);
    }

    /**
     * @return Cannot close()
     */
    public static String createErrorClose(){
        return new StringBuilder()
                .append(CANNOT)
                .append(SPACE)
                .append(CLOSE)
                .toString();
    }

    /**
     * PRIVATE util method.
     * @param operation
     * @param tableName
     * @param id
     * @return
     */
    private static String createInfo(String operation, String tableName, Long id){
        StringBuilder builder = new StringBuilder();
        builder.append(operation).append(SPACE);
        builder.append(tableName.toUpperCase()).append(SPACE);
        builder.append(WITH_ID).append(SPACE);
        builder.append(EQUALLY).append(SPACE);
        builder.append(id);

        return builder.toString();
    }
}
