package dao;

import entity.Request;

import java.util.List;

public interface RequestDAO {
    List<Request> findAll();
    List<Request> findByTrain(Long id);
    List<Request> findByRoute(Long id);
    Request findById(Long id);
    Double findPrice(Long id);

    Request create(Request request);
    Request update(Request request);
    void delete(Request request);
}
