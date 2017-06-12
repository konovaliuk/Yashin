package dao;

import exception.InvalidDataBaseOperation;
import model.entity.Request;

import java.sql.Connection;
import java.util.List;

/**
 * Interface to Data Access Object for <b>REQUEST</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface RequestDAO {
    /**
     * Find all REQUESTS in DB
     *
     * @author Andrii Yashin
     */
    List<Request> findAll();

    /**
     * Find REQUEST by ID
     *
     * @author Andrii Yashin
     */
    Request findById(Long id);

    /**
     * Insert new REQUEST
     *
     * @author Andrii Yashin
     */
    Request create(Request request);

    /**
     * Update REQUEST
     *
     * @author Andrii Yashin
     */
    Request update(Request request);

    /**
     * Delete REQUEST
     *
     * @author Andrii Yashin
     */
    void delete(Request request);


    void deleteRequests(List<Request> requests) throws InvalidDataBaseOperation;
    void approveRequests(List<Request> requests) throws InvalidDataBaseOperation;

}
