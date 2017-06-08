package dao;

import model.entity.Price;

import java.util.List;

/**
 * Interface to Data Access Object for <b>PRICE</b>
 * Provides CRUD operations
 * @author Andrii Yashin
 * @version 1.0
 */
public interface PriceDAO {
    /**
     * Find all PRICES in DB
     *
     * @author Andrii Yashin
     */
    List<Price> findAll();

    /**
     * Find PRICE by ID
     *
     * @author Andrii Yashin
     */
    Price findById(Long id);

    /**
     * Insert new PRICE
     *
     * @author Andrii Yashin
     */
    Price create(Price price);

    /**
     * Update PRICE
     *
     * @author Andrii Yashin
     */
    Price update(Price price);

    /**
     * Delete PRICE
     *
     * @author Andrii Yashin
     */
    void delete(Price price);
}
