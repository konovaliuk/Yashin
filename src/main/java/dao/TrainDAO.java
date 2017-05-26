package dao;

import model.entity.Train;

import java.util.List;

public interface TrainDAO {
    List<Train> findAll();
    List<Train> findByRoute(Long route_id);
    Train findById(Long id);

    Train create(Train train);
    Train update(Train train);
    void delete(Train train);

}
