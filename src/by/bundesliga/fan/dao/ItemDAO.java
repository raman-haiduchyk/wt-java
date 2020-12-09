package by.bundesliga.fan.dao;

import by.bundesliga.fan.bean.Category;
import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.dao.exception.DAOException;

import java.util.List;

public interface ItemDAO {
    List<Item> findItemsByCategory(int categoryID) throws DAOException;
    List<Category> getCategories() throws DAOException;
    Item getItem(int itemId) throws DAOException;
}
