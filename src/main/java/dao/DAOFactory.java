package dao;

/**
 * Interface for different DAO Factories
 * @author andrew_yashin
 * @version 1.0
 */
public interface DAOFactory {
    PriceDAO createPriceDAO();
    RequestDAO createRequestDAO();
    RouteDAO createRouteDAO();
    TrainDAO createTrainDAO();
    UserDAO createUserDAO();
    StationDAO createStationDAO();
}
