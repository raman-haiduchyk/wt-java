package by.bundesliga.fan.service.impl;

import by.bundesliga.fan.bean.Category;
import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.dao.ItemDAO;
import by.bundesliga.fan.dao.exception.DAOException;
import by.bundesliga.fan.dao.factory.DAOFactory;
import by.bundesliga.fan.service.ItemService;
import by.bundesliga.fan.service.exception.ServiceException;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override

    public List<Category> getCategories() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();
        try {
            return itemDAO.getCategories();
        } catch (DAOException e) {
            throw new ServiceException("Error while find all categories", e);
        }
    }

    @Override
    public List<Item> getItemsByCategory(int categoryId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (categoryId < 1) {
            return null;
        }
        try {
            return itemDAO.findItemsByCategory(categoryId);
        } catch (DAOException e) {
            throw new ServiceException("Error while find Items by category", e);
        }
    }

    @Override
    public Item getItem(int itemId) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        ItemDAO itemDAO = factory.getItemDAO();

        if (itemId < 1) {
            return null;
        }

        try {
            return itemDAO.getItem(itemId);
        } catch (DAOException e) {
            throw new ServiceException("Error while find Item by ID", e);
        }
    }
}
