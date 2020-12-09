package by.bundesliga.fan.dao.impl;

import by.bundesliga.fan.bean.Item;
import by.bundesliga.fan.bean.Order;
import by.bundesliga.fan.bean.User;
import by.bundesliga.fan.dao.OrderDAO;
import by.bundesliga.fan.dao.exception.DAOException;
import by.bundesliga.fan.dao.factory.DAOFactory;
import by.bundesliga.fan.dao.impl.connection.ConnectionPool;
import by.bundesliga.fan.dao.impl.connection.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String DB_COLUMN_ORDER_ID = "order_id";
    private static final String DB_COLUMN_DATE = "order_date";
    private static final String DB_COLUMN_COMMENT = "comment";
    private static final String DB_COLUMN_STATE = "status_name";
    private static final String DB_COLUMN_ITEM_ID = "item_id";
    private static final String DB_COLUMN_ITEM_COUNT = "count";
    private static final String DB_COLUMN_ADDRESS = "user_address";
    private static final String DB_COLUMN_PHONE = "user_phone";

    private static final String CREATE_EMPTY_ORDER_SQL = "{call bundesdb.create_empty_order(?,?)}";
    private static final String ADD_ITEM_TO_ORDER_SQL = "INSERT INTO bundesdb.order_positions(order_id, count, item_id, cost) " +
            "SELECT ? as order_id, ? as count, ? as item_id, (SELECT i.price * count as cost FROM bundesdb.items i WHERE i.id = item_id)";
    private static final String DELETE_ITEM_FROM_ORDER_SQL = "DELETE FROM bundesdb.order_positions where order_id = ? and item_id = ?";
    private static final String GET_ORDER_BY_ID_SQL = "SELECT o.*, p.item_id, p.count FROM bundesdb.orders o INNER JOIN bundesdb.order-positions p ON p.order_id = o.id WHERE o.id = ?";
    private static final String GET_ORDER_ID_BY_USER_SQL = "SELECT id as order_id FROM bundesdb.orders WHERE user_id = ?";
    private static final String CONFIRM_ORDER_SQL = "{call bundesdb.confirm_order(?,?)}";


    @Override
    public int createEmptyOrder(int userId) throws DAOException {

        CallableStatement cs = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            cs = connection.prepareCall(CREATE_EMPTY_ORDER_SQL);

            cs.setInt(1,userId);

            cs.registerOutParameter(DB_COLUMN_ORDER_ID, Types.INTEGER);

            cs.execute();

            return cs.getInt(DB_COLUMN_ORDER_ID);


        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding new Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding new Order", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void addItem(int orderId, int itemId, int count) throws DAOException {

        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(ADD_ITEM_TO_ORDER_SQL);
            ps.setInt(1, orderId);
            ps.setInt(2, count);
            ps.setInt(3, itemId);
            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while adding Item to Cart", e);
        } catch (SQLException e) {
            throw new DAOException("Error while adding Item to Cart", e);
        } finally {
            connectionPool.returnConnection(connection);
        }

    }

    @Override
    public void deleteItem(int orderId, int itemId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(DELETE_ITEM_FROM_ORDER_SQL);
            ps.setInt(1, orderId);
            ps.setInt(2, itemId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while remove Item from Cart", e);
        } catch (SQLException e) {
            throw new DAOException("Error while while remove Item from Cart", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public Order getOrder(int orderId) throws DAOException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(GET_ORDER_BY_ID_SQL);
            ps.setInt(1, orderId);

            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            }

            Map<Item, Integer> orderItems = new HashMap<>();
            String orderState = null;
            Date orderDate = null;
            String orderComment = null;
            String deliveryAddress = null;
            String phone = null;

            while (rs.next()) {
                Item item = DAOFactory.getInstance().getItemDAO().getItem(rs.getInt(DB_COLUMN_ITEM_ID));
                int itemCount = rs.getInt(DB_COLUMN_ITEM_COUNT);

                if (orderItems.containsKey(item)) {
                    orderItems.put(item, orderItems.get(item) + itemCount);
                }
                else {
                    orderItems.put(item, itemCount);
                }
            }

            rs.previous();

            if (rs.next()) {
                orderState = rs.getString(DB_COLUMN_STATE);
                orderDate = rs.getDate(DB_COLUMN_DATE);
                orderComment = rs.getString(DB_COLUMN_COMMENT);
                phone = rs.getString(DB_COLUMN_PHONE);
                deliveryAddress = rs.getString(DB_COLUMN_ADDRESS);
            }

            return new Order(orderId,deliveryAddress, phone, orderItems,orderDate, orderState, orderComment);

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while search Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while search Order", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public ArrayList<Integer> getUserOrders(int userId) throws DAOException {

        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;

        ArrayList<Integer> list = new ArrayList<>();

        try {
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(GET_ORDER_ID_BY_USER_SQL);
            ps.setInt(1, userId);

            rs = ps.executeQuery();

            if (rs == null) {
                return list;
            }

            while (rs.next()) {
                list.add(rs.getInt(DB_COLUMN_ORDER_ID));
            }
            return list;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while get OrderId", e);
        } catch (SQLException e) {
            throw new DAOException("Error while get OrderId", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }

    @Override
    public void confirmOrder(Order order) throws DAOException {
        CallableStatement cs = null;
        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            cs = connection.prepareCall(CONFIRM_ORDER_SQL);

            cs.setString(1,order.getComment());
            cs.setInt(2,order.getId());

            cs.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Error in Connection pool while confirm Order", e);
        } catch (SQLException e) {
            throw new DAOException("Error while confirm Order", e);
        } finally {
            connectionPool.returnConnection(connection);
        }
    }
}
