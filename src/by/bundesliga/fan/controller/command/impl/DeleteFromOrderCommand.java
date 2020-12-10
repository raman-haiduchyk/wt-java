package by.bundesliga.fan.controller.command.impl;

import by.bundesliga.fan.controller.command.Command;
import by.bundesliga.fan.service.OrderService;
import by.bundesliga.fan.service.exception.ServiceException;
import by.bundesliga.fan.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFromOrderCommand implements Command {

    private static final String ITEM_ID_REQUEST_ATTR = "itemId";
    private static final String ORDER_ID_SESSION_ATTR = "orderId";
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_main";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            int orderId = (int) session.getAttribute(ORDER_ID_SESSION_ATTR);
            int itemId = Integer.parseInt(req.getParameter(ITEM_ID_REQUEST_ATTR));

            orderService.deleteItem(orderId, itemId);

            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (ServiceException | NumberFormatException e) {
            resp.sendRedirect(REDIRECT_COMMAND);
        }
    }
}
