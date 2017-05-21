package test;

import dao.AbstractDAOFactory;
import dao.DAOFactory;
import dao.DataBase;
import dao.PriceDAO;

public class Main {
    public static void main(String[] args) {
        DAOFactory factory = AbstractDAOFactory.createDAOFactory(DataBase.MYSQL);
        PriceDAO priceDAO = factory.createPriceDAO();

        System.out.println(priceDAO.findAll());
        System.out.println(priceDAO.findById(6L));
    }
}
