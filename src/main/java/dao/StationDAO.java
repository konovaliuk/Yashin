package dao;

import model.entity.Station;

import java.util.List;

public interface StationDAO {
    List<Station> findAll();
    Station findById(Long id);


    Station create(Station station);
    Station update(Station station);
    void delete(Station station);
}
