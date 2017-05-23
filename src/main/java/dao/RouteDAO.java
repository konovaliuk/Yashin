package dao;

import entity.Route;
import entity.Train;

import java.util.List;

public interface RouteDAO {
    List<Route> findAll();
    Route findById(Long id);
    List<Route> findByFromId(Long id);
    List<Route> findByToId(Long id);

    Route create(Route route);
    Route update(Route route);
    void delete(Route route);
}
