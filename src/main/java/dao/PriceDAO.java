package dao;

import model.entity.Price;

import java.util.List;

public interface PriceDAO {
    List<Price> findAll();
    Price findById(Long id);

    Price create(Price price);
    Price update(Price price);
    void delete(Price price);
}
