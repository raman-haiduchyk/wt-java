package by.bundesliga.fan.dao.factory;

import by.bundesliga.fan.dao.*;
import by.bundesliga.fan.dao.impl.*;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserImpl = new UserDAOImpl();
    private final ItemDAO sqlItemImpl = new ItemDAOImpl();
    private final OrderDAO sqlOrderImpl = new OrderDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return sqlUserImpl;
    }

    public ItemDAO getItemDAO() {
        return  sqlItemImpl;
    }

    public OrderDAO getOrderDAO() {
        return  sqlOrderImpl;
    }

}
