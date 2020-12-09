package by.bundesliga.fan.service;

import by.bundesliga.fan.bean.User;
import by.bundesliga.fan.service.exception.ServiceException;

public interface UserService {
    User signIn(String login, byte[] password) throws ServiceException;
    boolean signUp(String login, byte[] password, String firstname, String lastname, String address, String email, String phone,  String favTeam, int roleId) throws ServiceException;
}
