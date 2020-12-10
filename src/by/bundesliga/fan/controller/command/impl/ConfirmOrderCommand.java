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

public class ConfirmOrderCommand implements Command {

    private static final String REDIRECT_COMMAND = "Controller?command=go_to_main";
    private static final String ORDER_ID_SESSION_ATTR = "orderId";
    private static final String CONFIRM_OK = "&confirm=ok&orderId=";
    private static final String CONFIRM_ERROR = "&error=confirmOrder";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Order order;
        int orderId;

        try {
            orderId = (int) session.getAttribute(ORDER_ID_SESSION_ATTR);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            order = orderService.getOrder(orderId);

            orderService.confirmOrder(order);

            session.removeAttribute(ORDER_ID_SESSION_ATTR);

            resp.sendRedirect(REDIRECT_COMMAND + CONFIRM_OK + orderId);

        } catch (ServiceException e) {
            resp.sendRedirect(REDIRECT_COMMAND + CONFIRM_ERROR);
        }

    }
}
