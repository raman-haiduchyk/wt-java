package by.bundesliga.fan.controller.command.impl;

import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.bean.Order;
import by.bundesliga.fan.controller.command.Command;
import by.bundesliga.fan.service.OrderService;
import by.bundesliga.fan.service.exception.ServiceException;
import by.bundesliga.fan.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetOrderInfoCommand implements Command {

    private static final String ORDER_ID_PARAM = "orderId";
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_order_info";
    private static final String REDIRECT_ERROR_COMMAND = "Controller?command=go_to_orders";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            int orderId = Integer.parseInt(req.getParameter(ORDER_ID_PARAM));
            Order order = orderService.getOrder(orderId);
            Map<Item, Integer> itemList = order.getItems();

            session.setAttribute("orderItems", itemList);
            session.setAttribute("orderInfo", order);
            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_ERROR_COMMAND);
        }
    }
}
