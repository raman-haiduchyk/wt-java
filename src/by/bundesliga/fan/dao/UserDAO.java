package by.bundesliga.fan.dao;

import by.bundesliga.fan.dao.exception.DAOException;

public interface UserDAO {
    void registration(String login, String firstName, String lastName, String email, String phoneNum, String address, String favTeam, int roleId) throws DAOException;
    void signIn (String login, byte[] password) throws DAOException;
}
