package by.bundesliga.fan.dao.impl;

import by.bundesliga.fan.bean.User;
import by.bundesliga.fan.dao.UserDAO;
import by.bundesliga.fan.dao.exception.*;
import by.bundesliga.fan.dao.impl.connection.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String DB_COLUMN_FAVTEAM = "team";
    private static final String DB_COLUMN_PHONE = "phone";
    private static final String DB_COLUMN_ADDRESS = "address";
    private static final String DB_COLUMN_EMAIL = "email";
    private static final String DB_COLUMN_ROLE = "roleName";
    private static final String DB_COLUMN_ID = "id";
    private static final String DB_COLUMN_FIRSTNAME = "firstname";
    private static final String DB_COLUMN_LASTNAME = "lastname";
    private static final String DB_COLUMN_LOGIN = "login";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_USER_SQL = "INSERT bundesdb.users(login, pass_hash, firstname, lastame, address, email, phone, team, role) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SIGN_IN_SQL = "SELECT u.*, r.name as roleName FROM bundesdb.users u INNER JOIN bundesdb.roles r ON u.role = r.id where u.login = ? and u.pass_hash = ?";

    public UserDAOImpl() {}

    private static String getSHAHash(byte[] password) throws NoSuchAlgorithmException {
        String generatedPasswordHash = null;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password);

        StringBuilder sb = new StringBuilder();
        for (byte aByte : hash) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        generatedPasswordHash = sb.toString();
        return generatedPasswordHash;
    }

    @Override
    public User signIn(String login, byte[] password) throws DAOException {
        Connection connection = null;
        try {
            connection = connectionPool.takeConnection();
            PreparedStatement ps = connection.prepareStatement(SIGN_IN_SQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, login);
            ps.setString(2, getSHAHash(password));

            ResultSet rs = ps.executeQuery();

            if(rs == null)
                return null;

            rs.last();
            if(rs.getRow() == 1) {
                User user = null; //= new User(rs.getInt(DB_COLUMN_ID), rs.getString(DB_COLUMN_LOGIN), rs.getString(DB_COLUMN_NAME), rs.getString(DB_COLUMN_LASTNAME), rs.getString(DB_COLUMN_EMAIL), rs.getString(DB_COLUMN_ROLE));
                ps.close();
                rs.close();
                return user;
            }
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while authorizing user", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while authorizing user", e);
        }
        catch (NoSuchAlgorithmException e) {
            throw new DAOException("Password hashing error", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
        return null;
    }

    @Override
    public void signUp(String login, byte[] password, String firstName, String lastName, String email, String phoneNum, String address, String favTeam, int roleId) throws DAOException {

        Connection connection = null;

        try {
            connection = connectionPool.takeConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
            ps.setString(1, login);
            ps.setString(2, getSHAHash(password));
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, address);
            ps.setString(6, email);
            ps.setString(7, phoneNum);
            ps.setString(8, favTeam);

            ps.executeUpdate();
            ps.close();
        }
        catch (ConnectionPoolException e) {
            throw new DAOException("Error in connection pool while registering a new user", e);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            throw new DAOUserAlreadyExistsException("Login already exists", e);
        }
        catch (SQLException e) {
            throw new DAOException("Error while registering a new user", e);
        }
        catch (NoSuchAlgorithmException e) {
            throw new DAOException("Password hashing error", e);
        }
        finally {
            connectionPool.returnConnection(connection);
        }
    }
}
