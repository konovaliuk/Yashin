package dao;

import model.entity.Train;

import java.util.List;

/**
 * Interface to Data Access Object for <b>TRAIN</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface TrainDAO {
    /**
     * Find all TRAINS in DB
     *
     * @author Andrii Yashin
     */
    List<Train> findAll();

    /**
     * Find TRAINS by given ID of ROUTE
     *
     * @author Andrii Yashin
     */
    List<Train> findByRoute(Long route_id);

    /**
     * Find TRAIN by ID
     *
     * @author Andrii Yashin
     */
    Train findById(Long id);

    /**
     * Insert new TRAIN
     *
     * @author Andrii Yashin
     */
    Train create(Train train);

    /**
     * Update TRAIN
     *
     * @author Andrii Yashin
     */
    Train update(Train train);

    /**
     * Delete TRAIN
     *
     * @author Andrii Yashin
     */
    void delete(Train train);

}
