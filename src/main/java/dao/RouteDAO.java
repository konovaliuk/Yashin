package dao;

import model.entity.Route;

import java.util.List;

/**
 * Interface to Data Access Object for <b>ROUTE</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface RouteDAO {

    /**
     * Find all ROUTES in DB
     *
     * @author Andrii Yashin
     */
    List<Route> findAll();

    /**
     * Find ROUTE by ID
     *
     * @author Andrii Yashin
     */
    Route findById(Long id);

    /**
     * Find ROUTES by given ID of STATION
     *
     * @author Andrii Yashin
     */
    List<Route> findByFromId(Long id);

    /**
     * Insert new ROUTE
     *
     * @author Andrii Yashin
     */
    Route create(Route route);

    /**
     * Update ROUTE
     *
     * @author Andrii Yashin
     */
    Route update(Route route);

    /**
     * Delete ROUTE
     *
     * @author Andrii Yashin
     */
    void delete(Route route);
}
