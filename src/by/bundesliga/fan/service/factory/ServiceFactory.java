package by.bundesliga.fan.service.factory;

import by.bundesliga.fan.service.*;
import by.bundesliga.fan.service.impl.*;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final ItemService itemService = new ItemServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ItemService getItemService() {
        return itemService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
