package by.bundesliga.fan.controller;

import by.bundesliga.fan.controller.exception.ControllerRuntimeException;
import by.bundesliga.fan.dao.impl.connection.ConnectionPool;
import by.bundesliga.fan.dao.impl.connection.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            ConnectionPool.getInstance().InitPoolData();
        }
        catch (ConnectionPoolException e) {
            throw new ControllerRuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().dispose();
    }
}
