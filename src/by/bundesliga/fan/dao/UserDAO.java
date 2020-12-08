package by.bundesliga.fan.dao;

import by.bundesliga.fan.bean.User;
import by.bundesliga.fan.dao.exception.DAOException;

public interface UserDAO {
    void signUp(String login, byte[] password, String firstName, String lastName, String email, String phoneNum, String address, String favTeam, int roleId) throws DAOException;
    User signIn (String login, byte[] password) throws DAOException;
}
