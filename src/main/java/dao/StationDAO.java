package dao;

import entity.Station;

import java.util.List;

public interface StationDAO {
    List<Station> findAll();
    Station findById(Long id);


    Station create(Station route);
    Station update(Station route);
    void delete(Station route);
}
