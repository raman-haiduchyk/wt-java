package by.bundesliga.fan.controller.command.impl;

import by.bundesliga.fan.bean.Category;
import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.bean.Order;
import by.bundesliga.fan.controller.command.Command;
import by.bundesliga.fan.service.ItemService;
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

public class GetItemsCommand implements Command {

    private static final String CATEGORY_ID_PARAM = "categoryId";
    private static final String REDIRECT_COMMAND = "Controller?command=go_to_catalog";
    private static final String REDIRECT_ERROR_COMMAND = "Controller?command=get_items";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession(true);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ItemService itemService = serviceFactory.getItemService();

        try {
            List<Category> categories = itemService.getCategories();
            ArrayList<Item> items = new ArrayList<>();
            if (req.getParameter(CATEGORY_ID_PARAM) != null){
                items.addAll(itemService.getItemsByCategory(Integer.parseInt(req.getParameter(CATEGORY_ID_PARAM))));
            } else {
                for (Category category : categories) {
                    items.addAll(itemService.getItemsByCategory(category.getCategoryId()));
                }
            }
            session.setAttribute("catalogItems", items);
            session.setAttribute("categories", categories);
            resp.sendRedirect(REDIRECT_COMMAND);

        } catch (ServiceException | NumberFormatException e) {
            session.removeAttribute(CATEGORY_ID_PARAM);
            resp.sendRedirect(REDIRECT_ERROR_COMMAND);
        }
    }
}
