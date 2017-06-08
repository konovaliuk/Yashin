package dao;

import model.entity.Station;

import java.util.List;

/**
 * Interface to Data Access Object for <b>STATION</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface StationDAO {
    /**
     * Find all STATIONS in DB
     *
     * @author Andrii Yashin
     */
    List<Station> findAll();

    /**
     * Find STATION by ID
     *
     * @author Andrii Yashin
     */
    Station findById(Long id);

    /**
     * Insert new STATION
     *
     * @author Andrii Yashin
     */
    Station create(Station station);

    /**
     * Update STATION
     *
     * @author Andrii Yashin
     */
    Station update(Station station);

    /**
     * Delete STATION
     *
     * @author Andrii Yashin
     */
    void delete(Station station);
}
