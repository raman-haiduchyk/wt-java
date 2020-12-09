package by.bundesliga.fan.dao;

import by.bundesliga.fan.bean.Order;
import by.bundesliga.fan.dao.exception.DAOException;

import java.util.ArrayList;

public interface OrderDAO {
    int createEmptyOrder(int userId) throws DAOException;
    void addItem(int orderId, int itemId, int count) throws DAOException;
    void deleteItem(int orderId, int itemId) throws DAOException;
    Order getOrder(int orderId) throws DAOException;
    ArrayList<Integer> getUserOrders(int userId) throws DAOException;
    void confirmOrder(Order order) throws DAOException;
}
