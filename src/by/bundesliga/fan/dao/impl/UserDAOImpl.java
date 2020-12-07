package by.bundesliga.fan.dao.impl;

import by.bundesliga.fan.dao.UserDAO;
import by.bundesliga.fan.dao.exception.DAOException;

public class UserDAOImpl implements UserDAO {
    @Override
    public void registration(String login, String firstName, String lastName, String email, String phoneNum, String address, String favTeam, int roleId) throws DAOException {

    }

    @Override
    public void signIn(String login, byte[] password) throws DAOException {

    }
}
