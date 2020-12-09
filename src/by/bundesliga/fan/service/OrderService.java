package by.bundesliga.fan.service;

import by.bundesliga.fan.bean.Order;
import by.bundesliga.fan.service.exception.ServiceException;

import java.util.ArrayList;

public interface OrderService {
    int createEmptyOrder(int userId) throws ServiceException;
    boolean addItem(int orderId, int itemId, int count) throws ServiceException;
    boolean deleteItem(int orderId, int itemId) throws ServiceException;
    Order getOrder(int orderId) throws ServiceException;
    ArrayList<Integer> getUserOrders(int userId) throws ServiceException;
    void confirmOrder(Order order) throws ServiceException;
}
