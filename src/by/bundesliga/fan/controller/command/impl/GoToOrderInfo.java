package by.bundesliga.fan.controller.command.impl;

import by.bundesliga.fan.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToOrderInfo implements Command {

    private static final String ORDER_INFO_PAGE_URI = "WEB-INF/jsp/order_info.jsp";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_INFO_PAGE_URI);
        dispatcher.forward(req, resp);
    }
}
