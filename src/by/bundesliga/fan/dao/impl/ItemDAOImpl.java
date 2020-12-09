package by.bundesliga.fan.dao.impl;

import by.bundesliga.fan.bean.Category;
import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.dao.ItemDAO;
import by.bundesliga.fan.dao.exception.DAOException;
import by.bundesliga.fan.dao.impl.connection.ConnectionPool;
import by.bundesliga.fan.dao.impl.connection.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_NAME = "name";
    private static final String DB_COLUMN_IMAGE_URL = "url";
    private static final String DB_COLUMN_PRICE = "price";
    private static final String DB_COLUMN_COUNT = "count";
    private static final String DB_COLUMN_SIZE = "size";
    private static final String DB_COLUMN_CATEGORY_NAME = "catName";
    private static final String DB_COLUMN_CATEGORY_DESC = "description";
    private static final String DB_COLUMN_MANUFACTURER = "manufacturer";
    private static final String DB_COLUMN_TEAM = "team";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_ITEMS_BY_CATEGORY = "SELECT i.*, c.name as catName  FROM bundesdb.items i INNER JOIN bundesdb.categories c ON i.catID=c.id where i.catID = ? and i.COUNT > 0";
    private static final String GET_ALL_CATEGORIES_SQL = "SELECT * FROM bbundesdb.categories";
    private static final String GET_ITEM_BY_ID = "SELECT * FROM bundesdb.items WHERE id = ?";

    @Override
    public List<Item> findItemsByCategory(int categoryID) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(GET_ITEMS_BY_CATEGORY);
            ps.setInt(1, categoryID);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            List<Item> itemList = new ArrayList<>();

            while (rs.next()) {
                itemList.add(new Item(
                        rs.getInt(DB_COLUMN_ID),
                        rs.getString(DB_COLUMN_IMAGE_URL),
                        rs.getString(DB_COLUMN_CATEGORY_NAME),
                        rs.getString(DB_COLUMN_TEAM),
                        rs.getString(DB_COLUMN_NAME),
                        rs.getString(DB_COLUMN_SIZE),
                        rs.getString(DB_COLUMN_MANUFACTURER),
                        rs.getInt(DB_COLUMN_COUNT),
                        rs.getDouble(DB_COLUMN_PRICE)
                ));
            }
            return itemList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Items", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Items", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public List<Category> getCategories() throws DAOException {
        Statement st = null;
        Connection connecion = null;
        ResultSet rs = null;

        try {
            connecion = connectionPool.takeConnection();
            st = connecion.createStatement();

            rs = st.executeQuery(GET_ALL_CATEGORIES_SQL);

            if (rs == null) {
                return null;
            }

            List<Category> categoryList = new ArrayList<>();

            while (rs.next()) {
                categoryList.add(new Category(
                        rs.getInt(DB_COLUMN_ID),
                        rs.getString(DB_COLUMN_NAME),
                        rs.getString(DB_COLUMN_CATEGORY_DESC)
                ));
            }

            return categoryList;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Categories", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Categories", e);
        } finally {
            connectionPool.returnConnection(connecion);
        }
    }

    @Override
    public Item getItem(int itemId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(GET_ITEM_BY_ID);
            ps.setInt(1, itemId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            Item item = null;

            if (rs.next()) {
                item = new Item(
                        rs.getInt(DB_COLUMN_ID),
                        rs.getString(DB_COLUMN_IMAGE_URL),
                        rs.getString(GET_ITEMS_BY_CATEGORY),
                        rs.getString(DB_COLUMN_TEAM),
                        rs.getString(DB_COLUMN_NAME),
                        rs.getString(DB_COLUMN_SIZE),
                        rs.getString(DB_COLUMN_MANUFACTURER),
                        rs.getInt(DB_COLUMN_COUNT),
                        rs.getDouble(DB_COLUMN_PRICE)
                );
            }

            return item;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while find Items", e);
        } catch (SQLException e) {
            throw new DAOException("Error while find Items", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
