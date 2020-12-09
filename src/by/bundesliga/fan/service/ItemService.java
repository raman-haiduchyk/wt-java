package by.bundesliga.fan.service;

import by.bundesliga.fan.bean.Category;
import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.service.exception.ServiceException;

import java.util.List;

public interface ItemService {
    List<Category> getCategories() throws ServiceException;
    List<Item> getItemsByCategory(int categoryId) throws ServiceException;
    Item getItem(int itemId) throws ServiceException;
}
