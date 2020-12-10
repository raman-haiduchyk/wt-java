package by.bundesliga.fan.controller.command.impl;

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

public class GetUserCartCommand implements Command {

    private static final String ORDER_ID_SESSION_ATTR = "orderId";
    private static final String USER_SESSION_ATTR = "user";
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_orders";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            int userId = (int) session.getAttribute(USER_SESSION_ATTR);
            ArrayList<Integer> ordersList = orderService.getUserOrders(userId);

            if(session.getAttribute(ORDER_ID_SESSION_ATTR) != null){
                int orderId = (int) session.getAttribute(ORDER_ID_SESSION_ATTR);
                ordersList.removeIf(id -> id == orderId);
                Order currentOrder = (Order)orderService.getOrder(orderId);
                session.setAttribute("cartItems", currentOrder.getItems());
            }
            session.setAttribute("orders", ordersList);

            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }
    }
}
