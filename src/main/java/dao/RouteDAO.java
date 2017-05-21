package dao;

import entity.Route;
import entity.Train;

import java.util.List;

public interface RouteDAO {
    List<Route> findAll();
    Route findById(Long id);

    Route create(Route route);
    Route update(Route route);
    void delete(Route route);
}
