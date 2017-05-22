package dao;

import entity.Price;
import entity.Train;

import java.util.Date;
import java.util.List;

public interface TrainDAO {
    List<Train> findAll();
    List<Train> findByRoute(Long route_id);
    List<Train> findByDate(Date date);
    Train findById(Long id);

    Train create(Train train);
    Train update(Train train);
    void delete(Train train);

}
