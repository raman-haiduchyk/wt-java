package by.bundesliga.fan.service.impl;

import by.bundesliga.fan.bean.User;
import by.bundesliga.fan.dao.UserDAO;
import by.bundesliga.fan.dao.exception.*;
import by.bundesliga.fan.dao.factory.DAOFactory;
import by.bundesliga.fan.service.UserService;
import by.bundesliga.fan.service.exception.*;

public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String login, byte[] password) throws ServiceException {

        if(login.equals("") || password.equals(""))
            return null;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            return userDAO.signIn(login, password);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while signing in", e);
        }
    }

    @Override
    public boolean signUp(String login, byte[] password, String firstname, String lastname, String address, String email, String phone,  String favTeam, int roleId) throws ServiceException {

        if(login.isEmpty() || password.equals("") || firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || address.isEmpty() || phone.isEmpty() || favTeam.isEmpty())
            return false;

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();

        try {
            userDAO.signUp(login, password, firstname, lastname, email, phone, address, favTeam, roleId);
        }
        catch (DAOUserAlreadyExistsException e) {
            throw new ServiceUserAlreadyExistsException("User with such login already exists", e);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while singing up", e);
        }
        return true;
    }
}

